// Cliente HTTP centralizado hacia el backend de Spring Boot.
// Cuando despliegues, solo cambia esta constante — nada más en el proyecto debería tener la URL hardcodeada.
const BASE_URL = "http://localhost:8080/api";

// Helper genérico: hace el fetch, valida la respuesta y regresa JSON,
// o lanza un Error con el mensaje que mande el backend.
async function peticion(endpoint, opciones = {}) {
  const respuesta = await fetch(`${BASE_URL}${endpoint}`, {
    headers: { "Content-Type": "application/json" },
    ...opciones,
  });

  // El backend a veces regresa texto plano en errores (ej. 401 del login)
  // y a veces JSON (validaciones @Valid). Intentamos parsear con cuidado.
  const contentType = respuesta.headers.get("content-type") || "";
  const cuerpo = contentType.includes("application/json")
    ? await respuesta.json()
    : await respuesta.text();

  if (!respuesta.ok) {
    // Si el backend mandó un objeto de validación (@Valid), armamos un mensaje legible
    const mensaje =
      typeof cuerpo === "string"
        ? cuerpo
        : cuerpo?.message || Object.values(cuerpo?.errors || {}).join(" ") || "Ocurrió un error inesperado.";
    throw new Error(mensaje);
  }

  return cuerpo;
}

// POST /api/users/register
export async function registrarUsuario({ nombre, apellido, correo, telefono, password }) {
  return peticion("/users/register", {
    method: "POST",
    body: JSON.stringify({
      name: nombre,
      lastName: apellido,
      email: correo,
      phone: telefono,
      password,
    }),
  });
}

// POST /api/auth/login
export async function iniciarSesion({ correo, password }) {
  const data = await peticion("/auth/login", {
    method: "POST",
    body: JSON.stringify({ email: correo, password }),
  });

  // Guardamos el token para las siguientes peticiones autenticadas
  sessionStorage.setItem("token", data.token);
  sessionStorage.setItem("usuario_email", data.user.email);
  sessionStorage.setItem("usuario_id", data.user.id);

  return data;
}
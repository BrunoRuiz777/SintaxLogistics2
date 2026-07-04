package org.generation.syntaxlogistics2.service;


import org.generation.syntaxlogistics2.repository.QuoteRepository;
import org.generation.syntaxlogistics2.repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    // Conexión con la base de datos de cotizaciones
    @Autowired
    private QuoteRepository quoteRepository;

    // Conexion con la tabla categoria de servicio
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    // Servicio de usuario (para saber de quien es la cotizacion)
    @Autowired
    private UserService userService;

    //Crear una cotizacion


}

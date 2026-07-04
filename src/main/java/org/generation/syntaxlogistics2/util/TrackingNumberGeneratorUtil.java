package org.generation.syntaxlogistics2.util;

import java.security.SecureRandom;

public class TrackingNumberGeneratorUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    // Alfabeto seguro: sin vocales y sin números confusos (O, 0, I, 1)
    private static final String CHARACTERS = "BCDFGHJKLMNPQRSTVWXYZ23456789";

    // Prefijo de la empresa con guion (4 caracteres)
    private static final String PREFIX = "STX-";

    // Longitud de la parte aleatoria (11 caracteres) -> Total: 15 caracteres
    private static final int RANDOM_LENGTH = 11;

    public static String generateTrackingNumber() {
        StringBuilder trackingNumber = new StringBuilder(PREFIX);

        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            trackingNumber.append(CHARACTERS.charAt(randomIndex));
        }

        // Resultado de ejemplo: STX-9F4K2W8BN3M (Exactamente 15 caracteres)
        return trackingNumber.toString();
    }
}

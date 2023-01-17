package reto3;

/*
 * Reto #3: EL GENERADOR DE CONTRASEÑAS
 * Dificultad: Media | Publicación: 16/01/23 | Corrección: 23/01/23
 *
 * Enunciado
 * Escribe un programa que sea capaz de generar contraseñas de forma aleatoria.
 * Podrás configurar generar contraseñas con los siguientes parámetros:
 * - Longitud: Entre 8 y 16.
 * - Con o sin letras mayúsculas.
 * - Con o sin números.
 * - Con o sin símbolos.
 * (Pudiendo combinar todos estos parámetros entre ellos)
 * https://elcodigoascii.com.ar/ Para mirar los códigos ASCI de las letras
 */


import java.util.List;
import static reto3.jitos_dev.Options.*;
import static reto3.jitos_dev.Symbol.*;

public class jitos_dev {

    public static void main(String[] args) {
        System.out.println(generatePassword(8, 16, CAN_UPPERCASE, CAN_NUMBERS, CAN_SYMBOLS));
        System.out.println(generatePassword(8, 16, CAN_UPPERCASE, CAN_NUMBERS));
        System.out.println(generatePassword(8, 16, CAN_NUMBERS, CAN_SYMBOLS));
        System.out.println((char) Integer.parseInt(generatePassword(8, 16, CAN_UPPERCASE, CAN_SYMBOLS)));
    }

    public static String generatePassword(int minLength, int maxLength, Options... options) {

        int valor = INTERROGACION.ASCI;

        return String.valueOf(valor);
    }

    /**
     * Código ASCI de los símbolos que vamos a utilizar:
     * - ! (33)
     * - # (35)
     * - $ (36)
     * - % (37)
     * - & (38)
     * - * (42)
     * - + (43)
     * - ? (63)
     * @return array de códigos ASCI
     */
    private int[] getSymbols() {
        return new int[]{33, 35, 36, 37, 38, 42, 43, 63};
    }

    public enum Symbol {
        EXCLAMACION("!", 33), ALMOHADILLA("#", 35), DOLLAR("$", 36), PORCENTAJE("%", 37), AMPERSAND("&", 38),
        ASTERISCO("*", 42), SUMA("+", 43), INTERROGACION("?", 63);

        private String value;
        private int ASCI;
        Symbol(String value, int ASCI) {
            this.value = value;
            this.ASCI = ASCI;
        }
    }

    public enum Options {
        CAN_UPPERCASE, CAN_NUMBERS, CAN_SYMBOLS;
    }
}

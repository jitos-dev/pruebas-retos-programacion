package reto3.kotlin

fun main(){
    println(generatePassword())
    println(generatePassword(length = 16))
    println(generatePassword(length = 1))
    println(generatePassword(length = 22))
    println(generatePassword(length = 12, canUppercase = true))
    println(generatePassword(length = 12, canUppercase = true, canNumber = true))
    println(generatePassword(length = 12, canUppercase = true, canNumber = true, canSymbols = true))
    println(generatePassword(length = 12, canUppercase = true, canSymbols = true))
}


//Contamos que es entre 8 y 16 siempre
fun generatePassword(length:Int = 8, canUppercase: Boolean = false, canNumber: Boolean = false, canSymbols: Boolean = false): String {
    val finalLength: Int = if (length < 8) 8 else if (length > 16) 16 else length
    //Si el mínimo es menor que el máximo o el máximo o el mínimo es menor o igual a 0 devolvemos 0


    /*Lista con los códigos ASCI de las letras minúsculas. Las minúsculas empiezan en el 97 hasta el 122 incluido
    * La hacemos mutable para luego poder añadir más que si la hacemos mutable (toList()) no podemos*/
    val asciCodes = (97..122).toMutableList()

    /*Lista con los códigos ASCI de las letras mayúsculas. Las mayúsculas empiezan en el 65 hasta el 97 incluido.
    * Aqui da igual que sea inmutable (toList()) porque es solo para recoger los datos y los pasamos a la
    * otra que es la que cambia y que es inmutable*/
    if (canUppercase)
        asciCodes += (65..97).toList()

    /*Lista con los códigos ASCI de los números. Los números empiezan en el 48 hasta el 57 incluido*/
    if (canNumber)
        asciCodes += (48..57).toList()

    if (canSymbols)
        asciCodes += (33..47).toList()

    var password = ""
    //Recorremos el bucle tantas veces como la longitud de la contraseña y le asignamos un valor aleatorio cada vez
    while (password.length < finalLength) {
        //con random() obtenemos un elemento de la lista de forma aleatoria y lo convertimos en char
        password += asciCodes.random().toChar()
    }

    return password
}
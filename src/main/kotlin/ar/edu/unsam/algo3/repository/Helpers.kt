package ar.edu.unsam.algo3.repository

import java.time.LocalDate
import java.time.Period

const val MENSAJE_ERROR_NUM_NEGATIVO = "los campos numericos deben ser positivos"
const val MENSAJE_ERROR_INGRESAR_NOMBRE = "debe ingresar un nombre"
const val MENSAJE_ERROR_INGRESAR_APELLIDO = "debe ingresar un apellido"
const val MENSAJE_ERROR_NOMBRE_CONFEDERACION = "Nombre de Confederación Inexistente"
object validadorStrings{
    fun errorStringVacio(string: String, errorMessage:String){
        if (string.isEmpty()){
            throw IllegalArgumentException(errorMessage)
        }
    }
}
object validadorNumeros{
    fun errorNumeroNegativo(numero: Number,errorMessage: String){
        if(!HelperNumerosEnteros.esPositivo(numero.toDouble())){
            throw IllegalArgumentException(errorMessage)
        }
    }
}

object HelperNumerosEnteros {
    fun esPar(numero: Int) = numero % 2 == 0
    fun esPositivo(numero: Number) = numero.toDouble() >= 0.0
}
object calculadoraEdad {
    fun calcularEdad(fechaNacimiento: LocalDate):Int = Period.between(fechaNacimiento, LocalDate.now()).years
}
class BussinesExpetion(override val message: String):Exception(message){}
object Comparar {
    fun total(buscado: String, listaComparar: List<String>, caseSense:Boolean = true) = listaComparar.any{ it.equals(buscado, ignoreCase = caseSense)}
    fun parcial(busquedoParcial: String, listaComparar: List<String>, caseSense:Boolean = true) = listaComparar.any { it.contains(busquedoParcial, ignoreCase = caseSense) }
}

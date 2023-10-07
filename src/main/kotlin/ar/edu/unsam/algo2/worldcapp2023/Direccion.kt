package ar.edu.unsam.algo2.worldcapp2023

import org.uqbar.geodds.Point

const val MENSAJE_ERROR_INGRESAR_PROVINCIA = "debe ingresar una provincia"
const val MENSAJE_ERROR_INGRESAR_LOCALIDAD = "debe ingresar una localidad"
const val MENSAJE_ERROR_INGRESAR_CALLE = "debe ingresar una calle"
const val MENSAJE_ERROR_FIGURITA_EXISTENTE = "La figurita que intenta agregar a faltantes ya existe en figuritas"
class Direccion(var provincia:String, var localidad:String, var calle:String, var altura:Int, var ubiGeografica: Point){
    init {
        validadorStrings.errorStringVacio(provincia, errorMessage = MENSAJE_ERROR_INGRESAR_PROVINCIA)
        validadorStrings.errorStringVacio(localidad, errorMessage = MENSAJE_ERROR_INGRESAR_LOCALIDAD)
        validadorStrings.errorStringVacio(calle, errorMessage = MENSAJE_ERROR_INGRESAR_CALLE)
        validarAltura()
    }
    fun distanciaConPoint(point: Point):Double = ubiGeografica.distance(point)
    //--------------------------------------------------- validación ---------------------------------------------------
    fun validarAltura(){
        if (!HelperNumerosEnteros.esPositivo(altura)){
            throw IllegalArgumentException(MENSAJE_ERROR_NUM_NEGATIVO)
        }
    }
}
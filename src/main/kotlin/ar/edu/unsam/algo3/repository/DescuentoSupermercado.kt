package ar.edu.unsam.algo3.repository

import java.time.LocalDate
import kotlin.math.min

//--------------------- mensjaes errores Descuento supermercados ---------------------
const val MENSAJE_ERROR_DS_UMBRAL_NEGATIVO = "la cantidad de paquetes asignada como umbral no puede ser negativa"
const val MENSAJE_ERROR_DS_SET_DIAS_FUERA_DE_RANGO = "debe agregar un rango de dias que corresponda a los dias del mes"
const val MENSAJE_ERROR_DS_COMBI_ANIDADA = "no se puede agregar un descuento de combinacion de descunetos dentro de otro"
const val MENSAJE_ERROR_DS_NUM_DIA_FUERA_DE_RANGO  = "debe ser un dia semanal dentro del rango del 1 al 7"

interface DescuentoSupermercado {
    //recibe cant sobres en pos de mantener el polimorfismo
    fun multiPorcentaje(cantSobres:Int): Double
}
class SinDescuento():DescuentoSupermercado{
    override fun multiPorcentaje(cantSobres: Int): Double = 0.0

}
//un numero entre 1 y 7 (1 = lunes : 7 = domingo)
class DescDiaSemanaX(var numeroDelDiaDeSemana: Int = 4):DescuentoSupermercado{
    val porcentaje = 0.1 //10%
    init {
        errorRangoFueraDiasMes(numeroDelDiaDeSemana)
    }
    override fun multiPorcentaje(cantSobres: Int): Double = if (hayDesucento()) porcentaje else 0.0

    fun hayDesucento():Boolean = (LocalDate.now().dayOfWeek.value == numeroDelDiaDeSemana)
    //validaciones
    fun errorRangoFueraDiasMes(dia: Int){
        if (dia !in (1..7)){
            throw IllegalArgumentException(MENSAJE_ERROR_DS_NUM_DIA_FUERA_DE_RANGO)
        }
    }}
class DescXDiasMes(var rangoDiasMes: IntRange = 1..10):DescuentoSupermercado{
    val porcetaje = 0.05 //5%
    init {
        errorRangoFueraDiasMes(rangoDiasMes)
    }
    override fun multiPorcentaje(cantSobres: Int): Double = if (hayDesucento()) porcetaje else 0.0

    fun hayDesucento():Boolean = (LocalDate.now().dayOfMonth in rangoDiasMes)
    //validaciones
    fun errorRangoFueraDiasMes(rangoDias: IntRange){
        if (((1..31).intersect(rangoDias)).isEmpty()){
            throw IllegalArgumentException(MENSAJE_ERROR_DS_SET_DIAS_FUERA_DE_RANGO)
        }
    }
}
class DescuentoCantPaquetes(var umbralPaquetes: Int = 200): DescuentoSupermercado {
    val porcentaje = 0.45 //descuento del 45%
    init {
        validadorNumeros.errorNumeroNegativo(umbralPaquetes,MENSAJE_ERROR_DS_UMBRAL_NEGATIVO)
    }
    override fun multiPorcentaje(cantSobres: Int): Double = if (cantSobres > umbralPaquetes) porcentaje else 0.0

}
class DescuentosCombi(val descuentos:MutableSet<DescuentoSupermercado>):DescuentoSupermercado {
    val descuentoMax = 0.5 //50% descuento
    init {
        errorDescuentosCombiAnidado()
    }
    override fun multiPorcentaje(cantSobres: Int): Double = min(descuentoMax, descuentos.sumOf{it.multiPorcentaje(cantSobres)})
    //validaciones
    fun errorDescuentosCombiAnidado(){
        if (descuentos.any { it is DescuentosCombi }){
            throw IllegalArgumentException(MENSAJE_ERROR_DS_COMBI_ANIDADA)
        }
    }
}


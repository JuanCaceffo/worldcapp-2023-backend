package ar.edu.unsam.algo3.domain

val impresionBaja = NivelImpresion(afectaValorEn = 1.0, "baja")
val impresionMedia = NivelImpresion(afectaValorEn = 0.85,"media")
val impresionAlta = NivelImpresion(afectaValorEn = 0.85, "alta")
class NivelImpresion (val afectaValorEn:Double, val nombre: String){}

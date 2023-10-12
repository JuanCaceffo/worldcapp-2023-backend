package ar.edu.unsam.algo3.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Pedido(val cantSobres:Int,val fechaEntrega: LocalDate){
    fun diasHastaEntrega() = ChronoUnit.DAYS.between(LocalDate.now(),fechaEntrega)
}

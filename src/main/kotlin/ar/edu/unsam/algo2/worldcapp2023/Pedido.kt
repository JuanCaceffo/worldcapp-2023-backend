package ar.edu.unsam.algo2.worldcapp2023

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Pedido(val cantSobres:Int,val fechaEntrega: LocalDate){
    fun diasHastaEntrega() = ChronoUnit.DAYS.between(LocalDate.now(),fechaEntrega)
}

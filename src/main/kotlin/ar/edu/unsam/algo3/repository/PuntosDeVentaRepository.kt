package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.PuntosDeVentaDTO
import ar.edu.unsam.algo3.dto.toDTO
import org.springframework.stereotype.Repository
import org.uqbar.geodds.Point

@Repository
class PuntosDeVentaRepository: Repositorio<PuntoDeVenta>() {
    val kiosko = Kioscos("kioskito", Direccion("bs as","3 de Febrero", "alpacal", 211, Point(2,2)),2, true)
   // val supermercado = Supermercados("chinito", Direccion("bs as","3 de Febrero", "alpacal", 211, Point(2,2)),2, SinDescuento())
    //val libreria = Librerias("lapicito", Direccion("bs as","3 de Febrero", "alpacal", 211, Point(2,2)),2)
    fun addElements(){
        this.create(kiosko)
      //  this.create(supermercado)
        //this.create(libreria)
    }
    //
    fun findAll(): List<PuntosDeVentaDTO> = elementos.map { it.value.toDTO() }

    fun inactivos() = elementos.filter{(!it.value.disponibilidad() && it.value.pedidosPendientes.isEmpty()) || (!it.value.disponibilidad() && !it.value.tienePedidoConEntregaProxima())}

    fun updateStock(recibidos: MutableList<Pedido>){
        elementos.values.forEach{ it.procesarPedidos(recibidos) }
    }
}
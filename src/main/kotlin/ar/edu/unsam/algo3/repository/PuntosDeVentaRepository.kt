package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.PuntosDeVentaDTO
import ar.edu.unsam.algo3.dto.toDTO
import org.springframework.stereotype.Repository
import org.uqbar.geodds.Point

@Repository
class PuntosDeVentaRepository: Repositorio<PuntoDeVenta>() {

    fun bootstrapAddElements(elements: Array<PuntoDeVenta>) = elements.forEach { this.create(it) }

    fun findAll(): List<PuntosDeVentaDTO> = elementos.map { it.value.toDTO() }

    fun inactivos() = elementos.filter{(!it.value.disponibilidad() && it.value.pedidosPendientes.isEmpty()) || (!it.value.disponibilidad() && !it.value.tienePedidoConEntregaProxima())}

    fun updateStock(recibidos: MutableList<Pedido>){
        elementos.values.forEach{ it.procesarPedidos(recibidos) }
    }
}
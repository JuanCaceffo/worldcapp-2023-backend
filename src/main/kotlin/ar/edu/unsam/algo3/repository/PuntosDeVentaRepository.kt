package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.*
import org.springframework.stereotype.Repository

@Repository
class PuntosDeVentaRepository: Repositorio<PuntoDeVenta>() {
    fun inactivos() = elementos.filter{(!it.value.disponibilidad() && it.value.pedidosPendientes.isEmpty()) || (!it.value.disponibilidad() && !it.value.tienePedidoConEntregaProxima())}

    fun updateStock(recibidos: MutableList<Pedido>){
        elementos.forEach{ it.value.procesarPedidos(recibidos) }
    }
}
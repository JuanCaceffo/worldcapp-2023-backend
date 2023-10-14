package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.Pedido
import ar.edu.unsam.algo3.domain.PuntoDeVenta
import org.springframework.stereotype.Repository

@Repository
class PuntosDeVentaRepository: Repositorio<PuntoDeVenta>() {
    fun inactivos() = elementos.filter{(!it.disponibilidad() && it.pedidosPendientes.isEmpty()) || (!it.disponibilidad() && !it.tienePedidoConEntregaProxima())}

    fun updateStock(recibidos: MutableList<Pedido>){
        elementos.forEach{ it.procesarPedidos(recibidos) }
    }
}
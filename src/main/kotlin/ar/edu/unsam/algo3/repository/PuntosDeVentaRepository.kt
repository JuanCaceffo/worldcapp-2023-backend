package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.toMarketCardDTO
import org.springframework.stereotype.Repository

@Repository
class PuntosDeVentaRepository: Repositorio<PuntoDeVenta>() {
    fun inactivos() = elementos.filter{(!it.value.disponibilidad() && it.value.pedidosPendientes.isEmpty()) || (!it.value.disponibilidad() && !it.value.tienePedidoConEntregaProxima())}

    fun ordenarPorMenorDistancia(usuario: Usuario) = getAll().sortedBy { it.distanciaPuntoVentaUsuario(usuario) }

    fun ordenarPorMasBarato(usuario: Usuario) = getAll().sortedBy { it.importeACobrar(usuario,1) }

    fun ordenarPorMasSobres() = getAll().sortedBy { it.stockSobres }

    fun ordenarPorSoloMasCercanos(usuario: Usuario) = getAll().filter { it.distanciaPuntoVentaUsuario(usuario) <= usuario.distanciaMaximaCercania }.sortedBy { it.distanciaPuntoVentaUsuario(usuario) }

    fun updateStock(recibidos: MutableList<Pedido>){
        elementos.forEach{ it.value.procesarPedidos(recibidos) }
    }
}
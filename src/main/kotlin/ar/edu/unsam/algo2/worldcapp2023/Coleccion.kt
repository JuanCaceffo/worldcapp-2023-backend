package ar.edu.unsam.algo2.worldcapp2023

const val MENSAJE_ERROR_ID_INEXISTENTE= "El ID no corresponde con ningun elementod del repositorio"
open class Coleccion<T : Coleccionable> {
    var elementos = mutableMapOf<Int, T>()

    private var idCounter = 0

    fun create(elemento: T){
        elemento.id(idCounter)
        elementos[elemento.id] = elemento
        idCounter += 1
    }

    fun delete(elemento: T){
        elementos.remove(elemento.id)
    }

    fun massiveDelete(lista: Map<Int, T>){
        lista.forEach{this.delete(it.value)}
    }

    fun update(updatedElement: T){
        validarIDElemento(updatedElement.id)
        elementos[updatedElement.id] = updatedElement
    }

    fun getById(id: Int): T? {
        validarIDElemento(id)
        return elementos[id]
    }

    fun search(value: String) = elementos.values.filter { it.validSearchCondition(value) }
    private fun validarIDElemento(id: Int) {
        if (!elementos.keys.contains(id)){
            throw IllegalArgumentException(MENSAJE_ERROR_ID_INEXISTENTE)
        }
    }
}

class ColeccionFigus: Coleccion<Figurita>() {
    fun setOnFire(nros: MutableList<Int>){
        elementos.forEach{ if (nros.contains(it.value.numero)) {it.value.OnFire(true)}}
    }
}

class ColeccionUsuarios: Coleccion<Usuario>() {
    fun inactivos() = elementos.filter{it.value.figuritasFaltantes.isEmpty() && it.value.figuritasRepetidas().isEmpty()}
}

class ColeccionPuntosVenta: Coleccion<PuntoDeVenta>() {
    fun inactivos() = elementos.filter{(!it.value.disponibilidad() && it.value.pedidosPendientes.isEmpty()) || (!it.value.disponibilidad() && !it.value.tienePedidoConEntregaProxima())}

    fun updateStock(recibidos: MutableList<Pedido>){
        elementos.values.forEach{ it.procesarPedidos(recibidos) }
    }
}

class ColeccionSelecciones:Coleccion<Seleccion>()



package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.RepositorioProps
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.ceil
import kotlin.math.max

//--------------------- mensjaes errores punto venta ---------------------
const val MENSAJE_ERROR_PV_FECHA_PEDIDO_ANTERIOR_FECHA_ACUTAL = "no puede crear un pedido con un fecha estimada de entrega anteriro a la fecha actual"
const val MENSAJE_ERROR_PV_BASE_ENVIO_NEGATIVA = "la base de envio debe ser un numero positivo"
const val MENSAJE_ERROR_PV_MAX_KM_NEGATIVA = "el umbral de maximo kilometros de envio debe ser positivo"
const val MENSAJE_ERROR_PV_STOCK_SOBRES_NEGATIVOS = "el stock de sobres debe ser positivo"
const val MENSAJE_ERROR_PV_NOMBRE_VACIO = "el punto de venta debe tener un nombre"

abstract class PuntoDeVenta(
    var nombre: String,
    val direccion : Direccion,
    var stockSobres: Int
): Cloneable, RepositorioProps(){
    val pedidosPendientes: MutableSet<Pedido> = mutableSetOf()
    private var maxKmEnvio: Double = 10.0
    private var baseDeEnvio = 1000.0
    companion object {
        const val costoMinimoSobre: Double = 170.0
    }

    init {
        validadorNumeros.errorNumeroNegativo(stockSobres, MENSAJE_ERROR_PV_STOCK_SOBRES_NEGATIVOS)
        validadorStrings.errorStringVacio(nombre,MENSAJE_ERROR_PV_NOMBRE_VACIO)
    }
    fun copy(): PuntoDeVenta = this.clone() as PuntoDeVenta
    fun cambiarBaseDeEnvio(nuevaBase:Double) {
        validadorNumeros.errorNumeroNegativo(nuevaBase, MENSAJE_ERROR_PV_BASE_ENVIO_NEGATIVA)
        baseDeEnvio=nuevaBase
    }

    fun importeACobrar(usuario: Usuario,cantSobres: Int): Double = (costoMinimoSobre * cantSobres + baseDeEnvio + extraPorKm(usuario)) * modificadorCosto(cantSobres)


    abstract fun modificadorCosto(cantSobres: Int): Double //pasamos cantSobres a modificadorCosto en pos de mantener el polimorfismo

    fun setMaxKmEnvio(cantKm: Double) {
        validadorNumeros.errorNumeroNegativo(cantKm, MENSAJE_ERROR_PV_MAX_KM_NEGATIVA)
        maxKmEnvio = cantKm
    }

    private fun extraPorKm(usuario: Usuario): Double {
        val multiExecso = 100.0
        val distanciaPuntoVentaVsUsuario = direccion.distanciaConPoint(usuario.direccion.ubiGeografica)

        return (max(0.0,(ceil(distanciaPuntoVentaVsUsuario - maxKmEnvio))) * multiExecso)
    }

    fun disponibilidad(): Boolean = stockSobres > 0

    fun addPedidosPendientes(pedido: Pedido){
        errorFechaEntregaAnteriorFechaActual(pedido)
        pedidosPendientes.add(pedido)
    }

    fun procesarPedidos(recibidos: MutableList<Pedido>){
        pedidosPendientes.removeAll(recibidos.toSet())
    }

    fun tienePedidoConEntregaProxima() = pedidosPendientes.any { (it.diasHastaEntrega() <= 90) }

    override fun validSearchCondition(value: String) =  Comparar.total(value, listOf(nombre))

    //------------------------------------------------- validaciones ------------------------------------------
    fun errorFechaEntregaAnteriorFechaActual(pedido: Pedido){
        if (pedido.fechaEntrega.isBefore(LocalDate.now())){
            throw IllegalArgumentException(MENSAJE_ERROR_PV_FECHA_PEDIDO_ANTERIOR_FECHA_ACUTAL)
        }
    }
}

class Kioscos(nombre: String, direccion: Direccion, stockSobres: Int, var hayEmpleados: Boolean) : PuntoDeVenta(nombre, direccion, stockSobres){

    var porcentajeEmpelados = 1.25 //25% extra
    var porcentajeDuenios = 1.1 //%10 extra
    override fun modificadorCosto(cantSobres: Int): Double = if (hayEmpleados) porcentajeEmpelados else porcentajeDuenios

}

class Librerias(nombre: String, direccion: Direccion, stockSobres: Int) : PuntoDeVenta(nombre, direccion, stockSobres){
    var adicionalSuperaUmbral = 1.1 //10% extra
    var adicionalEstandar = 1.05 //5% extra
    var umbralDias = 10
    override fun modificadorCosto(cantSobres: Int): Double = if (pedidosFabricaSuperanUmbral()) adicionalSuperaUmbral else adicionalEstandar
    private fun pedidosFabricaSuperanUmbral():Boolean = pedidosPendientes.any{ChronoUnit.DAYS.between(LocalDate.now(),it.fechaEntrega) >= umbralDias}
}
class Supermercados(nombre: String, direccion: Direccion, stockSobres: Int,var descuento: DescuentoSupermercado) : PuntoDeVenta(nombre, direccion, stockSobres){
    fun cambiarDescuento(nuevoDescuento:DescuentoSupermercado) {
        descuento = nuevoDescuento
    }
    override fun modificadorCosto(cantSobres: Int): Double = (1 - descuento.multiPorcentaje(cantSobres))
}

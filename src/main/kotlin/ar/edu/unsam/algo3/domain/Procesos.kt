package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import com.google.gson.Gson

abstract class Proceso() {
    open lateinit var mailSender: MailSender
    val processName = this.javaClass.simpleName

    private fun enviarMail(){
        mailSender.sendMail(Mail(to = "admin@worldcapp.com.ar", from="procesos@worldcapp.com.ar" ,subject = "Se realizó el proceso: $processName", content = "Se realizó el proceso: $processName"))
    }
    open fun execute(){
        enviarMail()
    }
}

class BorrarUserInactivo(private val toProcess: UsuariosRepository, override var mailSender: MailSender): Proceso() {
    override fun execute(){
        toProcess.massiveDelete(toProcess.inactivos())
        super.execute()
    }
}

class UpdateSelecciones(private val toProcess: SeleccionesRepository, val service: String, override var mailSender: MailSender): Proceso() {
    fun obtainSelecciones() = Gson().fromJson(service, Array<SeleccionDataExterna>::class.java).toList()
    override fun execute(){
        obtainSelecciones().forEach{ toProcess.update(Seleccion.crear(it)) }
        super.execute()
    }
}

class BorrarPuntosVentaInactivo(private val toProcess: PuntosDeVentaRepository, override var mailSender: MailSender): Proceso() {
    override fun execute(){
        toProcess.massiveDelete(toProcess.inactivos())
        super.execute()
    }
}

class CambiarAOnFire(private val toProcess: FiguritasRepository, val nros: MutableList<Int>, override var mailSender: MailSender): Proceso() {
    override fun execute() {
        toProcess.setOnFire(nros)
        super.execute()
    }
}

class UpdateStockPuntosVenta(private val toProcess: PuntosDeVentaRepository, val recibidos: MutableList<Pedido>, override var mailSender: MailSender): Proceso() {
    override fun execute(){
        toProcess.updateStock(recibidos)
        super.execute()
    }
}
package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.FiguritasRepositorio
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepositorio
import ar.edu.unsam.algo3.repository.SeleccionesRepositorio
import ar.edu.unsam.algo3.repository.RepositorioUsuarios
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

class BorrarUserInactivo(private val toProcess: RepositorioUsuarios, override var mailSender: MailSender): Proceso() {
    override fun execute(){
        toProcess.massiveDelete(toProcess.inactivos())
        super.execute()
    }
}

class UpdateSelecciones(private val toProcess: SeleccionesRepositorio, val service: String, override var mailSender: MailSender): Proceso() {
    fun obtainSelecciones() = Gson().fromJson(service, Array<SeleccionDataExterna>::class.java).toList()
    override fun execute(){
        obtainSelecciones().forEach{ toProcess.update(Seleccion.crear(it)) }
        super.execute()
    }
}

class BorrarPuntosVentaInactivo(private val toProcess: PuntosDeVentaRepositorio, override var mailSender: MailSender): Proceso() {
    override fun execute(){
        toProcess.massiveDelete(toProcess.inactivos())
        super.execute()
    }
}

class CambiarAOnFire(private val toProcess: FiguritasRepositorio, val nros: MutableList<Int>, override var mailSender: MailSender): Proceso() {
    override fun execute() {
        toProcess.setOnFire(nros)
        super.execute()
    }
}

class UpdateStockPuntosVenta(private val toProcess: PuntosDeVentaRepositorio, val recibidos: MutableList<Pedido>, override var mailSender: MailSender): Proceso() {
    override fun execute(){
        toProcess.updateStock(recibidos)
        super.execute()
    }
}
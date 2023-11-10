package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository

class BusinessException(message: String) : RuntimeException(message)

class ProcessBuilder(var mailSender: MailSender) {
    val proc = mutableListOf<Proceso>()

    fun borrarUserInactivo(toProcess: UsuariosRepository): ProcessBuilder {
        proc.add(BorrarUserInactivo(toProcess, mailSender))
        return this
    }

    fun updateSelecciones(toProcess: SeleccionesRepository, service: String): ProcessBuilder {
        proc.add(UpdateSelecciones(toProcess, service, mailSender))
        return this
    }

    fun borrarPuntosVentaInactivo(toProcess: PuntosDeVentaRepository): ProcessBuilder {
        proc.add(BorrarPuntosVentaInactivo(toProcess, mailSender))
        return this
    }

    fun cambiarAOnFire(toProcess: FiguritasRepository, nros: MutableList<Int>): ProcessBuilder {
        proc.add(CambiarAOnFire(toProcess, nros, mailSender))
        return this
    }

    fun updateStockPuntosVenta(toProcess: PuntosDeVentaRepository, recibidos: MutableList<Pedido>): ProcessBuilder {
        proc.add(UpdateStockPuntosVenta(toProcess,recibidos, mailSender))
        return this
    }

    fun build(): MutableList<Proceso> {
        if (proc.isEmpty()) {
            throw BusinessException("El programa no puede estar vacío")
        }
        return proc
    }
}

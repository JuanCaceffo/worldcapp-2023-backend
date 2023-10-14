package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.FiguritasRepositorio
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepositorio
import ar.edu.unsam.algo3.repository.SeleccionesRepositorio
import ar.edu.unsam.algo3.repository.RepositorioUsuarios

class BusinessException(message: String) : RuntimeException(message)

class ProcessBuilder(var mailSender: MailSender) {
    val proc = mutableListOf<Proceso>()

    fun borrarUserInactivo(toProcess: RepositorioUsuarios): ProcessBuilder {
        proc.add(BorrarUserInactivo(toProcess, mailSender))
        return this
    }

    fun updateSelecciones(toProcess: SeleccionesRepositorio, service: String): ProcessBuilder {
        proc.add(UpdateSelecciones(toProcess, service, mailSender))
        return this
    }

    fun borrarPuntosVentaInactivo(toProcess: PuntosDeVentaRepositorio): ProcessBuilder {
        proc.add(BorrarPuntosVentaInactivo(toProcess, mailSender))
        return this
    }

    fun cambiarAOnFire(toProcess: FiguritasRepositorio, nros: MutableList<Int>): ProcessBuilder {
        proc.add(CambiarAOnFire(toProcess, nros, mailSender))
        return this
    }

    fun updateStockPuntosVenta(toProcess: PuntosDeVentaRepositorio, recibidos: MutableList<Pedido>): ProcessBuilder {
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

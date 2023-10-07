package ar.edu.unsam.algo2.worldcapp2023

class BusinessException(message: String) : RuntimeException(message)

class ProcessBuilder(var mailSender: MailSender) {
    val proc = mutableListOf<Proceso>()

    fun borrarUserInactivo(toProcess: ColeccionUsuarios): ProcessBuilder {
        proc.add(BorrarUserInactivo(toProcess, mailSender))
        return this
    }

    fun updateSelecciones(toProcess: ColeccionSelecciones, service: String): ProcessBuilder {
        proc.add(UpdateSelecciones(toProcess, service, mailSender))
        return this
    }

    fun borrarPuntosVentaInactivo(toProcess: ColeccionPuntosVenta): ProcessBuilder {
        proc.add(BorrarPuntosVentaInactivo(toProcess, mailSender))
        return this
    }

    fun cambiarAOnFire(toProcess: ColeccionFigus, nros: MutableList<Int>): ProcessBuilder {
        proc.add(CambiarAOnFire(toProcess, nros, mailSender))
        return this
    }

    fun updateStockPuntosVenta(toProcess: ColeccionPuntosVenta, recibidos: MutableList<Pedido>): ProcessBuilder {
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

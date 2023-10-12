package ar.edu.unsam.algo3.repository

class BusinessException(message: String) : RuntimeException(message)

class ProcessBuilder(var mailSender: MailSender) {
    val proc = mutableListOf<Proceso>()

    fun borrarUserInactivo(toProcess: RepositorioUsuarios): ProcessBuilder {
        proc.add(BorrarUserInactivo(toProcess, mailSender))
        return this
    }

    fun updateSelecciones(toProcess: RepositorioSelecciones, service: String): ProcessBuilder {
        proc.add(UpdateSelecciones(toProcess, service, mailSender))
        return this
    }

    fun borrarPuntosVentaInactivo(toProcess: RepositorioPuntosDeVenta): ProcessBuilder {
        proc.add(BorrarPuntosVentaInactivo(toProcess, mailSender))
        return this
    }

    fun cambiarAOnFire(toProcess: RepositorioFiguritas, nros: MutableList<Int>): ProcessBuilder {
        proc.add(CambiarAOnFire(toProcess, nros, mailSender))
        return this
    }

    fun updateStockPuntosVenta(toProcess: RepositorioPuntosDeVenta, recibidos: MutableList<Pedido>): ProcessBuilder {
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

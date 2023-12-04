package ar.edu.unsam.algo3.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
object ErrorMessages {
  const val ID_INEXISTENTE = "El ID no corresponde con ningún elemento del repositorio"
}

object PuntoVentaErrorMessages {
  const val TIENE_STOCK = "El Punto de venta tiene stock de sobres o esta pronto a recibir nuevos."
  const val FALLO_EDICION = "El Punto de Venta no se pudo editar."
  const val FALLO_CREACION = "El Punto de Venta no se pudo crear."
}

object JugadorErrorMessages {
  const val POSICION_INEXISTENTE = "La posicion que a seleccionado no existe"
  const val SELECCION_INEXISTENTE= "La seleccion que a seleccionado no existe"
  const val DATA_INCOMPLETA = "Necesita ingresar mas campos"
  const val JUGADOR_UTILIZADO = "El jugador se encuentra utilizado actualmente"
}

object SeleccionErrorMessages {
  const val SELECCION_INEXISTENTE= "La seleccion que a seleccionado no existe"
  const val SELECCION_UTILIZADA = "La seleccion se encuentra asociada a un jugador actualmente"
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BussinesExpetion(msg:String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(msg: String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
class IllegalArgumentException(msg: String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
class NotImplementedError(msg: String) : RuntimeException(msg)


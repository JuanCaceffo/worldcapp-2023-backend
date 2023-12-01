package ar.edu.unsam.algo3.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.RuntimeException
object ErrorMessages {
  const val ID_INEXISTENTE = "El ID no corresponde con ningún elemento del repositorio"
  const val PUNTO_TIENE_STOCK = "El Punto de venta tiene stock de sobres o esta pronto a recibir nuevos."
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BussinesExpetion(msg:String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(msg: String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
class IllegalArgumentException(msg: String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
class NotImplementedError(msg: String) : RuntimeException(msg)


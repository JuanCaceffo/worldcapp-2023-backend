package ar.edu.unsam.algo3.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.RuntimeException


@ResponseStatus(HttpStatus.BAD_REQUEST)
class BussinesExpetion(msg:String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(msg: String) : RuntimeException(msg)

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
class NotImplementedError(msg: String) : RuntimeException(msg)
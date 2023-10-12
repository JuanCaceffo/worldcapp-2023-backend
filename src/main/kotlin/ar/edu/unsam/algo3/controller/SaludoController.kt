package ar.edu.unsam.algo3.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.annotation.processing.Generated

@RestController
class SaludoController {
    val saludador = Saludador()

    @GetMapping("/saludoDefault")
    @Operation(summary = "Devuelve un saludo por defecto")
    fun saludar() = this.saludador.buildSaludo()

    @GetMapping("/saludo/{persona}")
    @Operation(summary = "Devuelve un saludo personalizado, requiere la persona a saludar")
    fun saludarPersonalizadamente(@PathVariable persona: String) =
        this.saludador.buildSaludoCustom("Hola $persona!")

    @PutMapping(
        path = ["/saludoDefault"],
        consumes = [MediaType.TEXT_PLAIN_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    @Operation(summary = "Actualiza el valor del nuevo saludo por defecto")
    fun actualizarSaludoPersonalizado(@RequestBody nuevoSaludo: String): String {
        this.saludador.cambiarSaludoDefault(nuevoSaludo)
        return "Se actualizó el saludo correctamente"
    }
}

class Saludador {
    companion object {
        private var ultimoId = 1
        const val PERSONA_PROHIBIDA = "dodain"
    }

    private var saludoDefault = "Hola mundo!"

    fun buildSaludo() = buildSaludoCustom(this.saludoDefault)

    fun buildSaludoCustom(mensaje: String) = Saludo(ultimoId++, mensaje)

    fun cambiarSaludoDefault(nuevoSaludo: String) {
        if (nuevoSaludo == PERSONA_PROHIBIDA) {
            throw BusinessException("No se puede saludar a $PERSONA_PROHIBIDA")
        }
        this.saludoDefault = nuevoSaludo
    }
}

@Generated
data class Saludo(
    val id: Int,
    val mensaje: String,
) {
    val fechaCreacion: LocalDateTime = LocalDateTime.now()
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BusinessException(message: String) : RuntimeException(message) {}
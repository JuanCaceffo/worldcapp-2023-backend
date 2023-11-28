package ar.edu.unsam.algo3.service
import ar.edu.unsam.algo3.domain.Jugador
import ar.edu.unsam.algo3.dto.infoJugadorDTO
import ar.edu.unsam.algo3.repository.JugadorRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class JugadoresService (
    val jugadoresRepo: JugadorRepository
){
    fun crearJugador(infoJugador: infoJugadorDTO) {
        val nuevoJugador = Jugador(
            nombre = infoJugador.nombre,
            apellido = infoJugador.apellido,
            altura = infoJugador.altura,
            peso = infoJugador.peso,
            nroCamiseta = infoJugador.camiseta,
            fechaNacimiento = LocalDate.parse(infoJugador.nacimiento),
            anioDeDebut = LocalDate.parse(infoJugador.debut),
            cotizacion = infoJugador.cotizacion,
            esLider = infoJugador.esLider,
        )
        jugadoresRepo.create()
    }

}

package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.repository.FiguritasRepository
import org.springframework.beans.factory.InitializingBean

class WorldCappBoostrap (
  val figuritasRepository: FiguritasRepository,
  val jugadorRepository: JugadorRepository
  // val usuariosRepositorio: UsuarioRepositorio,
  // val puestosDeVentaRepositorio: PuestoDeVentaRepositorio,
): InitializingBean {
  fun crearFiguritas() {
    figuritasRepository.apply {
      create(1, true,

      )
    }
  }

  fun crearJugador() {
    jugadorRepositorio.apply {

    }
  }



  override fun afterPropertiesSet() {
    TODO("Not yet implemented")
  }

}
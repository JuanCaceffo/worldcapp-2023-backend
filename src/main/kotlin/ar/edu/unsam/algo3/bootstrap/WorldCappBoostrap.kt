package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.repository.FiguritasRepositorio
import org.springframework.beans.factory.InitializingBean

class WorldCappBoostrap (
  val figuritasRepositorio: FiguritasRepositorio,
  val jugadoresR
  // val usuariosRepositorio: UsuarioRepositorio,
  // val puestosDeVentaRepositorio: PuestoDeVentaRepositorio,
): InitializingBean {
  fun crearFiguritas() {
    figuritasRepositorio.apply {
      create(1, true,

      )
    }
  }

  fun crearJugador() {

  }

  override fun afterPropertiesSet() {
    TODO("Not yet implemented")
  }

}
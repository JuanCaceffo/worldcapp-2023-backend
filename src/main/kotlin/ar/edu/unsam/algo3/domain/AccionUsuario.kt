package ar.edu.unsam.algo3.domain

abstract class AccionesUsuarios {
    //TODO: Preguntar si hay alguna forma mejor
    val CODIGO_ACCION = this::class.simpleName.toString()
    abstract fun ejecutarAccion(usuario: Usuario, figurita: Figurita)
}
/*
Actor => Si al usuario solicitante,
Condición =>  le faltan 5 o menos figuritas para completar el álbum, OK
Acción => se le deben triplicar la cantidad de kilómetros máximos que considera como cercanos. OK
2da Condición => Esto debe ocurrir solo una vez: es decir que si ya se triplicó, no debería volver a triplicar (salvo que se vuelva a habilitar).
 */

class UsuarioTriplicaKM(): AccionesUsuarios(){
    private val TOPE_FIGURITAS_PARA_TRIPLICAR = 5
    private val MULTIPLICADOR = 3

    override fun ejecutarAccion(usuario: Usuario, figurita: Figurita) {
        if(leFaltanMenosDe5Figuritas(usuario)){
            usuario.distanciaMaximaCercania *= MULTIPLICADOR
            usuario.desactivarAccion(this)
        }
    }

    private fun leFaltanMenosDe5Figuritas(usuario: Usuario): Boolean = usuario.figuritasFaltantes.size <= TOPE_FIGURITAS_PARA_TRIPLICAR
}

// Si con esa figurita llenó el álbum y tiene más de un determinado número de figuritas a regalar, el usuario se convierte en Desprendido.
class ConvertirUsuarioEnDesprendido(): AccionesUsuarios() {
    private val TOPE_FIGURITAS_A_REGALAR = 2

    override fun ejecutarAccion(usuario: Usuario, figurita: Figurita) {
        if(seCumpleCondicion(usuario)){
            usuario.modificarComportamientoIntercambio(Desprendido(usuario))
        }
    }

    //Si se lleno el album, tiene mas figuritas a regalar que el tope definido y esta activada la acción, se cumple la condición de la acción
    private fun seCumpleCondicion(usuario: Usuario): Boolean =
        usuario.figuritasFaltantes.isEmpty() && usuario.listaFiguritasARegalar().size > TOPE_FIGURITAS_A_REGALAR
}
class FelicitarUsuario(var mailSender: MailSender):AccionesUsuarios(){
    val sender = "info@worldcapp.com.ar"
    override fun ejecutarAccion(usuario: Usuario, figurita: Figurita) {
        if (usuario.figuritasFaltantes.isEmpty()){
            val mail = Mail(
                from = sender,
                to = usuario.email,
                subject = "Felicitiaciones por completar el alubm ${usuario.nombreUsuario}!",
                //(Nro./ Valor / Selección / Apellido y Nombre Jugador).
                content = """
                |!${usuario.nombreUsuario} felicidades por completar el album de figuirtas WordlCApp!
                |los datos de la figuirta con la que lograste completar el album son:
                |* Numero de figurita: ${figurita.numero}
                |* Valoracion: ${figurita.valoracion()}
                |* Seleccion Del Jugador: ${figurita.jugador.seleccionPerteneciente}
                |* Appellido y nombre del jugador: ${figurita.jugador.apellido} ${figurita.jugador.nombre}.
                |!Gracias por utilizar nuestra plataforma¡
            """.trimMargin()
            )
            mailSender.sendMail(mail)
        }
    }

}
class ConvertirUsuarioEnNacionalista():  AccionesUsuarios(){
    var regSeleccionDeFigus = mutableListOf<Seleccion>()
    fun addRegSeleccionDeFigu(seleccion: Seleccion) = regSeleccionDeFigus.add(seleccion)
    private fun mismaSeleccion(): Boolean {
        val numUltimosRegs = 3
        val subListRegistro = regSeleccionDeFigus.takeLast(numUltimosRegs)
        //si los registros cumplen la condicion desada se evalua si el subconjunto de registros de figurita son de la misma seleccion
        return if (regSeleccionDeFigus.size >= numUltimosRegs) subListRegistro.all { it == subListRegistro[0] } else false
    }

    override fun ejecutarAccion(usuario: Usuario, figurita: Figurita) {
        addRegSeleccionDeFigu(figurita.jugador.seleccionPerteneciente)
        if (mismaSeleccion() && usuario.figuritasFaltantes.isNotEmpty()){
            //para que sea un usario nacionalista de la seleccion
            usuario.addSeleccionFavoritas(regSeleccionDeFigus.last())
            usuario.modificarComportamientoIntercambio(Nacionalista(usuario))
            //reseteamos el registro para que se reinicie el proceso de verificacion de selecciones
            regSeleccionDeFigus.clear()
        }
    }


}
class IncorporarFiguritaARepetidasReservadas(usuario: Usuario, vararg figuritas: Figurita):  AccionesUsuarios(){
    val figuritasReservadas: MutableList<Figurita> = mutableListOf()

    init{
        if (figuritas.isEmpty())
            throw IllegalArgumentException("Debe elegirse al menos una figurita a reservar")
        val figusNoRepetidas = figuritas.filter{figu -> !usuario.estaRepetida(figu)}
        if (figusNoRepetidas.isNotEmpty())
            throw IllegalArgumentException("Se pasaron figuritas que el usuario ${usuario.nombreUsuario} no tiene repetidas: $figusNoRepetidas")
        figuritas.forEach { figurita ->
            usuario.figuritas.remove(figurita)
            figuritasReservadas.add(figurita)
        }
    }

    override fun ejecutarAccion(usuario: Usuario, figuritaSolicitada: Figurita){
        if (usuario.figuritasRepetidas().isNotEmpty())
            return
        
        val reservadasRegalables = figuritasReservadasRegalablesPor(usuario)
        if (reservadasRegalables.isEmpty())
            return
        
        val regalablesMenosValiosas = reservadasRegalables.filter { figuritaSolicitada.valoracion() >= it.valoracion() }
        if(regalablesMenosValiosas.isEmpty())
            return
        
        val reservadaParaRegistrar = regalablesMenosValiosas.first()
        usuario.recibirFigurita(reservadaParaRegistrar)
        figuritasReservadas.remove(reservadaParaRegistrar)
        
        if (figuritasReservadas.isEmpty())
            usuario.desactivarAccion(this)
    }
    private fun figuritasReservadasRegalablesPor(usuario: Usuario) : List<Figurita> = figuritasReservadas.filter{ usuario.puedoDar(it) }
}
package ar.edu.unsam.algo3.repository

abstract class RepositorioProps {
    var id: Int = 0
    fun id(nuevoID: Int){ id = nuevoID}
    abstract fun validSearchCondition(value:String): Boolean
}



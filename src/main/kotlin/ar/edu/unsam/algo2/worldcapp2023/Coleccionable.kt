package ar.edu.unsam.algo2.worldcapp2023

abstract class Coleccionable {
    var id: Int = 0
    fun id(_id: Int){ id = _id}
    abstract fun validSearchCondition(value:String): Boolean
}



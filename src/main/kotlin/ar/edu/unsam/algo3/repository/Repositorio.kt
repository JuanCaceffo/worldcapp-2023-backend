package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.error.ErrorMessages
import ar.edu.unsam.algo3.error.NotFoundException
import org.springframework.stereotype.Repository

@Repository
class Repositorio<T : RepositorioProps> {
  var elementos = mutableMapOf<Int, T>()
  var idCounter = 0

  fun clear() {
    elementos.clear()
    idCounter = 0
  }

  fun getAll(): List<T> = elementos.values.toList()

  fun getTotalEntities(): Int = this.getAll().size

  fun create(elemento: T): T {
    elemento.id(idCounter)
    elementos[elemento.id] = elemento
    idCounter += 1
    return elemento
  }

  fun delete(elemento: T) {
    elementos.remove(elemento.id)
  }

  fun massiveDelete(lista: Map<Int, T>) {
    lista.forEach { this.delete(it.value) }
  }

  fun update(updatedElement: T) {
    elementos[updatedElement.id] = updatedElement
  }

  fun getById(id: Int): T {
    return elementos[id] ?: throw NotFoundException(ErrorMessages.ID_INEXISTENTE)
  }

  fun search(value: String):List<T>{
    return elementos.values.filter {team -> team.validSearchCondition(value)}
    return elementos.values.filter { it.validSearchCondition(value) }
  }
}




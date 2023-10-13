package ar.edu.unsam.algo3.domain

class ProcessIterator(val proc: MutableList<Proceso>): Iterator<Proceso> {
    var index: Int = 0

    override fun hasNext() = index < proc.size

    override fun next(): Proceso {
        return proc[index++]
    }
}

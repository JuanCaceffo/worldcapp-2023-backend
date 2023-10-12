package ar.edu.unsam.algo3.repository

class Admin{
    var processStarted: Boolean = false
    val procesos = mutableListOf<Proceso>()
    fun addProcess(proc: Proceso) {
        procesos.add(proc)
    }

    fun removeProcess(proc: Proceso) {
        procesos.remove(proc)
    }

    fun run() {
        if (procesos.isEmpty()) throw BusinessException("No hay procesos cargados para ejecutar")
        procesos.forEach{ it.execute() }
    }

    /*    override fun loadProcess(proc: MutableList<Proceso>) {
            if (this.processStarted) throw BusinessException("Ya hay un programa en ejecución")
            this.processIterator = ProcessIterator(proc)
        }

        override fun run() {
            this.start()
            if (!::processIterator.isInitialized) throw BusinessException("No hay procesos cargados para ejecutar")
            while (processIterator.hasNext()) {
                this.step()
            }
            this.stop()
        }

        override fun step() {
            if (!processStarted) throw BusinessException("El proceso no está iniciado")
            if (!processIterator.hasNext()) throw BusinessException("No hay más procesos para ejecutar")
            val proximoProceso = processIterator.next()
            proximoProceso.execute()
            //return proximoProceso
        }


    fun start() {
        processStarted = true
    }

    fun stop() {
        processStarted = false
    }
*/

}
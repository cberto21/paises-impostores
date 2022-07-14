package ar.edu.unahur.obj2.impostoresPaises

object Observatorio {
    var listaPaises : MutableList<Pais> = mutableListOf()

    fun buscarPaisPorNombre(nombrePais: String): Pais {
        return listaPaises.filter { it.nombre == nombrePais }.first()
    }

    fun sonLimitrofes(nombrePais1: String,nombrePais2: String): Boolean {
        val pais1 = buscarPaisPorNombre(nombrePais1.capitalize())
        val pais2 = buscarPaisPorNombre(nombrePais2.capitalize())
        return pais1.sonLimitrofes(pais2)
    }

    fun necesitanTraduccion(nombrePais1: String,nombrePais2: String): Boolean {
        val pais1 = buscarPaisPorNombre(nombrePais1.capitalize())
        val pais2 = buscarPaisPorNombre(nombrePais2.capitalize())
        return pais1.necesitanTraduccion(pais2)
    }

    fun sonPotencialesAliados(nombrePais1: String, nombrePais2: String): Boolean{
        val pais1 = buscarPaisPorNombre(nombrePais1.capitalize())
        val pais2 = buscarPaisPorNombre(nombrePais2.capitalize())
        return pais1.sonPotencialesAliados(pais2)
    }

    fun convieneIrDeCompras(paisOrigen: String, paisDestino: String): Boolean{
        val origen = buscarPaisPorNombre(paisOrigen.capitalize())
        val destino = buscarPaisPorNombre(paisDestino.capitalize())
        return origen.convieneIrDeCompras(destino)
    }

    fun aCuantoEquivale(unMonto: Double, paisOrigen: String, paisDestino: String): Double {
        val origen = buscarPaisPorNombre(paisOrigen.capitalize())
        val destino = buscarPaisPorNombre(paisDestino.capitalize())
        return origen.cuantoEquivale(unMonto, destino)
    }

}
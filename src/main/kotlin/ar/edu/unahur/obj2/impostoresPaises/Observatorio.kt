package ar.edu.unahur.obj2.impostoresPaises

object Observatorio {
    var listaPaises : MutableList<Pais> = mutableListOf()

    fun buscarPaisPorNombre(nombrePais: String): Pais {
        return listaPaises.filter { it.nombre == nombrePais.capitalize() }.first()
    }

    fun sonLimitrofes(nombrePais1: String,nombrePais2: String): Boolean {
        val pais1 = buscarPaisPorNombre(nombrePais1)
        val pais2 = buscarPaisPorNombre(nombrePais2)
        return pais1.sonLimitrofes(pais2)
    }

    fun necesitanTraduccion(nombrePais1: String,nombrePais2: String): Boolean {
        val pais1 = buscarPaisPorNombre(nombrePais1)
        val pais2 = buscarPaisPorNombre(nombrePais2)
        return pais1.necesitanTraduccion(pais2)
    }

    fun sonPotencialesAliados(nombrePais1: String, nombrePais2: String): Boolean{
        val pais1 = buscarPaisPorNombre(nombrePais1)
        val pais2 = buscarPaisPorNombre(nombrePais2)
        return pais1.sonPotencialesAliados(pais2)
    }

    fun convieneIrDeCompras(paisOrigen: String, paisDestino: String): Boolean{
        val origen = buscarPaisPorNombre(paisOrigen)
        val destino = buscarPaisPorNombre(paisDestino)
        return origen.convieneIrDeCompras(destino)
    }

    fun aCuantoEquivale(unMonto: Double, paisOrigen: String, paisDestino: String): Double {
        val origen = buscarPaisPorNombre(paisOrigen)
        val destino = buscarPaisPorNombre(paisDestino)
        return origen.cuantoEquivale(unMonto, destino)
    }

}
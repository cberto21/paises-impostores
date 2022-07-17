package ar.edu.unahur.obj2.impostoresPaises

object Observatorio {
    var listaPaises : MutableList<Pais> = mutableListOf()
    var continente: MutableMap<String, MutableList<Pais>> = mutableMapOf()

    private fun buscarPaisPorNombre(nombrePais: String): Pais {
        return listaPaises.filter { it.nombre == nombrePais.capitalize() }.first()
    }
    fun agregarContinente(nombre: String,paises: MutableList<Pais>){
        if(continente.containsKey(nombre)){
            continente[nombre]!!.addAll(paises)
        }else{
            continente[nombre] = paises
        }
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

    fun cincoISOconMayorDensidad(): List<String>{
        val listaAux: MutableList<Pais> = mutableListOf()
        listaAux.addAll(listaPaises)
        listaAux.sortByDescending{ it.densidadPoblacional() }
        return listaAux.subList(0,5).map{it.codigoIso3}
    }

    private fun cantidadPaisesPlurinacionales(continente: List<Pais>): Int {
        return continente.count { it.esPlurinacional() }
    }
    fun continenteConMasPaisesPlurinacionles(): String {
        return continente.maxByOrNull { cantidadPaisesPlurinacionales( it.value ) }!!.key
    }
    fun paisesIsla(): List<Pais> {
        return listaPaises.filter{ it.esUnaIsla() }
    }
    fun promedioDensidadPoblacionalPaisesIsla(): Double {
        return (this.paisesIsla().sumByDouble { it.densidadPoblacional() }) / this.paisesIsla().size
    }

}
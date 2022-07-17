package ar.edu.unahur.obj2.impostoresPaises

interface Pais{
    //Interfaz en comun para instanciar Paises
    var nombre: String
    var codigoIso3: String
    var poblacion: Long
    var superficie: Double
    var continente: String
    var codigoMoneda: String
    var cotizacionDolar: Double
    var bloquesRegionales: List<String>
    var idiomasOficiales: List<String>
    var paisesLimitrofes : MutableList<Pais>
    fun agregarListLimitrofe(paises: List<Pais>)
    fun sacarPais(unPais: Pais)
    fun esPlurinacional(): Boolean
    fun esUnaIsla(): Boolean
    fun densidadPoblacional(): Double
    fun vecinoMasPoblado(): Pais
    fun sonLimitrofes(pais:Pais): Boolean
    fun necesitanTraduccion(pais:Pais): Boolean
    fun compartenBloqueRegional(pais: Pais): Boolean
    fun sonPotencialesAliados(pais: Pais): Boolean
    fun convieneIrDeCompras(unPais: Pais): Boolean
    fun cuantoEquivale(unMonto: Double, unPais: Pais): Double
}

class PaisComposite : Pais {
    // PaisComposite: puede albergar conjunto de Paises
    override var nombre: String = ""
    override var codigoIso3: String = ""
    override var poblacion: Long = 0
    override var superficie: Double = 0.0
    override var continente: String = ""
    override var codigoMoneda: String = ""
    override var cotizacionDolar: Double = 0.0
    override var bloquesRegionales: List<String> = mutableListOf()
    override var idiomasOficiales: List<String> = mutableListOf()
    override var paisesLimitrofes :MutableList<Pais> = mutableListOf()

    override fun agregarListLimitrofe(paises: List<Pais>){
        paisesLimitrofes.addAll(paises)
    }
    override fun sacarPais(unPais: Pais){
        paisesLimitrofes.remove(unPais)
    }

    override fun esPlurinacional() = idiomasOficiales.size >= 2

    override fun esUnaIsla() = paisesLimitrofes.isEmpty()

    override fun densidadPoblacional(): Double {
        if (superficie != 0.0) return poblacion/superficie else return 0.0
    }

    override fun vecinoMasPoblado() : Pais {
            return paisesLimitrofes.plus(this).maxByOrNull { it.poblacion }!!
    }

    override fun sonLimitrofes(pais:Pais) = this.paisesLimitrofes.any { it.codigoIso3 == pais.codigoIso3 }

    override fun necesitanTraduccion(pais:Pais) = idiomasOficiales.intersect(pais.idiomasOficiales.toSet()).isEmpty()

    override fun compartenBloqueRegional(pais: Pais) = bloquesRegionales.intersect(pais.bloquesRegionales.toSet()).isNotEmpty()

    override fun sonPotencialesAliados(pais: Pais) = !this.necesitanTraduccion(pais) && compartenBloqueRegional(pais)

    override fun convieneIrDeCompras(unPais: Pais): Boolean {
        return cotizacionDolar < unPais.cotizacionDolar
    }

    override fun cuantoEquivale(unMonto: Double, unPais: Pais): Double {
        return unMonto / cotizacionDolar * unPais.cotizacionDolar
    }
}

class PaisInsular: Pais{

    override var nombre: String = ""
    override var codigoIso3: String = ""
    override var poblacion: Long = 0
    override var superficie: Double = 0.0
    override var continente: String = ""
    override var codigoMoneda: String = ""
    override var cotizacionDolar: Double = 0.0
    override var bloquesRegionales: List<String> = mutableListOf()
    override var idiomasOficiales: List<String> = mutableListOf()
    override var paisesLimitrofes: MutableList<Pais>
        get() = mutableListOf()
        set(value) {}
    override fun agregarListLimitrofe(paises: List<Pais>){}
    override fun sacarPais(unPais: Pais){}
    override fun esPlurinacional() = idiomasOficiales.size >= 2

    override fun esUnaIsla() = true

    override fun densidadPoblacional(): Double {
        if (superficie != 0.0) return poblacion/superficie else return 0.0
    }

    override fun vecinoMasPoblado() : Pais = this

    override fun sonLimitrofes(pais:Pais) = false

    override fun necesitanTraduccion(pais:Pais) = idiomasOficiales.intersect(pais.idiomasOficiales.toSet()).isEmpty()

    override fun compartenBloqueRegional(pais: Pais) = bloquesRegionales.intersect(pais.bloquesRegionales.toSet()).isNotEmpty()

    override fun sonPotencialesAliados(pais: Pais) = !this.necesitanTraduccion(pais) && compartenBloqueRegional(pais)

    override fun convieneIrDeCompras(unPais: Pais): Boolean {
        return cotizacionDolar < unPais.cotizacionDolar
    }

    override fun cuantoEquivale(unMonto: Double, unPais: Pais): Double {
        return unMonto / cotizacionDolar * unPais.cotizacionDolar
    }
}
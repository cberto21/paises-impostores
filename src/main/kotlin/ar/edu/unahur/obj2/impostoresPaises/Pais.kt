package ar.edu.unahur.obj2.impostoresPaises

interface Pais{
    var nombre: String
    val codigoIso3: String
    val poblacion: Long
    val superficie: Double
    val continente: String
    val codigoMoneda: String
    val cotizacionDolar: Double
    val bloquesRegionales: List<String>
    val idiomasOficiales: List<String>
    var paisesLimitrofes : MutableList<Pais>
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
class PaisComposite(
    override var nombre: String,
    override val codigoIso3: String,
    override val poblacion: Long,
    override val superficie: Double,
    override val continente: String,
    override val codigoMoneda: String,
    override val cotizacionDolar: Double,
    override val bloquesRegionales: List<String>,
    override val idiomasOficiales: List<String> ) : Pais {

    override var paisesLimitrofes :MutableList<Pais> = mutableListOf()

    fun agregarListLimitrofe(paises: List<PaisComposite>){
        paisesLimitrofes.addAll(paises)
    }
    fun sacarPais(unPais: Pais){
        paisesLimitrofes.remove(unPais)
    }

    override fun esPlurinacional() = idiomasOficiales.size > 2

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

class PaisInsular(
    override var nombre: String,
    override val codigoIso3: String,
    override val poblacion: Long,
    override val superficie: Double,
    override val continente: String,
    override val codigoMoneda: String,
    override val cotizacionDolar: Double,
    override val bloquesRegionales: List<String>,
    override val idiomasOficiales: List<String> ) : Pais {

    override var paisesLimitrofes: MutableList<Pais>
        get() = mutableListOf()
        set(value) {}

    override fun esPlurinacional() = idiomasOficiales.size > 2

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
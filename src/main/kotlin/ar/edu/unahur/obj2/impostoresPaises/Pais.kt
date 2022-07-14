package ar.edu.unahur.obj2.impostoresPaises


class Pais (
    var nombre: String,
    val codigoIso3: String,
    val poblacion: Long,
    val superficie: Double,
    val continente: String,
    val codigoMoneda: String,
    val cotizacionDolar: Double,
    val bloquesRegionales: List<String>,
    val idiomasOficiales: List<String> ) {

    var paisesLimitrofes :MutableList<Pais> = mutableListOf()

    fun esPlurinacional() = idiomasOficiales.size > 2

    fun esUnaIsla() = paisesLimitrofes.isEmpty()

    fun densidadPoblacional(): Double {
        if (superficie != 0.0) return poblacion/superficie else return 0.0
    }

    fun vecinoMasPoblado() : Pais {
        if (paisesLimitrofes.isEmpty())
            return this
        else
            /*todo Patrones*/
            return paisesLimitrofes.plus(this).maxByOrNull { it.poblacion }!!
    }

    fun sonLimitrofes(pais:Pais) = this.paisesLimitrofes.any { it.codigoIso3 == pais.codigoIso3 }

    fun necesitanTraduccion(pais:Pais) = idiomasOficiales.intersect(pais.idiomasOficiales.toSet()).isEmpty()

    fun compartenBloqueRegional(pais: Pais) = bloquesRegionales.intersect(pais.bloquesRegionales.toSet()).isNotEmpty()

    fun sonPotencialesAliados(pais: Pais) = !this.necesitanTraduccion(pais) && compartenBloqueRegional(pais)

    fun convieneIrDeCompras(unPais: Pais): Boolean {
        return cotizacionDolar < unPais.cotizacionDolar
    }

    fun cuantoEquivale(unMonto: Double, unPais: Pais): Double {
        return unMonto / cotizacionDolar * unPais.cotizacionDolar
    }
}
package ar.edu.unahur.obj2.impostoresPaises

class Director {
    lateinit var builder : builderPais
    fun setDirector(builder: builderPais){
        this.builder = builder
    }
    fun crearNorteAmericano(nombre: String,iso : String, poblacion: Long, superficie: Double, moneda: String,dolar : Double, regional: List<String>, idioma: List<String> = listOf("Ingles")): Pais {
        builder.setNombre(nombre)
        builder.setCodigoISO3(iso)
        builder.setPoblacion(poblacion)
        builder.setSuperficie(superficie)
        builder.setContinente("América")
        builder.setCodigoMoneda(moneda)
        builder.setCotizacionDolar(dolar)
        builder.setBloqueRegional(regional)
        builder.setIdiomasOficiales(idioma)
        return builder.getresult()
    }
    fun crearLatinoAmericano(nombre: String,iso : String, poblacion: Long, superficie: Double, moneda: String,dolar : Double, regional: List<String>, idioma: List<String> = listOf("Español")): Pais {
        builder.setNombre(nombre)
        builder.setCodigoISO3(iso)
        builder.setPoblacion(poblacion)
        builder.setSuperficie(superficie)
        builder.setContinente("América")
        builder.setCodigoMoneda(moneda)
        builder.setCotizacionDolar(dolar)
        builder.setBloqueRegional(regional)
        builder.setIdiomasOficiales(idioma)
        return builder.getresult()
    }
    fun crearAsiatico(nombre: String,iso : String, poblacion: Long, superficie: Double, moneda: String,dolar : Double, regional: List<String>, idioma: List<String>): Pais {
        builder.setNombre(nombre)
        builder.setCodigoISO3(iso)
        builder.setPoblacion(poblacion)
        builder.setSuperficie(superficie)
        builder.setContinente("Asia")
        builder.setCodigoMoneda(moneda)
        builder.setCotizacionDolar(dolar)
        builder.setBloqueRegional(regional)
        builder.setIdiomasOficiales(idioma)
        return builder.getresult()
    }
}
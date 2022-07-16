package ar.edu.unahur.obj2.impostoresPaises

interface builderPais{
    fun reset()
    fun setNombre(nombre: String)
    fun setCodigoISO3(iso: String)
    fun setPoblacion(poblacion: Long)
    fun setSuperficie(superficie: Double)
    fun setContinente(continente: String)
    fun setCodigoMoneda(codigoMon: String)
    fun setCotizacionDolar(dolar: Double)
    fun setBloqueRegional(bloqueReg: List<String>)
    fun setIdiomasOficiales(idiomas: List<String>)
    fun getresult(): Pais
}

class concreteBuilderPaisComp: builderPais{
    private lateinit var pais: PaisComposite
    init{
        this.reset()
    }
    override fun reset(){
        pais = PaisComposite()
    }

    override fun setNombre(nombre: String) {
        pais.nombre = nombre
    }

    override fun setCodigoISO3(iso: String) {
        pais.codigoIso3 = iso
    }

    override fun setPoblacion(poblacion: Long) {
        pais.poblacion = poblacion
    }

    override fun setSuperficie(superficie: Double) {
        pais.superficie = superficie
    }

    override fun setContinente(continente: String) {
        pais.continente = continente
    }

    override fun setCodigoMoneda(codigoMon: String) {
        pais.codigoMoneda = codigoMon
    }

    override fun setCotizacionDolar(dolar: Double) {
        pais.cotizacionDolar = dolar
    }

    override fun setBloqueRegional(bloqueReg: List<String>) {
        pais.bloquesRegionales = bloqueReg
    }

    override fun setIdiomasOficiales(idiomas: List<String>) {
        pais.idiomasOficiales = idiomas
    }

    override fun getresult(): PaisComposite {
        val nuevo = pais
        reset()
        return nuevo
    }
}

class concreteBuilderPaisInsu: builderPais{
    private lateinit var pais: PaisInsular
    init{
        this.reset()
    }
    override fun reset(){
        pais = PaisInsular()
    }

    override fun setNombre(nombre: String) {
        pais.nombre = nombre
    }

    override fun setCodigoISO3(iso: String) {
        pais.codigoIso3 = iso
    }

    override fun setPoblacion(poblacion: Long) {
        pais.poblacion = poblacion
    }

    override fun setSuperficie(superficie: Double) {
        pais.superficie = superficie
    }

    override fun setContinente(continente: String) {
        pais.continente = continente
    }

    override fun setCodigoMoneda(codigoMon: String) {
        pais.codigoMoneda = codigoMon
    }
    override fun setCotizacionDolar(dolar: Double) {
        pais.cotizacionDolar = dolar
    }
    override fun setBloqueRegional(bloqueReg: List<String>) {
        pais.bloquesRegionales = bloqueReg
    }

    override fun setIdiomasOficiales(idiomas: List<String>) {
        pais.idiomasOficiales = idiomas
    }

    override fun getresult(): PaisInsular {
        val nuevo = pais
        reset()
        return nuevo
    }
}
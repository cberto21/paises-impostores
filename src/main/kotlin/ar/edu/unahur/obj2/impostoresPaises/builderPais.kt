package ar.edu.unahur.obj2.impostoresPaises

interface builderPais{
    fun reset()
    fun setNombre(): String
    fun setCodigoISO3(): String
    fun setPoblacion(): Long
    fun setSuperficie(): Double
    fun setContinente(): String
    fun setCodigoMoneda(): String
    fun setBloqueRegional(): List<String>
    fun setIdiomasOficiales(): List<String>
    fun getresult()
}

class PaisMaker{

}
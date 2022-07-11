
interface BuilderPizza {
    fun reset()
    fun cantTomate(num: Int)
    fun tieneQueso()
    fun cantAceituna(num: Int)
    fun getResult(): Any
}

class PizzaBuilder() : BuilderPizza{
    lateinit var pizza: Pizza
    init {
        reset()
    }
    override fun reset() { pizza = Pizza() }

    override fun cantTomate(num: Int){ pizza.tomate = num }
    override fun tieneQueso() { pizza.queso = true }
    override fun cantAceituna(num: Int) { pizza.aceituna = num }

    override fun getResult(): Pizza {
        val construida : Pizza = pizza
        reset()
        return construida
    }
}
class PizzaManualBuilder() : BuilderPizza{
    lateinit var manual: ManualPizza
    init {
        reset()
    }
    override fun reset() { manual = ManualPizza() }

    override fun cantTomate(num: Int){ manual.tomate = num }
    override fun tieneQueso() { manual.queso = true }
    override fun cantAceituna(num: Int) { manual.aceituna = num }

    override fun getResult(): ManualPizza {
        val construida : ManualPizza = manual
        reset()
        return construida
    }
}
class Pizza{
    var queso : Boolean = false
    var tomate : Int = 0
    var aceituna : Int =0
}
class ManualPizza{
    var queso : Boolean = false
    var tomate : Int = 0
    var aceituna : Int =0

}

class Cocina{
    lateinit var builder : BuilderPizza
    fun setPizza(PizzaAConstruir: BuilderPizza){
        builder = PizzaAConstruir
    }
    fun pizzaConDeTodo(){
        builder.cantTomate(4)
        builder.tieneQueso()
        builder.cantTomate(6)
    }
    fun pizzaRata(){
        builder.tieneQueso()
        builder.cantTomate(1)
    }
}
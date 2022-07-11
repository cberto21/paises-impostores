import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PizzaTest: DescribeSpec( {
    var cocina = Cocina()
    it("ConstruirPizza con Todo"){
        var pizzaAConstruir = PizzaBuilder()
        cocina.setPizza(pizzaAConstruir)
        cocina.pizzaConDeTodo()
        var pizza = pizzaAConstruir.getResult()
        pizza.toString().shouldBe("Pizza(queso= con queso, tomate=4, aceituna=6)")
    }
    it("ConstruirManualPizza con Todo"){
        var manualAConstruir = PizzaManualBuilder()
        cocina.setPizza(manualAConstruir)
        cocina.pizzaConDeTodo()
        var manual = manualAConstruir.getResult()
        manual.toString().shouldBe("ManualPizza(queso= con queso, tomate=4, aceituna=6)")
    }
    it("Construir Pizza Rata"){
        var pizzaAConstruir = PizzaBuilder()
        cocina.setPizza(pizzaAConstruir)
        cocina.pizzaRata()
        var pizza = pizzaAConstruir.getResult()
        pizza.toString().shouldBe("Pizza(queso= sin queso, tomate=1, aceituna=0)")
    }
    it("Construir Manual Pizza Rata"){
        var manualAConstruir = PizzaManualBuilder()
        cocina.setPizza(manualAConstruir)
        cocina.pizzaRata()
        var manual = manualAConstruir.getResult()
        manual.toString().shouldBe("ManualPizza(queso= sin queso, tomate=1, aceituna=0)")
    }
})
package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PaisTest: DescribeSpec({

    // Listas usadas por los países
    val bloqueRegionalMercoSur = listOf("Mercosur")
    val idiomasDeRegionAmerica = listOf("español", "inglés", "portugués")
    val idiomasEnAsia = listOf("japonés")
    val bloqueRegionAsia = listOf("RCEP")

    // Países
    val director = Director()
    director.setDirector(concreteBuilderPaisComp())
    val chile = director.crearLatinoAmericano("Chile", "CHI", 18952038, 756950.0, "CLP",1012.70, bloqueRegionalMercoSur)
    val uruguay = director.crearLatinoAmericano("Uruguay", "URU", 3461734, 176215.0,  "UYU",40.31, bloqueRegionalMercoSur)
    val argentina = director.crearLatinoAmericano("Argentina", "ARG", 44938712, 278000000.0,"ARS",127.84, bloqueRegionalMercoSur)
    val bolivia =director.crearLatinoAmericano("Bolivia","BOL",11510000,109900000.0,"BOB", 6.86,bloqueRegionalMercoSur)
    val brasil = director.crearLatinoAmericano("Brasil", "BRA", 105045817, 756215950.0,"BRL",5.39, bloqueRegionalMercoSur, listOf("portugues", "español"))
    director.setDirector(concreteBuilderPaisInsu())
    val japon = director.crearAsiatico("Japon", "JPN", 126264931, 377975.0, "JPY",137.77, bloqueRegionAsia, mutableListOf("japones"))

    // LIMITROFES
    chile.agregarListLimitrofe(listOf(argentina,brasil,bolivia))
    uruguay.agregarListLimitrofe(listOf(argentina,brasil))
    argentina.agregarListLimitrofe(listOf(bolivia,uruguay,chile,brasil))
    bolivia.agregarListLimitrofe(listOf(argentina,chile))
    brasil.agregarListLimitrofe(listOf(argentina,bolivia,uruguay))


    describe("Testeo de Pais"){
        describe("Requerimiento1: Es Plurinacional"){
            it("brasil tiene mas 1 idiomas "){
                brasil.esPlurinacional().shouldBeTrue()
            }
            it("Japon tiene un solo idioma"){
                japon.esPlurinacional().shouldBeFalse()
            }
        }
        describe("Requerimiento 2: esUnaIsla") {
            it("Argentina no es una isla") {
                argentina.esUnaIsla().shouldBeFalse()
            }
            it("Japón es una isla") {
                japon.esUnaIsla().shouldBeTrue()
            }
        }
        describe("Requerimiento 3: densidadPoblacional") {
            it("Argentina tiene densidad poblacional de 0.16 aprox") {
                argentina.densidadPoblacional().shouldBe(0.16 plusOrMinus 0.01)
            }
            it("Uruguay NOO tiene la misma densidad poblacional de argentina"){
                japon.densidadPoblacional().shouldNotBe(0.16 plusOrMinus 0.01)
            }
        }
        describe("Requerimiento 4: vecinoMasPoblado") {
            it("El vecino más poblado de Argentina entre el mismo pais, Chile , Uruguay y Brasil es Brasil") {
                argentina.vecinoMasPoblado().shouldBe(brasil)
            }
            it("El vecino más poblado para Japón es Japón") {
                japon.vecinoMasPoblado().shouldBe(japon)
            }
            it("el vecino mas poblado para Bolivia, entre el mismo, Chile y Argentina es Argentina "){
                bolivia.vecinoMasPoblado().shouldBe(argentina)
            }
        }
        describe("Requerimiento 5: sonLimitrofes") {
            it("Argentina es limítrofe de Uruguay") {
                argentina.sonLimitrofes(uruguay).shouldBeTrue()
            }
            it("Argentina no es limítrofe de Japón") {
                argentina.sonLimitrofes(japon).shouldBeFalse()
            }
        }
        describe("Requerimiento 6: necesitanTraduccion") {
            it("Argentina necesita traducción con Japón") {
                argentina.necesitanTraduccion(japon).shouldBeTrue()
            }
            it("Argentina no necesita traducción con Chile") {
                argentina.necesitanTraduccion(chile).shouldBeFalse()
            }
        }
        describe("Requerimiento 7: sonPotencialesAliados") {
            it("Argentina es potencialmente aliado de Uruguay") {
                argentina.sonPotencialesAliados(uruguay).shouldBeTrue()
            }
            it("Argentina no es potencial aliada de Japón") {
                argentina.sonPotencialesAliados(japon).shouldBeFalse()
            }
        }
        describe("Requerimiento 8: combiene ir de compras"){
            it("De Argentina a Bolivia, no combiene"){
                argentina.convieneIrDeCompras(bolivia).shouldBeFalse()
            }
            it("De Bolivia a Argentina, si combiene"){
                bolivia.convieneIrDeCompras(argentina).shouldBeTrue()
            }
        }
        describe("Requerimiento 9: a cuanto equivale"){
            it("tengo 1000 pesos argentinos, me dan 53.66 Bolivianos"){
                argentina.cuantoEquivale(1000.0, bolivia).shouldBe(53.66 plusOrMinus 0.01)
            }
            it("tengo 20000 chilenos, y me dan 2720.84 Yenes "){
                chile.cuantoEquivale(20000.0,japon).shouldBe(2720.84 plusOrMinus 0.01)
            }
        }

    }

})


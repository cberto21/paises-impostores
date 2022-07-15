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
    val chile = PaisComposite("Chile", "CHI", 18952038, 756950.0, "América",  "CLP",1012.70, bloqueRegionalMercoSur, idiomasDeRegionAmerica)
    val uruguay = PaisComposite("Uruguay", "URU", 3461734, 176215.0, "América", "UYU",40.31, bloqueRegionalMercoSur, idiomasDeRegionAmerica )
    val argentina = PaisComposite("Argentina", "ARG", 44938712, 278000000.0,"América","ARS",127.84,bloqueRegionalMercoSur,idiomasDeRegionAmerica)
    val japon = PaisInsular("Japon", "JPN", 126264931, 377975.0, "asia","JPY",137.77, bloqueRegionAsia, idiomasEnAsia)
    val bolivia = PaisComposite("Bolivia","BOL",11510000,1099000000.0,"America","BOB", 6.86,bloqueRegionalMercoSur,idiomasDeRegionAmerica )

    // LIMITROFES
    chile.agregarListLimitrofe(listOf(argentina,bolivia))
    uruguay.agregarListLimitrofe(listOf(argentina))
    argentina.agregarListLimitrofe(listOf(bolivia,uruguay))
    bolivia.agregarListLimitrofe(listOf(argentina,chile))


    describe("Testeo de Pais"){
        describe("Requerimiento1: Es Plurinacional"){
            it("Argentina tiene 2 limitrofes es Pluri"){
                argentina.esPlurinacional().shouldBeTrue()
            }
            it("Japon es isla por lo tanto no es pluri"){
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
            it("El vecino más poblado de Argentina entre el mismo pais, Chile y Uruguay es Argentina") {
                argentina.vecinoMasPoblado().shouldBe(argentina)
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


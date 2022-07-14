package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class ObservatorioTest: DescribeSpec ({
    //
    // Listas usadas por los países
    val bloqueRegionalMercoSur = listOf("Mercosur")
    val idiomasDeRegionAmerica = listOf("español", "inglés", "portugués")
    val idiomasEnAsia = listOf("japonés")
    val bloqueRegionAsia = listOf("RCEP")

    // Países
    val chile = Pais("Chile", "CHI", 18952038, 756950.0, "América",  "CLP",1012.70, bloqueRegionalMercoSur, idiomasDeRegionAmerica)
    val uruguay = Pais("Uruguay", "URU", 3461734, 176215.0, "América", "UYU",40.31, bloqueRegionalMercoSur, idiomasDeRegionAmerica )
    val japon = Pais("Japon", "JPN", 126264931, 377975.0, "asia","JPY",137.77, bloqueRegionAsia, idiomasEnAsia)
    val argentina = Pais("Argentina", "ARG", 44938712, 278000000.0,"América","ARS",127.84, bloqueRegionalMercoSur,idiomasDeRegionAmerica)
    val bolivia = Pais("Bolivia","BOL",11510000,109900000.0,"America","BOB", 6.86,bloqueRegionalMercoSur,idiomasDeRegionAmerica )
    val brasil = Pais("Brasil", "BRA", 105045817, 756215950.0, "América",  "BRL",5.39, bloqueRegionalMercoSur, listOf("portugues"))

    // LIMITROFES
    chile.paisesLimitrofes.addAll(listOf(argentina,brasil,bolivia))
    uruguay.paisesLimitrofes.addAll(listOf(argentina,brasil))
    argentina.paisesLimitrofes.addAll(listOf(bolivia,uruguay,chile,brasil))
    bolivia.paisesLimitrofes.addAll(listOf(argentina,chile,brasil))
    brasil.paisesLimitrofes.addAll(listOf(argentina,bolivia,uruguay))

    Observatorio.listaPaises = mutableListOf(chile,uruguay,argentina,japon,bolivia, brasil)

    describe("Requerimiento 1: son Limitrofes"){
        it("chile es limitrofe de argentina"){
            Observatorio.sonLimitrofes("Argentina", "chile").shouldBeTrue()
        }
        it("japon no es limitrofe de Argentina"){
            Observatorio.sonLimitrofes("japon", "argentina").shouldBeFalse()
        }
    }
    describe("Requerimiento 2: necesitan Traduccion"){
        it("chile y argentina no necesitan traduccion"){
            Observatorio.necesitanTraduccion("Argentina", "chile").shouldBeFalse()
        }
        it("japon y argentina necesitan traduccion"){
            Observatorio.necesitanTraduccion("japon", "argentina").shouldBeTrue()
        }
    }
    describe("Requerimiento 3: son Potenciales Aliados"){
        it("chile y argentina son potenciales Aliados"){
            Observatorio.sonPotencialesAliados("argentina", "chile").shouldBeTrue()
        }
        it("argentina y brasil no son potenciales aliados, por diferente idioma"){
            Observatorio.sonPotencialesAliados("argentina", "brasil").shouldBeFalse()
        }
    }
    describe("Requerimiento 4: conviene ir de Compraas"){
        it("ir de argentina a bolivia no conviene"){
            Observatorio.convieneIrDeCompras("argentina","bolivia").shouldBeFalse()
        }
        it("ir de bolivia a argentina si conviene"){
            Observatorio.convieneIrDeCompras("bolivia","argentina").shouldBeTrue()
        }
    }
    describe("Requerimiento 5: a cuanto Equivale"){
        it("1000 pesos Argentinos,a reales"){
            Observatorio.aCuantoEquivale(1000.0,"argentina","brasil").shouldBe(42.16 plusOrMinus 0.01)
        }
        it("1000 reales, a yenes"){
            Observatorio.aCuantoEquivale(1000.0,"brasil","japon").shouldBe(25560.29 plusOrMinus 0.01)
        }
    }
})
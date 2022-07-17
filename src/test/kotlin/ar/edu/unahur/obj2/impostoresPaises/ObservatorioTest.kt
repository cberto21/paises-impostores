package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ObservatorioTest: DescribeSpec ({
    //
    // Listas usadas por los países
    val bloqueRegionalMercoSur = listOf("Mercosur")
    val bloqueRegionAsia = listOf("RCEP")

    // Países
    val director = Director()
    director.setDirector(concreteBuilderPaisComp())
    val chile = director.crearLatinoAmericano("Chile", "CHI", 18952038, 756950.0, "CLP",1012.70, bloqueRegionalMercoSur)
    val uruguay = director.crearLatinoAmericano("Uruguay", "URU", 3461734, 176215.0,  "UYU",40.31, bloqueRegionalMercoSur)
    val argentina = director.crearLatinoAmericano("Argentina", "ARG", 44938712, 278000000.0,"ARS",127.84, bloqueRegionalMercoSur)
    val bolivia =director.crearLatinoAmericano("Bolivia","BOL",11510000,109900000.0,"BOB", 6.86,bloqueRegionalMercoSur)
    val brasil = director.crearLatinoAmericano("Brasil", "BRA", 105045817, 756215950.0,"BRL",5.39, bloqueRegionalMercoSur, listOf("portugues"))
    director.setDirector(concreteBuilderPaisInsu())
    val hongKong = director.crearAsiatico("Hong Kong", "HKD", 7482000, 1114000.0, "HK",137.77, bloqueRegionAsia, mutableListOf("japones"))
    val japon = director.crearAsiatico("Japon", "JPN", 126264931, 377975.0, "JPY",137.77, bloqueRegionAsia, mutableListOf("japones"))

    // LIMITROFES
    chile.agregarListLimitrofe(listOf(argentina,brasil,bolivia))
    uruguay.agregarListLimitrofe(listOf(argentina,brasil))
    argentina.agregarListLimitrofe(listOf(bolivia,uruguay,chile,brasil))
    bolivia.agregarListLimitrofe(listOf(argentina,chile,brasil))
    brasil.agregarListLimitrofe(listOf(argentina,bolivia,uruguay))

    Observatorio.listaPaises = mutableListOf(chile,uruguay,argentina,japon,bolivia, brasil,hongKong)
    Observatorio.agregarContinente("America", mutableListOf(argentina,chile,bolivia,brasil, uruguay))
    Observatorio.agregarContinente("Asia", mutableListOf(japon, hongKong))

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
    describe("Requerimiento 4: conviene ir de Compras"){
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
    describe("Requerimiento 6: cinco ISO con mayor densidad"){
        it("japon, chile, uruguay, hong kong y argentina , son los 5 con  mayor densidad"){
            Observatorio.cincoISOconMayorDensidad().shouldBe(listOf("JPN", "CHI", "URU", "HKD", "ARG"))
        }
        it("bolivia y brasil son los de menor densidad"){
            Observatorio.cincoISOconMayorDensidad().shouldNotBe(listOf("BOL, BRA"))
        }
    }
    describe("Requerimiento 7: continen con mas paises Plurinacionales"){
        it("America es el mas plurinacional"){
            Observatorio.continenteConMasPaisesPlurinacionles().shouldBe("America")
        }
        it("Japon directamente no es el mas plurinacional"){
            Observatorio.continenteConMasPaisesPlurinacionles().shouldNotBe("Asia")
        }
    }
    describe("Test densidad: de cada pais"){
        japon.densidadPoblacional().shouldBe(334.05 plusOrMinus 0.01)
        hongKong.densidadPoblacional().shouldBe(6.71 plusOrMinus 0.01)
        argentina.densidadPoblacional().shouldBe(0.16 plusOrMinus 0.01)
        bolivia.densidadPoblacional().shouldBe(0.10 plusOrMinus 0.01)
        brasil.densidadPoblacional().shouldBe(0.13 plusOrMinus 0.01)
        chile.densidadPoblacional().shouldBe(25.03 plusOrMinus 0.01)
        uruguay.densidadPoblacional().shouldBe(19.64 plusOrMinus 0.01)
    }
    describe("Requerimiento 8: promedio Densidad de paises insulares"){
        it("el promedio de densidad es: 170.38"){
            Observatorio.promedioDensidadPoblacionalPaisesIsla().shouldBe(170.38 plusOrMinus 0.01)
        }
    }
})
package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PaisTest: DescribeSpec({

    // Listas usadas por los países
    val bloqueRegionalMercoSur = listOf("Mercosur")
    val idiomasDeRegionAmerica = listOf("español", "inglés", "portugués")
    val idiomasEnAsia = listOf("japonés")
    val bloqueRegionAsia = listOf("RCEP")

    // Países
    val chile = Pais("Chile", "CHI", 18952038, 756950.0, "América", emptyList(), bloqueRegionalMercoSur, idiomasDeRegionAmerica)
    val uruguay = Pais("Uruguay", "URU", 3461734, 176215.0, "América", emptyList(), bloqueRegionalMercoSur, idiomasDeRegionAmerica )
    val limitrofesArgentina = listOf(chile, uruguay)
    val argentina = Pais("Argentina", "ARG", 44938712, 278000000.0,"América", limitrofesArgentina, bloqueRegionalMercoSur,idiomasDeRegionAmerica)
    val japon = Pais("Japon", "JPN", 126264931, 377975.0, "asia", emptyList(), bloqueRegionAsia, idiomasEnAsia)
    val bolivia = Pais("Bolivia","BOL",11510000,1099000000.0,"America",listOf(chile,argentina),bloqueRegionalMercoSur,idiomasDeRegionAmerica )


})


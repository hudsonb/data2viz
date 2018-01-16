package io.data2viz.hierarchy

import io.data2viz.test.TestBase
import kotlin.test.Test

class HierarchyTests : TestBase() {

    // DO NOT CHANGE VALUES
    val width = 500.0
    val height = 400.0

    data class Hierarchical(
        val value: Int,
        val x: Double = .0,
        val y: Double = .0,
        val subElements: List<Hierarchical>? = null
    )

    val testTreeLight = Hierarchical(
        1, .0, .0, listOf(
            Hierarchical(11, .0, .0),
            Hierarchical(12, .0, .0)
        )
    )

    val testTreeMid =
        Hierarchical(
            1, 277.777777, .0, subElements = listOf(
                Hierarchical(
                    11, 138.888888, 133.333333, subElements = listOf(
                        Hierarchical(111, 55.555555, 266.666666),
                        Hierarchical(112, 111.111111, 266.666666),
                        Hierarchical(113, 166.666666, 266.666666),
                        Hierarchical(
                            114, 222.222222, 266.666666, subElements = listOf(
                                Hierarchical(1141, 194.444444, 400.0),
                                Hierarchical(1142, 250.0, 400.0)
                            )
                        )
                    )
                ),
                Hierarchical(
                    12, 416.666666, 133.333333, subElements = listOf(
                        Hierarchical(
                            121, .0, 250.0, subElements = listOf(
                                Hierarchical(1211, 361.111111, 400.0),
                                Hierarchical(1212, 416.666666, 400.0)
                            )
                        ),
                        Hierarchical(122, 444.444444, 266.666666)
                    )
                )
            )
        )

    @Test
    fun buildHierarchy() {
        val hierarchy = hierarchy(Hierarchical(0), { it.subElements })

        hierarchy.descendants().size shouldBe 1
        hierarchy.leaves().size shouldBe 1
    }

    @Test
    fun buildHierarchyFull() {
        val hierarchy = hierarchy(testTreeMid, { it.subElements })

        hierarchy.descendants().size shouldBe 3
        hierarchy.leaves().size shouldBe 8
    }

    @Test
    fun buildCluster() {
        val hierarchy = hierarchy(testTreeMid, { it.subElements })
        val cluster = cluster(hierarchy)
    }
}
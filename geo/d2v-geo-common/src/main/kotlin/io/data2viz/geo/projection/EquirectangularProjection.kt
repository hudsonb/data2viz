package io.data2viz.geo.projection

fun equirectangularProjection() = equirectangularProjection {}
fun equirectangularProjection(init: Projection.() -> Unit) = projection(EquirectangularProjection()) {
    scale = 152.63
    init()
}

class EquirectangularProjection : ProjectableInvertable {
    override fun project(lambda: Double, phi: Double) = doubleArrayOf(lambda, phi)
    override fun invert(x: Double, y: Double) = doubleArrayOf(x, y)
}
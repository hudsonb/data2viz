package io.data2viz.interpolate

import io.data2viz.color.Color
import io.data2viz.color.HslColor
import io.data2viz.test.namespace
import io.data2viz.math.deg
import io.data2viz.test.TestBase
import kotlin.browser.document
import kotlin.test.Test

class HSLTests : TestBase() {

    /**
     * "HSL SHORT linear interpolation [(300°, 100%, 25%), (38°, 100%, 50%)]
     * see https://github.com/d3/d3-interpolate#interpolateHsl for reference
     */
    @Test
    fun hslShortLinearInterpolation() {

        val iterator = interpolateHsl(HslColor(300.deg, 1.0, .25), HslColor(38.deg, 1.0, .5))
        displaySmallGradient("HslColor", iterator, 888, imageReference = "http://data2viz.io/img/hsl.png")
        iterator(0.5).toRgba().rgbHex shouldBe Color(0xbf0023).rgbHex
    }

    /**
     * "HSL LONG linear interpolation [(300°, 100%, 25%), (38°, 100%, 50%)]"
     */
    @Test
    fun hslLongLinearInterpolation() {
        val iterator = interpolateHslLong(HslColor(300.deg, 1.0, .25), HslColor(38.deg, 1.0, .5))
        displaySmallGradient("HslColor Long", iterator, 888, imageReference = "http://data2viz.io/img/hslLong.png")
        iterator(0.5).toRgba().rgbHex shouldBe Color(0x00bf9c).rgbHex
    }

    /**
     * "HSL SHORT linear interpolation [(38°, 100%, 50%), (300°, 100%, 25%)]"
     */
    @Test
    fun hslShortLinea() {
        val iterator = interpolateHsl(HslColor(38.deg, 1.0, .5), HslColor(300.deg, 1.0, .25))
        displaySmallGradient("HslColor Reverse", iterator, 888, imageReference = "http://data2viz.io/img/hslReverse.png")
        iterator(0.5).toRgba().rgbHex shouldBe Color(0xbf0023).rgbHex
    }

    /**
     * "HSL LONG linear interpolation [(38°, 100%, 50%), (300°, 100%, 25%)]"
     */
    @Test
    fun hslLongLinearInterpol() {
        val iterator = interpolateHslLong(HslColor(38.deg, 1.0, .5), HslColor(300.deg, 1.0, .25))
        displaySmallGradient(
            "HslColor Long Reverse",
            iterator,
            888,
            imageReference = "http://data2viz.io/img/hslLongReverse.png"
        )
        iterator(0.5).toRgba().rgbHex shouldBe Color(0x00bf9c).rgbHex
    }

    fun displaySmallGradient(
            context: String,
            percentToColor: (Double) -> HslColor,
            width: Int = 256,
            imageReference: String? = null
    ) {

        if (browserEnabled) {


            h2(context)
            document.body?.appendChild(
                nodeSVG("svg").apply {
                    setAttribute("width", "$width")
                    setAttribute("height", "20")
                    setAttribute("x", "0")
                    setAttribute("y", "0")
                    (0 until width).forEach { index ->
                        appendChild(
                            nodeSVG("rect").apply {
                                setAttribute("fill", percentToColor(index / width.toDouble()).toRgba().rgbHex)
                                setAttribute("x", "$index")
                                setAttribute("y", "0")
                                setAttribute("width", "1")
                                setAttribute("height", "20")
                            }
                        )
                    }
                }
            )
            if (imageReference != null) {
                val div = document.createElement("div")
                div.appendChild(
                    document.createElement("img").apply {
                        setAttribute("src", imageReference)
                        setAttribute("height", "20")
                        setAttribute("width", "$width")
                    }
                )
                document.body?.appendChild(div)
            }
        }

    }

    fun nodeSVG(name: String) = document.createElementNS(namespace.svg, name)
}

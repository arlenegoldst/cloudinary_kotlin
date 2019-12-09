package com.cloudinary.transformation

import com.cloudinary.cldAssertEqualsAsString
import com.cloudinary.transformation.layer.LayerSource
import com.cloudinary.transformation.layer.Position
import com.cloudinary.transformation.layer.TileMode
import com.cloudinary.transformation.resize.Resize
import com.cloudinary.transformation.resize.ResizeMode
import org.junit.Test

class CutterTest {
    @Test
    fun testCutter() {
        var expected = "l_hexagon_sample/c_scale,fl_relative,h_1.0,w_1.0/fl_cutter.layer_apply"

        val layer = LayerSource.media("hexagon_sample")
        val scale = Resize.scale { setWidth(1f).setHeight(1f).setMode(ResizeMode.RELATIVE) }
        var actualFromBuilder = Cutter.Builder(layer)
            .setTransformation(
                Transformation().resize(scale)

            ).build()

        var actualFromDsl = Transformation().cutter(layer) {
            setTransformation(Transformation().resize(scale))
        }

        cldAssertEqualsAsString(expected, actualFromBuilder)
        cldAssertEqualsAsString(expected, actualFromDsl)

        val position = Position.Builder().setX(50).setTileMode(TileMode.TILED).build()
        val crop = Resize.crop { setWidth(100).setHeight(50) }
        actualFromBuilder = Cutter.Builder(layer)
            .setTransformation(Transformation().resize(crop))
            .setPosition(position)
            .build()

        actualFromDsl = Transformation().cutter(layer) {
            setTransformation(Transformation().resize(crop))
            setPosition(position)
        }

        expected = "l_hexagon_sample/c_crop,h_50,w_100/fl_cutter.layer_apply.tiled,x_50"
        cldAssertEqualsAsString(expected, actualFromBuilder)
        cldAssertEqualsAsString(expected, actualFromDsl)
    }
}

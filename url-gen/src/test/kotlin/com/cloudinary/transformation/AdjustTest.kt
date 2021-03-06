package com.cloudinary.transformation

import com.cloudinary.cldAssert
import com.cloudinary.transformation.adjust.Adjust
import com.cloudinary.transformation.adjust.ImproveModeType
import org.junit.Test

class AdjustTest {

    @Test
    fun testTint() {
        cldAssert("e_tint:50:red:blue", Adjust.tint("50:red:blue"))
        cldAssert("e_tint:50:red:blue", Adjust.tint(50, "red", "blue"))
        cldAssert("e_tint:50:red:blue", Adjust.tint(50, Color.RED, Color.BLUE))
    }

    @Test
    fun test3DLut() {
        cldAssert("l_lut:iwltbap_aspen.3dl", Adjust.by3DLut("iwltbap_aspen.3dl"))
    }

    @Test
    fun testAutoContrast() {
        cldAssert("e_auto_contrast", Adjust.autoContrast())
        cldAssert("e_auto_contrast:50", Adjust.autoContrast(50))
    }

    @Test
    fun testBrightness() {
        cldAssert("e_brightness", Adjust.brightness())
        cldAssert("e_brightness:70", Adjust.brightness(70))
    }

    @Test
    fun testAutoBrightness() {
        cldAssert("e_auto_brightness", Adjust.autoBrightness())
        cldAssert("e_auto_brightness:70", Adjust.autoBrightness(70))
    }

    @Test
    fun testBrightnessHSB() {
        cldAssert("e_brightness_hsb", Adjust.brightnessHSB())
        cldAssert("e_brightness_hsb:70", Adjust.brightnessHSB(70))
    }

    @Test
    fun contrast() {
        cldAssert("e_contrast", Adjust.contrast())
        cldAssert("e_contrast:70", Adjust.contrast(70))

    }

    @Test
    fun fillLight() {
        cldAssert("e_fill_light", Adjust.fillLight())
        cldAssert("e_fill_light:70", Adjust.fillLight {
            bias(70)
        })
        cldAssert("e_fill_light:70:20", Adjust.fillLight {
            bias(20).blend(70)
        })
    }

    @Test
    fun hue() {
        cldAssert("e_hue", Adjust.hue())
        cldAssert("e_hue:40", Adjust.hue(40))
    }

    @Test
    fun saturation() {
        cldAssert("e_saturation", Adjust.saturation())
        cldAssert("e_saturation:70", Adjust.saturation(70))
    }

    @Test
    fun vibrance() {
        cldAssert("e_vibrance", Adjust.vibrance())
        cldAssert("e_vibrance:70", Adjust.vibrance(70))
    }

    @Test
    fun red() {
        cldAssert("e_red", Adjust.red())
        cldAssert("e_red:50", Adjust.red(50))
    }

    @Test
    fun green() {
        cldAssert("e_green", Adjust.green())
        cldAssert("e_green:-30", Adjust.green(-30))
    }

    @Test
    fun testBlue() {
        cldAssert("e_blue", Adjust.blue())
        cldAssert("e_blue:90", Adjust.blue(90))
    }

    @Test
    fun gamma() {
        cldAssert("e_gamma", Adjust.gamma())
        cldAssert("e_gamma:50", Adjust.gamma(50))
    }

    @Test
    fun replaceColor() {
        cldAssert(
            "e_replace_color:saddlebrown",
            Adjust.replaceColor(Color.SADDLEBROWN)
        )
        cldAssert(
            "e_replace_color:2F4F4F:20",
            Adjust.replaceColor(Color.Rgb("2F4F4F")) {
                tolerance(20)
            }
        )
        cldAssert(
            "e_replace_color:silver:50:89b8ed",
            Adjust.replaceColor(Color.SILVER) {
                tolerance(50)
                from(Color.Rgb("#89b8ed"))
            }
        )
    }

    @Test
    fun improve() {
        cldAssert("e_improve", Adjust.improve())

        cldAssert("e_improve:50", Adjust.improve {
            blend(50)
        })

        cldAssert("e_improve:indoor", Adjust.improve {
            mode(ImproveModeType.INDOOR)
        })

        cldAssert("e_improve:outdoor:30", Adjust.improve {
            blend(30)
            mode(ImproveModeType.OUTDOOR)
        })
    }

    @Test
    fun sharpen() {
        cldAssert("e_sharpen", Adjust.sharpen())
        cldAssert("e_sharpen:400", Adjust.sharpen(400))
    }

    @Test
    fun unsharpMask() {
        cldAssert("e_sharpen", Adjust.sharpen())
        cldAssert("e_sharpen:400", Adjust.sharpen(400))
    }

    @Test
    fun testUnsharpMask() {
        cldAssert("e_unsharp_mask", Adjust.unsharpMask())
        cldAssert("e_unsharp_mask:200", Adjust.unsharpMask(200))
    }

    @Test
    fun testAutoColor() {
        cldAssert("e_auto_color", Adjust.autoColor())
        cldAssert("e_auto_color:80", Adjust.autoColor(80))
    }

    @Test
    fun opacityThreshold() {
        cldAssert("e_opacity_threshold", Adjust.opacityThreshold())
        cldAssert("e_opacity_threshold:100", Adjust.opacityThreshold(100))
    }

    @Test
    fun opacity() {
        cldAssert("o_30", Adjust.opacity(30))
    }

    @Test
    fun viesusCorrect() {
        cldAssert("e_viesus_correct", Adjust.viesusCorrect())
        cldAssert("e_viesus_correct:skin_saturation", Adjust.viesusCorrect {
            skinSaturation()
        })
        cldAssert("e_viesus_correct:skin_saturation_20", Adjust.viesusCorrect {
            skinSaturation(20)
        })
        cldAssert("e_viesus_correct:no_redeye:skin_saturation_20", Adjust.viesusCorrect {
            skinSaturation(20)
            noRedEye()
        })
        cldAssert("e_viesus_correct:no_redeye", Adjust.viesusCorrect {
            noRedEye()
        })
    }

    @Test
    fun recolor() {
        cldAssert(
            "e_recolor:0.1:0.2:0.3:0.4:0.5:0.6:0.7:0.8:0.9",
            Adjust.recolor(
                arrayOf(
                    floatArrayOf(0.1F, 0.2F, 0.3F),
                    floatArrayOf(0.4F, 0.5F, 0.6F),
                    floatArrayOf(0.7F, 0.8F, 0.9F)
                )
            )
        )
    }
}
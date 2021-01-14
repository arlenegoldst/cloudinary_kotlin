package com.cloudinary.transformation.adjust

import com.cloudinary.transformation.Action
import com.cloudinary.transformation.Color
import com.cloudinary.util.cldRanged

abstract class Adjust : Action {
    companion object {
        /**
         * Used to define the opacity of an asset.
         * @param level
         */
        fun opacity(level: Int) = Opacity(level)

        /**
         * Blends the image with one or more tint colors at the intensity specified.
         * @param values
         */
        fun tint(vararg values: Any?) = Tint(*values)

        /**
         * Applies a vibrance filter on the image.
         * @param level
         */
        fun vibrance(level: Int? = null) = Vibrance(level)

        /**
         * Adjusts the color balance and blends the result with the original image.
         * @param level
         */
        fun autoColor(level: Int? = null) = AutoColor(level)

        /**
         * Adjusts the brightness.
         * @param level
         */
        fun brightness(level: Int? = null) = Brightness(level)

        /**
         * Adjusts the brightness and blends the result with the original image.
         * @param level
         */
        fun autoBrightness(level: Int? = null) = AutoBrightness(level)

        /**
         * Adjusts image brightness modulation in HSB to prevent artifacts in some images.
         * @param level
         */
        fun brightnessHSB(level: Int? = null) = BrightnessHSB(level)

        /**
         * Adjusts the contrast and blends the result with the original image.
         * @param level
         */
        fun autoContrast(level: Int? = null) = AutoContrast(level)

        /**
         * Applies an unsharp mask filter to the image.
         * @param level
         */
        fun unsharpMask(level: Int? = null) = UnsharpMask(level)

        /**
         * Enhances an image to its best visual quality with the Viesus Automatic Image Enhancement add-on.
         * @param
         */
        fun viesusCorrect(options: (ViesusCorrect.Builder.() -> Unit)? = null): ViesusCorrect {
            val builder = ViesusCorrect.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Adjusts the image's hue.
         * @param level
         */
        fun hue(level: Int? = null) = Hue(level)

        /**
         * Adjusts the gamma level.
         * @param level
         */
        fun gamma(level: Int? = null) = Gamma(level.cldRanged(-50, 150))

        /**
         * Adjusts the contrast.
         * @param level
         */
        fun contrast(level: Int? = null) = Contrast(level)

        /**
         * Adjusts the image's blue channel.
         * @param level
         */
        fun blue(level: Int? = null) = Blue(level)

        /**
         * Adjusts the image's green channel.
         * @param level
         */
        fun green(level: Int? = null) = Green(level)

        /**
         * Adjusts the image's red channel.
         * @param level
         */
        fun red(level: Int? = null) = Red(level)

        /**
         * Causes all semi-transparent pixels in an image to be either fully transparent or fully opaque.
         * @param level
         */
        fun opacityThreshold(level: Int? = null) = OpacityThreshold(level)

        /**
         * Adjusts the color saturation.
         * @param level
         */
        fun saturation(level: Int? = null) = Saturation(level)

        /**
         * Applies a sharpening filter to the image.
         * @param strength
         */
        fun sharpen(strength: Int? = null) = Sharpen(strength)

        /**
         * Maps an input color and those similar to the input color to corresponding shades of a specified output color, taking luminosity and chroma into account, in order to recolor an object in a natural way.
         * @param
         */
        fun replaceColor(toColor: Color, options: (ReplaceColor.Builder.() -> Unit)? = null): Adjust {
            val builder = ReplaceColor.Builder(toColor)
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Converts the colors of every pixel in an image based on the supplied color matrix, in which the value of each color channel is calculated based on the values from all other channels (e.g. a 3x3 matrix for RGB, a 4x4 matrix for RGBA or CMYK, etc).
         * @param colorMatrix
         */
        fun recolor(colorMatrix: Array<FloatArray>) = Recolor(colorMatrix)

        /**
         * Adjusts the fill light and blends the result with the original image.
         * @param
         */
        fun fillLight(options: (FillLight.Builder.() -> Unit)? = null): Adjust {
            val builder = FillLight.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Adjusts the image colors, contrast and brightness.
         * @param
         */
        fun improve(options: (Improve.Builder.() -> Unit)? = null): Adjust {
            val builder = Improve.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Defines the 3D lookup table to apply to images and videos.
         * @param publicID
         */
        fun by3DLut(publicId: String) = By3DLut(publicId)
    }
}


package com.cloudinary.transformation.background

import com.cloudinary.transformation.Color

abstract class Background {

    override fun toString(): String {
        return getValues()
    }

    protected abstract fun getValues(): String

    companion object {

        /**
         * Applies blurred background (Relevant only for videos).
         * @param
         */
        fun blurred(options: (BlurredBackground.Builder.() -> Unit)? = null): Background {
            val builder = BlurredBackground.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Applies background color automatically.
         * @param
         */
        fun auto() = AutoBackground()

        /**
         * Selects the predominant color while taking only the image border pixels into account.
         * @param
         */
        // auto:border
        fun border(options: (BorderBackground.Builder.() -> Unit)? = null): Background {
            val builder = BorderBackground.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Bases the gradient fade effect on the predominant colors in the border pixels of the image.
         */
        // auto:border_gradient
        fun borderGradient(options: (BorderGradientBackground.Builder.() -> Unit)? = null): Background {
            val builder = BorderGradientBackground.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Selects the predominant color while taking all pixels in the image into account.
         * @param
         */
        // auto:predominant
        fun predominant(options: (PredominantBackground.Builder.() -> Unit)? = null): Background {
            val builder = PredominantBackground.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Bases the gradient fade effect on the predominant colors in the image.
         * @param
         */
        // auto:predominant
        fun predominantGradient(options: (PredominantGradientBackground.Builder.() -> Unit)? = null): Background {
            val builder = PredominantGradientBackground.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Sets the background color.
         * @param color
         */
        fun color(color: Color) = ColorBackground(color)

        fun color(color: String) = color(Color.parseString(color))
    }
}
package com.cloudinary.transformation

class Border(
    private val width: Any,
    private val color: Color,
    private val cornerRadius: Any? = null
) : Action {

    private val type = "solid"

    companion object {
        /**
         * Adds a solid border around an image or video.
         * @param border
         */
        fun solid(border: (Builder.() -> Unit)? = null): Border {
            val builder = Builder()
            border?.let { builder.it() }
            return builder.build()
        }
    }

    override fun toString(): String {
        return "bo_${width}px_${type}_$color".joinWithValues(cornerRadius, separator = ",")
    }

    class Builder : TransformationComponentBuilder {
        private var width: Any? = null
        private var color: Color? = null
        private var cornerRadius: Any? = null

        /**
         * Sets the width of the border.
         * @param width
         */
        fun width(width: Int) = apply { this.width = width }
        fun width(width: Any) = apply { this.width = width }

        /**
         * Sets the color of the border.
         * @param color
         */
        fun color(color: String) = apply { this.color = Color.parseString(color) }
        fun color(color: Color) = apply { this.color = color }

        // TODO: Corner Radius inside Border action is a temp suggestions:
        /**
         * Rounds all four corners of an asset by the same pixel radius.
         * @param pixels
         */
        fun cornerRadius(vararg pixels: Int) = apply { this.cornerRadius = RoundCorners.byRadius(*pixels) }
        fun cornerRadius(vararg pixels: Any) = apply { this.cornerRadius = RoundCorners.byRadius(*pixels) }

        /**
         * Delivers the asset as a rounded circle or oval shape.
         * @param
         */
        fun maxCornerRadius() = apply { this.cornerRadius = RoundCorners.max() }

        override fun build(): Border {
            val width = width
            val color = color
            require(width != null && color != null) { "Width and Color qualifiers are required" }
            return Border(width, color, cornerRadius)
        }
    }
}


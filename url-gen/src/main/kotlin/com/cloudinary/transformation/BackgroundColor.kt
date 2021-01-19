package com.cloudinary.transformation

class BackgroundColor(private val color: Color) : Action {

    companion object {

        /**
         * Sets the background color.
         */
        fun color(color: Color) = BackgroundColor(color)
        fun color(color: String) = color(Color.parseString(color))
    }

    override fun toString(): String {
        return "b_$color"
    }
}
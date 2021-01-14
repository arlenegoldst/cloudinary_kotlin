package com.cloudinary.transformation.resize

import com.cloudinary.transformation.Param
import com.cloudinary.transformation.expression.Expression
import com.cloudinary.transformation.gravity.Gravity

class Crop(
    dimensions: Dimensions,
    relative: Boolean? = null,
    regionRelative: Boolean? = null,
    val gravity: Gravity? = null,
    val zoom: Any? = null,
    val x: Any? = null,
    val y: Any? = null
) :
    Resize(dimensions, relative, regionRelative) {
    override val actionType = "crop"

    override fun params(): Collection<Param?> {
        return super.params() + listOfNotNull(
            gravity?.let { Param("g", it) },
            x?.let { Param("x", it) },
            y?.let { Param("y", it) },
            zoom?.let { Param("z", it) }
        )
    }

    class Builder : BaseBuilder<Builder>() {
        private var gravity: Gravity? = null
        private var zoom: Any? = null
        private var x: Any? = null
        private var y: Any? = null

        /**
         * Defines the focal gravity for certain methods of cropping.
         * @param
         */
        fun gravity(gravity: Gravity) = apply {
            this.gravity = gravity
        }

        /**
         * A qualifier that controls how much of the original image surrounding the face to keep when using face detection or the object when using the Cloudinary Object-Aware Cropping addon.
         * @param zoom
         */
        private fun zoom(zoom: Any) = apply {
            this.zoom = zoom
        }

        fun zoom(zoom: String) = zoom(zoom as Any)
        fun zoom(zoom: Expression) = zoom(zoom as Any)
        fun zoom(zoom: Int) = zoom(zoom as Any)
        fun zoom(zoom: Float) = zoom(zoom as Any)

        private fun x(x: Any) = apply {
            this.x = x
        }

        /**
         * A qualifier that adjusts the starting location or offset of the corresponding transformation action.
         * @param x
         */
        fun x(x: String) = x(x as Any)
        fun x(x: Expression) = x(x as Any)
        fun x(x: Int) = x(x as Any)
        fun x(x: Float) = x(x as Any)

        private fun y(y: Any) = apply {
            this.y = y
        }

        /**
         * A qualifier that adjusts the starting location or offset of the corresponding transformation action.
         * @param y
         */
        fun y(y: String) = y(y as Any)
        fun y(y: Expression) = y(y as Any)
        fun y(y: Int) = y(y as Any)
        fun y(y: Float) = y(y as Any)

        override fun getThis() = this

        override fun build() = Crop(
            Dimensions(width, height, aspectRatio),
            relative,
            regionRelative,
            gravity,
            zoom,
            x,
            y
        )
    }
}
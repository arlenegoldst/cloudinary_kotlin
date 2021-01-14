package com.cloudinary.transformation.layer.position

import com.cloudinary.transformation.Param
import com.cloudinary.transformation.TransformationDsl
import com.cloudinary.transformation.gravity.CompassGravity
import com.cloudinary.transformation.gravity.FocusOnGravity
import com.cloudinary.transformation.gravity.Gravity
import com.cloudinary.transformation.gravity.OcrGravity

class LayerPosition private constructor(
    x: Any?,
    y: Any?,
    gravity: Gravity?,
    private val tiled: Boolean?,
    private val allowOverflow: Boolean = false
) : BaseLayerPosition(x, y, gravity) {
    init {
        require(
            gravity == null ||
                    gravity is OcrGravity ||
                    gravity is FocusOnGravity ||
                    gravity is CompassGravity
        ) {
            "When provided, gravity must be one of OcrGravity, CompassGravity or FocusOnGravity"
        }
    }

    override fun asParams(): List<Param> {
        return mutableListOf<Param>().apply {
            addAll(super.asParams())
            if (tiled == true) add(Param("fl", "tiled"))
            if (!allowOverflow) add(Param("fl", "no_overflow"))
        }
    }

    @TransformationDsl
    class Builder {
        private var gravity: Gravity? = null
        private var x: Any? = null
        private var y: Any? = null
        private var tiled: Boolean? = null
        private var allowOverflow: Boolean = true

        fun build() = LayerPosition(x, y, gravity, tiled, allowOverflow)

        /**
         * Defines the gravity based on directional values from a compass.
         * @param
         */
        fun gravity(gravity: CompassGravity) = apply {
            this.gravity = gravity
        }

        /**
         * Defines the area to keep when automatically resizing an image.
         * @param
         */
        fun gravity(gravity: FocusOnGravity) = apply {
            this.gravity = gravity
        }

        /**
         * A qualifier that adjusts the starting location or offset of the corresponding transformation action.
         * @param
         */
        fun x(x: Int) = apply { this.x = x }
        fun x(x: Any) = apply { this.x = x }
        fun y(y: Int) = apply { this.y = y }
        fun y(y: Any) = apply { this.y = y }

        /**
         * Tiles the added overlay over the entire image.
         * @param
         */
        fun tiled() = apply { this.tiled = true }

        /**
         *
         */
        fun allowOverflow(allowOverflow: Boolean = true) = apply {
            this.allowOverflow = allowOverflow
        }
    }
}


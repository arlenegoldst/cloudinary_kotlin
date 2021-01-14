package com.cloudinary.transformation.layer.position

import com.cloudinary.transformation.TransformationDsl
import com.cloudinary.transformation.gravity.CompassGravity

class VideoPosition private constructor(
    x: Any?,
    y: Any?,
    gravity: CompassGravity?
) :
    BaseLayerPosition(x, y, gravity) {

    @TransformationDsl
    class Builder {
        private var gravity: CompassGravity? = null
        private var x: Any? = null
        private var y: Any? = null

        fun build() = VideoPosition(x, y, gravity)

        /**
         * Defines the gravity based on directional values from a compass.
         * @param
         */
        fun gravity(gravity: CompassGravity) = apply {
            this.gravity = gravity
        }

        /**
         * A qualifier that adjusts the starting location or offset of the corresponding transformation action.
         * *@param
         */
        fun x(x: Int) = apply { this.x = x }
        fun x(x: Any) = apply { this.x = x }
        fun y(y: Int) = apply { this.y = y }
        fun y(y: Any) = apply { this.y = y }
    }
}

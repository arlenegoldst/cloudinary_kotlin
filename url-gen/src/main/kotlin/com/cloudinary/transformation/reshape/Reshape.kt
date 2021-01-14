package com.cloudinary.transformation.reshape

import com.cloudinary.transformation.Action
import com.cloudinary.transformation.layer.source.FetchSource
import com.cloudinary.transformation.layer.source.ImageSource
import com.cloudinary.transformation.layer.source.LayerSource
import com.cloudinary.transformation.layer.source.TextSource
import java.beans.Expression

abstract class Reshape : Action {

    companion object {
        /**
         * Skews an image according to the two specified values in degrees. Negative values skew an image in the opposite direction.
         * @param skewX
         */
        fun shear(skewX: Int, skewY: Int) = shear(skewX as Any, skewY as Any)

        /**
         * * Skews an image according to the two specified values in degrees. Negative values skew an image in the opposite direction.
         * @param skewY
         */
        fun shear(skewX: Any, skewY: Any): Shear {
            val builder = Shear.Builder()
            builder.skewX(skewX)
            builder.skewY(skewY)

            return builder.build()
        }

        fun shear(options: Shear.Builder.() -> Unit): Shear {
            val builder = Shear.Builder()
            builder.options()
            return builder.build()
        }

        /**
         * Distorts an image to a new shape by either adjusting its corners or by warping it into an arc.
         * @param points
         */
        fun distort(points: List<Int>) = Distort(points)

        /**
         * Distorts an image, or text overlay, to an arc shape.
         * @param degrees
         */
        fun distortArc(degrees: String) = DistortArc(degrees)
        fun distortArc(degrees: Int) = DistortArc(degrees)
        fun distortArc(degrees: Expression) = DistortArc(degrees)
        fun distortArc(degrees: Float) = DistortArc(degrees)

        private fun cutByImage(source: LayerSource, options: (CutByImage.Builder.() -> Unit)? = null): CutByImage {
            val builder = CutByImage.Builder(source)
            options?.let { builder.it() }
            return builder.build()
        }


        /**
         *
         */
        fun cutByImage(source: ImageSource, options: (CutByImage.Builder.() -> Unit)? = null) =
            cutByImage(source as LayerSource, options)

        fun cutByImage(source: TextSource, options: (CutByImage.Builder.() -> Unit)? = null) =
            cutByImage(source as LayerSource, options)

        fun cutByImage(source: FetchSource, options: (CutByImage.Builder.() -> Unit)? = null) =
            cutByImage(source as LayerSource, options)
    }
}

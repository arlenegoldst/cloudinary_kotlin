package com.cloudinary.transformation.delivery

import com.cloudinary.transformation.Action
import com.cloudinary.transformation.FormatType
import com.cloudinary.transformation.expression.Expression

abstract class Delivery : Action {
    companion object {

        /**
         * Default images can be used as a placeholder when the requested image does not exist.
         * @param
         */
        fun defaultImage(publicId: String) = DefaultImage(publicId)

        /**
         * Controls the density to use when delivering an image or when converting a vector file such as a PDF or EPS document to a web image delivery format.
         * @param density
         */
        fun density(density: Int) = Density(density)
        fun density(density: Expression) = Density(density)
        fun density(density: String) = Density(density)

        /**
         * Controls the color space used for the delivered image.
         * @param colorSpace
         */
        fun colorSpace(colorSpace: ColorSpaceType) = ColorSpace(colorSpace)

        /**
         * Image only. Renders the image using the specified color space (icc) file. The icc file must be uploaded to your account as a raw and authenticated file. Specify the public ID of your icc file including the file extension.
         * @param publicId
         */
        fun colorSpaceFromIcc(publicId: String) = ColorSpaceFromIcc(publicId)

        /**
         * Delivers the image in the specified device pixel ratio.
         * @param dpr
         */
        fun dpr(dpr: Dpr) = dpr
        fun dpr(dpr: Float) = Dpr.fromFloat(dpr)
        fun dpr(dpr: Expression) = Dpr.fromExpression(dpr)
        fun dpr(dpr: String) = Dpr.fromString(dpr)

        /**
         * Controls the compression quality for images and videos.
         * @param quality
         */
        fun quality(quality: Quality) = quality
        fun quality(level: Any, options: (Quality.Builder.() -> Unit)? = null): Quality {
            val builder = Quality.Builder(level)
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Sets the file format.
         * @param format
         */
        fun format(format: Format) = format
        fun format(format: FormatType, options: (Format.Builder.() -> Unit)? = null): Format {
            val builder = Format.Builder(format)
            options?.let { builder.it() }
            return builder.build()
        }
    }
}
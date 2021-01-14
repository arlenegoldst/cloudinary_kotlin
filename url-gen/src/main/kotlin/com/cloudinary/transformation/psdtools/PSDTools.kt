package com.cloudinary.transformation.psdtools

import com.cloudinary.transformation.Action

abstract class PSDTools : Action {
    companion object {
        /**
         * Trims pixels in an image according to a clipping path that was saved with the originally uploaded image. For example, a clipping path that was manually created using PhotoShop.
         * @param
         */
        fun clip(clippingPath: String, options: (Clip.Builder.() -> Unit)? = null): Clip {
            val builder = Clip.Builder(clippingPath)
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Trims pixels in an image according to a clipping path that was saved with the originally uploaded image. For example, a clipping path that was manually created using PhotoShop.
         * @param
         */
        fun clip(clippingPath: Int? = null, options: (Clip.Builder.() -> Unit)? = null): Clip {
            val builder = Clip.Builder(clippingPath)
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Represents an embedded smart object in a Photoshop document.
         * @param
         */
        fun smartObject(options: (SmartObject.Builder.() -> Unit)?): SmartObject {
            val builder = SmartObject.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         *
         */
        fun getLayer(options: (GetLayer.Builder.() -> Unit)): GetLayer {
            val builder = GetLayer.Builder()
            builder.options()
            return builder.build()
        }
    }
}

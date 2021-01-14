package com.cloudinary.transformation.videoedit

import com.cloudinary.transformation.Action
import com.cloudinary.transformation.layer.source.VideoSource

abstract class VideoEdit : Action {
    companion object {

        /**
         * Trims a video (and discards the rest).
         * @param
         */
        fun trim(options: Trim.Builder.() -> Unit): Trim {
            val builder = Trim.Builder()
            builder.options()
            return builder.build()
        }

        /**
         * Increases or decreases the volume by a percentage of the current volume.
         * @param level
         */
        fun volume(level: Int) = Volume(level)
        fun volume(level: Any) = Volume(level)
        fun volume(volume: Volume) = volume

        /**
         * Concatenates another video or image.
         * @param
         */
        fun concatenate(source: VideoSource, options: (Concatenate.Builder.() -> Unit)? = null): Concatenate {
            val builder = Concatenate.Builder(source)
            options?.let { builder.it() }
            return builder.build()
        }
    }
}

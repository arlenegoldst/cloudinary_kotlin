package com.cloudinary.transformation.layer.position

import com.cloudinary.transformation.TransformationDsl

class TimelinePosition private constructor(
    val startOffset: Any?,
    val duration: Any?
) {
    @TransformationDsl
    class Builder {
        private var startOffset: Any? = null
        private var duration: Any? = null

        fun build() = TimelinePosition(startOffset, duration)

        /**
         * Specifies the first second to include in the video (or audio clip). This parameter is often used in conjunction with the eo (end offset) and/or du (duration) parameters.
         * @param startOffset
         */
        fun startOffset(startOffset: Float) = apply { this.startOffset = startOffset }

        /**
         * Sets the duration (in seconds) of a video or audio clip.
         * @param duration
         */
        fun duration(duration: Float) = apply { this.duration = duration }
    }
}

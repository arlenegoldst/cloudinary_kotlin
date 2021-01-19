package com.cloudinary.transformation.layer

import com.cloudinary.transformation.Action
import com.cloudinary.transformation.TransformationComponentBuilder
import com.cloudinary.transformation.layer.position.LayerPosition
import com.cloudinary.transformation.layer.position.TimelinePosition
import com.cloudinary.transformation.layer.source.*

class Overlay private constructor(
    private val source: LayerSource,
    private val position: LayerPosition? = null,
    private val timelinePosition: TimelinePosition? = null,
    private val blendMode: BlendMode? = null
) : Action {
    companion object {
        /**
         * Overlays an image on the base image or video.
         */
        fun image(options: (ImageBuilder.() -> Unit)? = null): Overlay {
            val builder = ImageBuilder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Overlays the specified video on a base video.
         */
        fun video(options: (VideoBuilder.() -> Unit)? = null): Overlay {
            val builder = VideoBuilder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Overlays a remote image onto an image or video.
         * @param
         */
        fun fetch(options: (FetchBuilder.() -> Unit)? = null): Overlay {
            val builder = FetchBuilder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Adds a text layer.
         * @param
         */
        fun text(options: (TextBuilder.() -> Unit)? = null): Overlay {
            val builder = TextBuilder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Adds subtitles to a video.
         */
        fun subtitles(options: (SubtitlesBuilder.() -> Unit)? = null): Overlay {
            val builder = SubtitlesBuilder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Defines the asset to use as the layered file in an overlay or underlay.
         * @param
         */
        fun source(source: LayerSource, options: (Builder.() -> Unit)? = null): Overlay {
            val builder = Builder(source)
            options?.let { builder.it() }
            return builder.build()
        }
    }

    override fun toString(): String {
        return buildLayerComponent(
            "l",
            source,
            position,
            blendMode,
            timelinePosition
        )
    }

    class VideoBuilder : BaseBuilder() {
        fun source(publicId: String, source: (VideoSource.Builder.() -> Unit)? = null) = apply {
            val builder = VideoSource.Builder(publicId)
            source?.let { builder.it() }
            source(builder.build())
        }

        fun source(source: VideoSource) = apply { this.source = source }
    }

    class FetchBuilder : BaseBuilder() {
        fun source(publicId: String, source: (FetchSource.Builder.() -> Unit)? = null) = apply {
            val builder = FetchSource.Builder(publicId)
            source?.let { builder.it() }
            source(builder.build())
        }

        fun source(source: FetchSource) = apply { this.source = source }
    }

    class TextBuilder : BaseBuilder() {
        fun source(publicId: String, source: (TextSource.Builder.() -> Unit)? = null) = apply {
            val builder = TextSource.Builder(publicId)
            source?.let { builder.it() }
            source(builder.build())
        }

        fun source(source: TextSource) = apply { this.source = source }
    }

    class SubtitlesBuilder : BaseBuilder() {
        fun source(publicId: String, source: (SubtitlesSource.Builder.() -> Unit)? = null) = apply {
            val builder = SubtitlesSource.Builder(publicId)
            source?.let { builder.it() }
            source(builder.build())
        }

        fun source(source: SubtitlesSource) = apply { this.source = source }
    }

    class ImageBuilder : BaseBuilder() {
        fun source(publicId: String, source: (ImageSource.Builder.() -> Unit)? = null) = apply {
            val builder = ImageSource.Builder(publicId)
            source?.let { builder.it() }
            source(builder.build())
        }

        fun source(source: ImageSource) = apply { this.source = source }
    }

    class Builder(source: LayerSource) : BaseBuilder() {
        init {
            this.source = source
        }
    }

    abstract class BaseBuilder : TransformationComponentBuilder {
        protected var position: LayerPosition? = null
        protected var blendMode: BlendMode? = null
        protected var timelinePosition: TimelinePosition? = null
        protected var source: LayerSource? = null

        /**
         * The position of the concatenated video.
         */
        fun timelinePosition(timelinePosition: TimelinePosition) = apply { this.timelinePosition = timelinePosition }

        /**
         *
         */
        fun position(position: LayerPosition) = apply { this.position = position }
        fun position(position: (LayerPosition.Builder.() -> Unit)? = null) = apply {
            val builder = LayerPosition.Builder()
            position?.let { builder.it() }
            position(builder.build())
        }

        /**
         * Defines the mode of blending to use when overlaying an image.
         * @param
         */
        fun blendMode(blendMode: BlendMode) = apply { this.blendMode = blendMode }

        override fun build(): Overlay {
            val safeSource = source
            require(safeSource != null) { "A source must be provided" }
            return Overlay(safeSource, position, timelinePosition, blendMode)
        }
    }
}
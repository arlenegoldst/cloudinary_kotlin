package com.cloudinary.transformation.effect

import com.cloudinary.transformation.Action
import com.cloudinary.transformation.Region
import com.cloudinary.transformation.layer.source.LayerSource

abstract class Effect : Action {

    companion object {

        /**
         * Changes the speed of the video playback.
         * @param rate
         */
        fun accelerate(rate: Int? = null) = Accelerate(rate)

        /**
         * Removes small motion shifts from the video with a maximum extent of movement in the horizontal and vertical direction of 32 pixels.
         * @param factor
         */
        fun deshake(factor: Deshake.DeshakeFactor? = null) = Deshake(factor)

        /**
         * Plays the video or audio file in reverse.
         * @param
         */
        fun reverse() = Reverse()

        /**
         * Causes a video clip to play forwards and then backwards.
         * @param
         */
        fun boomerang() = Boomerang()

        /**
         * Adds visual noise to the video, visible as a random flicker of "dots" or "snow".
         * @param level
         */
        fun noise(level: Int? = null) = Noise(level)

        /**
         * Makes the background of the image transparent (or solid white for formats that do not support transparency).
         * @param level
         */
        fun makeTransparent(
            level: Int? = null,
            options: (MakeTransparent.Builder.() -> Unit)? = null
        ): MakeTransparent {
            val builder = MakeTransparent.Builder()
            options?.let { builder.it() }
            level?.let { builder.tolerance(it) }
            return builder.build()
        }

        /**
         * Create a waveform image (in the format specified by the file extension) from the audio or video file.
         * @param options
         */
        fun waveform(options: (Waveform.Builder.() -> Unit)? = null): Waveform {
            val builder = Waveform.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Generates an excerpt of the video based on Cloudinary's AI-powered preview algorithm, which identifies the most interesting video chunks from a video and uses these to generate a video preview.
         * @param options
         */
        fun preview(options: (Preview.Builder.() -> Unit)? = null): Preview {
            val builder = Preview.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Fade in at the beginning of the video.
         * @param duration
         */
        fun fadeIn(duration: Long? = null) = Fade(duration)

        /**
         * Fade out at the end of the video.
         * @param duration
         */
        fun fadeOut(duration: Long) = Fade(-duration)

        /**
         * Delivers a video or animated GIF that contains additional loops of the video/GIF.
         * @param additionalIterations
         */
        fun loop(additionalIterations: Int? = null) = Loop(additionalIterations)

        /**
         * Changes the color scheme of the image to sepia.
         * @param level
         */
        fun sepia(level: Int? = null) = Sepia(level)

        /**
         * Converts the image to black and white.
         * @param threshold
         */
        fun blackWhite(threshold: Int? = null) = Blackwhite(threshold)

        /**
         * Applies an ordered dither filter to the image.
         * @param filter
         */
        fun dither(filter: Dither? = null) = DitherEffect(filter)

        /**
         * Applies a vignette effect.
         * @param level
         */
        fun vignette(level: Int? = null) = Vignette(level)

        /**
         * Simulates the way an image would appear to someone with the specified color blind condition.
         * @param condition
         */
        fun simulateColorBlind(condition: SimulateColorBlindType? = null) = SimulateColorBlind(condition)

        /**
         * Applies a cartoon effect to an image.
         * @param options
         */
        fun cartoonify(options: (Cartoonify.Builder.() -> Unit)? = null): Cartoonify {
            val builder = Cartoonify.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Adds a shadow to the image.
         * @param strength
         */
        fun shadow(strength: Int? = null, options: (Shadow.Builder.() -> Unit)? = null): Shadow {
            val builder = Shadow.Builder()
            strength?.let { builder.strength(it) }
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Vectorizes the image.
         * @param
         */
        fun vectorize(options: (Vectorize.Builder.() -> Unit)? = null): Vectorize {
            val builder = Vectorize.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Adds an outline to a transparent image.
         * @param
         */
        fun outline(options: (Outline.Builder.() -> Unit)? = null): Outline {
            val builder = Outline.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Detects and removes image edges whose color is similar to the corner pixels.
         * @param
         */
        fun trim(options: (Trim.Builder.() -> Unit)? = null): Trim {
            val builder = Trim.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Applies the selected artistic filter to the image.
         * @param filter
         */
        fun artisticFilter(filter: ArtisticFilter) = Artistic(filter)

        /**
         * Negates the image colors. Creates a negative of the image.
         * @param
         */
        fun negate() = Negate()

        /**
         * Removes red eyes in the image.
         */
        fun redEye() = RedEye()

        /**
         * Converts the image to gray-scale (multiple shades of gray).
         */
        fun grayscale() = Grayscale()

        /**
         * Applies an oil painting effect to the image.
         * @param strength
         */
        fun oilPaint(strength: Int? = null) = OilPaint(strength)

        /**
         * Removes red eyes with the Advanced Facial Attribute Detection add-on.
         */
        fun advancedRedEye() = AdvancedRedEye()

        /**
         * Applies a pixelation effect to the image.
         * @param pixelWidth
         */
        fun pixelate(pixelWidth: Int? = null, region: Region? = null) = Pixelate(pixelWidth, region)

        /**
         * Applies a blurring filter to an asset.
         * @param strength
         */
        fun blur(strength: Int? = null, region: Region? = null) = Blur(strength, region)

        /**
         * Colorizes the image.
         * @param
         */
        fun colorize(options: (Colorize.Builder.() -> Unit)? = null): Colorize {
            val builder = Colorize.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Applies a gradient fade effect from the top edge of the image.
         * @param
         */
        fun gradientFade(options: (GradientFade.Builder.() -> Unit)? = null): GradientFade {
            val builder = GradientFade.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Applies stripes to the image to help people with common color-blind conditions to differentiate between colors that are similar for them.
         * @param
         */
        fun assistColorBlind(options: (AssistColorblind.Builder.() -> Unit)? = null): AssistColorblind {
            val builder = AssistColorblind.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Transfers the style of a source artwork to a target photograph using the Neural Artwork Style Transfer add-on.
         * @param source
         */
        fun styleTransfer(source: LayerSource, options: (StyleTransfer.Builder.() -> Unit)? = null): StyleTransfer {
            val builder = StyleTransfer.Builder(source)
            options?.let { builder.it() }
            return builder.build()
        }

        fun styleTransfer(styleTransfer: StyleTransfer) = styleTransfer
    }
}


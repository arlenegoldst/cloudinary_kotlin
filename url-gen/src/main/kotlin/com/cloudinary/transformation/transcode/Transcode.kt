package com.cloudinary.transformation.transcode

import com.cloudinary.transformation.Action

abstract class Transcode : Action {
    companion object {

        /**
         * Sets the streaming profile to apply to an HLS or MPEG-DASH adaptive bitrate streaming video.
         * @param
         */
        fun streamingProfile(profile: StreamingProfileType) = StreamingProfile(profile)
        fun streamingProfile(profile: String) = StreamingProfile(profile)

        /**
         * Sets the keyframe interval of the delivered video.
         * @param seconds
         */
        fun keyframeInterval(seconds: Float) = KeyframeInterval(seconds)

        /**
         * Controls the range of acceptable FPS (Frames Per Second) to ensure that video (even when optimized) is delivered with an expected FPS level (helps with sync to audio).
         * @param fps
         */
        fun fps(fps: Float) = Fps.Builder().fixed(fps).build()

        /**
         * Controls the FPS (Frames Per Second) of a video to ensure that the video (even when optimized) is delivered with an expected FPS level (helps with sync to audio). Can also be specified as a range.
         * @param from
         */
        fun fpsRange(from: Float) = Fps.Builder().min(from).build()
        fun fpsRange(from: Float, to: Float) = Fps.Builder().min(from).max(to).build()

        /**
         * Controls the video bitrate.
         * @param bitrate
         */
        fun bitRate(bitrate: String, options: (Bitrate.Builder.() -> Unit)? = null): Bitrate {
            val builder = Bitrate.Builder(bitrate)
            options?.let { builder.it() }
            return builder.build()
        }


        /**
         * Sets the audio sample frequency.
         * @param
         */
        fun audioFrequency(frequency: AudioFrequency) = AudioFrequencyTranscode(frequency)

        /**
         * Sets the audio codec or removes the audio channel.
         * @param
         */
        fun audioCodec(codec: AudioCodec) = AudioCodecTranscode(codec)

        /**
         * Converts a video to animated image.
         * @param format
         */
        fun toAnimated(format: String, options: (ToAnimated.Builder.() -> Unit)? = null): Transcode {
            val builder = ToAnimated.Builder(format)
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Controls the video codec.
         * @param
         */
        fun videoCodec(codec: VideoCodecType, options: (VideoCodec.Builder.() -> Unit)? = null): VideoCodec {
            val builder = VideoCodec.Builder(codec)
            options?.let { builder.it() }
            return builder.build()
        }
    }
}
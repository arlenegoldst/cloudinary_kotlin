package com.cloudinary.transformation

import com.cloudinary.transformation.delivery.ProgressiveMode

class FlagAction(private val value: Flag) : Action {
    override fun toString() = value.toString()
}

// Make these objects instead of classes
class Flag(vararg values: Any?) {

    val values: List<Any> = values.toList().filterNotNull()

    companion object {
        /**
         * When used together with automatic quality (q_auto): allow switching to PNG8 encoding if the quality algorithm decides that it's more efficient.
         * @param
         */
        fun anyFormat() = Flag("any_format")

        /**
         * Delivers the image as an attachment.
         * @param name
         */
        fun attachment(name: String? = null) = Flag("attachment", name)

        /**
         * The apng (animated PNG) flag alters the regular PNG delivery behavior by delivering an animated image asset in animated PNG format rather than a still PNG image. Keep in mind that animated PNGs are not supported in all browsers and versions.
         * @param
         */
        fun aPng() = Flag("apng")

        /**
         * When converting animated images to WebP format, generate an animated WebP from all the frames in the original animated file instead of only from the first still frame.
         * @param
         */
        fun animatedWebP() = Flag("awebp")

        /**
         * Trims pixels according to a clipping path included in the original image (e.g., manually created using PhotoShop).
         * @param
         */
        fun clip() = Flag("clip")

        /**
         * Trims pixels according to a clipping path included in the original image (e.g., manually created using PhotoShop) using an evenodd clipping rule.
         * @param
         */
        fun clipEvenOdd() = Flag("clip_evenodd")

        /**
         * Trims pixels according to the transparency levels of a given overlay image.
         * @param
         */
        fun cutter() = Flag("cutter")

        /**
         * Instructs Cloudinary to clear all image meta-data (IPTC, Exif and XMP) while applying an incoming transformation.
         * @param
         */
        fun forceStrip() = Flag("force_strip")

        /**
         * Adds ICC color space metadata to the image, even when the original image doesn't contain any ICC data.
         * @param
         */
        fun forceIcc() = Flag("force_icc")

        /**
         * Returns metadata of the input asset and of the transformed output asset in JSON instead of the transformed image.
         * @param
         */
        fun getInfo() = Flag("getinfo")

        /**
         * Allows specifying only either width or height so the value of the second axis remains as is, and is not recalculated to maintain the aspect ratio of the original image.
         * @param
         */
        fun ignoreAspectRatio() = Flag("ignore_aspect_ratio")

        /**
         * Sets the cache-control to immutable for the asset, which tells the browser that the asset does not have to be revalidated with the server when the page is refreshed, and can be loaded directly from the cache.
         * @param
         */
        fun immutableCache() = Flag("immutable_cache")

        /**
         * Keeps the copyright related fields when stripping meta-data. Without this flag, Cloudinary's default behavior is to strip all meta-data when generating new image transformations.
         * @param
         */
        fun keepAttribution() = Flag("keep_attribution")

        /**
         * Keeps all meta-data. Without this flag, Cloudinary's default behavior is to strip all meta-data when generating new image transformations.
         * @param
         */
        fun keepIptc() = Flag("keep_iptc")

        /**
         * Applies all chained transformations, until a transformation component that includes this flag, on the last added overlay or underlay instead of applying on the containing image.
         * @param
         */
        fun layerApply() = Flag("layer_apply")

        /**
         * Automatically use lossy compression when delivering animated GIF files. This flag can also be used as a conditional flag for delivering PNG files: it tells Cloudinary to deliver the image in PNG format (as requested) unless there is no transparency channel - in which case delivers in JPEG format.
         * @param
         */
        fun lossy() = Flag("lossy")

        /**
         * Prevents Cloudinary from extending the image canvas beyond the original dimensions when overlaying text and other images.
         * @param
         */
        fun noOverflow() = Flag("no_overflow")

        /**
         * When used with automatic fetch_format (f_auto): ensures that images with a transparency channel will be delivered in PNG format.
         * @param
         */
        fun preserveTransparency() = Flag("preserve_transparency")

        /**
         * Generate PNG images in the PNG8 format.
         * @param
         */
        fun png8() = Flag("png8")

        /**
         * Generates PNG images in the PNG24 format.
         * @param
         */
        fun png24() = Flag("png24")

        /**
         * Generates PNG images in the PNG32 format.
         * @param
         */
        fun png32() = Flag("png32")

        /**
         * Generates a JPG image using the progressive (interlaced) JPG format.
         * @param
         */
        fun progressive(mode: ProgressiveMode? = null) = Flag("progressive", mode)

        /**
         * Reduces the image to one flat pixelated layer (as opposed to the default vector based graphic) in order to enable PDF resizing and overlay manipulations.
         * @param
         */
        fun rasterize() = Flag("rasterize")

        /**
         * Modifies percentage-based width & height parameters of overlays and underlays (e.g., 1.0) to be relative to the overlaid region. Currently regions are only defined when using gravity 'face', 'faces' or 'custom'.
         * @param
         */
        fun regionRelative() = Flag("region_relative")

        /**
         * Modifies percentage-based width & height parameters of overlays and underlays (e.g., 1.0) to be relative to the containing image instead of the added layer.
         * @param
         */
        fun relative() = Flag("relative")

        /**
         * Replaces the first image embedded in a PDF with the image stipulated as an overlay, instead of adding it as another overlay.
         * @param
         */
        fun replaceImage() = Flag("replace_image")

        /**
         * Instructs Cloudinary to run a sanitizer on the image (relevant only for the SVG format).
         * @param
         */
        fun sanitize() = Flag("sanitize")

        /**
         * Instructs Cloudinary to clear all ICC color profile data included with the image.
         * @param
         */
        fun stripProfile() = Flag("strip_profile")

        /**
         * By default, text overlays are trimmed tightly to the text with no excess padding. This flag adds a small amount of padding around the text overlay string.
         * @param
         */
        fun textNoTrim() = Flag("text_no_trim")

        /**
         * Returns an error if the text overlay exceeds the image boundaries.
         * @param
         */
        fun textDisallowOverflow() = Flag("text_disallow_overflow")

        /**
         * Generates TIFF images using LZW compression and in the TIFF8 format.
         * @param
         */
        fun tiff8Lzw() = Flag("tiff8_lzw")

        /**
         * Tiles the added overlay over the entire image.
         * @param
         */
        fun tiled() = Flag("tiled")

        /**
         * Used when delivering a video file as an image format that supports animation, such as animated WebP.
         * Plays all frames rather than just delivering the first one as a static image. Use this flag in addition to the flag or parameter controlling the delivery format, for example f_auto or fl_awebp.
         * Note: When delivering a video in GIF format, it is delivered as an animated GIF by default and this flag is not necessary. To deliver a single frame of a video in GIF format, use the page parameter.
         * @param
         */
        fun animated() = Flag("animated")

        /**
         * If the requested video transformation has already been generated, this flag works identically to Flag::attachment.
         * However, if the video transformation is being requested for the first time, this flag causes the video download to begin immediately, streaming it as a fragmented video file.
         * In contrast, if the regular fl_attachment flag is used when a user requests a new video transformation, the download will begin only after the complete transformed video has been generated.
         * Most standard video players successfully play fragmented video files without issue.
         * @param
         */
        fun streamingAttachment() = Flag("streaming_attachment")

        /**
         * Deliver an HLS adaptive bitrate streaming file as HLS v3 instead of the default version (HLS v4).
         * @param
         */
        fun hlsV3() = Flag("hlsv3")

        /**
         * Keep the Display Aspect Ratio metadata of the uploaded video.
         * @param
         */
        fun keepDar() = Flag("keep_dar")

        /**
         * Don't stream a video that is currently being generated on the fly. Wait until the video is fully generated.
         * @param
         */
        fun noStream() = Flag("no_stream")

        /**
         * Convert the audio channel to mono.
         * @param
         */
        fun mono() = Flag("mono")

        /**
         * A qualifier that concatenates (splices) the video specified as an overlay to a base video (instead of adding it as a video-on-video overlay). By default, the overlay video is spliced to the end of the base video. You can use the start offset parameter set to 0 (so_0) to splice the overlay video to the beginning of the base video.
         * @param
         */
        fun splice() = Flag("splice")

        /**
         * Truncates (trims) a video file based on the times defined in the video file's metadata (relevant only where the file metadata includes a directive to play only a section of the video).
         * @param
         */
        fun truncateTS() = Flag("truncate_ts")

        /**
         * Create a waveform image (in the format specified by the file extension) from the audio or video file.
         * @param
         */
        fun waveform() = Flag("waveform")
    }

    override fun toString(): String {
        return "fl_${values.joinToString(":")}"
    }
}
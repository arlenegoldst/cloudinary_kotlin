package com.cloudinary.transformation.gravity

import com.cloudinary.transformation.TransformationDsl
import com.cloudinary.transformation.joinWithValues

// TODO these classes don't yet make enough sense
abstract class Gravity {
    companion object {
        /**
         * South center part (bottom center).
         * @param
         */
        fun south() =
            CompassGravity(Compass.SOUTH)

        /**
         * South east corner (bottom right).
         */
        fun southEast() =
            CompassGravity(Compass.SOUTH_EAST)

        /**
         * South west corner (bottom left).
         */
        fun southWest() =
            CompassGravity(Compass.SOUTH_WEST)

        /**
         * North center part (top center).
         * @param
         */
        fun north() =
            CompassGravity(Compass.NORTH)

        /**
         * North east corner (top right).
         * @param
         */
        fun northEast() =
            CompassGravity(Compass.NORTH_EAST)

        /**
         * North west corner (top left).
         */
        fun northWest() =
            CompassGravity(Compass.NORTH_WEST)

        /**
         * Middle east part (right).
         * @param
         */
        fun east() =
            CompassGravity(Compass.EAST)

        /**
         * Middle west part (left).
         * @param
         */
        fun west() =
            CompassGravity(Compass.WEST)

        /**
         * The center of the image.
         * @param
         */
        fun center() =
            CompassGravity(Compass.CENTER)

        /**
         * Detects all text elements in an image using the OCR Text Detection and Extraction add-on and uses the detected bounding box coordinates as the focus of the transformation.
         * @param
         */
        fun ocr(options: (OcrGravity.Builder.() -> Unit)? = null): OcrGravity {
            val builder = OcrGravity.Builder()
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Defines the area to keep when automatically resizing an image.
         * @param
         */
        fun focusOn(
            focusOn: FocusOn,
            vararg focusOnObjects: FocusOn,
            options: (FocusOnGravity.Builder.() -> Unit)? = null
        ): FocusOnGravity {
            val builder = FocusOnGravity.Builder(mutableListOf(focusOn).also { it.addAll(focusOnObjects) })
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Automatically identifies the most interesting regions to include when resizing.
         * @param
         */
        fun auto(vararg objects: IAutoGravityObject, options: (AutoGravity.Builder.() -> Unit)? = null): AutoGravity {
            val builder = AutoGravity.Builder()
            builder.autoFocus(*objects)
            options?.let { builder.it() }
            return builder.build()
        }

        /**
         * Defines the gravity based on directional values from a compass.
         * @param
         */
        fun compass(compass: Compass) = CompassGravity(compass)
    }
}

class CompassGravity internal constructor(private val compass: Compass) : Gravity() {
    override fun toString(): String {
        return compass.toString()
    }
}

class FocusOnGravity internal constructor(
    private val focusOnObjects: List<FocusOn>,
    private val fallbackGravity: AutoGravity? = null
) : Gravity() {
    override fun toString(): String {
        return focusOnObjects.joinToString(":").joinWithValues(fallbackGravity)
    }

    @TransformationDsl
    class Builder(private val objects: MutableList<FocusOn>) {
        private var fallbackGravity: AutoGravity? = null

        fun fallbackGravity(gravity: AutoGravity) = apply { this.fallbackGravity = gravity }
        fun build(): FocusOnGravity {
            return FocusOnGravity(objects, fallbackGravity)
        }
    }
}

class AutoGravity internal constructor(private val objects: List<IAutoGravityObject>) : Gravity() {
    override fun toString(): String {
        return "auto".joinWithValues(*objects.toTypedArray())
    }

    @TransformationDsl
    class Builder {
        val objects = mutableListOf<IAutoGravityObject>()

        fun autoFocus(vararg objects: IAutoGravityObject) = apply {
            this.objects.addAll(objects)
        }

        fun build(): AutoGravity {
            return AutoGravity(objects)
        }
    }
}

class AutoFocus {

    companion object {
        fun focusOn(focus: FocusOn, options: (AutoGravityObject.Builder.() -> Unit)? = null): AutoGravityObject {
            val builder = AutoGravityObject.Builder(focus)
            options?.let { builder.it() }
            return builder.build()
        }
    }
}

class OcrGravity(private val avoid: Boolean? = null) : Gravity() {
    override fun toString(): String {
        return "ocr_text".joinWithValues(avoid?.let { "avoid" }, separator = "_")
    }

    class Builder {
        var avoid: Boolean? = null

        fun avoid() = apply { this.avoid = true }

        fun build(): OcrGravity {
            return OcrGravity(avoid)
        }
    }
}

enum class Compass(private val value: String) {
    NORTH("north"),
    NORTH_EAST("north_east"),
    NORTH_WEST("north_west"),
    EAST("east"),
    SOUTH_EAST("south_east"),
    SOUTH("south"),
    SOUTH_WEST("south_west"),
    WEST("west"),
    CENTER("center");

    override fun toString(): String {
        return value
    }
}
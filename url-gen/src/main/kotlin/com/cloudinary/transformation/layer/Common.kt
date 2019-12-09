package com.cloudinary.transformation.layer

import com.cloudinary.transformation.*
import com.cloudinary.util.cldToString

class Position(params: Map<String, Param>) : Action<Position>(params) {
    class Builder : TransformationComponentBuilder {
        private var gravity: Gravity? = null

        private var x: Any? = null

        private var y: Any? = null

        private var tileMode: TileMode? = null

        private var allowOverflow: Boolean = true

        override fun build() = Position(
            listOfNotNull(
                gravity,
                x?.cldAsX(),
                y?.cldAsY(),
                tileMode?.asFlag(),
                if (!allowOverflow) FlagsParam(FlagKey.NO_OVERFLOW()) else null
            ).cldToActionMap()
        )

        fun setGravity(gravity: Gravity) = apply {
            this.gravity = gravity
        }

        fun setX(x: Int) = apply { this.x = x }

        fun setX(x: Any) = apply { this.x = x }

        fun setY(y: Int) = apply { this.y = y }

        fun setY(y: Any) = apply { this.y = y }

        fun setTileMode(tileMode: TileMode) = apply { this.tileMode = tileMode }

        fun setAllowOverflow(allowOverflow: Boolean) = apply {
            this.allowOverflow = allowOverflow
        }
    }

    override fun create(params: Map<String, Param>) = Position(params)
}

abstract class LayerSource internal constructor(value: List<Any>) : ParamValue(value) {
    companion object {
        fun fetch(remoteUrl: String, fetch: (FetchLayerSource.Builder.() -> Unit)? = null): FetchLayerSource {
            val builder = FetchLayerSource.Builder(remoteUrl)
            fetch?.let { builder.fetch() }
            return builder.build()
        }

        fun media(publicId: String, media: (MediaLayerSource.Builder.() -> Unit)? = null): MediaLayerSource {
            val builder = MediaLayerSource.Builder(publicId)
            media?.let { builder.media() }
            return builder.build()
        }

        fun text(
            text: String,
            fontFamily: String,
            fontSize: Int,
            textLayer: (TextLayerSource.Builder.() -> Unit)? = null
        ) =
            text(text, fontFamily, fontSize as Any, textLayer)

        fun text(
            text: String,
            fontFamily: String,
            fontSize: Any,
            textLayer: (TextLayerSource.Builder.() -> Unit)? = null
        ): TextLayerSource {
            val builder = TextLayerSource.Builder(text, fontFamily, fontSize)
            textLayer?.let { builder.textLayer() }
            return builder.build()
        }
    }
}

open class Layer internal constructor(private val transformation: TransformationComponent) :
    TransformationComponent {

    override fun toString() = transformation.cldToString()

    companion object {
        fun overlay(source: LayerSource, options: (Builder.() -> Unit)? = null): Layer {
            val builder = Builder(source).setParam("overlay", "l")
            options?.let { builder.options() }
            return builder.build()
        }

        fun underlay(source: LayerSource, options: (Builder.() -> Unit)? = null): Layer {
            val builder = Builder(source).setParam("underlay", "u")
            options?.let { builder.options() }
            return builder.build()
        }
    }

    class Builder(private val source: LayerSource) : TransformationComponentBuilder {
        private var transformation: TransformationComponent? = null
        private var position: Position? = null
        private var blendMode: BlendMode? = null
        private var paramName: String = "layer"
        private var paramKey: String = "l"
        private var extraParams: Collection<Param> = emptyList()
        private var flag: FlagKey? = null

        fun setTransformation(transformation: TransformationComponent) = apply { this.transformation = transformation }
        fun setTransformation(transformation: Transformation.Builder.() -> Unit): Builder {
            val builder = Transformation.Builder()
            builder.transformation()
            setTransformation(builder.build())
            return this
        }

        fun setPosition(position: Position) = apply { this.position = position }
        fun setPosition(position: (Position.Builder.() -> Unit)? = null): Builder {
            val builder = Position.Builder()
            position?.let { builder.it() }
            setPosition(builder.build())
            return this
        }

        fun setBlendMode(blendMode: BlendMode) = apply { this.blendMode = blendMode }

        internal fun setParam(name: String, key: String) = apply {
            this.paramName = name
            this.paramKey = key
        }

        fun setExtraParams(params: Collection<Param>) = apply { this.extraParams = params }
        fun setFlagKey(flag: FlagKey) = apply { this.flag = flag }

        override fun build() =
            Layer(
                buildLayerComponent(
                    source,
                    transformation,
                    position,
                    blendMode = blendMode,
                    paramName = paramName,
                    paramKey = paramKey,
                    extraParams = extraParams
                )
            )
    }
}

internal fun buildLayerComponent(
    source: LayerSource,
    transformation: TransformationComponent? = null,
    position: Position? = null,
    blendMode: BlendMode? = null,
    paramName: String,
    paramKey: String,
    extraParams: Collection<Param> = emptyList(),
    flag: FlagKey? = null
): Layer {
    // start with the layer param itself
    val layerParam = Param(paramName, paramKey, source)

    // layer apply flag + optional flags
    val allParams = mutableListOf<Param>(FlagsParam(FlagKey.LAYER_APPLY()))
    flag?.let { allParams.add(FlagsParam(it)) }
    allParams.addAll(extraParams)

    // optional blend mode
    blendMode?.let { allParams.add(Param("effect", "e", ParamValue(it))) }

    // construct the position component (this needs to include the extra parameters and some of flags):
    val positionComponent: TransformationComponent =
        (position?.add(allParams) ?: GenericAction(allParams.cldToActionMap()))
    val layerComponent = Transformation(GenericAction(layerParam))
    return Layer(
        if (transformation != null) {
            layerComponent.add(transformation).add(positionComponent)
        } else {
            layerComponent.add(positionComponent)
        }
    )
}

enum class BlendMode(private val value: String) {
    SCREEN("screen"),
    MULTIPLY("multiply"),
    OVERLAY("overlay");

    override fun toString(): String {
        return value
    }
}

enum class TileMode {
    NONE,
    TILED;

    fun asFlag() = if (this == TILED) FlagsParam(FlagKey.TILED()) else null
}
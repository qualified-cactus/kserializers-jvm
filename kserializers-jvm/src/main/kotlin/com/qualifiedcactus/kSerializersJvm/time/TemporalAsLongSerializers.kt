package com.qualifiedcactus.kSerializersJvm.time

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.DateTimeException
import java.time.Instant

/**
 * Serialize [Instant] to [Long] (Unix time). Data about milliseconds of this [Instant] is ignored.
 */
object InstantAsUnixTimeSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): Instant {
        try {
            return Instant.ofEpochSecond(decoder.decodeLong())
        } catch (e: DateTimeException) {
            throw SerializationException(e)
        }
    }

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeLong(value.epochSecond)
    }
}

typealias InstantAsUnixTime = @Serializable(InstantAsUnixTimeSerializer::class) Instant
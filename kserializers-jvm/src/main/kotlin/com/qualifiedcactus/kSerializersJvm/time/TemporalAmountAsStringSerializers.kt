package com.qualifiedcactus.kSerializersJvm.time

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Duration
import java.time.Period
import java.time.format.DateTimeParseException

/**
 * This is a serializer for [java.time.Duration], not Kotlin [kotlin.time.Duration] (which already has a built-in serializer).
 * Serialize [Duration] to [String] using ISO-8601 format.
 */
object DurationSerializer : KSerializer<Duration> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Duration {
        try {
            return Duration.parse(decoder.decodeString())
        } catch (e: DateTimeParseException) {
            throw SerializationException(e)
        }
    }

    override fun serialize(encoder: Encoder, value: Duration) {
        encoder.encodeString(value.toString())
    }
}
typealias DurationAsString = @Serializable(DurationSerializer::class) Duration

/**
 * Serialize [Period] to [String] using ISO-8601 format.
 */
object PeriodSerializer : KSerializer<Period> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Period {
        try {
            return Period.parse(decoder.decodeString())
        } catch (e: DateTimeParseException) {
            throw SerializationException(e)
        }
    }

    override fun serialize(encoder: Encoder, value: Period) {
        encoder.encodeString(value.toString())
    }
}
typealias PeriodAsString = @Serializable(PeriodSerializer::class) Period


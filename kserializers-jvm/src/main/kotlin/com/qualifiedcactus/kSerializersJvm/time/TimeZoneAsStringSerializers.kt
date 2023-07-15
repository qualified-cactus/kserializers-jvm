package com.qualifiedcactus.kSerializersJvm.time

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.DateTimeException
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.zone.ZoneRulesException

object ZoneIdSerializer : KSerializer<ZoneId> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): ZoneId {
        try {
            return ZoneId.of(decoder.decodeString())
        } catch (e: DateTimeException) {
            throw SerializationException(e)
        } catch (e: ZoneRulesException) {
            throw SerializationException(e)
        }

    }

    override fun serialize(encoder: Encoder, value: ZoneId) {
        encoder.encodeString(value.toString())
    }
}

typealias ZoneIdAsString = @Serializable(ZoneIdSerializer::class) ZoneId

object ZoneOffsetSerializer : KSerializer<ZoneOffset> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): ZoneOffset {
        try {
            return ZoneOffset.of(decoder.decodeString())
        } catch (e: DateTimeException) {
            throw SerializationException(e)
        }
    }

    override fun serialize(encoder: Encoder, value: ZoneOffset) {
        encoder.encodeString(value.toString())
    }
}

typealias ZoneOffsetAsString = @Serializable(ZoneOffsetSerializer::class) ZoneOffset
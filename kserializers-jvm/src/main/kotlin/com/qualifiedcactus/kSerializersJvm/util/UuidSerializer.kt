package com.qualifiedcactus.kSerializersJvm.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

/**
 * Serialize [UUID] to [String]
 */
object UuidSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        UuidSerializer::class.qualifiedName!!,
        PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): UUID {
        try {
            return UUID.fromString(decoder.decodeString())
        } catch (e: IllegalArgumentException) {
            throw SerializationException(e)
        }

    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}

typealias UuidAsString = @Serializable(UuidSerializer::class) UUID
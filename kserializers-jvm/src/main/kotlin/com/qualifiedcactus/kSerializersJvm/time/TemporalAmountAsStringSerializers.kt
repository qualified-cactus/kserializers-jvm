/*
 * Copyright 2023 qualified-cactus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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


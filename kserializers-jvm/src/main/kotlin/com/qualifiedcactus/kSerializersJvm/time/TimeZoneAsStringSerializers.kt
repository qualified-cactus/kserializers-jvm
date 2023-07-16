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

/**
 * Serialize [ZoneId] to  zone ID in [String].
 */
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

/**
 * Serialize [ZoneOffset] to  offset ID in [String].
 */
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
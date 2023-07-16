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
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.Temporal
import java.time.temporal.TemporalAccessor
import java.time.temporal.TemporalQuery

/**
 * An abstract class for serializing [Temporal] to [String].
 */
abstract class AbstractTemporalSerializer<T : TemporalAccessor>
constructor(
    private val formatter: DateTimeFormatter,
    private val temporalQuery: TemporalQuery<T>,
) : KSerializer<T> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this::class.qualifiedName!!, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): T {
        try {
            return formatter.parse(decoder.decodeString(), temporalQuery)
        } catch (e: DateTimeParseException) {
            throw SerializationException(e)
        }
    }

    override fun serialize(encoder: Encoder, value: T) {
        try {
            encoder.encodeString(formatter.format(value))
        } catch (e: DateTimeException) {
            throw SerializationException(e)
        }
    }
}

/**
 * Serialize [Instant] to [String] using the formatter [DateTimeFormatter.ISO_INSTANT].
 */
object InstantSerializer : AbstractTemporalSerializer<Instant>(DateTimeFormatter.ISO_INSTANT, Instant::from)

/**
 * Serialize [LocalDate] to [String] using the formatter [DateTimeFormatter.ISO_LOCAL_DATE].
 */
object LocalDateSerializer : AbstractTemporalSerializer<LocalDate>(DateTimeFormatter.ISO_LOCAL_DATE, LocalDate::from)

/**
 * Serialize [LocalDateTime] to [String] using the formatter [DateTimeFormatter.ISO_LOCAL_DATE_TIME].
 */
object LocalDateTimeSerializer : AbstractTemporalSerializer<LocalDateTime>(DateTimeFormatter.ISO_LOCAL_DATE_TIME, LocalDateTime::from)

/**
 * Serialize [LocalTime] to [String] using the formatter [DateTimeFormatter.ISO_LOCAL_TIME].
 */
object LocalTimeSerializer : AbstractTemporalSerializer<LocalTime>(DateTimeFormatter.ISO_LOCAL_TIME, LocalTime::from)

/**
 * Serialize [MonthDay] to [String] using [DateTimeFormatter] of pattern "--MM-dd".
 */
object MonthDaySerializer : AbstractTemporalSerializer<MonthDay>(DateTimeFormatter.ofPattern("--MM-dd"), MonthDay::from)

/**
 * Serialize [OffsetDateTime] to [String] using the formatter [DateTimeFormatter.ISO_OFFSET_DATE_TIME].
 */
object OffsetDateTimeSerializer : AbstractTemporalSerializer<OffsetDateTime>(DateTimeFormatter.ISO_OFFSET_DATE_TIME, OffsetDateTime::from)

/**
 * Serialize [OffsetTime] to [String] using the formatter [DateTimeFormatter.ISO_OFFSET_TIME].
 */
object OffsetTimeSerializer : AbstractTemporalSerializer<OffsetTime>(DateTimeFormatter.ISO_OFFSET_TIME, OffsetTime::from)

/**
 * Serialize [Year] to [String] using [DateTimeFormatter] of pattern "uuuu".
 */
object YearSerializer : AbstractTemporalSerializer<Year>(DateTimeFormatter.ofPattern("uuuu"), Year::from)

/**
 * Serialize [YearMonth] to [String] using [DateTimeFormatter] of pattern "uuuu-MM".
 */
object YearMonthSerializer : AbstractTemporalSerializer<YearMonth>(DateTimeFormatter.ofPattern("uuuu-MM"), YearMonth::from)

/**
 * Serialize [ZonedDateTime] to [String] using the formatter [DateTimeFormatter.ISO_ZONED_DATE_TIME].
 */
object ZonedDateTimeSerializer : AbstractTemporalSerializer<ZonedDateTime>(DateTimeFormatter.ISO_ZONED_DATE_TIME, ZonedDateTime::from)


typealias InstantAsString = @Serializable(InstantSerializer::class) Instant
typealias LocalDateAsString = @Serializable(LocalDateSerializer::class) LocalDate
typealias LocalDateTimeAsString = @Serializable(LocalDateTimeSerializer::class) LocalDateTime
typealias LocalTimeAsString = @Serializable(LocalTimeSerializer::class) LocalTime
typealias MonthDayAsString = @Serializable(MonthDaySerializer::class) MonthDay
typealias OffsetDateTimeAsString = @Serializable(OffsetDateTimeSerializer::class) OffsetDateTime
typealias OffsetTimeAsString = @Serializable(OffsetTimeSerializer::class) OffsetTime
typealias YearAsString = @Serializable(YearSerializer::class) Year
typealias YearMonthAsString = @Serializable(YearMonthSerializer::class) YearMonth
typealias ZonedDateTimeAsString = @Serializable(ZonedDateTimeSerializer::class) ZonedDateTime
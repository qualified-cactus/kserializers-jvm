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


object InstantSerializer : AbstractTemporalSerializer<Instant>(DateTimeFormatter.ISO_INSTANT, Instant::from)
object LocalDateSerializer : AbstractTemporalSerializer<LocalDate>(DateTimeFormatter.ISO_LOCAL_DATE, LocalDate::from)
object LocalDateTimeSerializer : AbstractTemporalSerializer<LocalDateTime>(DateTimeFormatter.ISO_LOCAL_DATE_TIME, LocalDateTime::from)
object LocalTimeSerializer : AbstractTemporalSerializer<LocalTime>(DateTimeFormatter.ISO_LOCAL_TIME, LocalTime::from)
object MonthDaySerializer : AbstractTemporalSerializer<MonthDay>(DateTimeFormatter.ofPattern("--MM-dd"), MonthDay::from)
object OffsetDateTimeSerializer : AbstractTemporalSerializer<OffsetDateTime>(DateTimeFormatter.ISO_OFFSET_DATE_TIME, OffsetDateTime::from)
object OffsetTimeSerializer : AbstractTemporalSerializer<OffsetTime>(DateTimeFormatter.ISO_OFFSET_TIME, OffsetTime::from)
object YearSerializer : AbstractTemporalSerializer<Year>(DateTimeFormatter.ofPattern("uuuu"), Year::from)
object YearMonthSerializer : AbstractTemporalSerializer<YearMonth>(DateTimeFormatter.ofPattern("uuuu-MM"), YearMonth::from)
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
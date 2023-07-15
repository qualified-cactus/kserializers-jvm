package com.qualifiedcactus.kSerializersJvm

import com.qualifiedcactus.kSerializersJvm.time.*
import com.qualifiedcactus.kSerializersJvm.util.UuidAsString
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.*
import java.util.UUID
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SerializerTest {

    @Serializable
    data class JsonDto(
        val instant: InstantAsString,
        val localDate: LocalDateAsString,
        val localDateTime: LocalDateTimeAsString,
        val localTime: LocalTimeAsString,
        val monthDay: MonthDayAsString,
        val offsetDateTime: OffsetDateTimeAsString,
        val offsetTime: OffsetTimeAsString,
        val year: YearAsString,
        val yearMonth: YearMonthAsString,
        val zonedDateTime: ZonedDateTimeAsString,
        val nullProperty: ZonedDateTimeAsString?,
        val duration: DurationAsString,
        val period: PeriodAsString,
        val uuid: UuidAsString,
        val zoneId: ZoneIdAsString,
        val zoneOffSet: ZoneOffsetAsString,
        val unixTime: InstantAsUnixTime
    )


    @Test
    fun test() {
        val dto = JsonDto(
            instant = Instant.now(),
            localDate = LocalDate.now(),
            localDateTime = LocalDateTime.now(),
            localTime = LocalTime.now(),
            monthDay = MonthDay.now(),
            offsetDateTime = OffsetDateTime.now(),
            offsetTime = OffsetTime.now(),
            year = Year.now(),
            yearMonth = YearMonth.now(),
            zonedDateTime = ZonedDateTime.now(),
            nullProperty = null,
            duration = Duration.ofSeconds(1234567891234567L),
            period = Period.parse("P1Y20M5D"),
            uuid = UUID.randomUUID(),
            zoneId = ZoneId.of("Europe/Paris"),
            zoneOffSet = ZoneOffset.of("+15:30:15"),
            unixTime = Instant.ofEpochSecond(99999999L),
        )
        val json = Json { prettyPrint = true }
        val jsonString = json.encodeToString(dto)
        val decodedDto = json.decodeFromString<JsonDto>(jsonString)
        println(jsonString)
        println(decodedDto)
        assertEquals(dto, decodedDto)
    }
}
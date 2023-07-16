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
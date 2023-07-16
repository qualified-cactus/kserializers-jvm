@file:JvmName("SerializerModuleExtensions")
package com.qualifiedcactus.kSerializersJvm

import com.qualifiedcactus.kSerializersJvm.time.*
import com.qualifiedcactus.kSerializersJvm.util.UuidSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.contextual


/**
 * Add the following [KSerializer] to via [contextual]:
 *
 * - [InstantSerializer]
 * - [LocalDateSerializer]
 * - [LocalDateTimeSerializer]
 * - [LocalTimeSerializer]
 * - [MonthDaySerializer]
 * - [OffsetDateTimeSerializer]
 * - [OffsetTimeSerializer]
 * - [YearSerializer]
 * - [YearMonthSerializer]
 * - [ZonedDateTimeSerializer]
 * - [ZoneIdSerializer]
 * - [ZoneOffsetSerializer]
 * - [UuidSerializer]
 *
 */
fun SerializersModuleBuilder.addSerializersForJvm() {
    contextual(InstantSerializer)
    contextual(LocalDateSerializer)
    contextual(LocalDateTimeSerializer)
    contextual(LocalTimeSerializer)
    contextual(MonthDaySerializer)
    contextual(OffsetDateTimeSerializer)
    contextual(OffsetTimeSerializer)
    contextual(YearSerializer)
    contextual(YearMonthSerializer)
    contextual(ZonedDateTimeSerializer)

    contextual(ZoneIdSerializer)
    contextual(ZoneOffsetSerializer)

    contextual(UuidSerializer)
}
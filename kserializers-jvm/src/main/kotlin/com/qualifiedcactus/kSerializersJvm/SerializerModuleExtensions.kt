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
# Module kserializers-jvm

## About this module

This module contains implementations of `KSerializer` for classes in `java.time` and `java.util.UUID`.

This module also contains the following `typealias` 
that use `@Serializable` annotation with those implementations of `KSerializer` :

- `InstantAsString`
- `LocalDateAsString`
- `LocalDateTimeAsString`
- `LocalTimeAsString`
- `MonthDayAsString`
- `OffsetDateTimeAsString`
- `OffsetTimeAsString`
- `YearAsString`
- `YearMonthAsString`
- `ZonedDateTimeAsString`
- `InstantAsUnixTime`
- `ZoneOffsetAsString`
- `ZoneIdAsString`
- `DurationAsString`
- `PeriodAsString`
- `UuidAsString`

For non-kotlin users, consider using `SerializerModuleExtensions.addSerializersForJvm`, or other manual methods.


# Package com.qualifiedcactus.kSerializersJvm.time

This packages contains `KSerializer` for classes in `java.time`.

# Package com.qualifiedcactus.kSerializersJvm.util

This packages contains `KSerializer` for classes in `java.util`.


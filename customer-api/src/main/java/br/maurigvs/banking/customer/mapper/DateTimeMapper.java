package br.maurigvs.banking.customer.mapper;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public final class DateTimeMapper {

    public static LocalDate toLocalDate(Timestamp timestamp){
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Timestamp toTimestamp(LocalDate localDate) {
        final var instant = localDate.atStartOfDay(ZoneId.systemDefault());
        return Timestamp.newBuilder()
                .setSeconds(instant.toEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}

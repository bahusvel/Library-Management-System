package util;

import java.time.*;
import java.util.Date;

/**
 * Created by denislavrov on 9/22/14.
 */
public class DateLocalDate {
    public static LocalDateTime date2LocalDateTime(Date input){
        Instant instant = input.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDate date2LocalDate(Date input){
        return date2LocalDateTime(input).toLocalDate();
    }

    public static LocalTime date2LocalTime(Date input){
        return date2LocalDateTime(input).toLocalTime();
    }

    public static Date localDateTime2Date(LocalDateTime input){
        Instant instant = input.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date localDate2Date(LocalDate input){
        Instant instant = input.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date localTime2Date(LocalTime input, final int A_YEAR, final int A_MONTH, final int A_DAY){
        Instant instant = input.atDate(LocalDate.of(A_YEAR, A_MONTH, A_DAY)).
                atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static void main(String[] args) {
        System.out.println(localDateTime2Date(date2LocalDateTime(new Date())));
    }

}

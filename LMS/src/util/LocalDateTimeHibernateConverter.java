package util;

import javax.persistence.AttributeConverter;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by denislavrov on 9/22/14.
 */
public class LocalDateTimeHibernateConverter implements AttributeConverter<LocalDateTime, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date date) {
        return null;
    }
}

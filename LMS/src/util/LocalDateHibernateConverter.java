package util;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by denislavrov on 9/22/14.
 */
public class LocalDateHibernateConverter implements AttributeConverter<LocalDate, Date>{
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return null;
    }
}

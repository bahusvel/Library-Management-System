package persistance;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * Created by denislavrov on 9/12/14.
 */

@Data
@Embeddable
public class Address {
    private String country;
    private String city;
    private String postalCode;
    private String address1;
    private String address2;
    private String address3;
}

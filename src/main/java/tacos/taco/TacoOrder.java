package tacos.taco;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.CreditCardNumber;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
public class TacoOrder implements Serializable{

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Date placedAt=new Date();

    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    @Size(max=2, message="Enter state code of maximum 2 characters")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Taco> tacos=new ArrayList<>();

    public void addTaco(Taco taco){
        tacos.add(taco);
    }

}

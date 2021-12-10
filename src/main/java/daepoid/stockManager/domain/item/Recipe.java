package daepoid.stockManager.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Recipe {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer price;

    private Double unitPrice;

    private Double weight;

}

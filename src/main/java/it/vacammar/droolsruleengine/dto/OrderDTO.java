package it.vacammar.droolsruleengine.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDTO implements Serializable {
    private long id;
    private String description;
    private int amount;
    private BigDecimal price;
}

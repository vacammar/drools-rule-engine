package it.vacammar.droolsruleengine.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RuleResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
}

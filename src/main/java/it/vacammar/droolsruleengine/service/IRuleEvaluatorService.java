package it.vacammar.droolsruleengine.service;

import it.vacammar.droolsruleengine.dto.RuleResultDTO;
import it.vacammar.droolsruleengine.entity.CustomRule;

public interface IRuleEvaluatorService {

    <D> RuleResultDTO evaluate(CustomRule rule, D data);
}

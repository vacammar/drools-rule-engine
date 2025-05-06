package it.vacammar.droolsruleengine.service;

import it.vacammar.droolsruleengine.dto.OrderDTO;
import it.vacammar.droolsruleengine.dto.RuleResultDTO;

public interface IOrderService {

    RuleResultDTO create(OrderDTO order);
}

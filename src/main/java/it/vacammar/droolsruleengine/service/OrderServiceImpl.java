package it.vacammar.droolsruleengine.service;

import it.vacammar.droolsruleengine.dto.OrderDTO;
import it.vacammar.droolsruleengine.dto.RuleResultDTO;
import it.vacammar.droolsruleengine.repository.IRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {
    private static final int CREATE_ORDER_RULE = 1;
    private final IRuleEvaluatorService ruleEvaluatorService;
    private final IRuleRepository ruleRepository;

    @Autowired
    public OrderServiceImpl(IRuleEvaluatorService ruleEvaluatorService, IRuleRepository ruleRepository) {
        this.ruleEvaluatorService = ruleEvaluatorService;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public RuleResultDTO create(OrderDTO order) {
        var customRule = this.ruleRepository.findById(CREATE_ORDER_RULE)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        return this.ruleEvaluatorService.evaluate(customRule, order);
    }
}

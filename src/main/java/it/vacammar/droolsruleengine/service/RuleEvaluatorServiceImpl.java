package it.vacammar.droolsruleengine.service;

import it.vacammar.droolsruleengine.dto.RuleResultDTO;
import it.vacammar.droolsruleengine.entity.CustomRule;
import org.drools.kiesession.rulebase.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RuleEvaluatorServiceImpl implements IRuleEvaluatorService {
    public static final String RULE_RESULT_KEY = "result";

    @Override
    public <D> RuleResultDTO evaluate(CustomRule rule, D data) {
        KieSession kieSession = null;
        try {
            var knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            knowledgeBuilder.add(ResourceFactory.newByteArrayResource(rule.getRuleFile()), ResourceType.DRL);

            var errors = knowledgeBuilder.getErrors();
            if (errors != null && !errors.isEmpty()) {
                var errorMsg = errors.stream().map(KnowledgeBuilderError::getMessage).collect(Collectors.joining());
                throw new RuntimeException(errorMsg);
            }

            var knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
            knowledgeBase.addPackages(knowledgeBuilder.getKnowledgePackages());
            kieSession = knowledgeBase.newKieSession();

            var ruleResult = new RuleResultDTO();
            kieSession.setGlobal(RULE_RESULT_KEY, ruleResult);
            kieSession.insert(data);
            kieSession.fireAllRules();
            return ruleResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (kieSession != null) {
                kieSession.dispose();
            }
        }
    }
}

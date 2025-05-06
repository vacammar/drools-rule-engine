package it.vacammar.droolsruleengine.api;

import it.vacammar.droolsruleengine.dto.OrderDTO;
import it.vacammar.droolsruleengine.dto.RuleResultDTO;
import it.vacammar.droolsruleengine.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<RuleResultDTO> create(@RequestBody OrderDTO order) {
        return ResponseEntity.ok(this.orderService.create(order));
    }
}

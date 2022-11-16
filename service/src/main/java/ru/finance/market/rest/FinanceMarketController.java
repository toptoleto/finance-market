package ru.finance.market.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.finance.market.dto.Client;
import ru.finance.market.service.FinanceMarketService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/finance")
@AllArgsConstructor
public class FinanceMarketController {

    private FinanceMarketService service;

    @GetMapping("/")
    public String check() {
        return "ok";
    }

    @PostMapping(value = "/user/add", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public void createClient(@RequestBody Client client) {
        service.createClient(client);
    }

    @GetMapping("/user/get")
    public List<Client> getClients() {
        return service.getClients();
    }
}
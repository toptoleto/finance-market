package ru.finance.market.app;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication(scanBasePackages = {"ru.finance.market"})
public class FinanceMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceMarketApplication.class, args);
    }

}
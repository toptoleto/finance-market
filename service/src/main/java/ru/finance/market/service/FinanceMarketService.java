package ru.finance.market.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.finance.market.dao.FinanceMarketDao;
import ru.finance.market.dto.Client;

import java.util.List;

@Service
@AllArgsConstructor
public class FinanceMarketService {

    private final FinanceMarketDao financeMarketDao;

    public void createClient(Client client) {
        financeMarketDao.createClient(client);
    }

    public List<Client> getClients() {
        return financeMarketDao.getClients();
    }
}

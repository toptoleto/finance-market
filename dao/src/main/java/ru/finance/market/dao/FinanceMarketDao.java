package ru.finance.market.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.finance.market.dto.Client;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Transactional
@AllArgsConstructor
public class FinanceMarketDao {

    private final EntityManager entityManager;

    public int createClient(Client client) {
        return entityManager.unwrap(Session.class)
                .createNativeQuery("insert into client (id, surname, first_name, middle_name) " +
                        "values (:id, :surname, :first_name, :middle_name)")
                .setParameter("id", client.getId())
                .setParameter("surname", client.getSurname())
                .setParameter("first_name", client.getFirstName())
                .setParameter("middle_name", client.getMiddleName())
                .executeUpdate();
    }

    public List<Client> getClients() {
        return entityManager.unwrap(Session.class)
                .createNativeQuery("select * from client", Tuple.class)
                .getResultStream()
                .map(s -> Client.builder()
                        .id(s.get("id", Integer.class))
                        .surname(s.get("surname", String.class))
                        .firstName(s.get("first_name", String.class))
                        .middleName(s.get("middle_name", String.class))
                        .build())
                .collect(toList());
    }
}

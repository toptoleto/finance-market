package ru.finance.market.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Client {
    Integer id;
    String surname;
    String firstName;
    String middleName;
}

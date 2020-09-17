package ru.db.touristAgency.utils;

import lombok.Data;

@Data
public class FindRequestParams {
    public enum Order {
        ASCENDING,
        DESCENDING
    }

    Integer page;

    Integer pageSize;

    Order order;

    String orderBy;
}

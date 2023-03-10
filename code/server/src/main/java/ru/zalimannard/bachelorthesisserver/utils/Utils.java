package ru.zalimannard.bachelorthesisserver.utils;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public abstract class Utils {

    public static List<Sort.Order> ordersByStringArray(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] sorts = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(sorts[1]), sorts[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }
        return orders;
    }

    private static Sort.Direction getSortDirection(String sort) {
        return sort.contains("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
    }

}

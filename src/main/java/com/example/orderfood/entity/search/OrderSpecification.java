package com.example.orderfood.entity.search;

import com.example.orderfood.entity.Food;
import com.example.orderfood.entity.Order;
import com.example.orderfood.entity.OrderDetail;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class OrderSpecification implements Specification<Order> {

    private final SearchCriteria searchCriteria;

    public OrderSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (searchCriteria.getOperator()) {
            case EQUALS:
                return criteriaBuilder.equal(
                        root.get(searchCriteria.getKey()),
                        searchCriteria.getValue());
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(
                        root.get(searchCriteria.getKey()),
                        String.valueOf(searchCriteria.getValue()));
            case GREATER_THAN_OR_EQUALS:
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(searchCriteria.getKey()),
                        String.valueOf(searchCriteria.getValue()));
            case LESS_THAN:
                return criteriaBuilder.lessThan(
                        root.get(searchCriteria.getKey()),
                        String.valueOf(searchCriteria.getValue()));
            case LESS_THAN_OR_EQUALS:
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(searchCriteria.getKey()),
                        String.valueOf(searchCriteria.getValue()));
            case JOIN:
                Join<OrderDetail, Food> orderDetailProductJoin = root.join("orderDetails").join("product");
                Predicate predicate = criteriaBuilder.or(
                        criteriaBuilder.like(root.get("id"), "%" + searchCriteria.getValue() + "%"),
                        criteriaBuilder.like(orderDetailProductJoin.get("name"), "%" + searchCriteria.getValue() + "%")
                );
                return predicate;
        }
        return null;
    }
    public static void main(String[] args) {
        Specification<Order> specification = Specification.where(null);

        SearchCriteria searchCriteria1 = new SearchCriteria(
                "orderStatus",
                SearchCriteriaOperator.EQUALS,
                1);
        OrderSpecification spec1 = new OrderSpecification(searchCriteria1);
        specification = specification.and(spec1);

        SearchCriteria searchCriteria2 = new SearchCriteria(
                "fullName",
                SearchCriteriaOperator.EQUALS,
                "Hoang");
        OrderSpecification spec2 = new OrderSpecification(searchCriteria2);
        specification = specification.and(spec2);


    }
}

package com.jayway.jsonpath;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Filter implements Predicate {

    private List<Predicate> criteriaList = new ArrayList<Predicate>();

    private Filter(Predicate criteria) {
        this.criteriaList.add(criteria);
    }

    private Filter(List<Predicate> criteriaList) {
        this.criteriaList = criteriaList;
    }

    /**
     * Creates a new Filter based on given criteria
     * @param criteria criteria
     * @return a new Filter
     */
    public static Filter filter(Predicate criteria) {
        return new Filter(criteria);
    }

    /**
     * Create a new Filter based on given list of criteria.
     * @param criteriaList list of criteria
     * @return
     */
    public static Filter filter(List<Predicate> criteriaList) {
        return new Filter(criteriaList);
    }

    @Override
    public boolean apply(PredicateContext ctx) {
        for (Predicate criteria : criteriaList) {
            if (!criteria.apply(ctx)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Predicate crit : criteriaList) {
            sb.append(crit.toString());
        }
        return sb.toString();
    }
}

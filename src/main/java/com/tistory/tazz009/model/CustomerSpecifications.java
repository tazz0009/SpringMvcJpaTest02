package com.tistory.tazz009.model;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecifications {

	public static Specification<Customer> accountExpiresBefore(final LocalDate date) {

		return new Specification<Customer>() {

			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Root<Account> accounts = query.from(Account.class);
				Path<Date> expiryDate = accounts.<Date> get("expiryDate");
				Predicate customerIsAccountOwner = cb.equal(accounts.<Customer> get("customer"), root);
				Predicate accountExpiryDateBefore = cb.lessThan(expiryDate, date.toDateMidnight().toDate());

				return cb.and(customerIsAccountOwner, accountExpiryDateBefore);
			}

		};
	}
	
}

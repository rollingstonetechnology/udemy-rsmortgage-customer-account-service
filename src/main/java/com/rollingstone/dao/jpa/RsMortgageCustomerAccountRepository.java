package com.rollingstone.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.domain.Account;
import com.rollingstone.domain.Customer;
import com.rollingstone.domain.Employment;



public interface RsMortgageCustomerAccountRepository extends PagingAndSortingRepository<Account, Long> {
    List<Account> findCustomerAccountsByCustomer(Customer customer);

    Page findAll(Pageable pageable);
}

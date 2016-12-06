package com.rollingstone.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rollingstone.dao.jpa.RsMortgageCustomerAccountRepository;
import com.rollingstone.domain.Account;
import com.rollingstone.domain.Customer;


/*
 * Service class to do CRUD for Customer Account through JPS Repository
 */
@Service
public class RsMortgageCustomerAccountService {

    private static final Logger log = LoggerFactory.getLogger(RsMortgageCustomerAccountService.class);

    @Autowired
    private RsMortgageCustomerAccountRepository customerAccountRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;
    
    @Autowired
  	private CustomerClient customerClient;

    public RsMortgageCustomerAccountService() {
    }

    public Account createAccount(Account account) throws Exception {
    	Account createdAccount = null;
    	if (account != null && account.getCustomer() != null){
    		
    		log.info("In service account create"+ account.getCustomer().getId());
    		if (customerClient == null){
        		log.info("In customerClient null got customer");
    		}
    		else {
    			log.info("In customerClient not null got customer");
    		}
    		
    		Customer customer = customerClient.getCustomer((new Long(account.getCustomer().getId())));
    		
    		if (customer != null){
    			createdAccount  = customerAccountRepository.save(account);
    		}else {
    			log.info("Invalid Customer");
    			throw new Exception("Invalid Customer");
    		}
    	}
    	else {
    			throw new Exception("Invalid Customer");
    	}
        return createdAccount;
    }

    public Account getAccount(long id) {
        return customerAccountRepository.findOne(id);
    }

    public void updateAccount(Account account) throws Exception {
    	Account createdAccount = null;
    	if (account != null && account.getCustomer() != null){
    		
    		log.info("In service account create"+ account.getCustomer().getId());
    		if (customerClient == null){
        		log.info("In customerClient null got customer");
    		}
    		else {
    			log.info("In customerClient not null got customer");
    		}
    		
    		Customer customer = customerClient.getCustomer((new Long(account.getCustomer().getId())));
    		
    		if (customer != null){
    			createdAccount  = customerAccountRepository.save(account);
    		}else {
    			log.info("Invalid Customer");
    			throw new Exception("Invalid Customer");
    		}
    	}
    	else {
    			throw new Exception("Invalid Customer");
    	}
    }

    public void deleteAccount(Long id) {
    	customerAccountRepository.delete(id);
    }

    public Page<Account> getAllAccountsByPage(Integer page, Integer size) {
        Page pageOfAccounts = customerAccountRepository.findAll(new PageRequest(page, size));
        
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("com.rollingstone.getAll.largePayload");
        }
        return pageOfAccounts;
    }
    
    public List<Account> getAllAccounts() {
        Iterable<Account> pageOfAccounts = customerAccountRepository.findAll();
        
        List<Account> customerAccounts = new ArrayList<Account>();
        
        for (Account account : pageOfAccounts){
        	customerAccounts.add(account);
        }
    	log.info("In Real Service getAllAccounts  size :"+customerAccounts.size());

    	
        return customerAccounts;
    }
    
    public List<Account> getAllAccountsForCustomer(Customer customer) {
        Iterable<Account> pageOfAccounts = customerAccountRepository.findCustomerAccountsByCustomer(customer);
        
        List<Account> customerAccounts = new ArrayList<Account>();
        
        for (Account account : pageOfAccounts){
        	customerAccounts.add(account);
        }
    	log.info("In Real Service getAllAccounts  size :"+customerAccounts.size());

    	
        return customerAccounts;
    }
}

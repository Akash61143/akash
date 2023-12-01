package com.csi.service;

import com.csi.model.Customer;
import com.csi.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceClass {
    @Autowired
    CustomerRepo customerRepo;

    public Customer save(Customer customer)
    {
        return customerRepo.save(customer);
    }

    public List<Customer> findAll()
    {
        return customerRepo.findAll();
    }

    public Optional<Customer> findById(int custId)
    {
        return customerRepo.findById(custId);
    }

    public List<Customer> findByAnyInput(String anyInput)
    {
        return  findAll().stream().filter(x->x.getCustEmailId().equals(anyInput)
        || String.valueOf(x.getCustContact()).equals(anyInput)
        ||String.valueOf(x.getCustAmount()).equals(anyInput)
        ||x.getBillDescription().equals(anyInput)
        ||String.valueOf(x.getCustId()).equals(anyInput)
        ||x.getCustGST().equals(anyInput)).toList();

    }
    public List<Customer> findByCustomerName(String custName)
    {
        return findAll().stream().filter(x->x.getCustName().equals(custName)).toList();
    }

    public List<Customer> sortById()
    {
        return findAll().stream().sorted(Comparator.comparingInt(Customer::getCustId)).toList();
    }

    public Customer findByGstNumber(String custGST)
    {
        return (Customer) customerRepo.findAll().stream().filter(x->x.getCustGST().equals(custGST));
    }

    public Customer updateById(Customer customer)
    {
       return customerRepo.save(customer);
    }

    public void deleteById(int custId)
    {
        customerRepo.deleteById(custId);
    }
}

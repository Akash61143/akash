package com.csi.controller;

import com.csi.exception.RecordNotFound;
import com.csi.model.Customer;
import com.csi.service.ServiceClass;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    ServiceClass serviceClass;

    @PostMapping("/save")
    public ResponseEntity<String> saveData(@RequestBody Customer customer)
    {
        serviceClass.save(customer);
        return ResponseEntity.ok("DATA SAVED SUCCESSFULLY");
    }

    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable int custId)
    {
        return ResponseEntity.ok(serviceClass.findById(custId));
    }

    @GetMapping("/findbyname/{custName}")
    public ResponseEntity<List<Customer>> findByName(@PathVariable String custName)
    {
        return ResponseEntity.ok(serviceClass.findByCustomerName(custName));
    }

    @PostMapping("/updatebyid/{custId}")
    public ResponseEntity<Customer> updateById(@PathVariable int custId,@Valid @RequestBody Customer customer)
    {
        Customer customer1=serviceClass.findById(custId).orElseThrow(()->new RecordNotFound("ID NOT FOUND"));
        customer1.setCustName(customer.getCustName());
        customer1.setCustContact(customer.getCustContact());
        customer1.setBillDescription(customer.getBillDescription());
        customer1.setCustDate(customer.getCustDate());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustGST(customer.getCustGST());
        customer1.setCustAmount(customer.getCustAmount());
        return new ResponseEntity<>(serviceClass.updateById(customer1), HttpStatus.CREATED);
    }

    @GetMapping("/findbyanyinput/{anyInput}")
    public ResponseEntity<List<Customer>> findByAnyInput(@PathVariable String anyInput)
    {
        return ResponseEntity.ok(serviceClass.findByAnyInput(anyInput));
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Customer>> sortById()
    {
        return ResponseEntity.ok(serviceClass.sortById());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer>> sortByName()
    {
        return ResponseEntity.ok(serviceClass.findAll().stream().sorted(Comparator.comparing(Customer::getCustName)).toList());
    }

    @GetMapping("/findbygstnumber/{custGST}")
    public ResponseEntity<Customer> findByGSTNumber(@PathVariable String custGST)
    {
        return ResponseEntity.ok(serviceClass.findByGstNumber(custGST));
    }

    @GetMapping("/sortbyamount")
    public ResponseEntity<List<Customer>> sortByAmount()
    {
        return ResponseEntity.ok(serviceClass.findAll().stream().sorted(Comparator.comparingDouble(Customer::getCustAmount)).toList());
    }
}

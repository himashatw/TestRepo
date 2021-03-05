package com.example.demo10jpa.controllers;

import com.example.demo10jpa.dao.CustomerRepo;
import com.example.demo10jpa.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    CustomerRepo customerRepo;

    @RequestMapping("home")
    @ResponseBody
    public String homeMethod(Customer customer) {
        customerRepo.save(customer);
        return customer.toString();
    }

    @RequestMapping("findCustomer")
    @ResponseBody
    public String getCustomer(@RequestParam("customerID") int id) {
        Customer customer = customerRepo.findById(id).orElse(null);
        return customer.toString();
    }

    @RequestMapping("getCustomerDetails")
    @ResponseBody
    public String getCustomerByName(@RequestParam("customerName") String name){
        List<Customer> customerList = customerRepo.findByName(name);
        return customerList.toString();
    }


    @RequestMapping("customerGreaterThan")
    @ResponseBody
    public String getCustomersGreaterThan(int id){
        List<Customer> customerList = customerRepo.findByIdGreaterThan(id);
        return customerList.toString();
    }


    @RequestMapping("getGraterThanName")
    @ResponseBody
    public String getGraterThanName(int id, String name){
        List<Customer> customerList = customerRepo.findByIdGreaterThanAndAndName(id,name);
        return customerList.toString();
    }

    @RequestMapping("getNameOrderBy")
    @ResponseBody
    public String getNameOrderBy(@RequestParam("CustomerName") String name){
        List <Customer> customerList = customerRepo.findByNameSortedById(name);
        return customerList.toString();
    }



    //REST methods



    @GetMapping(path="Customers")
    public List<Customer> getCustomers(){
        return customerRepo.findAll();
    }

    @RequestMapping("Customers/{id}")
    public Optional<Customer> getThisCustomer(@PathVariable("id") int id){
        return customerRepo.findById(id);
    }


    @PostMapping ("Customers")
    public Customer PostCustomer(@RequestBody Customer customer){
        return customerRepo.save(customer);
    }

    @DeleteMapping("Customers/{id}")
    public String DeleteCustomer(@PathVariable("id") int id){
        Customer customer = customerRepo.getOne(id);
        customerRepo.delete(customer);
        return "Deleted : "+id;
    }

    @PutMapping("Customers")
    public Customer PutCustomer(@RequestBody Customer customer){
        return customerRepo.save(customer);
    }


}

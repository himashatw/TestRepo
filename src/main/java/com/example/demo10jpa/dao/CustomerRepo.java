package com.example.demo10jpa.dao;



import com.example.demo10jpa.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    public List<Customer> findByName(String name);

    public List<Customer> findByIdGreaterThan(int id);

    public List<Customer> findByIdGreaterThanAndAndName(int id, String name);

    @Query("from Customer where name=?1 order by id")
    public List<Customer> findByNameSortedById(String name);
}

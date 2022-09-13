package com.volvo.thiagoteixeira.dao;

import com.volvo.thiagoteixeira.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<CustomerModel, String> {

    @Query("SELECT c FROM CustomerModel AS c JOIN c.addresses a WHERE a.zipCode = :zipCode")
    List<CustomerModel> findCustomersByZipCode(String zipCode);
}

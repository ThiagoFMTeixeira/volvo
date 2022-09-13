package com.volvo.thiagoteixeira.dao;

import com.volvo.thiagoteixeira.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerModel, String> {

}

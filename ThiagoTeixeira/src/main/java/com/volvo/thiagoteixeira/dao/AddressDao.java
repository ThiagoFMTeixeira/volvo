package com.volvo.thiagoteixeira.dao;

import com.volvo.thiagoteixeira.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<AddressModel, String> {

}

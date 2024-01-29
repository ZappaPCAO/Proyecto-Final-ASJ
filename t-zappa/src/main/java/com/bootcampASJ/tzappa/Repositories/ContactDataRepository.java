package com.bootcampASJ.tzappa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.ContactData;

public interface ContactDataRepository extends JpaRepository<ContactData, Integer> {

}

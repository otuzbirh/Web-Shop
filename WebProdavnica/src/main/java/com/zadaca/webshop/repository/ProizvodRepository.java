package com.zadaca.webshop.repository;

import com.zadaca.webshop.model.Proizvod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod,Integer> {


}

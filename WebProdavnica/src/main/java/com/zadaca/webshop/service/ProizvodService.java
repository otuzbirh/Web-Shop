package com.zadaca.webshop.service;

import com.zadaca.webshop.dto.ProizvodDto;
import com.zadaca.webshop.model.Proizvod;

import java.util.List;

public interface ProizvodService {
 List<Proizvod> getAllProizvod();
 void saveProizvod(ProizvodDto proizvodDto);
 ProizvodDto getProizvodById(int id);
 Integer getMaxId();
 void editProizvod(ProizvodDto proizvodDto);

}


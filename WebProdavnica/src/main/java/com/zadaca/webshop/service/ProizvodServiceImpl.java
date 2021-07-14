package com.zadaca.webshop.service;

import com.zadaca.webshop.dto.ProizvodDto;
import com.zadaca.webshop.model.Proizvod;
import com.zadaca.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Access;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProizvodServiceImpl implements ProizvodService{
    @Autowired
    private ProizvodRepository proizvodRepository;

    @Override
    public List<Proizvod> getAllProizvod() {
        return proizvodRepository.findAll();
    }

    @Override
    public void saveProizvod(ProizvodDto proizvodDto) {
        Proizvod proizvod=new Proizvod();
        proizvod.setNaziv(proizvodDto.getNaziv());
        proizvod.setCijena(proizvodDto.getCijena());
        proizvod.setMarka(proizvodDto.getMarka());
        proizvod.setKolicina(proizvodDto.getKolicina());
        proizvod.setOpis(proizvodDto.getOpis());
        proizvod.setSlika(proizvodDto.getSlika());
        proizvodRepository.save(proizvod);
    }
    @Override
    public void editProizvod(ProizvodDto proizvodDto) {
        Proizvod proizvod=new Proizvod();
        proizvod.setId(proizvodDto.getId());
        proizvod.setNaziv(proizvodDto.getNaziv());
        proizvod.setCijena(proizvodDto.getCijena());
        proizvod.setMarka(proizvodDto.getMarka());
        proizvod.setKolicina(proizvodDto.getKolicina());
        proizvod.setOpis(proizvodDto.getOpis());
        proizvod.setSlika(proizvodDto.getSlika());
        proizvodRepository.save(proizvod);
    }
    @Override
    public Integer getMaxId() {
        List<Proizvod> p=proizvodRepository.findAll();
        if(p.size()==0){
            return 0;
        }
        List<Integer> ids=new ArrayList<>();
        for(int i=0; i<p.size(); ++i){
            ids.add(p.get(i).getId());
        }
        return Collections.max(ids);
    }

    @Override
    public ProizvodDto getProizvodById(int id) {
        Optional<Proizvod> optional=proizvodRepository.findById(id);
        Proizvod proizvod;
   ProizvodDto p;
        if(optional.isPresent()){
            proizvod=optional.get();
           p=new ProizvodDto();
            p.setCijena(proizvod.getCijena());
            p.setNaziv(proizvod.getNaziv());
            p.setKolicina(proizvod.getKolicina());
            p.setMarka(proizvod.getMarka());
            p.setId(proizvod.getId());
            p.setOpis(proizvod.getOpis());

            p.setSlika(proizvod.getSlika());
        }else {
            proizvod=null;
          p=null;
        }
        return p;
    }
}

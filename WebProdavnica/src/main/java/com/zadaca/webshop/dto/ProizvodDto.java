package com.zadaca.webshop.dto;



import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProizvodDto {
    private int id;
    @NotNull
    @NotEmpty(message = "Potrebno je da unesete naziv")
    private String naziv;
    @NotEmpty(message = "Potrebno je da unesete opis")
    private String opis;
    @NotNull
    @Min(value = 500,message = "Cijana minimalno 500")
    private float cijena;
    @NotNull
    @Min(value = 1,message = "Morate unijeti bar 1 na stanje")
    private int kolicina;
    @NotEmpty(message = "Potrebno je da unesete marku proizvoda")
    private String marka;

    private String slika;

    private MultipartFile img;

    public ProizvodDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public float getCijena() {
        return cijena;
    }

    public void setCijena(float cijena) {
        this.cijena = cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}

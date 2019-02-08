package com.example.handbook.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "[0-9]{10}|[0-9]{12}]", message = "Должно быть 10 или 12 цифр")
    private String inn;

    @Pattern(regexp = "[0-9]{13}", message = "Должно быть 13 цифр")
    private String ogrn;

    @Size(min = 5, max = 10, message = "Должно быть не меньше 5 и не больше 10 символов")
    private String name;

    @Size(min = 5, max = 10, message = "Должно быть не меньше 5 и не больше 10 символов")
    private String adres;

    public Insurance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}

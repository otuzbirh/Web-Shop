package com.zadaca.webshop.dto;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

    @NotNull
    @NotEmpty(message = "Obavezan unos imena")
    private String firstName;
    @NotNull
    @NotEmpty(message = "Obavezan unos prezimena")
    private String lastName;
    @NotNull
    @NotEmpty(message = "Obavezan unos email")
    @Email
    private String email;
    @NotNull
    @NotEmpty(message = "Obavezan unos sifre")
    @Size(min = 5, max = 20, message = "Sifra mora biti duza od 5 i kraca od 20 karaktera")
    private String password;
    @NotEmpty(message = "Obavezan unos potvrde sifre")
    private String repeatPwd;



    public UserRegistrationDto() {
    }

    public boolean checkPwd(){
        if(this.password.equals(this.repeatPwd)){
            return true;
        }else{
            return false;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getRepeatPwd() {
        return repeatPwd;
    }

    public void setRepeatPwd(String repeatPwd) {
        this.repeatPwd = repeatPwd;
    }

}

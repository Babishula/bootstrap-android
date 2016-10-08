package com.ssg.android.demo.dto;

/**
 * Created by Max on 08/10/2016.
 */

public class People {
    public int id;
    public String login;
    public String password;
    public String email;
    public String addresse;
    public String city;
    public String codePostal;
    public String phoneNumber;
    public String poste;

    public People(int id, String login, String password, String email, String addresse, String city, String codePostal, String phoneNumber, String poste){
        this.id=id;
        this.login=login;
        this.password=password;
        this.email=email;
        this.addresse=addresse;
        this.city=city;
        this.codePostal=codePostal;
        this.phoneNumber=phoneNumber;
        this.poste=poste;
    }
}

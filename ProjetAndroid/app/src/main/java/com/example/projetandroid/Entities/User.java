package com.example.projetandroid.Entities;

import com.google.gson.annotations.SerializedName;

public class User {

    public int id ;
    @SerializedName("nom")
    public String nom;
    @SerializedName("prenom")
    public String prenom;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("numtel")
    public String numtel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", numtel='" + numtel + '\'' +
                '}';
    }

    public User(String nom, String prenom, String email, String password, String numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numtel = numtel;
    }

    public User() {

    }
}

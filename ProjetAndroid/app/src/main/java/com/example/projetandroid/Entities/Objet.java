package com.example.projetandroid.Entities;
import com.google.gson.annotations.SerializedName;

public class Objet {
    public int id ;
    @SerializedName("iduser")
    public int iduser;
    @SerializedName("objet")
    public String objet;
    @SerializedName("lieu")
    public String lieu;
    @SerializedName("description")
    public String description;
    @SerializedName("etat")
    public String etat;

    public int getId() {
        return id;
    }

    public int getIduser() {
        return iduser;
    }

    public String getObjet() {
        return objet;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Objet( int iduser, String objet, String lieu, String description, String etat) {

        this.iduser = iduser;
        this.objet = objet;
        this.lieu = lieu;
        this.description = description;
        this.etat = etat;
    }


    public Objet() {
    }

    @Override
    public String toString() {
        return "Objet{" +
                "id=" + id +
                ", iduser='" + iduser + '\'' +
                ", objet='" + objet + '\'' +
                ", lieu='" + lieu + '\'' +
                ", description='" + description + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }
}


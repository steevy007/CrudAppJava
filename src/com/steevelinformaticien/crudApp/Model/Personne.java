    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steevelinformaticien.crudApp.Model;

/**
 *
 * @author Sanon
 */
public class Personne {

    private int id;
    private String nom;
    private String Prenom;
    private String lieu_n;
    private String date_n;
    private String sexe;
    private String profession;
    private String desc;

    public Personne() {
    }

    public Personne(int id, String nom, String Prenom, String lieu_n, String date_n, String sexe, String profession, String desc) {
        this.id = id;
        this.nom = nom;
        this.Prenom = Prenom;
        this.lieu_n = lieu_n;
        this.date_n = date_n;
        this.sexe = sexe;
        this.profession = profession;
        this.desc = desc;
    }

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
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getLieu_n() {
        return lieu_n;
    }

    public void setLieu_n(String lieu_n) {
        this.lieu_n = lieu_n;
    }

    public String getDate_n() {
        return date_n;
    }

    public void setDate_n(String date_n) {
        this.date_n = date_n;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Personne{" + "nom=" + nom + ", Prenom=" + Prenom + ", lieu_n=" + lieu_n + ", date_n=" + date_n + ", sexe=" + sexe + ", profession=" + profession + ", desc=" + desc + '}';
    }

}

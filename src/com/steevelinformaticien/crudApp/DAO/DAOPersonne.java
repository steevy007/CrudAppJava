/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steevelinformaticien.crudApp.DAO;

import com.steevelinformaticien.crudApp.Model.Personne;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanon
 */
public class DAOPersonne implements DAOInterface<Personne> {

    private DAOConnexion conn=DAOConnexion.getInsDB() ;
    private PreparedStatement st;
    

    @Override
    public List<Personne> listPersonne() {
        List<Personne> p = new ArrayList<Personne>();
        try {
            st=conn.getConn().prepareStatement("SELECT * FROM personne ORDER BY nom");
            conn.setResult(st.executeQuery());
            while(conn.getResult().next()){
                p.add(new Personne(conn.getResult().getInt("id"),conn.getResult().getString("nom"),conn.getResult().getString("prenom"),conn.getResult().getString("lieu_n"),conn.getResult().getString("date_n"),conn.getResult().getString("sexe"),conn.getResult().getString("profession"),conn.getResult().getString("description")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public boolean Insert(Personne c) {
        boolean rep = false;
        try {
            Personne p = new Personne(0, c.getNom(), c.getPrenom(), c.getLieu_n(), c.getDate_n(), c.getSexe(), c.getProfession(), c.getDesc());
            st = conn.getConn().prepareStatement("INSERT INTO personne(nom,prenom,lieu_n,date_n,sexe,profession,description) VALUES(?,?,?,?,?,?,?)");
            st.setString(1, p.getNom());
            st.setString(2, p.getPrenom());
            st.setString(3, p.getLieu_n());
            st.setString(4, p.getDate_n());
            st.setString(5, p.getSexe());
            st.setString(6, p.getProfession());
            st.setString(7, p.getDesc());
            st.execute();
            rep = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }


    @Override
    public void delete(int id) {
        try{
            st=conn.getConn().prepareStatement("Delete from personne WHERE id=?");
            st.setInt(1,id);
            st.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Personne t,int id) {
        try{
            st=conn.getConn().prepareStatement("UPDATE personne set nom=?,prenom=?,lieu_n=?,date_n=?,sexe=?,profession=?,description=? WHERE id=?");
            st.setString(1, t.getNom());
            st.setString(2, t.getPrenom());
            st.setString(3, t.getLieu_n());
            st.setString(4, t.getDate_n());
            st.setString(5, t.getSexe());
            st.setString(6, t.getProfession());
            st.setString(7, t.getDesc());
            st.setInt(8, id);
            st.execute();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Personne getObjectData(int d) {
        Personne p=null;
        try{
            st=conn.getConn().prepareStatement("SELECT * FROM personne WHERE id=?");
            st.setInt(1,d);
            conn.setResult(st.executeQuery());
            while(conn.getResult().next()){
               p=new Personne(conn.getResult().getInt("ID"),conn.getResult().getString("nom"),conn.getResult().getString("prenom"),conn.getResult().getString("lieu_n"),conn.getResult().getString("date_n"),conn.getResult().getString("sexe"),conn.getResult().getString("profession"),conn.getResult().getString("description")); 
            }
        }catch(Exception e){
            
        }
        return p;
    }

}

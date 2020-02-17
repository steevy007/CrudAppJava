/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steevelinformaticien.crudApp.Controller;

import com.steevelinformaticien.crudApp.DAO.DAOPersonne;
import com.steevelinformaticien.crudApp.Model.Personne;
import com.steevelinformaticien.crudApp.View.MainFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sanon
 */
public class PersonneController {

    private MainFrame V;
    private Personne M;
    private DAOPersonne dao;
    DefaultTableModel model;

    public PersonneController(MainFrame V, Personne M, DAOPersonne dao) {
        this.V = V;
        this.M = M;
        this.dao = dao;
        init();
    }

    public void init() {
        this.V.getBt_nett().addActionListener(l -> nettoyer());
        this.V.getBt_ajout().addActionListener(l -> ajouter());
        this.V.getRefresh().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                update();
            }
        });
        this.V.getBt_supp().addActionListener(l -> delete());
        this.V.getTablePers().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                select();
            }
        });
        this.V.getBt_mod().addActionListener(l -> modifier());
        this.V.getSearchFilter().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                search();
            }
        });
        initTable();
        numberPers();     
    }

    public void nettoyer() {
        this.V.getJt_nom().setText("");
        this.V.getJt_lieu().setText("");
        this.V.getJt_prenom().setText("");
        this.V.getJt_proffesion().setText("");
        this.V.getArea_desc().setText("");
        this.V.getJt_sexe().setSelectedIndex(0);
        this.V.getJdate_date().setDate(null);
    }

    public void ajouter() {
        //recuperation de la date du jour
        if (V.getJt_nom().getText().isEmpty() || V.getJt_prenom().getText().isEmpty() || V.getJt_lieu().getText().isEmpty() || V.getJt_proffesion().getText().isEmpty() || V.getJdate_date().getDateFormatString().isEmpty() || V.getArea_desc().getText().isEmpty()) {
            JOptionPane.showMessageDialog(V, "Veuillez Remlir Correctement les Champs");
        } else if (V.getJdate_date().getDate().after(new Date())) {
            JOptionPane.showMessageDialog(V, "La date de Naissance doit etre inferieur a la date du jour");
        } else {
            M.setNom(V.getJt_nom().getText());
            M.setPrenom(V.getJt_prenom().getText());
            M.setLieu_n(V.getJt_lieu().getText());
            //conversion de la date en String
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            M.setDate_n(dateFormat.format(V.getJdate_date().getDate()));
            M.setSexe("" + V.getJt_sexe().getSelectedItem().toString());
            M.setProfession(V.getJt_proffesion().getText());
            M.setDesc(V.getArea_desc().getText());
            if (dao.Insert(M)) {
                JOptionPane.showMessageDialog(V, "Insertion reussi");
                update();
                numberPers(); 
                
            } else {
                JOptionPane.showMessageDialog(V, "Faute Systeme");
            }
        }

        //System.out.println(M.toString());
    }

    public void modifier() {
        if(!V.getTablePers().isRowSelected(V.getTablePers().getSelectedRow())){
            JOptionPane.showMessageDialog(V,"Veuillez d'abort selectionner le champ a modifier");
        }else{
            if (V.getJt_nom().getText().isEmpty() || V.getJt_prenom().getText().isEmpty() || V.getJt_lieu().getText().isEmpty() || V.getJt_proffesion().getText().isEmpty() || V.getJdate_date().getDateFormatString().isEmpty() || V.getArea_desc().getText().isEmpty()) {
            JOptionPane.showMessageDialog(V, "Veuillez Remlir Correctement les Champs");
        } else if (V.getJdate_date().getDate().after(new Date())) {
            JOptionPane.showMessageDialog(V, "La date de Naissance doit etre inferieur a la date du jour");
        } else{
        //convertir la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(V.getJdate_date().getDate());
        Personne p = new Personne(0, V.getJt_nom().getText(), V.getJt_prenom().getText(), V.getJt_lieu().getText(), date, V.getJt_sexe().getSelectedItem().toString(), V.getJt_proffesion().getText(), V.getArea_desc().getText());
        dao.edit(p, recupererId());
        JOptionPane.showMessageDialog(V, "Modification Reussi");
        update();
        
        }
        }
    }

    public void initTable() {
        model = new DefaultTableModel();
        String data[] = {"boum", "boum"};
        model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prenom", "Lieu de Naissance", "Date de Naissance", "Sexe", "Profession", "Description"});
        for (int i = 0; i < dao.listPersonne().size(); i++) {
            Object[] obj = {
                dao.listPersonne().get(i).getId(),
                dao.listPersonne().get(i).getNom(),
                dao.listPersonne().get(i).getPrenom(),
                dao.listPersonne().get(i).getLieu_n(),
                dao.listPersonne().get(i).getDate_n(),
                dao.listPersonne().get(i).getSexe(),
                dao.listPersonne().get(i).getProfession(),
                dao.listPersonne().get(i).getDesc()
            };

            model.addRow(obj);
        }
        V.getTablePers().setModel(model);
    }

    public void update() {
        model.setRowCount(0);
        initTable();
        nettoyer();
    }

    public int recupererId() {
        int id = 0;
        id = (int) V.getTablePers().getValueAt(V.getTablePers().getSelectedRow(), 0);

        return id;
    }

    public void delete() {
        //recuperation de l'identifiant selectionner
        try {
            dao.delete(recupererId());
            int rep = JOptionPane.showConfirmDialog(V, "Etes vous sur de vouloir supprimer cette personne");
            if (rep == 0) {
                JOptionPane.showMessageDialog(V, "Supression Reussi");
                update();
                numberPers(); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(V, "Veuillez Selectionner une ligne");
        }

    }

    public void select() {
        if (V.getTablePers().isRowSelected(V.getTablePers().getSelectedRow())) {
            Personne p = (Personne) dao.getObjectData(recupererId());
            V.getJt_nom().setText(p.getNom());
            V.getJt_prenom().setText(p.getPrenom());
            V.getJt_lieu().setText(p.getLieu_n());
            //conversion de la chaine en date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(p.getDate_n());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            V.getJdate_date().setDate(date);
            V.getJt_proffesion().setText(p.getProfession());
            V.getArea_desc().setText(p.getDesc());
            if (p.getSexe().equals("M")) {
                V.getJt_sexe().setSelectedIndex(0);
            } else {
                V.getJt_sexe().setSelectedIndex(1);
            }
        }
    }
    
    public void search(){
        model.setRowCount(0);
         for (int i = 0; i < dao.search(V.getSearchFilter().getText()).size(); i++) {
            Object[] obj = {
                dao.search(V.getSearchFilter().getText()).get(i).getId(),
                dao.search(V.getSearchFilter().getText()).get(i).getNom(),
                dao.search(V.getSearchFilter().getText()).get(i).getPrenom(),
                dao.search(V.getSearchFilter().getText()).get(i).getLieu_n(),
                dao.search(V.getSearchFilter().getText()).get(i).getDate_n(),
                dao.search(V.getSearchFilter().getText()).get(i).getSexe(),
                dao.search(V.getSearchFilter().getText()).get(i).getProfession(),
                dao.search(V.getSearchFilter().getText()).get(i).getDesc()
            };

            model.addRow(obj);
        }
        V.getTablePers().setModel(model);
        numberPers(); 
        
    }
    
    public void numberPers(){
        this.V.getNumberPers().setText(""+model.getRowCount());
    }

}

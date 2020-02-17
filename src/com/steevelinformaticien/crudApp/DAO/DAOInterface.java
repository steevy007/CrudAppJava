/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steevelinformaticien.crudApp.DAO;

import com.steevelinformaticien.crudApp.Model.Personne;
import java.util.List;

/**
 *
 * @author Sanon
 */
public interface DAOInterface<T> {

    public List<T> listPersonne();

    public boolean Insert(T t);

    public List<T> search(String search);

    public void delete(int id);
    
    public void edit(T t,int id);
    
    public T getObjectData(int d);
}

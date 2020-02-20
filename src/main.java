
import com.steevelinformaticien.crudApp.Controller.PersonneController;
import com.steevelinformaticien.crudApp.DAO.DAOPersonne;
import com.steevelinformaticien.crudApp.Model.Personne;
import com.steevelinformaticien.crudApp.View.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanon
 */
public class main {
    public static void main(String[] args) {
        /*
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        try {
            UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainFrame F=new MainFrame();
        Personne P=new Personne();
        DAOPersonne dao=new DAOPersonne();
        new PersonneController(F,P,dao);
        F.setVisible(true);
    }
}

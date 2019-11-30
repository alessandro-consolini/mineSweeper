/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campominatogithub;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Alessandro Consolini
 * 
 * Il gioco sarà solo di una difficoltà
 * Intermedio: campo 16 x 16 con 40 mine
 */

class FinestraPrincipale extends JFrame{
    private GamePanel campo;
    private JButton restart;
    private JButton difficile;
    private JPanel p;           //pannello in cui inserisci il campo
    //aggiungi il menu a tendina
    
    public FinestraPrincipale(){
        
        campo = new GamePanel();
        restart = new JButton(":)");
        
        restart.addActionListener(new restartListener(campo));
        
        this.setLayout(new BorderLayout(1,1));
        this.add(restart,BorderLayout.NORTH);
        this.add(campo,BorderLayout.CENTER);
        this.setSize(new Dimension(600,600));
    }
    
    public void aumentaDifficolta( ) {
        p.remove(campo);
        campo = new GamePanel();//con le dimensioni e bombe
        p.add(campo);
        p.invalidate();
        p.repaint();
    }
}


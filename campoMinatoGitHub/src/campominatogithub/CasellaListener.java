/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campominatogithub;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * @author Alessandro Consolini
 */
public class CasellaListener implements ActionListener {
    private JButton[][] field;
    private int[][] checkField;
    private int[][] checked;
    private int i;
    private int j;
    private GamePanel p;
    
    public CasellaListener(JButton[][] field, int[][] checkField, int[][]checked,int i, int j, GamePanel panel){
        this.field = field;
        this.checkField=checkField;
        this.checked = checked;
        this.i=i;
        this.j=j;
        p=panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(checkField[i][j]==9){
            field[i][j].setText("*");
            /*Faccio in modo che sembri un JLabel una volta scelto*/
            field[i][j].setFocusPainted(false);
            field[i][j].setMargin(new Insets(0, 0, 0, 0));
            field[i][j].setBorderPainted(false);
            field[i][j].setEnabled(false);
            field[i][j].setBackground(Color.yellow);
            p.showAllBombs();
            JOptionPane.showMessageDialog(null,"Hai Perso", "BOOM",JOptionPane.WARNING_MESSAGE);
            p.restart();
        }else if(checkField[i][j]==0){
            p.chainReaction(i, j);
            //controllo se l'utente ha vinto
            p.checkWin();
        }else{
            field[i][j].setText(""+checkField[i][j]);
            checked[i][j] = 1;
            /*Faccio in modo che sembri un JLabel una volta scelto*/
            field[i][j].setFocusPainted(false);
            field[i][j].setMargin(new Insets(0, 0, 0, 0));
            field[i][j].setContentAreaFilled(false);
            field[i][j].setBorderPainted(false);
            field[i][j].setOpaque(false);
            
            switch(checkField[i][j]){
                case 1: field[i][j].setForeground( Color.BLUE ); break;
                case 2: field[i][j].setForeground( new Color(41,148,44) ); break; //verde
                case 3: field[i][j].setForeground( Color.RED ); break;
                case 4: field[i][j].setForeground( new Color(10,0,80) ); break;   //blu scuro
                case 5: field[i][j].setForeground( new Color(116,21,1) ); break;  //rosso scuro                
                case 6: field[i][j].setForeground( Color.CYAN ); break;            
                case 7: field[i][j].setForeground( new Color(163,50,189) ); break; //viola
                case 8: field[i][j].setForeground( Color.GRAY ); break; 
            }
            //controllo se il giocatore ha vinto
            p.checkWin();
        }
    }
}

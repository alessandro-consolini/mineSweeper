/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campominatogithub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Alessandro Consolini
 * 
 * Intermedio: campo 16 x 16 con 40 bombe
 */
class GamePanel extends JPanel{
    private JButton[][] field;  //campo di bottoni
    private JLabel tempo;
    private int[][] checkField; //campo di interi in cui controllare se sono presenti le bombe
    private int[][] checked;    //matrice di caselle controllate
    private ActionListener[][] actionlisteners;
    
    /*queste no saranno costanti*/
    //andranno messe in input nel costruttore
    public static final int width = 16;
    public static final int height = 16;
    public static final int bombs = 40;
    /*----------------------------*/
    public GamePanel(){
        this.setField();
        tempo = new JLabel();
    }
    
    public void setTempo(int i ) {
        tempo.setText(i+"");
    }
    public int getTempo(){
        return Integer.parseInt(tempo.getText());
    }
    
    public void setField(){
        checkField = new int[width][height];
        field = new JButton[width][height];
        checked = new int[width][height];
        
        actionlisteners = new ActionListener[width][height];
        
        this.placeBombs();
        this.calculateBombs();
        this.createField();
        
        /*-----------stampa cheat-----------*/
        for(int i=0; i<height;i++){
            for(int j=0; j<width;j++){
                System.out.print(checkField[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        /*----------------------------------*/
    }
    
    private void placeBombs(){
        int cont = 0;
        Random rand = new Random();
        int b;      //random number to add a bomb(if 0 add bomb)
        
        //inizializzo a 0 la matrice di controllo
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                checked[i][j]=0;
            }
        }
        
        //aggiungo le bombe
        //le bombe sono contrassegnate con un 9 
        //è il primo valore che non potra mai essere raggiunto da una cella
        while(cont<bombs){
            for(int i=0; i<width && cont<bombs; i++){
                for(int j=0; j<height && cont<bombs; j++){
                    if(checkField[i][j] != 9){
                        b = rand.nextInt(6);
                        if(b==0){
                            checkField[i][j] = 9;
                            cont++;
                        }
                    }
                }
            }
        }
    }
    
    private void calculateBombs(){
        int cont;
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                cont=0;
                if(checkField[i][j]!=9){
                    //gli angoli
                    if(i==0 && j==0){
                        if(checkField[i][j+1] == 9){ cont++; }
                        if(checkField[i+1][j] == 9){ cont++; }
                        if(checkField[i+1][j+1]==9){ cont++; }
                    }else if(i==0 && j==width-1){
                        if(checkField[i][j-1] == 9){ cont++; }
                        if(checkField[i+1][j] == 9){ cont++; }
                        if(checkField[i+1][j-1]==9){ cont++; }
                    }else if(i==height-1 && j==width-1){
                        if(checkField[i-1][j] == 9){ cont++; }
                        if(checkField[i][j-1] == 9){ cont++; }
                        if(checkField[i-1][j-1]==9){ cont++; }
                    }else if(i==height-1 && j==0){
                        if(checkField[i][j+1] == 9){ cont++; }
                        if(checkField[i-1][j] == 9){ cont++; }
                        if(checkField[i-1][j+1]==9){ cont++; }
                    }//ai lati
                    else if(i==0 && j!=0){
                        if(checkField[i+1][j-1]==9){ cont++; }
                        if(checkField[i][j-1] == 9){ cont++; }
                        if(checkField[i][j+1] == 9){ cont++; }
                        if(checkField[i+1][j] == 9){ cont++; }
                        if(checkField[i+1][j+1]==9){ cont++; }
                    }else if(i!=0 && j==0){
                        if(checkField[i-1][j] == 9){ cont++; }
                        if(checkField[i-1][j+1]==9){ cont++; }
                        if(checkField[i][j+1] == 9){ cont++; }
                        if(checkField[i+1][j] == 9){ cont++; }
                        if(checkField[i+1][j+1]==9){ cont++; }
                    }else if(i!=0 && j==width-1){
                        if(checkField[i][j-1] == 9){ cont++; }
                        if(checkField[i+1][j] == 9){ cont++; }
                        if(checkField[i-1][j] == 9){ cont++; }
                        if(checkField[i+1][j-1]==9){ cont++; }
                        if(checkField[i-1][j-1]==9){ cont++; }
                    }else if(i==height-1 && j!=0){
                        if(checkField[i][j-1] == 9){ cont++; }
                        if(checkField[i-1][j] == 9){ cont++; }
                        if(checkField[i][j+1] == 9){ cont++; }
                        if(checkField[i-1][j-1]==9){ cont++; }
                        if(checkField[i-1][j+1]==9){ cont++; }
                    }else{
                        if(checkField[i-1][j] == 9){ cont++; }
                        if(checkField[i+1][j] == 9){ cont++; }
                        if(checkField[i][j-1] == 9){ cont++; }
                        if(checkField[i][j+1] == 9){ cont++; }
                        if(checkField[i-1][j-1]==9){ cont++; }
                        if(checkField[i+1][j+1]==9){ cont++; }
                        if(checkField[i-1][j+1]==9){ cont++; }
                        if(checkField[i+1][j-1]==9){ cont++; }
                    }
                    checkField[i][j]=cont;
                }
            }
        }
    }
    
    private void createField(){
        this.setLayout(new GridLayout(width,height));
        //istanzio le caselle(JButton)
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                field[i][j] = new JButton();
            }
        }
        //reset della matrice delle caselle controllate
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                checked[i][j]=0;
            }
        }
        
        /*-------------------------------------------*/
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                actionlisteners[i][j] = new CasellaListener(field,checkField,checked,i,j,this);
            }
        }
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                field[i][j].addActionListener(actionlisteners[i][j]);
                field[i][j].addMouseListener(new RightClickListener(field[i][j]));
            }
        }
        /*-------------------------------------------*/
        
        //le aggiungo al pannello 
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                this.add(field[i][j]);
            }
        }
        this.repaint();
    }
    
    public void chainReaction(int i, int j){
        if(checkField[i][j]==0 && checked[i][j]==0){
            field[i][j].setText(" ");
            checked[i][j] = 1;
            
            /*Faccio in modo che sembri un JLabel una volta scelto*/
            field[i][j].setFocusPainted(false);
            field[i][j].setMargin(new Insets(0, 0, 0, 0));
            field[i][j].setContentAreaFilled(false);
            field[i][j].setBorderPainted(false);
            field[i][j].setOpaque(false);
            
            //agli angoli
            if(i==0 && j==0){
                chainReaction(i,j+1);
                chainReaction(i+1,j);
                if(checkField[i+1][j+1]!=0)
                    chainReaction(i+1,j+1);
                
            }else if(i==0 && j==width-1){
                chainReaction(i,j-1);
                chainReaction(i+1,j);
                if(checkField[i+1][j-1]!=0)
                    chainReaction(i+1,j-1);
            }else if(i==height-1 && j==width-1){
                chainReaction(i-1,j);
                chainReaction(i,j-1);
                if(checkField[i-1][j-1]!=0)
                    chainReaction(i-1,j-1);
            }else if(i==height-1 && j==0){
                chainReaction(i,j+1);
                chainReaction(i-1,j);
                if(checkField[i-1][j+1]!=0)
                    chainReaction(i-1,j+1);
            }//ai lati
            else if(i==0 && j!=0){
                chainReaction(i,j-1);
                chainReaction(i,j+1);
                chainReaction(i+1,j);
                
                if(checkField[i+1][j+1]!=0)
                    chainReaction(i+1,j+1);
                if(checkField[i+1][j-1]!=0)
                    chainReaction(i+1,j-1);
                
            }else if(i!=0 && j==0){
                chainReaction(i-1,j);
                chainReaction(i,j+1);
                chainReaction(i+1,j);
                if(checkField[i+1][j+1]!=0)
                    chainReaction(i+1,j+1);
                if(checkField[i-1][j+1]!=0)
                    chainReaction(i-1,j+1);
                
            }else if(i!=0 && j==width-1){
                chainReaction(i,j-1);
                chainReaction(i+1,j);
                chainReaction(i-1,j);
                
                if(checkField[i+1][j-1]!=0)
                    chainReaction(i+1,j-1);
                if(checkField[i-1][j-1]!=0)
                    chainReaction(i-1,j-1);
            }else if(i==height-1 && j!=0){
                chainReaction(i,j-1);
                chainReaction(i-1,j);
                chainReaction(i,j+1);
                if(checkField[i-1][j-1]!=0)
                    chainReaction(i-1,j-1);
                if(checkField[i-1][j+1]!=0)
                    chainReaction(i-1,j+1);
            }else{
                chainReaction(i-1,j);
                chainReaction(i+1,j);
                chainReaction(i,j-1);
                chainReaction(i,j+1);
                if(checkField[i-1][j-1]!=0)
                    chainReaction(i-1,j-1);
                if(checkField[i+1][j+1]!=0)
                    chainReaction(i+1,j+1);
                if(checkField[i-1][j+1]!=0)
                    chainReaction(i-1,j+1);
                if(checkField[i+1][j-1]!=0)
                    chainReaction(i+1,j-1);
            }
        }else if(checked[i][j] == 0){
            //field[i][j].doClick(); alternativa a copiare dall'actionListener
            //Ma parecchio inefficiente.
            
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
            
        }
    }
    
    public void restart(){
        /*---------------------------------------------*/
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                field[i][j].removeActionListener(actionlisteners[i][j]);
            }
        }
        /*---------------------------------------------*/
        //faccio in modo che i bottobi tornino come ad inizio gioco
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                field[i][j].setFocusPainted(true);
                field[i][j].setContentAreaFilled(true);
                field[i][j].setBorderPainted(true);
                field[i][j].setOpaque(true);
                field[i][j].setEnabled(true);
                field[i][j].setBackground(null);
                field[i][j].setForeground(Color.black);
                field[i][j].setText("");
            }
        }
        
        checkField = new int[width][height];
        
        /*-------------------------------------------*/
        actionlisteners = new ActionListener[width][height];
        /*-------------------------------------------*/
        
        this.placeBombs();
        this.calculateBombs();
        this.resetField();
        
        //stampa cheat        
        for(int i=0; i<height;i++){
            for(int j=0; j<width;j++){
                System.out.print(checkField[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    private void resetField(){
        /*-----------RESET DEI LISTENERS-----------*/
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                actionlisteners[i][j] = new CasellaListener(field,checkField,checked,i,j,this);
            }
        }
        
        //aggiungo l'ascoltatore ad ogni nuova casella(bottone)
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                field[i][j].addActionListener(actionlisteners[i][j]);
            }
        }
        /*-----------------------------------------*/
        this.repaint();
    }
    
    void showAllBombs() {
        for(int i=0; i<GamePanel.height; i++){
            for(int j=0; j<GamePanel.width; j++){
                if(checkField[i][j]==9){
                    field[i][j].setFocusPainted(false);
                    field[i][j].setMargin(new Insets(0, 0, 0, 0));
                    field[i][j].setBorderPainted(false);
                    field[i][j].setBackground(Color.yellow);
                    field[i][j].setText("*");
                }
            }
        }
    }
    
    private int countChecked(){
        int count=0;
        for(int i=0; i<GamePanel.height; i++){
            for(int j=0; j<GamePanel.width; j++){
                if(checked[i][j]==1)
                    count++;
            }
        }
        return count;
    }
    
    public void checkWin(){
        if(countChecked() == ((width*height)-bombs)){
            // stop timer
            
            JOptionPane.showMessageDialog(null,"HAI VINTO!","vittoria",JOptionPane.INFORMATION_MESSAGE);
            showAllBombs();
            restart();
        }
    }
}

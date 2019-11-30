/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campominatogithub;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Alessandro
 */
public class RightClickListener implements MouseListener {
    private boolean rightClick;
    private JButton b;
    
    public RightClickListener(JButton b){
        this.b = b;
        rightClick = false;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //in caso di click destro
        if(me.getButton() == MouseEvent.BUTTON3) {
            if(rightClick == false && b.getText().equals("")){
                b.setBackground(Color.black);
                b.setEnabled(false);
                rightClick = true;
            }else{
                b.setBackground(null);
                b.setEnabled(true);
                rightClick = false;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) { }

    @Override
    public void mouseReleased(MouseEvent me) { }

    @Override
    public void mouseEntered(MouseEvent me) { }

    @Override
    public void mouseExited(MouseEvent me) { }
}

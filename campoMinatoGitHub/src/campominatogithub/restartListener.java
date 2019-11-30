/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campominatogithub;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alessandro
 */
public class restartListener implements ActionListener {
    private GamePanel p;
    
    public restartListener(GamePanel campo) {
        p=campo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        p.restart();
    }
}

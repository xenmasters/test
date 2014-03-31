
package co2220.coursework1.twobuttonsrandomrec;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Jonte Dillon Alexis Burke
 */
public class TwoButtonsRandomRec{
    JFrame frame;
    DrawPanel drawPanel;
    JButton startBtn, stopBtn;
    int frameWidth = 500;
    int frameHeight = 500;
    
    public static void main (String[] args){
        TwoButtonsRandomRec test = new TwoButtonsRandomRec();
    }//close method main
    
    /**
     * 
     */
    public TwoButtonsRandomRec(){
        interfaceGUI();
        ButtonActions ba = new ButtonActions();
        ba.actionListeners();
    }//close constructor TwoButtonsRandomRec
    
    /**
     * 
     */
    public void interfaceGUI(){
        
        startBtn = new JButton("START");
        stopBtn = new JButton("STOP");         
        
        drawPanel = new DrawPanel();
        drawPanel.add(startBtn);
        drawPanel.add(stopBtn);
    
        frame = new JFrame("Random Rectangles with Two Buttons"); 
        frame.add(startBtn,BorderLayout.NORTH);
        frame.add(stopBtn,BorderLayout.SOUTH);
        frame.setSize(frameWidth,frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }//close method interfaceGUI
    
    /**
     * 
     */
    class ButtonActions implements ActionListener{
        
        /**
         * 
         */
        public void actionListeners(){
            startBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(e.getSource() == startBtn){
                        new Thread(){
                            public void run(){
                                frame.repaint();
                                frame.add(drawPanel,BorderLayout.CENTER);
                                frame.setVisible(true);
                            }//close run
                        }.start();
                    }//close if
                }//close actionPerformed
            });

            stopBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(e.getSource() == stopBtn){
                        Thread.currentThread().interrupt();
                        frame.remove(drawPanel);
                    }//close if
                }//close actionPerformed
            });       
        }//close method actionListeners

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }//close inner class ButtonActions
    
    /**
     * 
     */
    class DrawPanel extends JPanel{

        int x,y;
        /**
         * 
         * @param g 
         */
        @Override
        public void paintComponent(Graphics g){

            int currentWidth = this.getWidth();
            int currentHeight = this.getHeight();
            int randomWidth = Shuffler(currentWidth - 50);
            int randomHeight = Shuffler(currentHeight - 50);
            int randomX = Shuffler(randomHeight);
            int randomY = Shuffler(randomWidth); 
            
            g.drawRect(x,y,currentWidth,currentHeight);
            Color RecColor = new Color(Shuffler(255), Shuffler(255), Shuffler(255));
            g.setColor(RecColor);
            g.fillRect(randomX,randomY,randomHeight,randomWidth);
            repaint();
            try {
                Thread.sleep(600);
            } catch (InterruptedException ex) { 
            }//close try catch
        }//close method paintComponent
        
        /**
        * 
        */
        public int Shuffler(int num){
            return (int)(Math.random() * num);
        }//close method Shuffler
    }//close inner class DrawPanel    
}//close class TwoButtonsRandomRec
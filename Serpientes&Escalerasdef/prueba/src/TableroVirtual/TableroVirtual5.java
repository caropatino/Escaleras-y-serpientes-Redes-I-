/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableroVirtual;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author carop
 */
public class TableroVirtual5 extends Tablero implements ActionListener{

 JLabel lblYou = new JLabel("Jugador 1");
 JLabel lblComp = new JLabel("Jugador 2");
 JLabel lblComp3 = new JLabel("Jugador 3");
 JLabel lblComp4 = new JLabel("Jugador 4");
 

 JButton jugar = new JButton("Jugar");
 JButton newGame = new JButton("Nuevo Juego");

 JLabel lblCompNo = new JLabel("");
 JLabel lblYouNo = new JLabel("");
 JLabel lblComp3No = new JLabel("");
 JLabel lblComp4No = new JLabel("");

 JLabel lblCompPos = new JLabel("");
 JLabel lblYouPos = new JLabel("");
 JLabel lblComp3Pos = new JLabel("");
 JLabel lblComp4Pos = new JLabel("");
 
 HashMap<Integer,Integer> ladder = new HashMap<Integer,Integer>();
 HashMap<Integer,Integer> snake = new HashMap<Integer,Integer>();

 int youCount = 1;
 int compCount = 1;
 int comp3Count = 1;
 int comp4Count = 1;

 
 int w=10,h=10; // tamaño de las fichas del jugador
 int x=0,y=0;

 StringBuffer compList = new StringBuffer();
 StringBuffer youList = new StringBuffer();
 StringBuffer comp3List = new StringBuffer();
  StringBuffer comp4List = new StringBuffer();

 Random dies = new Random();

 public TableroVirtual5(){
  super();
  setLayout(null);
 // setLayout(new BorderLayout());
  setVisible(true);
  setResizable(false);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //getContentPane().setBackground(Color.LIGHT_GRAY);
  setBounds(10, 10, 700, 700);
  JLabel background=new JLabel(new ImageIcon("C:\\Users\\carop\\Documents\\UCAB\\6TO-7MO SEMESTRE\\Redes\\PROYECTO SERPIENTES\\tablero5.jpg"));
  background.setBounds(0, -10, 550, 570);
  add(background);

  jugar.setBounds(570, 200, 100, 30);
  add(jugar);

  lblComp.setBounds(560, 300, 60, 30);
  add(lblComp);
  
  lblYou.setBounds(560, 250, 60, 30);
  add(lblYou);
  
   lblComp3.setBounds(560, 350, 60, 30);
  add(lblComp3);
  
   lblComp4.setBounds(560, 400, 60, 30);
  add(lblComp4);
  
  lblYouNo.setBounds(640, 250, 60, 30);
  lblYouNo.setOpaque(true);
  //lblYouNo.setBackground(Color.BLUE);
  lblYouNo.setForeground(Color.BLACK);
  add(lblYouNo);

  lblCompNo.setBounds(640, 300, 60, 30);
  lblCompNo.setOpaque(true);
 // lblCompNo.setBackground(Color.RED);
  lblCompNo.setForeground(Color.BLACK);
  add(lblCompNo);
  
   lblComp3No.setBounds(640, 350, 60, 30);
  lblComp3No.setOpaque(true);
 // lblCompNo.setBackground(Color.RED);
  lblComp3No.setForeground(Color.BLACK);
  add(lblComp3No);

  
   lblComp4No.setBounds(640, 300, 60, 30);
  lblComp4No.setOpaque(true);
 // lblCompNo.setBackground(Color.RED);
  lblComp4No.setForeground(Color.BLACK);
  add(lblComp4No);
  
  newGame.setBounds(570, 140, 100, 30);
  add(newGame);
  coinPosition(1,compCount);
  lblCompPos.setBounds(x, y, w, h);
  lblCompPos.setOpaque(true);
  lblCompPos.setBackground(Color.RED);
  background.add(lblCompPos);

  coinPosition(2,youCount);
  lblYouPos.setBounds(x, y, w, h);
  lblYouPos.setOpaque(true);
  lblYouPos.setBackground(Color.BLUE);
  background.add(lblYouPos);

  coinPosition(3,comp3Count);
  lblComp3Pos.setBounds(x, y, w, h);
  lblComp3Pos.setOpaque(true);
  lblComp3Pos.setBackground(Color.GREEN);
  background.add(lblComp3Pos);
  
   coinPosition(4,comp4Count);
  lblComp4Pos.setBounds(x, y, w, h);
  lblComp4Pos.setOpaque(true);
  lblComp4Pos.setBackground(Color.ORANGE);
  background.add(lblComp4Pos);
  
  repaint();

        snake.put(40,19);
        snake.put(78,55);
        snake.put(91,54);
        snake.put(36,25);

        ladder.put(59,99);
        ladder.put(5,32);
        ladder.put(75,97);

  newGame.addActionListener(this);
  jugar.addActionListener(this);


 }

 
  public static void main(String args[])  throws Throwable {
 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  new TableroVirtual5();
  }
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == newGame) {
    youCount = 1;
    compCount = 1;
    coinPosition(1,compCount);
    lblCompPos.setBounds(x, y, w, h);
    coinPosition(2,youCount);
    lblYouPos.setBounds(x, y, w, h);
    lblYouNo.setText("");
    lblCompNo.setText("");
    jugar.setVisible(true);
    repaint();
   } else if (e.getSource() == jugar) {
    compList = new StringBuffer();

    int playAgain = playDies(2);
    coinPosition(2,youCount);
    lblYouPos.setBounds(x, y, w, h);
    repaint();
    if(ladder.containsKey(youCount)) {
     youCount = ladder.get(youCount);
    } else if (snake.containsKey(youCount)){
     youCount = snake.get(youCount);
    }
    coinPosition(2,youCount);
    lblYouPos.setBounds(x, y, w, h);
    repaint();
    if(youCount == 100) {
     jugar.setVisible(false);
     JOptionPane.showMessageDialog(this, "You win");
    } else if(playAgain == 0) {
     youList = new StringBuffer();
     do {
      playAgain = playDies(1);
      coinPosition(1,compCount);
      lblCompPos.setBounds(x, y, w, h);
      repaint();
      if(ladder.containsKey(compCount)) {
       compCount = ladder.get(compCount);
      } else if (snake.containsKey(compCount)){
       compCount = snake.get(compCount);
      }
      coinPosition(1,compCount);
      lblCompPos.setBounds(x, y, w, h);
      repaint();
      if(compCount == 100) {
       jugar.setVisible(false);
       JOptionPane.showMessageDialog(this, "Computer win");
       break;
      }

     } while(playAgain == 1);
    }

   }
  }

  private int playDies(int player) {
   int playAgain = 0;
   int diesResult = 0;
   while(diesResult == 0) {
    diesResult = dies.nextInt(7);
   }
   if(player == 2){
    youList.append(String.valueOf(diesResult));
    youList.append(",");
    lblYouNo.setText(youList.toString());
    if(youCount+diesResult <= 100) {
     youCount = youCount+diesResult;
     if(diesResult == 1 || diesResult == 5 || diesResult == 6) {
      playAgain = 1;
     }
    }
   } else {
    compList.append(String.valueOf(diesResult));
    compList.append(",");
    lblCompNo.setText(compList.toString());
    if(compCount+diesResult <= 100) {
     compCount = compCount+diesResult;
     if(diesResult == 1 || diesResult == 5 || diesResult == 6) {
      playAgain = 1;
     }
    }
   }
   return playAgain;
  }

  public void coinPosition(int compOrYou, int count) {

   int xpos = count%10;
   int ypos = count/10;
   if(xpos == 0) {
    xpos = 10;
    ypos = ypos-1;
   }
   if(compOrYou == 1) {
    x = 105 + (xpos*35) - 35;
    y = 430 - (ypos*35);
   }
   if (compOrYou ==2){
    x = 120 + (xpos*35) - 35;
    y = 430 - (ypos*35);
   }
   if (compOrYou ==3){
    x = 105 + (xpos*35) - 35;
    y = 445 - (ypos*35);
   }
    if (compOrYou ==4){
    x = 120 + (xpos*35) - 35;
    y = 445 -(ypos * 35);
   }
  }
  
   public void turno(String turno) {
        System.out.println("turno en tablero " + turno);
        if ("00000000".equals(turno)) {
            System.out.println("turno del jugador 1");

            if (!comuni.getEstacion().equals(turno)) {
                this.jugar.setEnabled(false);

            } else {
                this.jugar.setEnabled(true);
            }

        }
        if ("00000001".equals(turno)) {
            System.out.println("turno del jugador 2");
            System.out.println("estacion en tablero" + comuni.getEstacion());
            if (!comuni.getEstacion().equals(turno)) {

                System.out.println("entro al if del boton");

                this.jugar.setEnabled(false);
            } else {
                this.jugar.setEnabled(true);
            }

        }
        if ("00000010".equals(turno)) {
            System.out.println("turno del jugador 3");
            if (!comuni.getEstacion().equals(turno)) {
                this.jugar.setEnabled(false);
            } else {
                this.jugar.setEnabled(true);
            }

        }
        if ("00000011".equals(turno)) {
            System.out.println("turno del jugador 4");
            if (!comuni.getEstacion().equals(turno)) {
                this.jugar.setEnabled(false);
            } else {
                this.jugar.setEnabled(true);
            }

        }
        turnoLocal = turno;
    }
}
    
    
    



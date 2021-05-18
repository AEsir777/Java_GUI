import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class TicTacTowSimulator extends JFrame
{
   //components
   private JLabel[] display = new JLabel[9];
   private JButton game;
   private JButton exit;
   
   //String
   private int[] number = new int[9];
   private int x, o;
   
   //constructor
   public TicTacTowSimulator()
   {
      //set the windows
      setTitle("tic tac tow simulator");
      setSize(150,200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //set the components
      Font font1 = new Font("Dialog", Font.PLAIN, 30);
      JPanel panel1 = new JPanel(new GridLayout(3,3));
      JPanel panel3 = new JPanel();
      
      for(int i = 0; i < 9; i++)
      {
         display[i] = new JLabel(" ");
         display[i].setFont(font1);
         
         panel1.add(display[i]);
      }
      
      game = new JButton("new game");
      exit = new JButton("exit");
      
      game.addActionListener(new ButtonListener());
      exit.addActionListener(new ButtonListener());
      
      panel3.add(game);
      panel3.add(exit);
      
      //panels
      JPanel panel2 = new JPanel();
      panel2.add(panel1);
      panel2.add(panel3);
      
      //attach the panel to the content pane
      add(panel2);
      
      //visible
      setVisible(true);
   }
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent a)
      {
         if(a.getSource() == game)
         {
            for(int i = 0; i < 9; i++)
            {
               number[i] = (int)(Math.random()*2);
               
               if(number[i] == 0)
                  display[i].setText("O");
                  else
                     if(number[i] == 1)
                        display[i].setText("X");
            }
            
            x = 0;
            o = 0;
            
            determine(0,1,2);
            determine(3,4,5);
            determine(6,7,8);
            determine(0,4,8);
            determine(2,4,6);
            determine(0,3,6);
            determine(1,4,7);
            determine(2,5,8);
            
            if(x > o)
               JOptionPane.showMessageDialog(null, "X wins!");
               else
                  if(x < o)
                     JOptionPane.showMessageDialog(null, "O wins!");
                     else
                        JOptionPane.showMessageDialog(null, "It's a tie");  
         }//end game
         else
            if(a.getSource() == exit)
               System.exit(0);
      }
   }//end inner
   
   private void determine (int n1, int n2, int n3)
   {
      if((number[n1] + number[n2] + number[n3]) == 0)   
         o++;
      else
         if((number[n1] + number[n2] + number[n3]) == 3)
            x++;
   }
   
   public static void main(String[] args)
   {
      new TicTacTowSimulator();
   }
}//end class TicTacTowSimulator
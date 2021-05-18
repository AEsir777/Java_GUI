import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Scanner;

class ShoppingCartSystem extends JFrame
{
   //components
   private JList list, shoppingCart1, shoppingCart2;
   private JButton remove, clear, checkOut;
   private JPanel listPanel, buttonPanel, cartPanel;
   private JScrollPane pane1, pane2;
   
   private String[] name, price, display1, display2, content1, content2;
   
   public ShoppingCartSystem() throws IOException
   {
      //set the windows
      setTitle("shopping cart system");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //set the list
      getList();
      
      //set the panels and other components
      buildListPanel();
      buildButtonPanel();
      buildCartPanel();
      
      //attach the panel to the content pane
      add(listPanel,BorderLayout.NORTH);
      add(cartPanel,BorderLayout.CENTER);
      add(buttonPanel,BorderLayout.SOUTH);
      
      pack();
      
      //visible
      setVisible(true);
   }
   
   private void getList() throws IOException
   {
      //open the file
      File file = new File("BookPrice.txt");
      Scanner reader = new Scanner(file);
      
      String goods = "";
      
      while(reader.hasNext())
      {
         goods += reader.nextLine();
         goods += ",";
      }
      
      //close the file
      reader.close();
      
      //organize the information
      String[] arr = goods.split(",");
      
      name = new String[arr.length/2];
      price = new String[arr.length - arr.length/2];
      
      int indexP = 0;
      int indexN = 0;
      
      for(int i = 0; i < arr.length; i++)
      {
         if(i % 2 == 0)
            name[indexN++] = arr[i];
         else
            price[indexP++] = arr[i];
      }
      
      list = new JList(name);
   }
   
   private void buildListPanel()
   {
      list.addListSelectionListener(new listListener());
   
      listPanel = new JPanel();
      listPanel.add(list);
   }
   
   
   private void buildButtonPanel()
   {
      remove = new JButton("remove");
      clear = new JButton("clear");
      checkOut = new JButton("check out");
      
      remove.addActionListener(new ButtonListener());
      clear.addActionListener(new ButtonListener());
      checkOut.addActionListener(new ButtonListener());
      
      buttonPanel = new JPanel();
      buttonPanel.add(remove);
      buttonPanel.add(clear);
      buttonPanel.add(checkOut);
   }
   
   private void buildCartPanel()
   {
      shoppingCart1 = new JList();
      shoppingCart1.setVisibleRowCount(6);
      shoppingCart1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     
      shoppingCart2 = new JList();
      shoppingCart2.setVisibleRowCount(6);
      shoppingCart2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      pane1 = new JScrollPane(shoppingCart1);
      pane2 = new JScrollPane(shoppingCart2);
      
      cartPanel = new JPanel();
      cartPanel.add(pane1);
      cartPanel.add(pane2);   
   }
   
   //event listeners
   private class listListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent a)
      {
         int[] index = list.getSelectedIndices();
         
         display1 = new String[index.length];
         display2 = new String[index.length];
         
         for(int i = 0; i < index.length; i++)
         {
            display1[i] = name[index[i]];
            display2[i] = price[index[i]];   
         }
         
         //change the shopping cart
         shoppingCart1.setListData(display1);
         shoppingCart2.setListData(display2);
      }
   }
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent a)
      {
         if(a.getSource() == remove)
         {
            int q = shoppingCart1.getSelectedIndex();
            
            content1 = new String[display1.length - 1];
            content2 = new String[display1.length - 1];
            
            int b = 0;
            
            for(int i = 0; i < display1.length; i++)
            {
               if(i != q)
               {
                  content1[b] = display1[i];
                  content2[b] = display2[i];
                  b++;
               }
            }
            
            shoppingCart1.setListData(content1);
            shoppingCart2.setListData(content2);  
            
            display1 = content1;
            display2 = content2;  
         }//end remove
         else
            if(a.getSource() == clear)
            {
               display1 = null;
               display2 = null;
            
               shoppingCart1.setListData(new String[0]);
               shoppingCart2.setListData(new String[0]);
            }//end clear
               else
                  if(a.getSource() == checkOut)
                  {
                     double subtotal = 0;
                     double tax, total;
                     
                     //calculation
                     try
                     {
                        for(int i = 0; i < display2.length; i++)
                        {
                           subtotal += Double.parseDouble(display2[i]);
                        }
                     }
                     catch(Exception e)
                     {
                     }
                     
                     tax = subtotal * 0.06;
                     total = tax + subtotal;
                     
                     String[] o = {"The subtotal of the shopping cart is: ", "The tax is: ", "The total is: "};
                     String[] p = {String.format("$%,.2f",subtotal), String.format("$%,.2f",tax), 
                                   String.format("$%,.2f",total)};   
                     
                     shoppingCart1.setListData(o);
                     shoppingCart2.setListData(p);
                  }//end checkOut
      }
   }
   
   public static void main(String[] args) throws IOException
   {
      new ShoppingCartSystem();
   }
}//end class ShoppingCartSystem
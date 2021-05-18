import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class SkateboardDesigner extends JFrame
{
   //components
   JList deck, truck, wheel, miscellaneous;
   JTextArea display;
   JButton cal;
   JPanel listPanel, buttonPanel, textPanel;
   
   //constructor
   public SkateboardDesigner()
   {
      //set the windows
      setTitle("skateboard designer");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //panels
      buildListPanel();
      buildTextPanel();
      buildButtonPanel();
      
      //attach the panel to the content pane
      add(listPanel, BorderLayout.NORTH);
      add(textPanel, BorderLayout.CENTER);
      add(buttonPanel, BorderLayout.SOUTH);
      
      pack();
      
      //visible
      setVisible(true);
   }
   
   private void buildListPanel()
   {
      String[] deckType = {"The Master Thrasher", "The Dictator", "The Street King"};
      String[] truckType = {"7.75 inch axle", "8 inch axle", "8.5 inch axle"};
      String[] wheelType = {"51mm", "55mm", "58mm", "61mm"};
      String[] service = {"Grip tape", "Bearings", "Riser pads", "Nuts&bolts kit"}; 
       
      //set the components
      deck = new JList(deckType);
      truck = new JList(truckType);
      wheel = new JList(wheelType);
      miscellaneous = new JList(service);
      
      deck.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      truck.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      wheel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         
      //add the scroll bar
      wheel.setVisibleRowCount(3);
      miscellaneous.setVisibleRowCount(3);
      
      JScrollPane scroll = new JScrollPane(wheel);
      JScrollPane scroll2 = new JScrollPane(miscellaneous);
      
      //add to the panel
      listPanel = new JPanel();
      
      listPanel.setLayout(new GridLayout(2,2));
      
      listPanel.add(deck);
      listPanel.add(truck);
      listPanel.add(scroll);
      listPanel.add(scroll2);
   }
   
   private void buildTextPanel()
   {
      //set the component
      display = new JTextArea(4, 15);
      
      //add to the panel
      textPanel = new JPanel();
      textPanel.add(display);
   }
   
   private void buildButtonPanel()
   {
      cal = new JButton("calculate");
      
      //add action listener
      cal.addActionListener(new buttonListener());
      
      buttonPanel = new JPanel();
      buttonPanel.add(cal);
   }
   
   private class buttonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent a)
      {
         //price
         int[] deckPrice = {60, 45, 50};
         int[] truckPrice = {35, 40, 45};
         int[] wheelPrice = {20, 22, 24, 28};
         int[] miscellaneousPrice = {10, 30, 2, 3};
      
         //get the info
         int deckIndex = deck.getSelectedIndex();
         int truckIndex = truck.getSelectedIndex();
         int wheelIndex = wheel.getSelectedIndex();
         int[] serviceIndex = miscellaneous.getSelectedIndices();
         
         //count for the subtotal
         double subtotal = 0;
         
         subtotal += deckPrice[deckIndex];
         subtotal += truckPrice[truckIndex];
         subtotal += wheelPrice[wheelIndex];
         
         for(int i = 0; i < serviceIndex.length; i++)
         {
            subtotal += miscellaneousPrice[serviceIndex[i]];
         }
         
         double tax = subtotal * 0.06;
         double total = subtotal + tax;
         
         //display
         display.setText(String.format("The subtotal for the skate board is $%,.2f.\n" + "The tax is $%,.2f.\n" +
                                        "The total is $%,.2f.\n", subtotal, tax, total));     
      }
   }
   
   public static void main(String[] args)
   {
      new SkateboardDesigner();
   }
}//end class SkateboardDesigner

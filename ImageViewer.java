import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class ImageViewer
{
   //components
   private JMenuBar menuBar;
   private JMenu insert;
   private JMenuItem picture;
   private JFileChooser fileChooser;
   private ImageIcon image;
   private JLabel label;
   private JFrame frame;
   
   //constructor
   public ImageViewer()
   {
      //set the windows
      frame = new JFrame();
      
      frame.setTitle("image viewer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(260,140);
      
      //set component
      label = new JLabel("Please choose an image to insert.");
      
      buildMenu();
      
      //JPanel
      JPanel panel = new JPanel();
      
      panel.add(label);
      
      //attach the panel to the conten pane
      frame.add(panel);
      frame.setJMenuBar(menuBar);
      
      //visible
      frame.setVisible(true);
   }
   
   public void buildMenu()
   {
      menuBar = new JMenuBar();
      insert = new JMenu("insert");
      
      picture = new JMenuItem("picture");
      picture.addActionListener(new MenuItemListener());
      
      insert.add(picture);
      menuBar.add(insert);
   }
   
   private class MenuItemListener implements ActionListener
   {
      public void actionPerformed(ActionEvent a)
      {
         fileChooser = new JFileChooser("Pictures");

         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
         {
            File file = fileChooser.getSelectedFile();
            String path = file.getPath();
            
            image = new ImageIcon(path);          
         }
         
         label.setIcon(image);
         
         frame.pack();
      }
      
   }//end inner class
   
   public static void main(String[] args)
   {
      new ImageViewer();
   }
}//end class ImageViewer
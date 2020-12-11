import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LA7_GUI_3 extends JFrame { 
    private JLabel word = new JLabel("Hello");

    public LA7_GUI_3(){
		setTitle("Mouse press");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(null);
        c.addMouseListener(new MyMouseListener());

		word.setBounds(100, 50, 40, 40);
        setSize(400, 400);
        setVisible(true); 
        c.add(word);
    }
    
    class MyMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            word.setLocation(x, y);
        }
    }
	public static void main(String [] args) {
        new LA7_GUI_3();
	}
}

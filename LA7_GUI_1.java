import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LA7_GUI_1 extends JFrame {
    private JLabel word = new JLabel("HELLO");
    
    public LA7_GUI_1() {
		setTitle("상, 하, 좌, 우");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
        c.setLayout(null);
		c.addKeyListener(new MyKeyListener()); 
		word.setBounds(50, 50, 100, 100);
        setSize(400, 400);
        setVisible(true); 
        c.add(word);
        c.setFocusable(true);
        c.requestFocus();
    }
    
    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int x = word.getX();
            int y = word.getY();
            if(e.getKeyCode() == KeyEvent.VK_UP){
                word.setLocation(x, y - 10);
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                word.setLocation(x, y + 10);
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                word.setLocation(x - 10, y);
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                word.setLocation(x + 10, y);
            }
        }
    }
	public static void main(String [] args) {
        new LA7_GUI_1();
	}
}
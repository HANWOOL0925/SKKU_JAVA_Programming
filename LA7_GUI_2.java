import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class LA7_GUI_2 extends JFrame { 
    private JLabel word = new JLabel("<Enter> 키로 배경색이 바뀝니다.");
    private Container c = getContentPane();
    public LA7_GUI_2(){
		setTitle("Press Enter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLayout(new FlowLayout());
		c.addKeyListener(new MyKeyListener()); 
        setSize(400, 400);
        setVisible(true); 
        c.add(word);
        c.setFocusable(true);
        c.requestFocus();
    }
    
    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            Random random = new Random();
            int R = random.nextInt(256);
            int G = random.nextInt(256);
            int B = random.nextInt(256);

            if(e.getKeyChar() == '\n'){
                word.setText("r = " + R + ", g = " + G + ", b = " + B);
                c.setBackground(new Color(R, G, B));
            }
        }
    }
	public static void main(String [] args) {
        new LA7_GUI_2();
	}
}

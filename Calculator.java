import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JList;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import javax.swing.ScrollPaneLayout;
import javax.swing.JScrollPane;

import java.util.Stack;

public class Calculator extends JFrame implements ActionListener{
	JPanel contentPane;
	JPanel pnDisplay;
	JPanel pnInput;
	JScrollPane pnHistory;
	JTextField textField;
	JButton btn7;
	JButton btn8;
	JButton btn9;
	JButton btnAdd;
	JButton btnClearAll;
	JButton btn4;
	JButton btn5;
	JButton btn6;
	JButton btnMinus;
	JButton btnClearText;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btnMultiply;
	JButton btnOpenBracket;
	JButton btn0;
	JButton btnDot;
	JButton btnEqual;
	JButton btnDivide;
	JButton btnCloseBracket;
	JList list;
	DefaultListModel<String> listData;

	String num1;
	String num2;
	String num3;
	String operator;
	
	Stack<String> OperStack = new Stack<String>();
	Stack<String> NumStack = new Stack<String>();
	boolean isCal = false;
	boolean FP = false;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Calculator() {

		setTitle("Simple Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pnDisplay = new JPanel();
		contentPane.add(pnDisplay, BorderLayout.NORTH);
		pnDisplay.setLayout(new GridLayout(0, 1, 0, 0));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Courier New", Font.PLAIN, 28));
		pnDisplay.add(textField);
		textField.setColumns(10);

		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				String s = String.valueOf(e.getKeyChar());
				
				char key = e.getKeyChar();

				if(Character.isDigit(key)) {
					num3 = num3.concat(s);
					textField.setText(num3);
					FP = false;
					PushNumber(num3);
				}
				else if(key == '.') {
					if(FP) return;
					num3 = num3.concat(".");
					FP = true;
					textField.setText(num3);
				}
				else {
					PushOperator(s);
					num3 = "";
				}
				e.consume();
			}
	
			@Override
			public void keyPressed(KeyEvent e) {

			}
			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		pnInput = new JPanel();
		contentPane.add(pnInput, BorderLayout.CENTER);
		pnInput.setLayout(new GridLayout(4, 5, 5, 5));

		btn7 = new JButton("7");
		btn7.setFont(new Font("굴림", Font.BOLD, 14));
		btn7.addActionListener(this);
		pnInput.add(btn7);

		btn8 = new JButton("8");
		btn8.setFont(new Font("굴림", Font.BOLD, 14));
		btn8.addActionListener(this);
		pnInput.add(btn8);

		btn9 = new JButton("9");
		btn9.setFont(new Font("굴림", Font.BOLD, 14));
		btn9.addActionListener(this);
		pnInput.add(btn9);

		btnAdd = new JButton("+");
		btnAdd.setFont(new Font("굴림", Font.BOLD, 14));
		btnAdd.addActionListener(this);
		pnInput.add(btnAdd);

		btnClearAll = new JButton("C");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				listData.removeAllElements();
				NumStack.clear();
				OperStack.clear();
				textField.setEditable(true);
				num1 = "";
				num2 = "";
				num3 = "";
				operator = "";
			}
		});
		btnClearAll.setFont(new Font("굴림", Font.BOLD, 14));
		btnClearAll.addActionListener(this);
		pnInput.add(btnClearAll);

		btn4 = new JButton("4");
		btn4.setFont(new Font("굴림", Font.BOLD, 14));
		btn4.addActionListener(this);
		pnInput.add(btn4);

		btn5 = new JButton("5");
		btn5.setFont(new Font("굴림", Font.BOLD, 14));
		btn5.addActionListener(this);
		pnInput.add(btn5);

		btn6 = new JButton("6");
		btn6.setFont(new Font("굴림", Font.BOLD, 14));
		btn6.addActionListener(this);
		pnInput.add(btn6);

		btnMinus = new JButton("-");
		btnMinus.setFont(new Font("굴림", Font.BOLD, 14));
		btnMinus.addActionListener(this);
		pnInput.add(btnMinus);

		btnClearText = new JButton("CE");
		btnClearText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField.setEditable(true);
			}
		});
		btnClearText.setFont(new Font("굴림", Font.BOLD, 14));
		btnClearText.addActionListener(this);
		pnInput.add(btnClearText);

		btn1 = new JButton("1");
		btn1.setFont(new Font("굴림", Font.BOLD, 14));
		btn1.addActionListener(this);
		pnInput.add(btn1);

		btn2 = new JButton("2");
		btn2.setFont(new Font("굴림", Font.BOLD, 14));
		btn2.addActionListener(this);
		pnInput.add(btn2);

		btn3 = new JButton("3");
		btn3.setFont(new Font("굴림", Font.BOLD, 14));
		btn3.addActionListener(this);
		pnInput.add(btn3);

		btnMultiply = new JButton("x");
		btnMultiply.setFont(new Font("굴림", Font.BOLD, 14));
		btnMultiply.addActionListener(this);
		pnInput.add(btnMultiply);

		btnOpenBracket = new JButton("(");
		btnOpenBracket.setFont(new Font("굴림", Font.BOLD, 14));
		btnOpenBracket.addActionListener(this);
		pnInput.add(btnOpenBracket);

		btn0 = new JButton("0");
		btn0.setFont(new Font("굴림", Font.BOLD, 14));
		btn0.addActionListener(this);
		pnInput.add(btn0);

		btnDot = new JButton(".");
		btnDot.setFont(new Font("굴림", Font.BOLD, 14));
		btnDot.addActionListener(this);
		pnInput.add(btnDot);

		btnEqual = new JButton("=");
		btnEqual.setFont(new Font("굴림", Font.BOLD, 14));
		btnEqual.addActionListener(this);
		pnInput.add(btnEqual);

		btnDivide = new JButton("/");
		btnDivide.setFont(new Font("굴림", Font.BOLD, 14));
		btnDivide.addActionListener(this);
		pnInput.add(btnDivide);

		btnCloseBracket = new JButton(")");
		btnCloseBracket.setFont(new Font("굴림", Font.BOLD, 14));
		btnCloseBracket.addActionListener(this);
		pnInput.add(btnCloseBracket);

		pnHistory = new JScrollPane();
		contentPane.add(pnHistory, BorderLayout.EAST);
		pnHistory.setPreferredSize(new Dimension(120, 200));

		listData = new DefaultListModel();
		pnHistory.setLayout(new ScrollPaneLayout());
		list = new JList(listData);
		pnHistory.add(list);
		pnHistory.setViewportView(list);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					String output = listData.get(list.getSelectedIndex());
					textField.setText(output);
					PushNumber(output);
				}
			}
		});
		num1 = "";
		num2 = "";
		num3 = "";
		operator = "";
	}
	
	public void actionPerformed(ActionEvent e){
		String s = e.getActionCommand();
		char c = s.charAt(0);
		if(s == ".") {
			if(FP) return;
			else {
				num3 = num3.concat(".");
				FP = true;
				textField.setText(num3);
			}
		}
		else if(Character.isDigit(c)) {
			num3 = num3.concat(s);
			textField.setText(num3);
			FP = false;
			PushNumber(num3);
		}
		else {
			PushOperator(s);
			num3 = "";
		}
	}

	public void Calculate()
	{
		double result = 0;
		while(!OperStack.isEmpty()) {	//calculate until stack is empty.
			String oper = OperStack.pop();
			double num = Double.parseDouble(NumStack.pop());		
			if(oper == "+" || oper.charAt(0) == '+'){
				result += num;
			}
			else if(oper == "-"|| oper.charAt(0) == '-'){
				result -= num;
			}
		}
		result = result + Double.parseDouble(NumStack.pop());		
		String output;
		if(result % 1 != 0)
			output = String.format("%.2f", result);
		else
			output = String.format("%.0f", result);
		num3 = output;
		num1 = output;
		textField.setText(output);
		listData.addElement(output);
	}
	
	public void PushNumber(String s) {
		textField.setEditable(true);
		if(FP == false) {
			num3 = s;
		}
		else {
			num3 = num3.concat(s);
		}
		if(isCal == false) {
			num1 = num3;
		}
		else {
			double n1 = 0f;
			double n2 = Double.valueOf(num2);
			double n3 = Double.valueOf(num3);
			if(operator == "x" || operator.charAt(0) == 'x'){
				n1 = n2 * n3;
			}
			else 
				n1 = n2 / n3;
			num1 = Double.toString(n1);
		}
		textField.setText(num3);
	}
	
	public void PushOperator(String s) {
		char c = s.charAt(0);
		if(textField.isEditable()) {
			listData.addElement(num3);		
			listData.addElement(s);
			FP = false;
			isCal = false;
			
			if(c == '=') {
				NumStack.push(num1);
				Calculate();
			}
			else if(c == '+' || c == '-') {
				NumStack.push(num1);
				OperStack.push(s);
			}
			else {
				num2 = num1;
				operator = s;
				isCal = true;
			}
			textField.setEditable(false);
		}
	}
}
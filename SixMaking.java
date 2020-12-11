import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridLayout;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SixMaking extends JFrame implements ActionListener{
	JPanel contentPane;
	JPanel pnDisplay;
	JPanel pnInput;
	JPanel moveBtn;

	JButton[][] btn = new JButton[30][5];
	JButton btnSelect;

	private int[][] map = {{0, 0, 0, 0, 0}, // 맵의 배열 1일 때 적, -1일 때 청, 0일 때 돌이 안놓여짐
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0}, // 0행
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0}, // 1행
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0}, // 2행
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0}, // 3행
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0}}; // 4행

	private int[][] table = {{0, 0, 0, 0, 0}, // 칸 위의 돌의 개수
								{0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0}};

	private int[] stone = {0, 0, 0, 0, 0}; // 이동할 말들

	private int[] store = {0, 0, 0, 0, 0}; // 이동하지 못했을 때 복구하기 위한 용도

	private boolean checkRNB = true; // 적/청 차례 확인
	boolean preselect = false; // select 눌러졌나 확인

	public SixMaking() {
		setTitle("Six Making");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pnDisplay = new JPanel();
		contentPane.add(pnDisplay, BorderLayout.NORTH);
		pnDisplay.setLayout(new GridLayout(0, 1, 0, 0));

		pnInput = new JPanel();
		contentPane.add(pnInput, BorderLayout.CENTER);
		pnInput.setLayout(new GridLayout(30, 5, 0, 0));

		for(int m = 0; m < 30; m++){
			for(int j = 0; j < 5; j++){
				btn[m][j] = new JButton("0");
				btn[m][j].setText(""+m+j); // 인덱스 mj로 text를 설정
				btn[m][j].setFont(new Font("굴림", Font.BOLD, 0));
				btn[m][j].addActionListener(this);
				if((m/6+j)%2 ==0){
					btn[m][j].setBackground(Color.WHITE);
				}
				else{
					btn[m][j].setBackground(Color.BLACK);
				}
				pnInput.add(btn[m][j]);
			}
		}

		moveBtn = new JPanel();
		contentPane.add(moveBtn, BorderLayout.EAST);
		moveBtn.setPreferredSize(new Dimension(120, 100));
		
		btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("굴림", Font.BOLD, 14));
		btnSelect.addActionListener(this);
		btnSelect.setBackground(Color.WHITE);
		moveBtn.add(btnSelect);
	}

	public void setMap(int x, int y) { //checkBNW를 확인해 map에 RED, BLUE저장
		if(checkRNB){
			map[x][y] = 1;
			checkRNB = false;
		}
		else{
			map[x][y] = -1;
			checkRNB = true;
		}
	}

	public void drawStone(JButton btn, int x, int y) { // 배열의 정보가 파랑일경우 파란돌, 빨강일경우 빨간돌을 그림
		if(x/6 == (x+1)/6){ //한 칸에 6개의 버튼이 있으므로 x/6 == (x+1)/6이라는 것은 지금 버튼과 그 밑의 버튼이 같은 칸에 있다는 뜻
			JOptionPane.showMessageDialog(null, "놓을 수 없는 자리입니다."); // 내가 누른 칸이 맨 밑의 칸이 아니므로
		}
		else if(checkRNB && redstone < 5){
			btn.setBackground(Color.RED);
			setMap(x, y); //맵에 색깔정보를 저장
			table[(x/6)][y] += 1; // 말의 개수를 넣어주기
			redstone++;
		}
		else if (!checkRNB && bluestone < 5){
			btn.setBackground(Color.BLUE);
			setMap(x, y);
			table[(x/6)][y] += 1;
			bluestone++;
		}
		else{
			JOptionPane.showMessageDialog(null, "놓을 수 있는 말의 개수는 5개입니다."); // 돌의 개수가 5개가 넘어가면
		}
	}
	int x;
	int y;
	int x1;
	int y1; // (x1,y1)이 지금 위치 (x, y)가 이동할 위치
	int i = 0; // stone index
	int size = 0; // stone size
	int u = 0; // store index
	int size1 = 0; // store size
	char a; //인덱스좌표 값 (j)
	String b; // 인덱스좌표 값 (m)
	int count = 0; // popup창 발생여부를 확인하기 위한 값
	int num = 0; // 이동할 말 개수
	int redstone = 0; // 적말의 개수
	int bluestone = 0; // 청말의 개수

	public void actionPerformed(ActionEvent e){
		JButton but = (JButton)e.getSource();
		String s = e.getActionCommand();
		if(!preselect){ // 이동시 select를 눌러야하므로 그 여부로 if/else 나누기
			if(s == "Select"){
				preselect = true; // select가 눌림
				if(checkRNB){
					checkRNB = false;
				}
				else{
					checkRNB = true;
				}//새로운 말을 놓지 않아 차례가 바뀌지 않으므로 이를 바꾸어준다.
			}
			else{
				if(s.length() == 3){ // s는 00에서 294까지 존재하므로 자릿수에 따라 구분 
					a = s.charAt(2); // y좌표를 a에
					b = s.substring(0, 2); // x좌표를 b에
				}
				else{
					a = s.charAt(1);
					b = s.substring(0, 1);
				}
				x = Integer.parseInt(b);
				y = Character.getNumericValue(a);
				if (map[x][y] == 0){ // map[x][y]가 0이므로 아무 돌도 놓이지 않은 상태
					drawStone(but, x, y); // 말을 그려준다
				}
				else{
					move(x, y); // 이동할 말의 색깔정보를 배열에 저장
					recover(x, y); // 이동 실패의 경우에 대비해 동일 정보를 저장
					map[x][y] = 0; // 이동을 하면 돌이 없어지므로 배열정보를 다시 0으로
					num++; // 이동할 말의 개수
					if((x/6+y)%2 ==0){ // 칸의 x값 + y값이 짝수면 하얀배경
						but.setBackground(Color.WHITE);
					}
					else{
						but.setBackground(Color.BLACK);
					}
					x1 = x;
					y1 = y; // 마지막으로 이동할 돌의 위치를 x1, y1에 저장
				}
			}
		}
		else{ // select 버튼이 눌렸을 때, 즉 이동이 필요할 때
			if(s.length() == 3){
				a = s.charAt(2);
				b = s.substring(0, 2);
			}
			else{
				a = s.charAt(1);
				b = s.substring(0, 1);
			}
			x = Integer.parseInt(b);
			y = Character.getNumericValue(a);
			show(but, s, x, y); // 칸의 개수에 따른 이동방식 비교
			if(count == 0){ // pop up 창이 떴다면, 즉 이동이 불가했을 때
				while(size < i + 1){ // map과 색깔정보를 되돌리기
					if(stone[size] == 1){
						btn[x-size][y].setBackground(Color.RED);
						map[x-size][y] = 1;
					}
					else if(stone[size] == -1){
						btn[x-size][y].setBackground(Color.BLUE);
						map[x-size][y] = -1;
					}
					size++;
				}
			}
			count = 0; // 이 위에까지가 일련의 과정이므로 map, table을 제외하고 초기화
			for(i=0; i<5 ;i++){
				stone[i] = 0;
			}
			size = 0;
			i = 0;
			for(u=0; u<5 ;u++){
				store[u] = 0;
			}
			size1 = 0;
			u = 0;
			num = 0;
			preselect = false;
		}
	}
	public void move(int x, int y){ // stone에 색깔정보 저장
		while(stone[i] != 0){
			i++;
		}
		stone[i] = map[x][y];
	}

	public void recover(int x, int y){ // store에 색깔정보 저장
		while(store[u] != 0){
			u++;
		}
		store[u] = map[x][y];
	}

	public void colorecover(int x1, int y1){ // 이동에 실패했을 경우 다시 원래대로 색깔을 칠해주어야한다.
		while(size1 < u + 1){
			if(store[size1] == 1){
				btn[x1+size1][y1].setBackground(Color.RED);
				map[x1+size1][y1] = 1;
			}
			else if(stone[size1] == -1){
				btn[x1+size1][y1].setBackground(Color.BLUE);
				map[x1+size1][y1] = -1;
			}
			size1++;
		}
	}

	public boolean winCheckUp(int w, int h) { // 칸의 말 개수가 6개가 넘어가면 true 반환
		if (table[w][h] > 5)
			return true;
		else
			return false;
	}

	public void show(JButton btn, String s, int x, int y){
		int c = table[x1/6][y1]; // c가 칸에 놓인 말의 개수 1~5에 따라 Pawn ~ Queen
		switch(c){
			case 1: // Pawn
			if (((x1/6 - x/6 == 1 && y1 - y == 0) || (x1/6 - x/6 == -1 && y1 - y == 0) || (x1/6 - x/6 == 0 && y1 - y == 1) || (x1/6 - x/6 == 0 && y1 - y == -1)) && (x+1)%6 != 0){ //x, y의 좌표 차이가 하나는 1, 하나는 0일 때 && 이동한 곳이 맨 밑칸이 아닐 때(아무 말이 없는 곳으로 이동하지 않았을 때)
				table[x/6][y] += num; // 이동한 곳의 말 개수 증가
				table[x1/6][y1] -= num; // 이동된 곳의 말 개수 감수
				if (winCheckUp(x/6, y) == true){
					if(stone[i] == 1){
						JOptionPane.showMessageDialog(null, "빨간말이 승리하였습니다.");
					}
					else{
						JOptionPane.showMessageDialog(null, "파란말이 승리하였습니다.");
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "잘못된 이동입니다.");
				colorecover(x1, y1);
				count = 1;
			}
			break;
			case 2: // Rook
			if (((x1/6 - x/6) == 0 || (y1 - y) == 0) && (x+1)%6 != 0){ //x, y의 좌표 차이가 하나는 0일 때
				table[x/6][y] += num;
				table[x1/6][y1] -= num;
				if (winCheckUp(x/6, y) == true){
					if(stone[i] == 1){
						JOptionPane.showMessageDialog(null, "빨간말이 승리하였습니다.");
					}
					else{
						JOptionPane.showMessageDialog(null, "파란말이 승리하였습니다.");
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "잘못된 이동입니다.");
				colorecover(x1, y1);
				count = 1;
			}
			break;
			case 3: // Knight
			if (((x1/6 - x/6) * (y1 - y) == 2  || (x1/6 - x/6) * (y1 - y) == -2) && (x+1)%6 != 0){ // x좌표 차는 |2|, y좌표 차는 |1|
				table[x/6][y] += num;
				table[x1/6][y1] -= num;
				if (winCheckUp(x/6, y) == true){
					if(stone[i] == 1){
						JOptionPane.showMessageDialog(null, "빨간말이 승리하였습니다.");
					}
					else{
						JOptionPane.showMessageDialog(null, "파란말이 승리하였습니다.");
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "잘못된 이동입니다.");
				colorecover(x1, y1);
				count = 1;
			}
			break;
			case 4: // Bishop
			if (((x1/6 - x/6) == (y1 - y) || (x1/6 - x/6) == (y - y1)) && (x+1)%6 != 0){ // x, y 좌표 차가 같을 때
				table[x/6][y] += num;
				table[x1/6][y1] -= num;
				if (winCheckUp(x/6, y) == true){
					if(stone[i] == 1){
						JOptionPane.showMessageDialog(null, "빨간말이 승리하였습니다.");
					}
					else{
						JOptionPane.showMessageDialog(null, "파란말이 승리하였습니다.");
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "잘못된 이동입니다.");
				colorecover(x1, y1);
				count = 1;
			}
			break;
			case 5: // Queen
			if (((x1/6 - x/6) == 0 || (y1 - y) == 0 || (x1/6 - x/6) == (y1 - y) || (x1/6 - x/6) == (y - y1)) && (x+1)%6 != 0) { // Rook이나 Bishop처럼 이동했을 때
				table[x/6][y] += num;
				table[x1/6][y1] -= num;
				if (winCheckUp(x/6, y) == true){
					if(stone[i] == 1){
						JOptionPane.showMessageDialog(null, "빨간말이 승리하였습니다.");
					}
					else{
						JOptionPane.showMessageDialog(null, "파란말이 승리하였습니다.");
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "잘못된 이동입니다.");
				colorecover(x1, y1);
				count = 1;
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SixMaking frame = new SixMaking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
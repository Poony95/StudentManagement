package studentJtable;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StudentTest_UI extends JFrame {
	JTextField jtf_no;
	JTextField jtf_name;
	JTextField jtf_kor;
	JTextField jtf_eng;
	JTextField jtf_math;
	
	public StudentTest_UI() {
		jtf_no = new JTextField();
		jtf_name = new JTextField();
		jtf_kor = new JTextField();
		jtf_eng = new JTextField();
		jtf_math = new JTextField();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5,2));
		
		p.add(new JLabel("학생번호"));
		p.add(jtf_no);
		p.add(new JLabel("이름"));
		p.add(jtf_name);
		p.add(new JLabel("국어"));
		p.add(jtf_kor);
		p.add(new JLabel("영어"));
		p.add(jtf_eng);
		p.add(new JLabel("수학"));
		p.add(jtf_math);
		
		JButton btn = new JButton("등록");
		add(p, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int no = Integer.parseInt(jtf_no.getText());
				String name = jtf_name.getText();
				int kor = Integer.parseInt(jtf_kor.getText());
				int eng = Integer.parseInt(jtf_eng.getText());
				int math = Integer.parseInt(jtf_math.getText());
				
				String sql= "insert into student values ("+no+",'"+name+"', "+kor+", "+eng+", "+math+")";
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					String url = "jdbc:oracle:thin:@localhost:1521:XE";
					Connection conn = DriverManager.getConnection(url, "c##sist", "sist");
					Statement stmt = conn.createStatement();
					int re = stmt.executeUpdate(sql);
					
					if(re ==1 ) {
						JOptionPane.showMessageDialog(null, "레코드를 추가했습니다.");
					}else {
						JOptionPane.showMessageDialog(null, "실패");
					}
					stmt.close();
					conn.close();
					
					jtf_no.setText("");
					jtf_name.setText("");
					jtf_kor.setText("");
					jtf_eng.setText("");
					jtf_math.setText("");
					
				} catch (Exception e2) {
					System.out.println("예외발생"+e2.getMessage());
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new StudentTest_UI();
	}

}

package studentJtable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StudentNameList extends JFrame {

	JTextArea jta;
	public StudentNameList() {
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp, BorderLayout.CENTER);
		
		JButton btn = new JButton("모든 학생 이름");
		add(btn, BorderLayout.SOUTH);
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "select s_name from student";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					String url = "jdbc:oracle:thin:@localhost:1521:XE";
					Connection conn = DriverManager.getConnection(url,"c##sist", "sist");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						String name = rs.getString(1);
						jta.append(name+"\n");
					}
					rs.close();
					stmt.close();
					conn.close();
				
				} catch (Exception e2) {
					System.out.println("예외발생"+e2.getMessage());
				}
			}
		});
	}
	
	
	public static void main(String[] args) {
		new StudentNameList();
	}

}

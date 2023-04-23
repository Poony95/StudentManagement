package studentJtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentAllTable extends JFrame {

	JTable table;
	Vector<String> colName;
	Vector<Vector<String>> rowDate;
	
	public StudentAllTable() {
		colName = new Vector<String>();
		colName.add("번호");
		colName.add("이름");
		colName.add("국어");
		colName.add("영어");
		colName.add("수학");
		
		rowDate = new Vector<Vector<String>>();
		table = new JTable(rowDate, colName);
		JScrollPane jsp = new JScrollPane(table);
		
		add(jsp);
		readStudent();
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void readStudent() {
		try {
			String sql = "select * from student";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String id = "c##sist";
			String pwd = "sist";
			Connection conn = DriverManager.getConnection(url,id,pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				int kor = rs.getInt(3);
				int eng = rs.getInt(4);
				int math = rs.getInt(5);
				
				Vector<String > v = new Vector<String>();
				
				v.add(no+"");
				v.add(name);
				v.add(kor+"");
				v.add(eng+"");
				v.add(math+"");
				
				rowDate.add(v);
			}
			table.updateUI();
			stmt.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("예외발생"+ e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new StudentAllTable();
	}

}

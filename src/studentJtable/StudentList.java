package studentJtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;

public class StudentList extends JFrame {

	public static void main(String[] args) {
		
		try {
			String sql = "select s_name, s_kor from student";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			Connection conn = DriverManager.getConnection(url,"c##sist","sist");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String name = rs.getString(1);
				int kor = rs.getInt(2);
				System.out.println(name +","+ kor);
			}
			stmt.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("예외발생 " + e.getMessage());
		
		}
		
		
	}

}

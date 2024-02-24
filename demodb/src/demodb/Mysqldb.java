package demodb;
import java.sql.*;
import java.util.Scanner;

public class Mysqldb {

	public static void main(String[] args) throws Exception {
	
		
		Scanner sc = new Scanner(System.in);
		int id, year, userinp;
		String name, branch, choice = "y";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Database loaded...");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","Password@123");
		Statement stmt = con.createStatement();
		
		String insertsql = "insert into student values(?,?,?,?)"; 
		PreparedStatement psinsert = con.prepareStatement(insertsql);
		
		String updatesql = "update student set name=?, year=?, branch=? where id=?";
		PreparedStatement psupdate = con.prepareStatement(updatesql);
		
		String deletesql = "delete from student where id=?";
		PreparedStatement psdelete = con.prepareStatement(deletesql);
		
		String readallsql = "select * from student";
		
		
		String readid = "select * from student where id=?";
		PreparedStatement psread = con.prepareStatement(readid);
		
		do {
			
			System.out.println("Please enter\n 1 to insert\n 2 for read\n 3 for update\n 4 for delete ");
			userinp = sc.nextInt();
			switch(userinp) {
			case 1:
				System.out.println("Enter id =>");
				id = sc.nextInt();
				psinsert.setInt(1, id);
				System.out.println("Enter name =>");
				name = sc.next();
				psinsert.setString(2, name);
				System.out.println("Enter year =>");
				year = sc.nextInt();
				psinsert.setInt(3, year);
				System.out.println("Enter branch =>");
				branch = sc.next();
				psinsert.setString(4, branch);
				psinsert.executeUpdate();
				System.out.println("Record inserted");
				System.out.println("Do you want to continue (y/n)");
				choice = sc.next();
				break;
			case 2:
				System.out.println("Enter\n 1 to access all data\n 2 for specific record");
				int inp = sc.nextInt();
				switch(inp) {
				case 1:
					ResultSet rsall = stmt.executeQuery(readallsql);
					while(rsall.next()) {
						System.out.println(rsall.getInt("id")+"|"+rsall.getString("name")+"|"+rsall.getInt("year")+"|"+rsall.getString("branch"));
					}
					System.out.println("Do you want to continue (y/n)");
					choice = sc.next();
					break;
				case 2:
					System.out.println("Enter id =>");
					id = sc.nextInt();
					psread.setInt(1, id);
					ResultSet rs = psread.executeQuery();
					while(rs.next()) {
						System.out.println(rs.getInt("id")+"|"+rs.getString("name")+"|"+rs.getInt("year")+"|"+rs.getString("branch"));
					}
					System.out.println("Do you want to continue (y/n)");
					choice = sc.next();
					break;
				default:
					System.out.println("Invalid input");
					break;
				}
				break;
			case 3:
				System.out.println("Enter id for locating =>");
				id = sc.nextInt();
				psupdate.setInt(4, id);
				System.out.println("Enter updated name =>");
				name = sc.next();
				psupdate.setString(1, name);
				System.out.println("Enter updated year =>");
				year = sc.nextInt();
				psupdate.setInt(2, year);
				System.out.println("Enter updated branch =>");
				branch = sc.next();
				psupdate.setString(3, branch);
				psupdate.executeUpdate();
				System.out.println("Record updated");
				System.out.println("Do you want to continue (y/n)");
				choice = sc.next();
				break;
			case 4:
				System.out.println("Enter id for deleting record =>");
				id = sc.nextInt();
				psdelete.setInt(1, id);
				psdelete.executeUpdate();
				System.out.println("Record deleted");
				System.out.println("Do you want to continue (y/n)");
				choice = sc.next();
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
			
			
			
		}while(choice.equals("y"));
		System.out.println("Execution completed...");
		sc.close();
		con.close();
	}

}

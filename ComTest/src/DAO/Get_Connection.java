package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Get_Connection {
	public static Connection getConnection()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8";
		String Name="root";
		String password="123456";
		//System.out.print("���ݿ����ӳɹ�");
		return (Connection)DriverManager.getConnection(url, Name, password);//��ȡ����
	}
	
	public static void close(ResultSet rs,Statement sta,Connection con)throws Exception{
		if(rs!=null){
                 //�رս����
               rs.close();
		}
		if(sta!=null){
                 //�رղ������
                sta.close();
		}
		if(con!=null){
                //�ر�����
               con.close();
		}
	}

}

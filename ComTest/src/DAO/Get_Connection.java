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
		//System.out.print("数据库连接成功");
		return (Connection)DriverManager.getConnection(url, Name, password);//获取连接
	}
	
	public static void close(ResultSet rs,Statement sta,Connection con)throws Exception{
		if(rs!=null){
                 //关闭结果集
               rs.close();
		}
		if(sta!=null){
                 //关闭操作句柄
                sta.close();
		}
		if(con!=null){
                //关闭链接
               con.close();
		}
	}

}

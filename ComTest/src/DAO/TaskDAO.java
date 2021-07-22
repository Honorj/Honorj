package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.TaskBean;

public class TaskDAO {
	//已完成工作
		public ArrayList<TaskBean> findAlldo() {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList<TaskBean> list = new ArrayList<TaskBean>();
			try {
				conn = Get_Connection.getConnection();
				stmt = conn.createStatement();
				String sql = "SELECT * FROM task where root='1'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					TaskBean taskbean=new TaskBean();
					taskbean.setRoot(rs.getString("root"));
					taskbean.setName(rs.getString("name"));
					taskbean.setTime("time");
					list.add(taskbean);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					Get_Connection.close(rs, stmt, conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		
		//未完成工作    
		   public ArrayList<TaskBean> findAllundo() {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			ArrayList<TaskBean> list1 = new ArrayList<TaskBean>();
			try {
				conn = Get_Connection.getConnection();
				stmt = conn.createStatement();
				String sql = "SELECT * FROM task where root='0'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					TaskBean taskbean=new TaskBean();
					taskbean.setRoot(rs.getString(1));
					taskbean.setName(rs.getString(2));
					taskbean.setTime(rs.getString(3));
					list1.add(taskbean);
				}
				return list1;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					Get_Connection.close(rs, stmt, conn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		   
		   
		//增加任务
		 public boolean insert(TaskBean taskbean) {
			 Connection con=null;
				PreparedStatement psta=null;
				String sql="insert into task (root,name,time) values(?,?,?)";
				boolean flag=false;
				try{
					con=Get_Connection.getConnection();
					psta=con.prepareStatement(sql);
					psta.setString(1, taskbean.getRoot());
					psta.setString(2, taskbean.getName());
					psta.setString(3, taskbean.getTime());
					flag=psta.executeUpdate()>0;
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try{
						Get_Connection.close(null, psta, con);
					}catch(Exception e){
						e.printStackTrace();
					}
					return flag;//返回flag大于等于1即为true
				}
		}
		 
		 
		 //删除任务
		    public boolean delete(String name) {
		    	Connection con=null;
				PreparedStatement psta=null;
				boolean flag=false;
				String sql="delete from task where name=?";
				try{
					con=Get_Connection.getConnection();
					psta=con.prepareStatement(sql);
					psta.setString(1, name);
					flag=psta.executeUpdate()>0;
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try{
						Get_Connection.close(null, psta, con);
					}catch(Exception e){
						e.printStackTrace();
					}
					return flag;
				}			
				}
		    
		    
		    //未完成任务转完成任务 
		    public boolean update(String name) {
					Connection con=null;
					PreparedStatement psta=null;
					boolean flag=false;
					String sql="update task set root='1' where name=?";
					try{
						con=Get_Connection.getConnection();
						psta=con.prepareStatement(sql);
						psta.setString(1, name);
						System.out.print(sql);
						flag=psta.executeUpdate()>0;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						Get_Connection.close(null, psta, con);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return flag;
			}
}

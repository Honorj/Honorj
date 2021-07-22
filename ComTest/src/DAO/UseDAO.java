package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.UserBean;

public class UseDAO {
	//��ѯ���У��飩
		public ArrayList findAll(){
			Connection con=null;
			PreparedStatement psta=null;
			ResultSet rs=null;
			ArrayList list=new ArrayList();
			String sql="select * from tester9";
			try{
				con=Get_Connection.getConnection();
				psta=con.prepareStatement(sql);
				rs=psta.executeQuery();
				while(rs.next()){
					UserBean obj=new UserBean();
					obj.setID(rs.getString(1));
					obj.setName(rs.getString(2));
					obj.setSex(rs.getString(3));
					obj.setAddress(rs.getString(4));
					obj.setPhone(rs.getString(5));
					list.add(obj);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					Get_Connection.close(rs, psta, con);
				}catch(Exception e){
					e.printStackTrace();
				}
				return list;
			}
		}
		
		//���뷽��������
		public boolean add(UserBean obj){
			Connection con=null;
			PreparedStatement psta=null;
			String sql="insert into tester9 (ID,name,sex,address,phone) values(?,?,?,?,?)";
			boolean flag=false;
			try{
				con=Get_Connection.getConnection();
				psta=con.prepareStatement(sql);
				psta.setString(1, obj.getID());
				psta.setString(2, obj.getName());
				psta.setString(3, obj.getSex());
				psta.setString(4, obj.getAddress());
				psta.setString(5, obj.getPhone());
				flag=psta.executeUpdate()>0;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					Get_Connection.close(null, psta, con);
				}catch(Exception e){
					e.printStackTrace();
				}
				return flag;//����flag���ڵ���1��Ϊtrue
			}
		}
		
		//���·������޸����ݣ����ģ�
				public boolean update(UserBean obj){
					Connection con=null;
					PreparedStatement psta=null;
					String sql="update tester9 set name=?,sex=?,address=?,phone=? where ID=?";
					boolean flag=false;
					try{
						con=Get_Connection.getConnection();
						psta=con.prepareStatement(sql);
						psta.setString(1, obj.getName());
						psta.setString(2, obj.getSex());
						psta.setString(3, obj.getAddress());
						psta.setString(4, obj.getPhone());
						psta.setString(5, obj.getID());
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
		
		//ɾ��������ɾ��
		public boolean remove(String id){
			Connection con=null;
			PreparedStatement psta=null;
			boolean flag=false;
			String sql="delete from tester9 where ID=?";
			try{
				con=Get_Connection.getConnection();
				psta=con.prepareStatement(sql);
				psta.setString(1, id);
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
		//ͨ��id����
				public UserBean findById(String id){
					Connection con=null;
					PreparedStatement psta=null;
					ResultSet rs=null;
					UserBean obj=null;
					String sql="select * from tester9 where ID=?";
					try{
						con=Get_Connection.getConnection();
						psta=con.prepareStatement(sql);
						psta.setString(1, id);
						rs=psta.executeQuery();
						if(rs.next()){
							obj=new UserBean();
							obj.setID(rs.getString(1));
							obj.setName(rs.getString(2));
							obj.setSex(rs.getString(3));
							obj.setAddress(rs.getString(4));
							obj.setPhone(rs.getString(5));
						}
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						try{
							Get_Connection.close(rs, psta, con);
						}catch(Exception e){
							e.printStackTrace();
						}
						return obj;
					}
				}
		
}

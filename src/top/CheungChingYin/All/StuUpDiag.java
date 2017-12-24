package top.CheungChingYin.All;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Driver;
/*
 * 这个类用于修改某一行的数据
 */
public class StuUpDiag extends JDialog implements ActionListener {

	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jf1,jf2,jf3,jf4,jf5,jf6;
	JPanel jp1,jp2,jp3;
	JButton jb1,jb2;
	String sourceID;
	
	public StuUpDiag(Frame owner, String title, boolean modal, StuModel sm, int rowNum) {
		
		super(owner,title,modal);//用于表示一个独立的对话框
		
		jl1=new JLabel("学号");
		jl2=new JLabel("姓名");
		jl3=new JLabel("性别");
		jl4=new JLabel("年龄");
		jl5=new JLabel("专业");
		jl6=new JLabel("系部");
		
		//设置文本框，获取数据库中的数据以便修改
		jf1=new JTextField(10);
		jf1.setText((sm.getValueAt(rowNum, 0).toString()));//设置输入框中是选中行的学号
		sourceID=sm.getValueAt(rowNum, 0).toString();//记录选中行的学生学号
		jf2=new JTextField(10);
		jf2.setText((sm.getValueAt(rowNum, 1).toString()));
		jf3=new JTextField(10);
		jf3.setText((sm.getValueAt(rowNum, 2).toString()));
		jf4=new JTextField(10);
		jf4.setText((sm.getValueAt(rowNum, 3).toString()));
		jf5=new JTextField(10);
		jf5.setText((sm.getValueAt(rowNum, 4).toString()));
		jf6=new JTextField(10);
		jf6.setText((sm.getValueAt(rowNum, 5).toString()));
		
		//设置按钮
		jb1=new JButton("修改");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		
		//设置jpanel
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//Jpanel设置布局
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jf1);
		jp2.add(jf2);
		jp2.add(jf3);
		jp2.add(jf4);
		jp2.add(jf5);
		jp2.add(jf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2, BorderLayout.EAST);
		this.add(jp3, BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.setSize(300,200);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			Connection ct=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			Statement stat=null;
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("StuUpDiag的JDBC驱动加载成功");
				ct=DriverManager.getConnection(MySqlAccount.url,MySqlAccount.user,MySqlAccount.passwd);
				String strsql="UPDATE `studentmanagement`.`stuinformation` SET `stuId`=?, `stuName`=?, `stuSex`=?, `stuAge`=?, `stuSpecialty`=?, `stuDept`=? WHERE `stuId`=?;";
				String idSQL="select * from stuInformation";
				boolean flag=true;
				stat=ct.createStatement();
				rs=stat.executeQuery(idSQL);
				ps=ct.prepareStatement(strsql);
				
				if(jf1.getText().equals(sourceID)){
					flag=false;
				}
				//给对象赋值
				
				while(rs.next()){
					if(rs.getString(1).equals(jf1.getText()) && flag){
						JOptionPane.showMessageDialog(this, "学号重复");
					}
					else if(jf1.getText().length()<15 &&jf1.getText().length()!=0 ||jf1.getText().length()>15){
						JOptionPane.showMessageDialog(this, "学号长度必须要为15位，请重新输入！");
						break;
					}else if(jf1.getText().length()==0){
						JOptionPane.showMessageDialog(this, "学号不能为空！");
						break;
					}else{
						ps.setString(1, jf1.getText());
					}
					
				}
				
				
				
				if(jf2.getText().length()>=1){
					ps.setString(2, jf2.getText());
				}else{
					JOptionPane.showMessageDialog(this, "姓名不能为空");
				}
				
				
				if(jf3.getText().equals("男") || jf3.getText().equals("女")){
					ps.setString(3, jf3.getText());
				}else if(jf3.getText().length()==0){
					JOptionPane.showMessageDialog(this, "性别不能为空！");
				}else{
					JOptionPane.showMessageDialog(this, "性别只能填 “男” 或 “女” ！");
				}
				
				
				if(jf4.getText().length()<=2 && jf4.getText().length() !=0){
					ps.setString(4, jf4.getText());
				}else if(jf4.getText().length()==0){
					JOptionPane.showMessageDialog(this, "年龄不能为空！");
				}else{
					JOptionPane.showMessageDialog(this, "年龄格式不对！");
				}
				
				
				if(jf5.getText().length()>=1){
					ps.setString(5, jf5.getText());
				}else{
					JOptionPane.showMessageDialog(this, "专业不能为空 ！");
				}
				
				
				if(jf6.getText().length()>=1){
					ps.setString(6, jf6.getText());
				}else{
					JOptionPane.showMessageDialog(this, "系部不能为空！");
				}
				ps.setString(7, sourceID);
				ps.executeUpdate();
				this.dispose();//关闭学生对话框
				
			} catch (Exception e2) {
				e2.printStackTrace();
//				JOptionPane.showMessageDialog(this, "注意！学号重复！");
			}finally {
				try{
					if(rs!=null){
						rs.close();
						rs=null;
					}
					if(ps!=null){
						ps.close();
						ps=null;
					}
					if(ct!=null){
						ct.close();
						ct=null;
					}
				}catch(Exception e3){
					e3.printStackTrace();
				}
			}
		}
		if(e.getSource()==jb2){
			this.dispose();//按取消键关闭当前窗口
		}
	}
	
	

}

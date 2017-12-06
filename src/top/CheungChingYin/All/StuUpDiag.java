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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Driver;

public class StuUpDiag extends JDialog implements ActionListener {

	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jf1,jf2,jf3,jf4,jf5,jf6;
	JPanel jp1,jp2,jp3;
	JButton jb1,jb2;
	
	public StuUpDiag(Frame owner, String title, boolean modal, StuModel sm, int rowNum) {
		jl1=new JLabel("学号");
		jl2=new JLabel("姓名");
		jl3=new JLabel("性别");
		jl4=new JLabel("年龄");
		jl5=new JLabel("专业");
		jl6=new JLabel("系部");
		
		//设置文本框，获取数据库中的数据以便修改
		jf1=new JTextField(10);
		jf1.setText((sm.getValueAt(rowNum, 0).toString()));
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
		this.setSize(300,200);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			Connection ct=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost:3306/studentmanagement";
				String user="root";
				String passwd="123456";
				ct=DriverManager.getConnection(url,user,passwd);
				String strsql="UPDATE `studentmanagement`.`stuinformation` SET `stuId`=?, `stuName`=?, `stuSex`=?, `stuAge`=?, `stuSpecialty`=?, `stuDept`=? WHERE `stuId`=?;";
				ps=ct.prepareStatement(strsql);
				
				//给对象赋值
				String temp=jf1.getText();
				ps.setString(1, jf1.getText());
				ps.setString(2, jf2.getText());
				ps.setString(3, jf3.getText());
				ps.setString(4, jf4.getText());
				ps.setString(5, jf5.getText());
				ps.setString(6, jf6.getText());
				ps.setString(7, temp);
				ps.executeUpdate();
				this.dispose();//关闭学生对话框
				
			} catch (Exception e2) {
				e2.printStackTrace();
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

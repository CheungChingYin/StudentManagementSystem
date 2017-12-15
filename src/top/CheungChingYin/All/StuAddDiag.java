package top.CheungChingYin.All;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StuAddDiag extends JDialog implements ActionListener {
	
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jf1,jf2,jf3,jf4,jf5,jf6;
	JPanel jp1,jp2,jp3;
	JButton jb1,jb2;
	//owner指父窗口，title是指窗口名，modal指是否窗口模式
	public StuAddDiag(Frame owner, String title, boolean modal) {
		
		super(owner,title,modal);
		jl1=new JLabel("学号");
		jl2=new JLabel("姓名");
		jl3=new JLabel("性别");
		jl4=new JLabel("年龄");
		jl5=new JLabel("专业");
		jl6=new JLabel("系部");
		
		jf1=new JTextField(10);
		jf2=new JTextField(10);
		jf3=new JTextField(10);
		jf4=new JTextField(10);
		jf5=new JTextField(10);
		jf6=new JTextField(10);
		
		jb1=new JButton("添加");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//布局设置
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));
		
		jp3.add(jb1);
		jp3.add(jb2);
		
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
		
		//frame的布局
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
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
				//加载驱动
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("StuAddDiag的JDBC驱动加载成功");
				ct=DriverManager.getConnection(MySqlAccount.url,MySqlAccount.user,MySqlAccount.passwd);
				
				//编辑sql语句
				String strsql="insert into stuInformation values(?,?,?,?,?,?)";
				ps=ct.prepareStatement(strsql);
				
				//给对象赋值
				ps.setString(1, jf1.getText());
				ps.setString(2, jf2.getText());
				ps.setString(3, jf3.getText());
				ps.setString(4, jf4.getText());
				ps.setString(5, jf5.getText());
				ps.setString(6, jf6.getText());
				
				ps.executeUpdate();
				this.dispose();//关闭学生对话框
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "学号重复");
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

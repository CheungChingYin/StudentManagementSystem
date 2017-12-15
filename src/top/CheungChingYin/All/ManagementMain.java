package top.CheungChingYin.All;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ManagementMain extends JFrame implements ActionListener {
//定义控件
	JPanel jp1,jp2;
	JLabel jl1,jl2;
	JButton jb1,jb2,jb3,jb4;//按钮
	JTable jt;//显示一个表格
	JScrollPane jsp;//滚动条
	JTextField jtf;
	StuModel sm;
//定义连接数据库的变量
	Statement stat=null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs=null;
	
	public static void main(String[] args) {
		Login login=new Login();
	}
	public ManagementMain() {
		// 构造函数
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入名字：");
	
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
	
		jb2=new JButton("添加");
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
		jb4.addActionListener(this);
	
		jp2=new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
	
		//创建模板对象
		sm=new StuModel();
	
		//初始化
		jt=new JTable(sm);
		jsp=new JScrollPane(jt);
	
		//将jsp放到jframe中
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(600,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if(a.getSource()==jb1){
			System.out.println("用户希望查询...");
			String name=this.jtf.getText().trim();
			String sql="SELECT * FROM stuInformation WHERE stuName='"+name+"'";//SQL的选择语句
			sm=new StuModel(sql);
			jt.setModel(sm);
		}
		else if(a.getSource()==jb2){
			//弹出添加界面
			System.out.println("添加...");
			StuAddDiag sa=new StuAddDiag(this,"添加学生",true);
			//重新获得新的数据模型
			sm=new StuModel();
			jt.setModel(sm);
		}
		else if(a.getSource()==jb4){
			//删除记录
			//获得学生id
			int rowNum=this.jt.getSelectedRow();//返回用户点中的行
			if(rowNum==-1){//未选中行情况会返回一个-1
				//提示
				JOptionPane.showMessageDialog(this, "请选中一行");
				return;
			}
			//获得学生的ID
			String stuId=(String)sm.getValueAt(rowNum, 0);
			System.out.println("ID:"+stuId);
			//连接数据库，执行删除任务
			try {
				//加载驱动
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Test3的JDBC驱动加载成功");
				//连接MySQL数据库
				
				ct=DriverManager.getConnection(MySqlAccount.url, MySqlAccount.user, MySqlAccount.passwd);
				System.out.println("数据库连接成功");
				/*
				 * PreparedStatement 实例包含已编译的 SQL 语句。这就是使语句“准备好”。包含于
				   PreparedStatement 对象中的 SQL 语句可具有一个或多个 IN 参数。
				   IN参数的值在 SQL 语句创建时未被指定。相反的，该语句为每个 IN 参数保留一个问号（“？”）
				          作为占位符。每个问号的值必须在该语句执行之前，通过适当的setXXX 方法来提供。
				 */
				ps=ct.prepareStatement("delete from stuinformation where stuId=?");
				ps.setString(1, stuId);
				ps.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
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
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			sm=new StuModel();
			//更新jtable
			jt.setModel(sm);
		}
		else if(a.getSource()==jb3){
			System.out.println("用户希望修改...");
			//用户修改模块
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			//显示对话框
			System.out.println("对话框");
			StuUpDiag su=new StuUpDiag(this,"修改资料",true,sm,rowNum);
			sm=new StuModel();
			jt.setModel(sm);
		}
	}
	
}

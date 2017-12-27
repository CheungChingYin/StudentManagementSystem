package top.CheungChingYin.All;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * ���ڵ�¼���棬��֤�Ƿ��ǹ���Ա
 */
public class Login extends JFrame implements ActionListener {

	JLabel jl1,jl2;
	JTextField jf1,jf2;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3;
	
	public Login() {

		jl1=new JLabel("�˺�          ");
		jl2=new JLabel("����          ");
		
		jf1=new JTextField(10);
		jf2=new JTextField(10);
		
		jb1=new JButton("��½");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//���ò���
		jp1.setLayout(new GridLayout(2, 1));
		jp2.setLayout(new GridLayout(2, 1));
		jp3.setLayout(new GridLayout(1, 2));
		
		jp1.add(jl1);
		jp1.add(jl2);
		
		jp2.add(jf1);
		jp2.add(jf2);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		
		this.setSize(250,100);
		this.setTitle("�û���½");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);//���ô���λ��Ϊ����
		this.setVisible(true);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			
			Connection ct=null;
			ResultSet rs=null;
			Statement stat=null;
			
			String name=this.jf1.getText().trim();//����ʻ���
			String password=this.jf2.getText().trim();//������������
			
			try {
				//��������
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Login��JDBC�������سɹ�");
				ct=DriverManager.getConnection(MySqlAccount.url,MySqlAccount.user,MySqlAccount.passwd);
				String sql="SELECT * FROM login";
				stat=ct.createStatement();
				rs=stat.executeQuery(sql);//#######################
				boolean flag=true;
				while(rs.next()){
					if(name.equals(rs.getString(1)) && password.equals(rs.getString(2))){//���ﲻ��ֱ��ʹ��==��ֻ����string.equals()
						JOptionPane.showMessageDialog(this, "��¼�ɹ�");
						ManagementMain test3=new ManagementMain();//��¼�ɹ�����ʾ������
						flag=false;
						this.dispose();//��¼���ڹر�
						break;
						
					}
					
				}
				if(flag){
					JOptionPane.showMessageDialog(this, "�˻����������");//�˺��������
				}
				
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}finally {
				
				try{
					if(rs!=null){
						rs.close();//�رն���
						rs=null;//�ͷ���Դ
					}
					if(stat !=null){
						stat.close();
						stat=null;
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
		else if(e.getSource()==jb2){//���ȡ����ť
			this.dispose();
		}
		
	}
	
}

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
	//ownerָ�����ڣ�title��ָ��������modalָ�Ƿ񴰿�ģʽ
	public StuAddDiag(Frame owner, String title, boolean modal) {
		
		super(owner,title,modal);
		jl1=new JLabel("ѧ��");
		jl2=new JLabel("����");
		jl3=new JLabel("�Ա�");
		jl4=new JLabel("����");
		jl5=new JLabel("רҵ");
		jl6=new JLabel("ϵ��");
		
		jf1=new JTextField(10);
		jf2=new JTextField(10);
		jf3=new JTextField(10);
		jf4=new JTextField(10);
		jf5=new JTextField(10);
		jf6=new JTextField(10);
		
		jb1=new JButton("���");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//��������
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
		
		//frame�Ĳ���
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
				//��������
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("StuAddDiag��JDBC�������سɹ�");
				ct=DriverManager.getConnection(MySqlAccount.url,MySqlAccount.user,MySqlAccount.passwd);
				
				//�༭sql���
				String strsql="insert into stuInformation values(?,?,?,?,?,?)";
				ps=ct.prepareStatement(strsql);
				
				//������ֵ
				ps.setString(1, jf1.getText());
				ps.setString(2, jf2.getText());
				ps.setString(3, jf3.getText());
				ps.setString(4, jf4.getText());
				ps.setString(5, jf5.getText());
				ps.setString(6, jf6.getText());
				
				ps.executeUpdate();
				this.dispose();//�ر�ѧ���Ի���
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "ѧ���ظ�");
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
			this.dispose();//��ȡ�����رյ�ǰ����
		}
	}
}

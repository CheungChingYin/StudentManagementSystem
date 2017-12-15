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
//����ؼ�
	JPanel jp1,jp2;
	JLabel jl1,jl2;
	JButton jb1,jb2,jb3,jb4;//��ť
	JTable jt;//��ʾһ�����
	JScrollPane jsp;//������
	JTextField jtf;
	StuModel sm;
//�����������ݿ�ı���
	Statement stat=null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs=null;
	
	public static void main(String[] args) {
		Login login=new Login();
	}
	public ManagementMain() {
		// ���캯��
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1=new JLabel("���������֣�");
	
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
	
		jb2=new JButton("���");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
	
		jp2=new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
	
		//����ģ�����
		sm=new StuModel();
	
		//��ʼ��
		jt=new JTable(sm);
		jsp=new JScrollPane(jt);
	
		//��jsp�ŵ�jframe��
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
			System.out.println("�û�ϣ����ѯ...");
			String name=this.jtf.getText().trim();
			String sql="SELECT * FROM stuInformation WHERE stuName='"+name+"'";//SQL��ѡ�����
			sm=new StuModel(sql);
			jt.setModel(sm);
		}
		else if(a.getSource()==jb2){
			//������ӽ���
			System.out.println("���...");
			StuAddDiag sa=new StuAddDiag(this,"���ѧ��",true);
			//���»���µ�����ģ��
			sm=new StuModel();
			jt.setModel(sm);
		}
		else if(a.getSource()==jb4){
			//ɾ����¼
			//���ѧ��id
			int rowNum=this.jt.getSelectedRow();//�����û����е���
			if(rowNum==-1){//δѡ��������᷵��һ��-1
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			//���ѧ����ID
			String stuId=(String)sm.getValueAt(rowNum, 0);
			System.out.println("ID:"+stuId);
			//�������ݿ⣬ִ��ɾ������
			try {
				//��������
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Test3��JDBC�������سɹ�");
				//����MySQL���ݿ�
				
				ct=DriverManager.getConnection(MySqlAccount.url, MySqlAccount.user, MySqlAccount.passwd);
				System.out.println("���ݿ����ӳɹ�");
				/*
				 * PreparedStatement ʵ�������ѱ���� SQL ��䡣�����ʹ��䡰׼���á���������
				   PreparedStatement �����е� SQL ���ɾ���һ������ IN ������
				   IN������ֵ�� SQL ��䴴��ʱδ��ָ�����෴�ģ������Ϊÿ�� IN ��������һ���ʺţ���������
				          ��Ϊռλ����ÿ���ʺŵ�ֵ�����ڸ����ִ��֮ǰ��ͨ���ʵ���setXXX �������ṩ��
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
			//����jtable
			jt.setModel(sm);
		}
		else if(a.getSource()==jb3){
			System.out.println("�û�ϣ���޸�...");
			//�û��޸�ģ��
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			//��ʾ�Ի���
			System.out.println("�Ի���");
			StuUpDiag su=new StuUpDiag(this,"�޸�����",true,sm,rowNum);
			sm=new StuModel();
			jt.setModel(sm);
		}
	}
	
}

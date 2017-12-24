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
 * ����������޸�ĳһ�е�����
 */
public class StuUpDiag extends JDialog implements ActionListener {

	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jf1,jf2,jf3,jf4,jf5,jf6;
	JPanel jp1,jp2,jp3;
	JButton jb1,jb2;
	String sourceID;
	
	public StuUpDiag(Frame owner, String title, boolean modal, StuModel sm, int rowNum) {
		
		super(owner,title,modal);//���ڱ�ʾһ�������ĶԻ���
		
		jl1=new JLabel("ѧ��");
		jl2=new JLabel("����");
		jl3=new JLabel("�Ա�");
		jl4=new JLabel("����");
		jl5=new JLabel("רҵ");
		jl6=new JLabel("ϵ��");
		
		//�����ı��򣬻�ȡ���ݿ��е������Ա��޸�
		jf1=new JTextField(10);
		jf1.setText((sm.getValueAt(rowNum, 0).toString()));//�������������ѡ���е�ѧ��
		sourceID=sm.getValueAt(rowNum, 0).toString();//��¼ѡ���е�ѧ��ѧ��
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
		
		//���ð�ť
		jb1=new JButton("�޸�");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		
		//����jpanel
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//Jpanel���ò���
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
				System.out.println("StuUpDiag��JDBC�������سɹ�");
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
				//������ֵ
				
				while(rs.next()){
					if(rs.getString(1).equals(jf1.getText()) && flag){
						JOptionPane.showMessageDialog(this, "ѧ���ظ�");
					}
					else if(jf1.getText().length()<15 &&jf1.getText().length()!=0 ||jf1.getText().length()>15){
						JOptionPane.showMessageDialog(this, "ѧ�ų��ȱ���ҪΪ15λ�����������룡");
						break;
					}else if(jf1.getText().length()==0){
						JOptionPane.showMessageDialog(this, "ѧ�Ų���Ϊ�գ�");
						break;
					}else{
						ps.setString(1, jf1.getText());
					}
					
				}
				
				
				
				if(jf2.getText().length()>=1){
					ps.setString(2, jf2.getText());
				}else{
					JOptionPane.showMessageDialog(this, "��������Ϊ��");
				}
				
				
				if(jf3.getText().equals("��") || jf3.getText().equals("Ů")){
					ps.setString(3, jf3.getText());
				}else if(jf3.getText().length()==0){
					JOptionPane.showMessageDialog(this, "�Ա���Ϊ�գ�");
				}else{
					JOptionPane.showMessageDialog(this, "�Ա�ֻ���� ���С� �� ��Ů�� ��");
				}
				
				
				if(jf4.getText().length()<=2 && jf4.getText().length() !=0){
					ps.setString(4, jf4.getText());
				}else if(jf4.getText().length()==0){
					JOptionPane.showMessageDialog(this, "���䲻��Ϊ�գ�");
				}else{
					JOptionPane.showMessageDialog(this, "�����ʽ���ԣ�");
				}
				
				
				if(jf5.getText().length()>=1){
					ps.setString(5, jf5.getText());
				}else{
					JOptionPane.showMessageDialog(this, "רҵ����Ϊ�� ��");
				}
				
				
				if(jf6.getText().length()>=1){
					ps.setString(6, jf6.getText());
				}else{
					JOptionPane.showMessageDialog(this, "ϵ������Ϊ�գ�");
				}
				ps.setString(7, sourceID);
				ps.executeUpdate();
				this.dispose();//�ر�ѧ���Ի���
				
			} catch (Exception e2) {
				e2.printStackTrace();
//				JOptionPane.showMessageDialog(this, "ע�⣡ѧ���ظ���");
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

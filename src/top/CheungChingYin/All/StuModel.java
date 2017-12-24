package top.CheungChingYin.All;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.*;
/*
 * ��������ڿ��Ʊ����ʾ������
 */
public class StuModel extends AbstractTableModel {
	
	//���������
	Vector rowData,columnNames;//Vector���ڲ��빦��
	
	//�������ݿ����
	Statement stat=null;
	Connection ct=null;
	ResultSet rs=null;
	
	//��ʼ��
	public void init(String sql){
		if(sql.equals("")){//���SQL���Ϊ�վ�ִ��һ��SQL���
			sql="select * from stuInformation";
			
		}
		
		//��������
		columnNames=new Vector();
		columnNames.add("ѧ��");//����ÿ���е�����
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("רҵ");
		columnNames.add("ϵ��");
		
		rowData=new Vector();//��Ŷ���
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("StuModel��JDBC�������سɹ�");
			ct=DriverManager.getConnection(MySqlAccount.url,MySqlAccount.user,MySqlAccount.passwd);
			stat=ct.createStatement();
			rs=stat.executeQuery(sql);//ִ�в�ѯ
			
			while(rs.next()){//����һ��
				Vector hang = new Vector();
				hang.add(rs.getString(1));//��ȡ���ݿ�ĳ�е�һ������
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				
				rowData.add(hang);//��ÿһ��������ʱ��������
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void addStu(String sql){
		//ִ���û������sql���
	}

	public StuModel(String sql) {
		//ͨ������sql������������ģ��
		this.init(sql);
	}

	public StuModel() {
		//��ʼ������ģ��
		this.init("");
	}

	@Override
	public int getRowCount() {//�����ж�����
		return rowData.size();
	}

	@Override
	public int getColumnCount() {//�����ж�����
		return this.columnNames.size();
	}

	@Override
	//���ĳ��ĳ������
	public Object getValueAt(int row, int column) {//����ĳ��ĳ�е�һ������
		return ((Vector)(this.rowData.get(row))).get(column);
	}
	
	//�õ���������
	public String getColumnName(int column){//���صڼ���
		return (String)this.columnNames.get(column);
	}

}

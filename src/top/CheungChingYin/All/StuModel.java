package top.CheungChingYin.All;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.*;
/*
 * 这个类用于控制表格显示的内容
 */
public class StuModel extends AbstractTableModel {
	
	//存放行数据
	Vector rowData,columnNames;//Vector用于插入功能
	
	//定义数据库变量
	Statement stat=null;
	Connection ct=null;
	ResultSet rs=null;
	
	//初始化
	public void init(String sql){
		if(sql.equals("")){//如果SQL语句为空就执行一下SQL语句
			sql="select * from stuInformation";
			
		}
		
		//设置列名
		columnNames=new Vector();
		columnNames.add("学号");//设置每个列的名字
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("专业");
		columnNames.add("系部");
		
		rowData=new Vector();//存放多行
		try {
			//驱动加载
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("StuModel的JDBC驱动加载成功");
			ct=DriverManager.getConnection(MySqlAccount.url,MySqlAccount.user,MySqlAccount.passwd);
			stat=ct.createStatement();
			rs=stat.executeQuery(sql);//执行查询
			
			while(rs.next()){//下移一行
				Vector hang = new Vector();
				hang.add(rs.getString(1));//获取数据库某行第一列数据
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				
				rowData.add(hang);//把每一行数据暂时储存起来
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		
			try{
				if(rs!=null){
					rs.close();//关闭对象
					rs=null;//释放资源
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
		//执行用户输入的sql语句
	}

	public StuModel(String sql) {
		//通过传递sql语句来获得数据模型
		this.init(sql);
	}

	public StuModel() {
		//初始化数据模型
		this.init("");
	}

	@Override
	public int getRowCount() {//返回有多少行
		return rowData.size();
	}

	@Override
	public int getColumnCount() {//返回有多少列
		return this.columnNames.size();
	}

	@Override
	//获得某行某列数据
	public Object getValueAt(int row, int column) {//返回某行某列的一个数据
		return ((Vector)(this.rowData.get(row))).get(column);
	}
	
	//得到属性名字
	public String getColumnName(int column){//返回第几列
		return (String)this.columnNames.get(column);
	}

}

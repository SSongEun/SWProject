import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class ProductDAO {
	
	String jdbcDriver = "com.mysql.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/javadb";
	Connection conn;
	//�ڹٿ� JDBC�� �����ϱ� ����
	
	PreparedStatement pstmt;
	ResultSet rs;
	//��ɿ� ���� ��ȯ���� ��ɾ �����ϱ� ����
	
	Vector<String> items = null;
	//items����  ũ�Ⱑ �������� ���ϴ� �ڷᱸ�� Vector�� ����
	String sql;
	//��ɾ �����ϱ� ���� String ����
	
	public void connectDB(){
		try{
			Class.forName(jdbcDriver);
			
			conn = DriverManager.getConnection(jdbcUrl,"javabook","1234");
			System.out.println("DB ���� ����!!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//DB�� �����ϱ� ���ؼ� ������ ��й�ȣ�� �̿��� �����Ͽ� ������ �����Ǹ� ���� �޽����� �����
	public void closeDB(){
		try{
			pstmt.close();
			rs.close();
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//DB�� ������ ���� ����

	
	public ArrayList<Product> getAll(){
		connectDB();
		sql = "select * from product";
		
		ArrayList<Product> datas = new ArrayList<Product>();
		
		items = new Vector<String>();
		items.add("��ü");
		try{
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			Product p = new Product();
			p.setPrcode(rs.getInt("prcode"));
			p.setPrname(rs.getString("prname"));
			p.setPrice(rs.getInt("price"));
			p.setManufacture(rs.getString("manufacture"));
			datas.add(p);
			items.add(String.valueOf(rs.getInt("prcode")));
		}
		rs.close();
		System.out.println("ó���Ϸ�");
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeDB();
		return datas;
	}
	//product ���̺� ����� �����͵��� ���� get�ϱ� ���� �Լ�
	//rs�� ���� �����ͷ� �Ű����鼭 �����Ͱ� ���� ������ �����͸� �о����
	public Product getProduct(int prcode){
		connectDB();
		sql = "select * from product where prcode=?";
		Product p = null;
		
		try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, prcode);
		rs = pstmt.executeQuery();
		
		rs.next();
		p = new Product();
		p.setPrcode(rs.getInt("prcode"));
		p.setPrname(rs.getString("prname"));
		p.setPrice(rs.getInt("price"));
		p.setManufacture(rs.getString("manufacture"));
		rs.close();
		System.out.println("ó���Ϸ�");
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeDB();
		return p;
	}
	//product ���̺��� prcode�� �̿��� �����͸� get �ϱ� ���� �Լ�
	//prcode�� �޾Ƽ� ���̺� ���� �ִ� ������ �� prcode�� ������ �� Ʃ���� �������� ����
	public boolean newProduct(Product product){
		connectDB();
		sql = "Insert into product(prname,price,manufacture) values(?,?,?)";
		
		int result = 0;
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getPrname());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getManufacture());
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeDB();
		if(result>0)
			return true;
		else
			return false;
	}
	//���ο� �����͸� �����ϱ� ���� �Լ�
	//prname, price, manufacture ���� �����Ͽ� �������� �����Ѵ�.
	//�� �� ������Ʈ �� ��ɾ 1�� �̻��̸� true ���� ��ȯ�Ͽ� ������� �ְ�,
	//�׷��� ������ false���� ��ȯ�Ͽ� �ش�.
	
	public boolean delProduct(int prcode){
		connectDB();
		sql="delete from product where prcode=?";
		boolean result=false;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, prcode);
			if(pstmt.executeUpdate()>0)
				result =true;
			else 
				result=false;
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeDB();
		return result;
	}
	//�����͸� �����ϱ� ���� �Լ�
	//product ���̺��� prcode�� �̿��� �ش� Ʃ���� �����ϱ� ���� �Լ�
	//�� �� ������Ʈ �� ��ɾ 1�� �̻��̸� true ���� ��ȯ�Ͽ� ������� �ְ�,
	//�׷��� ������ false���� ��ȯ�Ͽ� �ش�.
	
	public boolean updateProduct(Product product){
		connectDB();
		sql="update product set prname=?, price=?, manufacture=? where prcode=?";
		int result=0;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, product.getPrname());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getManufacture());
			pstmt.setInt(4, product.getPrcode());
			result=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeDB();
		if(result>0)
			return true;
		else
			return false;
	}
	//�̹� ����� �����͸� �����ϱ� ���� �Լ�
	//���� �� ���� ������Ʈ�ؼ� ���̺� Ʃ���� ������Ʈ ����
	//�� �� ������Ʈ �� ��ɾ 1�� �̻��̸� true ���� ��ȯ�Ͽ� ������� �ְ�,
	//�׷��� ������ false���� ��ȯ�Ͽ� �ش�.
	
	public Vector<String> getItems(){
		return items;
	}
	//Vector�� ���� �� �����۵��� ��ȯ���ִ� �Լ� 
}


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
	//자바와 JDBC를 연동하기 위함
	
	PreparedStatement pstmt;
	ResultSet rs;
	//명령에 대한 반환값과 명령어를 저장하기 위함
	
	Vector<String> items = null;
	//items들을  크기가 동적으로 변하는 자료구조 Vector로 선언
	String sql;
	//명령어를 저장하기 위한 String 변수
	
	public void connectDB(){
		try{
			Class.forName(jdbcDriver);
			
			conn = DriverManager.getConnection(jdbcUrl,"javabook","1234");
			System.out.println("DB 연결 성공!!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//DB에 연결하기 위해서 계정과 비밀번호를 이용해 접속하여 연결이 성공되면 성공 메시지를 띄워줌
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
	//DB에 접속을 끊기 위함

	
	public ArrayList<Product> getAll(){
		connectDB();
		sql = "select * from product";
		
		ArrayList<Product> datas = new ArrayList<Product>();
		
		items = new Vector<String>();
		items.add("전체");
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
		System.out.println("처리완료");
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeDB();
		return datas;
	}
	//product 테이블에 저장된 데이터들을 전부 get하기 위한 함수
	//rs가 다음 데이터로 옮겨지면서 데이터가 없을 때까지 데이터를 읽어와줌
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
		System.out.println("처리완료");
		}catch(SQLException e){
			e.printStackTrace();
		}
		closeDB();
		return p;
	}
	//product 테이블에서 prcode를 이용해 데이터를 get 하기 위한 함수
	//prcode를 받아서 테이블 내에 있는 데이터 중 prcode와 같으면 그 튜플을 가져오기 위함
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
	//새로운 데이터를 삽입하기 위한 함수
	//prname, price, manufacture 값을 삽입하여 쿼리문을 실행한다.
	//이 때 업데이트 된 명령어가 1개 이상이면 true 값을 반환하여 실행시켜 주고,
	//그렇지 않으면 false값을 반환하여 준다.
	
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
	//데이터를 삭제하기 위한 함수
	//product 테이블에서 prcode를 이용해 해당 튜플을 삭제하기 위한 함수
	//이 때 업데이트 된 명령어가 1개 이상이면 true 값을 반환하여 실행시켜 주고,
	//그렇지 않으면 false값을 반환하여 준다.
	
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
	//이미 저장된 데이터를 수정하기 위한 함수
	//수정 된 값을 업데이트해서 테이블 튜플을 업데이트 해줌
	//이 때 업데이트 된 명령어가 1개 이상이면 true 값을 반환하여 실행시켜 주고,
	//그렇지 않으면 false값을 반환하여 준다.
	
	public Vector<String> getItems(){
		return items;
	}
	//Vector로 저장 된 아이템들을 반환해주는 함수 
}


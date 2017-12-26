import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class AppMain extends JFrame implements ActionListener{
	Boolean editmode=false;
	JPanel p1 = new JPanel();
	//왼쪽에 배치할 패널을 선언하기 위함
    JLabel[] n1 = new JLabel[4]; 
    // p1에 배치할 라벨을 선언하기 위함
	JLabel m1 = new JLabel(" ##메세지: 상품 정보를 가져왔습니다!!");
	// 패널 가장 위의 메세지 출력을 하기 위함

	JPanel p2 = new JPanel();
	//가운데에 배치할 패널을 선언하기 위함
	JComboBox cb = new JComboBox();
	// p2에 들어갈 콤보박스를 생성하기 위함
	JTextField tf[] = new JTextField[3];
	// p2에 들어갈 textField를 생성하기 위함

	JScrollPane sp = new JScrollPane();
	// textField에 스크롤을 추가하기 위함
	JTextArea ta = new JTextArea();
	// 전체 목록 출력하기 위한 공간을 만들기 위함

	JPanel p3 = new JPanel();
	// p3 패널 선언하기 위함
	JButton[] btn = new JButton[3];
	// 버튼 3개 선언하기 위함
	ProductDAO dao = new ProductDAO();
	Product product = new Product();
	//제품관련 데이터를 다루기 위해 생성
	
	  public AppMain() {
	      setSize(800, 500);
	      // 전체 사이즈 설정하기 위함
	      startUI();
	      // UI 디스플레이 함수 호출하기 위함
	      refreshData();
	      //처음 시작할 때 
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.setVisible(true);
	      //창을 닫을 수 있고, 눈에 보여주기 위함
	   }

	   private void startUI() {
	      m1.setPreferredSize(new Dimension(800, 50));
	      this.getContentPane().add(BorderLayout.NORTH, m1);
	      //m1을 borderLayout을 사용하여 맨 위에 배치하기 위함

	      p1.setPreferredSize(new Dimension(100, 300));
	      p1.setSize(100, 400);
	      p1.setLayout(new GridLayout(4, 1));
	      // p1 사이즈 지정 후 라벨을 4행 1열로 생성하기 위함

	      n1[0] = new JLabel("관리번호");
	      n1[1] = new JLabel("상품명");
	      n1[2] = new JLabel("단가");
	      n1[3] = new JLabel("제조사");
	      //라벨명을 지정해주기 위함
	      
	      for (int i = 0; i < 4; i++) {
	         p1.add(n1[i]);
	         //라벨들을 p1패널에 추가시켜줌
	      }
	      this.getContentPane().add(BorderLayout.WEST, p1);
	      //p1을 borderLayout을 사용하여 맨 위에 배치하기 위함
	      p2.setPreferredSize(new Dimension(100, 300));
	      p2.setLayout(new GridLayout(4, 1));
	   // p2 사이즈 지정 후 4행 1열로 생성하기 위함
	      cb = new JComboBox();
	      //콤보박스 생성
	      p2.add(cb);
	      // p2에 콤보박스 추가
	      tf[0] = new JTextField(15);
	      tf[1] = new JTextField(15);
	      tf[2] = new JTextField(15);
	   // 텍스트 필드 크기 선언하기 위함

	      for (int i = 0; i < 3; i++) {
	         p2.add(tf[i]);
	      // p2에 텍스트 필드 추가하기 위함
	      }
	      p2.setSize(100, 400);
	      this.getContentPane().add(BorderLayout.CENTER, p2);
	    //p2을 borderLayout을 사용하여 중앙에 배치하기 위함

	      ta.setEditable(false);
	      //사용자 마음대로 수정하기 못하게 하기 위함
	      sp.setPreferredSize(new Dimension(500, 400));
	      //사이즈 지정
	      sp.setViewportView(ta);//
	      // 가로 세로 스크롤을 추가하기 위함
	      sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      //항상 스크롤바를 보여주게 하기 위함
	      sp.setSize(new Dimension(500, 400));
	      this.getContentPane().add(BorderLayout.EAST, sp);
	    //sp을 borderLayout을 사용하여 오른쪽에 배치하기 위함
	      p3.setLayout(null);
	      //레이아웃 null 설정

	      p3.setPreferredSize(new Dimension(800, 50));
	     
	      btn[0] = new JButton("등록");
	      btn[0].setBounds(250, 15, 100, 20);
	      btn[1] = new JButton("조회");
	      btn[1].setBounds(360, 15, 100, 20);
	      btn[2] = new JButton("삭제");
	      btn[2].setBounds(470, 15, 100, 20);
	   // 버튼 이름, 사이즈, 배치를 설정하기 위함
	      for (int i = 0; i < 3; i++) {
	         p3.add(btn[i]);
	         btn[i].addActionListener(this);
	      // 버튼 p3에 추가
	      //버튼에 액션리스너 추가(등록, 수정, 조회, 삭제)
	      }
	      this.getContentPane().add(BorderLayout.SOUTH, p3);
	    //p3을 borderLayout을 사용하여 맨밑에 배치하기 위함
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			product = new Product();
			//버튼을 구별하여 다른 액션을 취하도록 함
			System.out.println("버튼눌림");
			if(obj==btn[0]){
				product.setPrname(tf[0].getText());
				product.setPrice(Integer.parseInt(tf[1].getText()));
				product.setManufacture(tf[2].getText());
				System.out.println("버튼눌림");
			//등록버튼을 누르면 수정이나 추가 기능을 수행하게 되는데 상품의 이름과 가격, 제조업체를 넣어준다.
				
			if(editmode == true){
				product.setPrcode(Integer.parseInt((String)cb.getSelectedItem()));
				if(dao.updateProduct(product)){
					m1.setText("상품을 수정했습니다!");
					clearField();
					editmode = false;
				}//상품을 수정하고 다시 editMode를 false 시켜줌
				else
					m1.setText("상품 수정이 실패했습니다!");
				//상품수정 실패
			}
			//수정모드 값이 true일 때 수정하기 위함
			else{
				if(dao.newProduct(product)){
					m1.setText("상품을 등록했습니다!!");
				}//상품 등록
				else
					m1.setText("상품 등록이 실패했습니다!!");
				//상품 등록 실패
			}//수정모드 값이 false일 때 등록하기 위함
			
			refreshData();
			//데이터 갱신
			}
			else if(obj == btn[1]){
				String s =(String)cb.getSelectedItem();
				if(!s.equals("전체")){
					product = dao.getProduct(Integer.parseInt(s));
					if(product != null){
						m1.setText("상품정보를 가져왔습니다!!");
						tf[0].setText(product.getPrname());
						tf[1].setText(String.valueOf(product.getPrice()));
						tf[2].setText(product.getManufacture());
						editmode = true;
					}//전체를 선택한게 아니라면 선택된 prcode에 해당하는 상품의 정보를 불러와주고 editmode를 true로 바꿔준다.
					else{
						m1.setText("상품이 검색되지 않았습니다!!");
					}
					//상품 검색 실패
				}
			}// 상품 조회 기능을 구현하기 위함
				else if(obj == btn[2]){
					String s =(String)cb.getSelectedItem();
					if(s.equals("전체"))
						m1.setText("전체 삭제는 불가능합니다!!");
					//전체를 선택했다면 불가능하다는 메시지를 띄워주게 하기 위함
					else{
						//전체가 아닌 하나의 상품을 선택했다면
						if(dao.delProduct(Integer.parseInt(s))){
							m1.setText("상품이 삭제되었습니다!!");
						}
						//prcode값에 해당하는 상품을 삭제해줌
						else{
							m1.setText("상품이 삭제되지 않았습니다!!");
						//상품 삭제 실패
						}
					}
					refreshData();
					//데이터 갱신하기 위함
			}//삭제 기능을 구현하기 위함
			
		}
		public void refreshData() {
		      ta.setText("");
		      ArrayList<Product> datas;
		      clearField();
		      editmode = false;
		      //초기화, 필요한 데이터를 생성해주고 editmode를 false시킴
		      ta.append("관리번호\t상품명\t\t단가\t제조사\n");
		      //textArea 위에 관리번호, 상품명, 단가, 제조사를 써줌
		      datas = dao.getAll();
		      //dao에 저장된 데이터들을 모두 datas에 저장
		      
		      cb.setModel(new DefaultComboBoxModel(dao.getItems()));
		   // 데이터를 변경하면 콤보박스 갱신
		      if (datas != null) {
		         for (Product p : datas) {
		            StringBuffer sb = new StringBuffer();
		            sb.append(p.getPrcode() + "\t");
		            sb.append(p.getPrname() + "\t\t");
		            sb.append(p.getPrice() + "\t");
		            sb.append(p.getManufacture() + "\n");
		            ta.append(sb.toString());
		         }
		      } 
		   // datas에 데이터가 들어있다면 for문을 이용하여 ArrayList의 전체 데이터를 형식에 맞춰 출력해줌
		      else {
		         ta.append("등록된 상품이 없습니다. !!\n상품을 등록해 주세요!!");
		      }
		      //datas에 데이터가 없다면 등록된 데이터가 없다고 알려줌
		   }
		public void clearField(){
			tf[0].setText("");
			tf[1].setText("");
			tf[2].setText("");
		}
		//텍스트 필드를 초기화 시켜줌
		     
}
			
	
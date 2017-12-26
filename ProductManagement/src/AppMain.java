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
	//���ʿ� ��ġ�� �г��� �����ϱ� ����
    JLabel[] n1 = new JLabel[4]; 
    // p1�� ��ġ�� ���� �����ϱ� ����
	JLabel m1 = new JLabel(" ##�޼���: ��ǰ ������ �����Խ��ϴ�!!");
	// �г� ���� ���� �޼��� ����� �ϱ� ����

	JPanel p2 = new JPanel();
	//����� ��ġ�� �г��� �����ϱ� ����
	JComboBox cb = new JComboBox();
	// p2�� �� �޺��ڽ��� �����ϱ� ����
	JTextField tf[] = new JTextField[3];
	// p2�� �� textField�� �����ϱ� ����

	JScrollPane sp = new JScrollPane();
	// textField�� ��ũ���� �߰��ϱ� ����
	JTextArea ta = new JTextArea();
	// ��ü ��� ����ϱ� ���� ������ ����� ����

	JPanel p3 = new JPanel();
	// p3 �г� �����ϱ� ����
	JButton[] btn = new JButton[3];
	// ��ư 3�� �����ϱ� ����
	ProductDAO dao = new ProductDAO();
	Product product = new Product();
	//��ǰ���� �����͸� �ٷ�� ���� ����
	
	  public AppMain() {
	      setSize(800, 500);
	      // ��ü ������ �����ϱ� ����
	      startUI();
	      // UI ���÷��� �Լ� ȣ���ϱ� ����
	      refreshData();
	      //ó�� ������ �� 
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.setVisible(true);
	      //â�� ���� �� �ְ�, ���� �����ֱ� ����
	   }

	   private void startUI() {
	      m1.setPreferredSize(new Dimension(800, 50));
	      this.getContentPane().add(BorderLayout.NORTH, m1);
	      //m1�� borderLayout�� ����Ͽ� �� ���� ��ġ�ϱ� ����

	      p1.setPreferredSize(new Dimension(100, 300));
	      p1.setSize(100, 400);
	      p1.setLayout(new GridLayout(4, 1));
	      // p1 ������ ���� �� ���� 4�� 1���� �����ϱ� ����

	      n1[0] = new JLabel("������ȣ");
	      n1[1] = new JLabel("��ǰ��");
	      n1[2] = new JLabel("�ܰ�");
	      n1[3] = new JLabel("������");
	      //�󺧸��� �������ֱ� ����
	      
	      for (int i = 0; i < 4; i++) {
	         p1.add(n1[i]);
	         //�󺧵��� p1�гο� �߰�������
	      }
	      this.getContentPane().add(BorderLayout.WEST, p1);
	      //p1�� borderLayout�� ����Ͽ� �� ���� ��ġ�ϱ� ����
	      p2.setPreferredSize(new Dimension(100, 300));
	      p2.setLayout(new GridLayout(4, 1));
	   // p2 ������ ���� �� 4�� 1���� �����ϱ� ����
	      cb = new JComboBox();
	      //�޺��ڽ� ����
	      p2.add(cb);
	      // p2�� �޺��ڽ� �߰�
	      tf[0] = new JTextField(15);
	      tf[1] = new JTextField(15);
	      tf[2] = new JTextField(15);
	   // �ؽ�Ʈ �ʵ� ũ�� �����ϱ� ����

	      for (int i = 0; i < 3; i++) {
	         p2.add(tf[i]);
	      // p2�� �ؽ�Ʈ �ʵ� �߰��ϱ� ����
	      }
	      p2.setSize(100, 400);
	      this.getContentPane().add(BorderLayout.CENTER, p2);
	    //p2�� borderLayout�� ����Ͽ� �߾ӿ� ��ġ�ϱ� ����

	      ta.setEditable(false);
	      //����� ������� �����ϱ� ���ϰ� �ϱ� ����
	      sp.setPreferredSize(new Dimension(500, 400));
	      //������ ����
	      sp.setViewportView(ta);//
	      // ���� ���� ��ũ���� �߰��ϱ� ����
	      sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      //�׻� ��ũ�ѹٸ� �����ְ� �ϱ� ����
	      sp.setSize(new Dimension(500, 400));
	      this.getContentPane().add(BorderLayout.EAST, sp);
	    //sp�� borderLayout�� ����Ͽ� �����ʿ� ��ġ�ϱ� ����
	      p3.setLayout(null);
	      //���̾ƿ� null ����

	      p3.setPreferredSize(new Dimension(800, 50));
	     
	      btn[0] = new JButton("���");
	      btn[0].setBounds(250, 15, 100, 20);
	      btn[1] = new JButton("��ȸ");
	      btn[1].setBounds(360, 15, 100, 20);
	      btn[2] = new JButton("����");
	      btn[2].setBounds(470, 15, 100, 20);
	   // ��ư �̸�, ������, ��ġ�� �����ϱ� ����
	      for (int i = 0; i < 3; i++) {
	         p3.add(btn[i]);
	         btn[i].addActionListener(this);
	      // ��ư p3�� �߰�
	      //��ư�� �׼Ǹ����� �߰�(���, ����, ��ȸ, ����)
	      }
	      this.getContentPane().add(BorderLayout.SOUTH, p3);
	    //p3�� borderLayout�� ����Ͽ� �ǹؿ� ��ġ�ϱ� ����
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			product = new Product();
			//��ư�� �����Ͽ� �ٸ� �׼��� ���ϵ��� ��
			System.out.println("��ư����");
			if(obj==btn[0]){
				product.setPrname(tf[0].getText());
				product.setPrice(Integer.parseInt(tf[1].getText()));
				product.setManufacture(tf[2].getText());
				System.out.println("��ư����");
			//��Ϲ�ư�� ������ �����̳� �߰� ����� �����ϰ� �Ǵµ� ��ǰ�� �̸��� ����, ������ü�� �־��ش�.
				
			if(editmode == true){
				product.setPrcode(Integer.parseInt((String)cb.getSelectedItem()));
				if(dao.updateProduct(product)){
					m1.setText("��ǰ�� �����߽��ϴ�!");
					clearField();
					editmode = false;
				}//��ǰ�� �����ϰ� �ٽ� editMode�� false ������
				else
					m1.setText("��ǰ ������ �����߽��ϴ�!");
				//��ǰ���� ����
			}
			//������� ���� true�� �� �����ϱ� ����
			else{
				if(dao.newProduct(product)){
					m1.setText("��ǰ�� ����߽��ϴ�!!");
				}//��ǰ ���
				else
					m1.setText("��ǰ ����� �����߽��ϴ�!!");
				//��ǰ ��� ����
			}//������� ���� false�� �� ����ϱ� ����
			
			refreshData();
			//������ ����
			}
			else if(obj == btn[1]){
				String s =(String)cb.getSelectedItem();
				if(!s.equals("��ü")){
					product = dao.getProduct(Integer.parseInt(s));
					if(product != null){
						m1.setText("��ǰ������ �����Խ��ϴ�!!");
						tf[0].setText(product.getPrname());
						tf[1].setText(String.valueOf(product.getPrice()));
						tf[2].setText(product.getManufacture());
						editmode = true;
					}//��ü�� �����Ѱ� �ƴ϶�� ���õ� prcode�� �ش��ϴ� ��ǰ�� ������ �ҷ����ְ� editmode�� true�� �ٲ��ش�.
					else{
						m1.setText("��ǰ�� �˻����� �ʾҽ��ϴ�!!");
					}
					//��ǰ �˻� ����
				}
			}// ��ǰ ��ȸ ����� �����ϱ� ����
				else if(obj == btn[2]){
					String s =(String)cb.getSelectedItem();
					if(s.equals("��ü"))
						m1.setText("��ü ������ �Ұ����մϴ�!!");
					//��ü�� �����ߴٸ� �Ұ����ϴٴ� �޽����� ����ְ� �ϱ� ����
					else{
						//��ü�� �ƴ� �ϳ��� ��ǰ�� �����ߴٸ�
						if(dao.delProduct(Integer.parseInt(s))){
							m1.setText("��ǰ�� �����Ǿ����ϴ�!!");
						}
						//prcode���� �ش��ϴ� ��ǰ�� ��������
						else{
							m1.setText("��ǰ�� �������� �ʾҽ��ϴ�!!");
						//��ǰ ���� ����
						}
					}
					refreshData();
					//������ �����ϱ� ����
			}//���� ����� �����ϱ� ����
			
		}
		public void refreshData() {
		      ta.setText("");
		      ArrayList<Product> datas;
		      clearField();
		      editmode = false;
		      //�ʱ�ȭ, �ʿ��� �����͸� �������ְ� editmode�� false��Ŵ
		      ta.append("������ȣ\t��ǰ��\t\t�ܰ�\t������\n");
		      //textArea ���� ������ȣ, ��ǰ��, �ܰ�, �����縦 ����
		      datas = dao.getAll();
		      //dao�� ����� �����͵��� ��� datas�� ����
		      
		      cb.setModel(new DefaultComboBoxModel(dao.getItems()));
		   // �����͸� �����ϸ� �޺��ڽ� ����
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
		   // datas�� �����Ͱ� ����ִٸ� for���� �̿��Ͽ� ArrayList�� ��ü �����͸� ���Ŀ� ���� �������
		      else {
		         ta.append("��ϵ� ��ǰ�� �����ϴ�. !!\n��ǰ�� ����� �ּ���!!");
		      }
		      //datas�� �����Ͱ� ���ٸ� ��ϵ� �����Ͱ� ���ٰ� �˷���
		   }
		public void clearField(){
			tf[0].setText("");
			tf[1].setText("");
			tf[2].setText("");
		}
		//�ؽ�Ʈ �ʵ带 �ʱ�ȭ ������
		     
}
			
	
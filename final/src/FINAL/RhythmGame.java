package FINAL;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RhythmGame {

	private JFrame th;
	//frame�� upcall����Ҷ� ����Ҽ� �ֵ��� ������ �ϴ� th ���� ����
	private JPanel primary;
	private JPanel THEFirstScreen;
	//primary�� ��ġ�� �����ϱ� ���� �������� ���� ������ ���� �г��� upcall�Ҷ� �ʿ��� ���̰�
	//THEFirstScreen�� �ʹݿ� �����Ҷ� �����г�2�� �ִ°��� upcall�������� �θ��� �ʿ��� ���̴�.
	
	
	
	
	private ImageIcon introBackground = new ImageIcon(Main.class.getResource("../images/crowd.jpg"));
	// � ���ȭ���� ��������� ���Ѵ�.
	
	private JPanel MusicPanel = new JPanel();
	private JLabel BackGround = new JLabel(introBackground);
	private JLabel LOGO= new JLabel(new ImageIcon(Main.class.getResource("../images/logo.png")));
	//MUSICPANEL�� JPanel�̰�
	//BACKGROUND�� JLABEL�ν� ���ȭ���� ��´�.
	//LOGO�� �ΰ� �̹����� ��� ���̴�.
	 
	
	
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/start.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/start_Entered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/end.png"));
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/end_Entered.png"));
	//STARTBUTTON,BACKBUTTON,EXITBUTTON�� ���� �̹����� �����Ͽ���
	//���� ����ڰ� ���콺�� ENTER�������� EXIT�������� ���� �̹������̴�.
	//��, ȣ�����Ҷ� �̹����� ���� �ٲܼ� �ֵ��� �̹��� �������� �޾Ƴ��´�.
	
	private JButton Back = new JButton(backButtonBasicImage);
	private JButton Start = new JButton(startButtonBasicImage);
	private JButton End = new JButton(exitButtonBasicImage);
	//BACK,START,END ��ư���� �����Ѵ�.���� �⺻ �̹����� �������� �Ѵ�
	private RML MouseL;
	//��ư�� �������� �ൿ�� ������ �����ʸ� �����Ѵ�.
	Music introMusic = new Music("ShineLikeaStar.mp3", true);
	//���� �뷡�� �����Ѵ�. ���� �ݺ����� �����Ѵ�.
	public RhythmGame(JPanel p,JPanel MainScreen,JFrame t) {
		th=t;
		primary=p;
		THEFirstScreen=MainScreen;
		//UPCALL������ �����ڷ� �Է¹��� �͵�� �����Ű��
		
		
		MusicPanel.setPreferredSize(new Dimension(Main.screenWidth, Main.screenHeight));
		MusicPanel.setLayout(null);
		MusicPanel.setBounds(0, 0, 1280, 690);
		//MUSICPANELũ��� LAYOUT�� ��ġ ����
		
		BackGround.setSize(new Dimension(1280, 690));
		BackGround.setBounds(0, 0, 1280, 690);
		BackGround.setLayout(null);
		//BACKGROUND�� ũ��� LAYOUT�� ��ġ ����
		
		MouseL = new RML();
		//���콺 ������ ������.
		
		
		
		//��ư ��ġ�� ������ ����� �����Ѵ�
		Back.setBounds(0, 0, 50, 50);
		buttonInit(Back);
		Start.setBounds(100, 500, 400, 100);
		buttonInit(Start);
		End.setBounds(800, 500, 400, 100);
		buttonInit(End);
		////////////////////////////////////////
		
		LOGO.setBounds(1050,0,200,200);
		//�ΰ� ��ġ ����
		
		BackGround.add(Back);
		BackGround.add(Start);
		BackGround.add(End);
		BackGround.add(LOGO);
		//BACKGROUND�� ��ư ���ϰ�
		
		Back.addMouseListener(MouseL);
		Start.addMouseListener(MouseL);
		End.addMouseListener(MouseL);
		//��ư 3������ ���콺 �����ʸ� ������
		
		MusicPanel.add(BackGround);
		//�����г����� BACKGROUND�� ���̰�
		primary.add(MusicPanel);
		// MusicPanel���� ��� MusicMain�� �ش��ϴ°͵��� ������ ���δ�.
		//�ٷ�� ���� �ϱ� ���Ͽ�.
		
		
		//��� �۾��� ������ �뷡�� �����Ѵ�
		introMusic.start();

	}

	private void buttonInit(JButton k) {
		k.setBorderPainted(false);
		k.setFocusPainted(false);
		k.setContentAreaFilled(false);
	}
	//��ư BORDER�� FOCUS�� AREAFILLED�� ��� FALSE���� �ش�

	private void Back() {
		introMusic.close();
		THEFirstScreen.setVisible(true);
		MusicPanel.setVisible(false);
	}
	//�ڷΰ���
	//�뷡�� ����
	//�ڷΰ��� ���������� UPCALL�������� THEFirstScreen�� ���̰� �ϰ�
	//���� �г��� �Ⱥ��̰� �Ѵ�

	private void Start() {
		introMusic.close();
		MusicPanel.setVisible(false);
		new RhythmGameMusicSelection(primary,THEFirstScreen,th);
	}
	//�����ϱ�
	//�뷡 ������
	//�����г� �Ⱥ��̰�
	//������ӹ����������� �����Ѵ�.
	
	
	private void END() {
		System.exit(0);
	}
	//������
	//�ý�������

	private class RML implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if (obj == End) {
				End.setIcon(exitButtonEnteredImage);
				End.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (obj == Back) {
				Back.setIcon(backButtonEnteredImage);
				Back.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (obj == Start) {
				Start.setIcon(startButtonEnteredImage);
				Start.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if (obj == End) {
				End.setIcon(exitButtonBasicImage);
				End.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (obj == Back) {
				Back.setIcon(backButtonBasicImage);
				Back.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (obj == Start) {
				Start.setIcon(startButtonBasicImage);
				Start.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if (obj == Back) {
				Back();
			} else if (obj == Start) {
				Start();
			} else if (obj == End) {
				END();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	//����ڰ� ��ư�� �����ų� ���콺�� ��ư�� �ø��� �������� �ϴ� ��� �ൿ�鿡 ���ؼ�
	//������� �������� ���� ���̴�
	//��ư �̹����� Ŀ���� ���콺�� �ø��� ������ ���� �޶�����.

}

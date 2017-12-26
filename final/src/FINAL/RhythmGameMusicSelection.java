package FINAL;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RhythmGameMusicSelection extends JPanel {

	private Image screenImage; // ����̹��� ������ ���� ����
	private Graphics screenGraphic; // �̹����� �׷����� �ޱ� ���� ����
	// ��ũ���� �׸��� �ʿ��� �͵� ����
	private JPanel primary;
	private JPanel THEFirstPanel;
	// upcall�Ҷ� ����Ҽ� �ֵ��� �� �гο��� �޾ƿ� ���� �ִ� ���̴�./
	private JPanel MusicSelect = new BackGround();
	// .���� ���� ȭ���� ��� Music Select ȭ��
	// background��� ���� Ŭ������ ���� �ȴ�.

	private Image musicSelectionImage = new ImageIcon(Main.class.getResource("../images/headphone.jpg")).getImage();
	// ���ȭ��

	//////////////////////////////////////////
	private ImageIcon RightBasicImage = new ImageIcon(Main.class.getResource("../images/left.png"));
	private ImageIcon RightEnteredImage = new ImageIcon(Main.class.getResource("../images/left_Entered.png"));
	private ImageIcon LeftBasicImage = new ImageIcon(Main.class.getResource("../images/right.png"));
	private ImageIcon LeftEnteredImage = new ImageIcon(Main.class.getResource("../images/right_Entered.png"));
	private ImageIcon EasyBasicImage = new ImageIcon(Main.class.getResource("../images/Easy.png"));
	private ImageIcon EasyEnteredImage = new ImageIcon(Main.class.getResource("../images/Easy_selected.png"));
	private ImageIcon HardBasicImage = new ImageIcon(Main.class.getResource("../images/HARD.png"));
	private ImageIcon HardEnteredImage = new ImageIcon(Main.class.getResource("../images/HARD_selected.png"));
	private ImageIcon BackBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private ImageIcon BackEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	// ��ư�� ���� �̹������̴�, ȣ�����Ҷ� ���� �̹����� �ٲܼ� �ֵ��� �ϰ� ���ش�.

	// ��ư���� ������ �κ��̴�. ���� �⺻ �̹����� �������� �����Ͽ���.
	private JButton Right = new JButton(RightBasicImage);
	private JButton Left = new JButton(LeftBasicImage);
	private JButton MButton;
	private JButton Easy = new JButton(EasyBasicImage);
	private JButton Hard = new JButton(HardBasicImage);
	private JButton Back = new JButton(BackBasicImage);
	// ��ư �����̴�.

	private boolean isMusicSelectionScreen;
	private boolean GameScreen;
	// ����ȭ������ �� ������ �Ǻ��ϴ� �Ұ��̴�
	ArrayList<Track> trackList = new ArrayList<Track>();
	// Ʈ���� ���� ����Ʈ ����
	private Music selectedMusic;// ���� ���õ� �뷡�� ������� Music ��ü�̴�.
	private Image titleImage; // �ʱ� �� ����ȭ�鿡�� �� ȭ�� �̸�
	private Image selectedImage; // �ʱ� �� ����ȭ�鿡�� �� ����
	private int nowSelected = 0;// ���� ���õ� Ʈ�� ��ȣ

	public static Game game;
	private MusicSelectListener MSL;
	// game�� ���� ���콺 ������ ����
	// game�� ���α׷� �����߿� �׻� �����ϰ� �ȴ�.
	// keyListener�� ���Ͽ� �ս��� game�� method�� �����Ҽ� �ֵ��� static���� �����Ͽ���.

	RhythmGameMusicSelection(JPanel Primary, JPanel One, JFrame th) {
		// trackList�� �� ������� �߰��ϴ� �κ��̴�.
		trackList.add(new Track("mirrormirror.png", "night.png", "deemoBG.jpg", "mirror_night.mp3",
				"shortVer mirrornight.mp3", "MIRROR NIGHT"));
		trackList.add(new Track("rock.png", "Queen.png", "rockyou.jpg", "cropped rockyou.mp3", "shortVer rockyou.mp3",
				"WE WILL ROCK YOU"));
		trackList.add(new Track("stargazerr.png", "STARGAZER .jpg", "stargazer.png", "StarGazer.mp3",
				"shortVer stargazer.mp3", "STARGAZER"));
		// �� ����, �ٹ� ǥ��, ��׶���, ª�� ���̶���Ʈ ��, ��ü ��, �� ����
		selectTrack(0);
		// ù��°�� ����
		MSL = new MusicSelectListener();

		MusicSelect.addKeyListener(new keyListenerLeft());
		MusicSelect.addKeyListener(new keyListenerRight());
		// ���� Ű�Է¿��� ������ ���ܼ� ���콺 �����ʸ� 2���� ���� �����ϰ� ���ʿ���
		// �θ��� �Ǵ� judge�Լ��� ���ؼ��� 2���� ������ ������ ����Ű�Է��� �ǵ��� �Ͽ���.
		MusicSelect.setFocusable(true);
		// MusicSelect.requestFocusInWindow();

		GameScreen = false;
		primary = Primary;
		THEFirstPanel = One;
		isMusicSelectionScreen = true;
		// upcall�ʿ��Ѱ� �� �Ұ� �ʱ�ȭ
		MusicSelect.setBounds(0, -30, 1280, 720);
		MusicSelect.setLayout(null);
		// musicSelect�г��� ���� 30��ŭ ����ø��� �����Ѵ�.

		ImageIcon image = new ImageIcon(Main.class.getResource("../images/back.png"));
		Image pre = image.getImage();
		Image scale = pre.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		MButton = new JButton(new ImageIcon(scale));
		// ���� �������� �ƴҶ��� ���ư ����

		Right.setBounds(-10, 350, 100, 100);
		Left.setBounds(1200, 350, 100, 100);
		MButton.setBounds(0, 30, 50, 50);
		Easy.setBounds(0, 570, 180, 160);
		Hard.setBounds(270, 570, 180, 160);
		Back.setBounds(0, 30, 50, 50);
		// ��ư ��ġ�� ����

		buttonInit(Right);
		buttonInit(Left);
		buttonInit(Easy);
		buttonInit(Hard);
		buttonInit(Back);
		buttonInit(MButton);
		// rhythmGame Ŭ���� ����
		Right.addMouseListener(MSL);
		MButton.addMouseListener(MSL);
		Left.addMouseListener(MSL);
		Hard.addMouseListener(MSL);
		Easy.addMouseListener(MSL);
		Back.addMouseListener(MSL);
		// ���콺 ������ �߰�
		MusicSelect.add(Right);
		MusicSelect.add(Left);
		MusicSelect.add(MButton);
		MusicSelect.add(Hard);
		MusicSelect.add(Easy);
		MusicSelect.add(Back);
		// �߰�
		Back.setVisible(false);
		// �ΰ��� ���� back ��ư�̴�--> �Ұ���ȭ
		primary.add(MusicSelect);
		// primary�� ���Ѵ�

	}

	private void buttonInit(JButton k) {
		k.setBorderPainted(false);
		k.setFocusPainted(false);
		k.setContentAreaFilled(false);
	}
	// rhythmgameŬ���� ����

	class BackGround extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			screenImage = createImage(Main.screenWidth, Main.screenHeight);
			screenGraphic = screenImage.getGraphics();
			// �̹��� �����ϰ�
			// �׷����� �����Ŀ�
			screenDraw((Graphics2D) screenGraphic);
			// ���� �Ѱ��ְ�
			g.drawImage(screenImage, 0, 0, null);
			// �� �� �׷����� �׸���
		}// ���� ó��

		// �׸� �׸��� ��
		public void screenDraw(Graphics2D g) {

			g.drawImage(musicSelectionImage, 0, 0, null);// ���ȭ�� ���
			if (isMusicSelectionScreen) {
				// �뷡 ����ȭ���϶�
				g.drawImage(selectedImage, 0, 150, null);
				g.drawImage(titleImage, 450, 150, null);
				// �� ������,�� �ٹ��̹��� ���� �׸���
			}
			if (GameScreen) {
				// ���� ȭ���϶��� �������� ���� �Ѱ��ش�
				game.screenDraw(g);
			}
			paintComponents(g);
			try {
				Thread.sleep(3);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// �ʹ� ���� �ٲ�� ������ �� ���� ���߾���.
			// ����
			repaint();
			// ��� �ٽ� �׷��ֵ��� �Ѵ�
		}
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();

		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);// ���� �ݺ�
		selectedMusic.start();
		// ���õ� �뷡�� ������ ����
		// �̹������� trackList��� �޾ƿ���
		// �뷡�� �ش��ϴ� �뷡�� �����Ѵ�
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else {
			nowSelected++;
		}
		selectTrack(nowSelected);
		// ������ ������ �ʱ�ȭ ��Ű��
		// �ƴϸ� ������Ų��
		// �뷡 ������ ����
	}

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else {
			nowSelected--;
		}
		selectTrack(nowSelected);// ����
		// selectRight����
	}

	public void gameStart(int nowSelected, String difficult) {
		if (selectedMusic != null)
			selectedMusic.close();

		isMusicSelectionScreen = false;
		Right.setVisible(false);
		Left.setVisible(false);
		MButton.setVisible(false);
		Easy.setVisible(false);
		Hard.setVisible(false);
		Back.setVisible(true);
		// ���� �����Ҷ�
		// ���� ���� �Ϳ� �ش��ϴ� ���͵� �Ұ���ȭ

		musicSelectionImage = new ImageIcon(
				Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		// �ش� ���ȭ������ �ٲٰ�
		game = new Game(trackList.get(nowSelected).getTitleName(), difficult,
				trackList.get(nowSelected).getGameMusic());
		// �ش� �������� ��������
		game.start();
		// ���� ����

		MusicSelect.setFocusable(true);
		MusicSelect.requestFocusInWindow();
		// JPanel���� Ű�����ʰ� �� �ȵ� Ȥ�ø��� �ٽ��ѹ� ����Ͽ���/
	}

	public void ReturnToMusicSelection() {

		Right.setVisible(true);
		Left.setVisible(true);
		MButton.setVisible(true);
		Easy.setVisible(true);
		Hard.setVisible(true);
		Back.setVisible(false);
		musicSelectionImage = new ImageIcon(Main.class.getResource("../images/headphone.jpg")).getImage();
		game.interrupt();
		game.close();
		// ���ӿ��� ���ö� �ൿ
		// ��� �͵� gameStart�� ����

		selectTrack(nowSelected);
		// ���� ���õȰ� �뷡 Ʋ��
		isMusicSelectionScreen = true;
		// �뷡���� ȭ�� ������ �����
	}

	private void RETURNTOMAIN() {

		MusicSelect.setVisible(false);
		THEFirstPanel.setVisible(true);
		selectedMusic.close();
		// �������� ���ư���
		// ó�� 2�� ����ȭ�� �ִ°͸� ���̰� �Ѵ�
	}

	// ���콺 �̺�Ʈ�� ���� ������ �ٲٰ�
	// Ŭ���ÿ� �ش��ϴ� �ൿ���� �����Ų��.
	private class MusicSelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if (obj == MButton) {// �ǵ��ư��� ��ư�� ������ �� �������� �ǵ��ư���
				RETURNTOMAIN();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {// Hover Effect����, ���콺 Ŀ���� ��ư ���� �÷��� ��, �հ��� ������� ���ϵ��� ����
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if (obj == Right) {
				Right.setIcon(RightEnteredImage);
				Right.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (obj == Left) {
				Left.setIcon(LeftEnteredImage);
				Left.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (obj == Easy) {
				Easy.setIcon(EasyEnteredImage);
				Easy.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (obj == Hard) {
				Hard.setIcon(HardEnteredImage);
				Hard.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else if (obj == Back) {
				Back.setIcon(BackEnteredImage);
				Back.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {// Hover Effect����, ���콺 Ŀ���� ��ư�� ������ ����� ��, ���� Ŀ�� ������� ���ϵ��� ����
			Object obj = e.getSource();
			// TODO Auto-generated method stub
			if (obj == Right) {
				Right.setIcon(RightBasicImage);
				Right.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (obj == Left) {
				Left.setIcon(LeftBasicImage);
				Left.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (obj == Easy) {
				Easy.setIcon(EasyBasicImage);
				Easy.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (obj == Hard) {
				Hard.setIcon(HardBasicImage);
				Hard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else if (obj == Back) {
				Back.setIcon(BackBasicImage);
				Back.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {// ���콺�� ������ ��
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			if (obj == MButton) {// �ǵ��ư���
				RETURNTOMAIN();
			} else if (obj == Right) {// ���������� �ѱ��
				selectRight();
			} else if (obj == Left) {// �������� �ѱ��
				selectLeft();
			} else if (obj == Easy) {// ���� ���̵� ����
				System.out.println("EASY");
				GameScreen = true;
				gameStart(nowSelected, "EASY");
			} else if (obj == Hard) {// ����� ���̵� ����
				GameScreen = true;
				gameStart(nowSelected, "HARD");
			} else if (obj == Back) {// �ǵ��ư���
				GameScreen = false;
				isMusicSelectionScreen = false;
				ReturnToMusicSelection();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}

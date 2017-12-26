package FINAL;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	// ���� Ŭ������ THREAD�� ��ӹ޾Ƽ� ���ȴ�.

	///////////////////////////////////////////////////////
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image noteRouteLineComboImage = new ImageIcon(Main.class.getResource("../images/comboOver10.png"))
			.getImage();
	private Image noteRouteLineComboOver50Image = new ImageIcon(Main.class.getResource("../images/comboOver50.png"))
			.getImage();
	// Ű�� ������ �ڿ� �޺����� ���� ���� �ٲ�� �ϴ� �̹������̴�./////////

	// ������ �κ�:����ڿ��� � Ÿ�ֿ̹� � Ű�� ���������� ������ �Ǵ� �κ��̴�.
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	////////////////////////

	// ���� �� �ؿ��� ���� ����(�� ����, ����, �ܰ�)�� �˷��ִ� �κ� �� ��� �κ��̴�
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/GameInfoImage.png")).getImage();
	//////////////// ���������� ��� String���� �׷�����

	/////////////////////////////////////////////////
	// Ű�� ������ ���ʿ� ��Ÿ���� �̹����� �ش��ϴ� �κ��̴�.
	// �⺻�����δ� �Ķ���, 10�޺� �Ѿ�� �� ���� ���ǵ� �̹������ �ٲ�� �ȴ�.
	// SŰ �κ�
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	// DŰ �κ�
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	// FŰ �κ�
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	// �����̽��� �κ�
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	// JŰ �κ�
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	// KŰ �κ�
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	// LŰ �κ�
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	///////////////////////////////////////////////////

	// ����ڿ��� �������� �̹��� �κе�
	private Image FLARE = new ImageIcon(Main.class.getResource("../images/flare.png")).getImage();
	private Image ENDBACKGROUND = new ImageIcon(Main.class.getResource("../images/selectionBackground.jpg")).getImage();
	private Image LOGO = new ImageIcon(Main.class.getResource("../images/logo.png")).getImage();
	// �÷���, ������ ȭ��,�ΰ���� �����Ͽ���/

	// ��������///////////////////
	private String titleName;// ������ ���ִ� ����
	private String difficulty;// ���̵�
	private String musicTitle;// �뷡�� ���۽����ֱ� ���� ����
	////////////////////////////

	// ��������\\\\\\\\\\\\\\\\\
	private Music gameMusic;// ��������
	private Music scoreMusic;// ������
	// ���� ���ǰ� �������� ���� �����Ͽ���.

	public static int Life; // ������� ���Ӽ����� �˷��ֱ� ���� ����
	public static int Wrong = -3; // MISS�� ���� �� life�� -3��ŭ �� �پ��� ����
	public static int Gamescore = 0;// ���� �ջ��� ���� ����
	// ��� Ŭ���������� �����Ҽ��ֵ��� public static �� ���
	// ��Ʈ�� landing�� ����good, perfect, late, early, great, miss���� ���� �� ������, �� �ܲ����� ������
	// �ٸ���

	// ����ڰ� �Է��� Ű���� ���� ������ ���� �������� �������� ������ ���̴�
	private final int GOOD = 100;
	private final int PERFECT = 500;
	private final int LATE = 50;
	private final int EARLY = 50;
	private final int GREAT = 200;
	private final int SpecialNotes = 1000;
	/////////////////////////////////////////////////

	private int flag = 0;
	// �������� �ѹ��� ���ü� �ֵ��� �ϴ� ������ġ
	private int LifeFlag = 0;
	// �������� 0���� �������� �׿� ���� �ൿ�� trigger�Ҽ� �ֵ��� �� lifeflag�̴�.
	private boolean MusicFlag;
	// �ʹݿ� ��¿������ �������� ������ ������ �־ �ε����ϰ� MusicFlag�� ���Ͽ�
	// �����ϰ� �Ͽ���.

	private String GameScoreString; // ����
	private String returnedValueOfNoteJudgement; // ��Ʈ�� landing�� �����ϱ� ���� ����
	// ������ ��� String�κа� ��Ʈ�� ���������� return���� �޴� String�κ��̴�.

	public static String JUDGEMENT; // ��Ʈ�� landing�� �����ϱ� ���� ����
	public static int combo;// combo �Ǵ��� ���� ����
	public static String COMBO;
	public static int Maxcombo;// maxCombo�� �����ϱ����� ����
	public static String MAXCOMBO;

	Beats[] BEATS = null;// ��Ʈ ������ ����
	ArrayList<Note> noteList = new ArrayList<Note>();// �������� ��Ʈ ������ ���� ����Ʈ ����

	// Game Ŭ���� ������, �ʱ�ȭ
	public Game(String titleName, String difficulty, String musicTitle) {
		LifeFlag = 0;
		flag = 0;
		MusicFlag = false;
		Gamescore = 0;
		JUDGEMENT = new String("");
		combo = 0;
		Maxcombo = 0;
		if (difficulty.equals("HARD")) // ����� ���̵��� �� ������ 60���� ����
		{
			Life = 60;
		} else { // ���� ���̵��� �� ������ 100���� ����
			Life = 100;
		}

		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		scoreMusic = new Music("ROBOTICS.mp3", true);
	}
	// ��ü �ʱ�ȭ�� �뷡 � �뷡�� Ʋ�� �ݺ����ε� �ʱ�ȭ
	// flag������ 0���� �ʱ�ȭ
	// musicFlag�� false�� �ʱ�ȭ
	// ����� ���� ���������� �ٸ��� �ְ�
	// ���� �뷡�� �̸��� �޾� �ݺ������� gameMusic�� �������ְ�
	// scoreMusic�� �׻� ���� �뷡�� ���ü� �ֵ��� �������ش�.

	public void screenDraw(Graphics2D g) {
		if ((!gameMusic.isAlive() && (MusicFlag == true)) || (LifeFlag != 0)) {
			// ������ ���� �����̴�. ������ �װ� MusicFlag�� ���̰ų� LifeFlag �� �ߵ��Ǿ�����
			g.drawImage(ENDBACKGROUND, 0, 0, null);
			g.drawImage(LOGO, 700, 150, null);
			// �� ����� ����������� �׸��� �ΰ� �߰�
			g.setColor(Color.black);
			g.setFont(new Font("Elephant", Font.BOLD, 50));
			g.drawString(titleName, 150, 400);
			MAXCOMBO = String.format("MAXCOMBO: %3d", Maxcombo);
			g.drawString(MAXCOMBO, 150, 500);
			g.drawString("Score:   " + GameScoreString, 150, 600);
			// ������ ������ �׷��ְ�
			this.interrupt();
			// game�� ������Ų��
			if (flag == 0 && !gameMusic.isInterrupted()) {
				for (int i = 0; i < noteList.size(); i++) {
					Note note = noteList.get(i);
					note.KILLTHREAD();

				} // �������� ��� ���̰�
				System.out.println("������ ����");
				scoreMusic.start();// ������ ����
				flag++;

			}
			// flag�� ����Ͽ� noteList�ȿ� �ִ� ��� ��Ʈ���� ���̰�
			// ������ �����Ű�� ���� �����Ѵ�
		} else {// ������ ���� �������϶�
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			if (difficulty.equals("EASY")) { // ���̵��� easy�� ��
				// ��Ʈ�� ��Ʈ�̹����� �׸���.
				g.drawImage(noteRouteSImage, 228, 30, null);
				g.drawImage(noteRouteDImage, 332, 30, null);
				g.drawImage(noteRouteKImage, 848, 30, null);
				g.drawImage(noteRouteLImage, 952, 30, null);

				g.drawImage(noteRouteLineImage, 328, 30, null);
				g.drawImage(noteRouteLineImage, 432, 30, null);
				g.drawImage(noteRouteLineImage, 844, 30, null);
				g.drawImage(noteRouteLineImage, 948, 30, null);
				// ������ S,D,K,L Ű �� �ش��ϴ� �͸� �׸���
			} else if (difficulty.equals("HARD")) {// ���̵��� hard�� ��
				// ��Ʈ�� ��Ʈ�̹����� �׸���.
				g.drawImage(noteRouteSImage, 228, 30, null);
				g.drawImage(noteRouteDImage, 332, 30, null);
				g.drawImage(noteRouteFImage, 436, 30, null);
				g.drawImage(noteRouteSpace1Image, 540, 30, null);
				g.drawImage(noteRouteSpace2Image, 640, 30, null);
				g.drawImage(noteRouteJImage, 744, 30, null);
				g.drawImage(noteRouteKImage, 848, 30, null);
				g.drawImage(noteRouteLImage, 952, 30, null);

				g.drawImage(noteRouteLineImage, 224, 30, null);
				g.drawImage(noteRouteLineImage, 328, 30, null);
				g.drawImage(noteRouteLineImage, 432, 30, null);
				g.drawImage(noteRouteLineImage, 536, 30, null);
				g.drawImage(noteRouteLineImage, 740, 30, null);
				g.drawImage(noteRouteLineImage, 844, 30, null);
				g.drawImage(noteRouteLineImage, 948, 30, null);
				g.drawImage(noteRouteLineImage, 1052, 30, null);
				// ����� ��� S,D,F,SpaceBar,J,K,L�� �ش��ϴ� ��� �̹������� �׸���.
			}
			// *******
			g.drawImage(gameInfoImage, 0, 660, null);
			g.drawImage(judgementLineImage, 0, 580, null);
			// ���� ȭ�� �ؿ� �κ��̴�. �����ΰ� ������ �κ��̴�.
			g.setColor(Color.black);
			// �������� ���� ����
			for (int i = 0; i < noteList.size(); i++) {
				Note note = noteList.get(i);// ��Ʈ ����Ʈ�κ��� �޾ƿ��� ���ο� note�� ���Խ�Ų��.
				if (!note.isProceeding()) {// ��Ʈ�� �������� �ƴҰ��
					noteList.remove(i);// ����Ʈ���� ���ְ�
					i--;// �ϳ� ���δ�.
				} // ���ְ�
				else {
					note.screenDraw(g);// ���� note�� �ѱ��
				} // ������ ��� �׸���.

			}
			// ��Ʈ �׸���

			// �ؿ��� ���Ӱ��� �����鿡 ���� �κ��̴�.
			// ������ ���� ������ �ٸ���
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.blue);
			g.drawString(titleName, 20, 702); // �� ������ �۾�ü, �۾� ���� ����
			g.setColor(Color.WHITE);
			if (difficulty.equals("EASY")) { // ���� ���̵��� �� �۾� ���� ����
				g.setColor(Color.yellow);
				g.drawString(difficulty, 1190, 702);
			} else {
				g.setColor(Color.WHITE); // ����� ���̵��� �� �۾� ���� ����
				g.drawString(difficulty, 1190, 702);
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(Color.orange);
			if (difficulty.equals("EASY")) {// ���̵��� easy�� �� key setting
				g.drawString("S", 270, 609);
				g.drawString("D", 374, 609);
				g.drawString("K", 889, 609);
				g.drawString("L", 993, 609);

			} else if (difficulty.equals("HARD")) {// ���̵��� hard�� �� key setting
				g.drawString("S", 270, 609);
				g.drawString("D", 374, 609);
				g.drawString("F", 478, 609);
				g.drawString("Space Bar", 580, 609);
				g.drawString("J", 784, 609);
				g.drawString("K", 889, 609);
				g.drawString("L", 993, 609);
			}

			// ���� ������ �������� �ջ� ����� ���� �ȳ� �κ�

			g.setColor(Color.LIGHT_GRAY);// ************
			g.setFont(new Font("Elephant", Font.BOLD, 30)); // ���� �� �۾�ü ����
			GameScoreString = String.format("%06d", Gamescore);
			g.drawString(GameScoreString, 565, 700);
			g.drawImage(FLARE, 440, 350, null);
			g.setFont(new Font("Arial", Font.BOLD, 15)); // ȭ�� ������ �ջ� ��� �ȳ��� ���� �۾�ü ����
			g.setColor(Color.green);// ���� ����

			// ��� �ȳ��� ���� ���� �� ��ġ ����
			g.drawString("MISS = -100", 1100, 60);
			g.drawString("LATE = +50", 1100, 90);
			g.drawString("EARLY = +50", 1100, 120);
			g.drawString("GOOD = +100", 1100, 150);
			g.drawString("GREAT = +200", 1100, 180);
			g.drawString("PERFECT = +500", 1100, 210);
			g.drawString("SPECIAL NOTES = +1000", 1100, 240);
			g.drawString("COMBO=+5", 1100, 270);
			// ******

			g.setFont(new Font("Elephant", Font.BOLD, 30));
			g.setColor(Color.white);
			// judgement String�� special�� �����ϰ������� ���� �ȴ�.
			if (JUDGEMENT.contains("SPECIAL")) {// ���� perfect���� ������ �ִٸ�
				if (JUDGEMENT.contains("PERFECT")) {
					g.setFont(new Font("Elephant", Font.BOLD, 30));
					g.setColor(Color.ORANGE);
					g.drawString(JUDGEMENT, 450, 350);
					g.setFont(new Font("Elephant", Font.BOLD, 30));
					g.setColor(Color.white);
					// Ư���� ����� ��ġ��������ش�.

				} else {
					g.setFont(new Font("Elephant", Font.BOLD, 30));
					g.setColor(Color.cyan);
					g.drawString(JUDGEMENT, 500, 350);
					g.setFont(new Font("Elephant", Font.BOLD, 30));
					g.setColor(Color.white);
					// �ٸ� Ư���� ����� ��ġ�� ����Ѵ�
				}
			} else if (JUDGEMENT.equals("PERFECT")) {
				// perfect�� ������ ������
				g.setFont(new Font("Elephant", Font.BOLD, 30));
				g.setColor(Color.red);
				g.drawString(JUDGEMENT, 550, 450);
				g.setFont(new Font("Elephant", Font.BOLD, 30));
				g.setColor(Color.white);
				// ���� Ư���� ����� ������ش�.

			} else {
				g.drawString(JUDGEMENT, 580, 450);
				// �׳� ����Ѵ�.
			}
			//// �������� �κ��̴�/ ����ڰ� �Է��� Ű���� ���� ������ �����ش�

			COMBO = String.format("COMBO: %3d", combo);
			g.setFont(new Font("SansSerif", Font.BOLD, 30));
			g.setColor(Color.magenta);
			g.drawString(COMBO, 1090, 300);
			// �޺��� ���� ���̴�

			// ������ ���� �κ��̴�. life���� ���Ͽ� �󸶳� �׷����� ���Ѵ�
			g.setColor(Color.red);
			g.drawString("LIFE :" + Life, 0, 560);
			if (Life > 80) {
				g.fillRect(0, 250, 50, 50);
				g.fillRect(0, 305, 50, 50);
				g.fillRect(0, 360, 50, 50);
				g.fillRect(0, 415, 50, 50);
				g.fillRect(0, 470, 50, 50);

			} else if (Life > 60) {
				g.fillRect(0, 305, 50, 50);
				g.fillRect(0, 360, 50, 50);
				g.fillRect(0, 415, 50, 50);
				g.fillRect(0, 470, 50, 50);

			} else if (Life > 40) {
				g.fillRect(0, 360, 50, 50);
				g.fillRect(0, 415, 50, 50);
				g.fillRect(0, 470, 50, 50);

			} else if (Life > 20) {
				g.fillRect(0, 415, 50, 50);
				g.fillRect(0, 470, 50, 50);

			} else if (Life >= 0) {
				g.fillRect(0, 470, 50, 50);

			} else {
				// �������� 0���� ���������� �뷡�� ������ �������� �����־���Ѵ�.
				if (LifeFlag == 0) {
					gameMusic.close();// �������� ������
					this.interrupt();// game�� ���� ���߰�
					for (int i = 0; i < noteList.size(); i++) {
						Note note = noteList.get(i);
						note.KILLTHREAD();

					} // ��� ��Ʈ����Ʈ�ȿ� �ִ°͵� ���δ�
					g.drawImage(ENDBACKGROUND, 0, 0, null);
					// ����ȭ�� �̹��� �׸���
					LifeFlag++;// ������ �÷��� ����--> ����ȭ��
				}
			}

		} // During the game play
	}// screenDraw

	// S,D,F,SpaceBar,J,K,L�� ���� �� �Ҹ��� ������ ����
	// �̺κк��� �� ~460 �ٱ����� ����ڰ� Ű�� �������� � �ൿ�� �Ұ����� ���ϴ� �Լ����̴�.
	// �� �Լ����� keyListenerŬ������ ���ؼ� trigger�ǰ� ���� ���� P(KEY)���� ������ R(KEY)��������
	// �����Ͽ���. ������ �� Ű���� ���� JUDGE�� �����ϰ� �ǰ� �޺����� ���� �̹��� ���� �ٲ��ְ�
	// Ű�� ������ ������ �����Ų��.
	// ������ ���� �̹����� �ٽ� �ٲ۴�.
	public void PS() {// Press S
		judgeLeft("S");// SŰ�� ���Ͽ�
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		if (combo > 10) {
			noteRouteSImage = noteRouteLineComboImage;
		}
		if (combo > 20) {
			noteRouteSImage = noteRouteLineComboOver50Image;
		}
		new Music("drumSmall1.mp3", false).start();
	}

	public void RS() {// Release S
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void PD() {// Press D
		judgeLeft("D");// DŰ�� ���Ͽ�
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		if (combo > 10) {
			noteRouteDImage = noteRouteLineComboImage;
		}
		if (combo > 20) {
			noteRouteDImage = noteRouteLineComboOver50Image;
		}
		new Music("drumSmall1.mp3", false).start();
	}

	public void RD() {// Release D
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void PF() {// Press F
		judgeLeft("F");// FŰ�� ���Ͽ�
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		if (combo > 10) {
			noteRouteFImage = noteRouteLineComboImage;
		}
		if (combo > 20) {
			noteRouteFImage = noteRouteLineComboOver50Image;
		}
		new Music("drumSmall1.mp3", false).start();
	}

	public void RF() {// Release F
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void PSP() {// Press SpaceBar
		judgeLeft("Space");// SpaceBar�� ���Ͽ�
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		if (combo > 10) {
			noteRouteSpace1Image = noteRouteLineComboImage;
			noteRouteSpace2Image = noteRouteLineComboImage;
		}
		if (combo > 20) {
			noteRouteSpace1Image = noteRouteLineComboOver50Image;
			noteRouteSpace2Image = noteRouteLineComboOver50Image;
		}
		new Music("drumBig1.mp3", false).start();
	}

	public void RSP() {// Release SpaceBar
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void PJ() {// Press J
		judgeRight("J");// JŰ �� ���Ͽ�
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		if (combo > 10) {
			noteRouteJImage = noteRouteLineComboImage;
		}
		if (combo > 20) {
			noteRouteJImage = noteRouteLineComboOver50Image;
		}
		new Music("drumSmall1.mp3", false).start();
	}

	public void RJ() {// Release J
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void PK() {// Press K
		judgeRight("K");// KŰ�� ���Ͽ�
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		if (combo > 10) {
			noteRouteKImage = noteRouteLineComboImage;
		}
		if (combo > 20) {
			noteRouteKImage = noteRouteLineComboOver50Image;
		}
		new Music("drumSmall1.mp3", false).start();
	}

	public void RK() {// Release K
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}

	public void PL() {// Press L
		judgeRight("L");// LŰ�� ���Ͽ�
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		if (combo > 10) {
			noteRouteLImage = noteRouteLineComboImage;
		}
		if (combo > 20) {
			noteRouteLImage = noteRouteLineComboOver50Image;
		}
		new Music("drumSmall1.mp3", false).start();
	}

	public void RL() {// Release L
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	}
	// �� 312~320�� ���̿� ���� �Ϸ��Ͽ����ϴ�.

	@Override
	public void run() {
		START();
	}
	// game�� �����Ű�� run�Լ��� ȣ���ϰԵǰ� �� �ȿ���
	// �Ǻ��� �޾ƿ��� �׿����� ��Ʈ�� ����Ʈ���ִ� �۾��� �����ϰ� �ȴ�.

	// close�� ���� �ּ� ��
	public void close() {

		gameMusic.close();
		// ���� ������ ���� ������
		this.interrupt();
		// �� ���ӵ� �����鼭
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			note.KILLTHREAD();

		}
		// ���� ��Ʈ ����Ʈ�ȿ� �ִ� ��� ��Ʈ�� ����
		// ��Ʈ�� ���� ������ �� ���̰�
		if (scoreMusic != null) {
			if (scoreMusic.isAlive()) {
				scoreMusic.close();
			}
		}
		// ���ھ� �뷡�� ���������
		// ���ھ� �뷡���� ����.

	}

	// ************
	// START�Լ��� ���Ը��ؼ� ���̸��� ���̵��� ���� � �Ǻ��� ���� ���ϴ� �۾��̶� �����ϸ�ȴ�
	// starttime�� term�� ���Ͽ� ���ο� ��Ʈ�� � �ð��� ������ ���Ѵ�.
	public void START() {
		if (titleName.equals("STARGAZER")) {// Stargazer ��
			if (difficulty.equals("EASY")) {
				int startTime = 100;
				int term = 150;// ��Ʈ �� 150
				System.out.println("START");
				BEATS = new Beats[] { new Beats(startTime + term, "D", true), new Beats(startTime + term, "K"),
						new Beats(startTime + term * 5, "D"), new Beats(startTime + term * 5, "K"),
						new Beats(startTime + term * 9, "S"), new Beats(startTime + term * 9, "L", true),
						new Beats(startTime + term * 13, "D", true), new Beats(startTime + term * 13, "K"),
						new Beats(startTime + term * 17, "D"), new Beats(startTime + term * 17, "K"),
						new Beats(startTime + term * 21, "S"), new Beats(startTime + term * 21, "L"),
						new Beats(startTime + term * 25, "D"), new Beats(startTime + term * 25, "K"),
						new Beats(startTime + term * 29, "D"), new Beats(startTime + term * 29, "K"),
						new Beats(startTime + term * 33, "S", true), new Beats(startTime + term * 33, "L"),
						new Beats(startTime + term * 37, "D"), new Beats(startTime + term * 37, "K"),
						new Beats(startTime + term * 41, "D", true), new Beats(startTime + term * 41, "K"),
						new Beats(startTime + term * 45, "S"), new Beats(startTime + term * 45, "L", true),
						new Beats(startTime + term * 49, "D"), new Beats(startTime + term * 49, "K", true),
						new Beats(startTime + term * 53, "D"), new Beats(startTime + term * 53, "K"),
						new Beats(startTime + term * 57, "S"), new Beats(startTime + term * 57, "L"),
						new Beats(startTime + term * 61, "D"), new Beats(startTime + term * 61, "K"),
						new Beats(startTime + term * 65, "D"), new Beats(startTime + term * 65, "K"),
						new Beats(startTime + term * 69, "S"), new Beats(startTime + term * 69, "L"),
						new Beats(startTime + term * 73, "D"), new Beats(startTime + term * 73, "K", true),
						new Beats(startTime + term * 77, "D"), new Beats(startTime + term * 77, "K"),
						new Beats(startTime + term * 81, "S", true), new Beats(startTime + term * 81, "L"),
						new Beats(startTime + term * 85, "D"), new Beats(startTime + term * 85, "K"),
						new Beats(startTime + term * 89, "D"), new Beats(startTime + term * 89, "K", true),
						new Beats(startTime + term * 93, "S", true), new Beats(startTime + term * 93, "L"),
						new Beats(startTime + term * 97, "D"), new Beats(startTime + term * 97, "K"),
						new Beats(startTime + term * 101, "D"), new Beats(startTime + term * 101, "K"),
						new Beats(startTime + term * 105, "S"), new Beats(startTime + term * 105, "L"),
						new Beats(startTime + term * 109, "D"), new Beats(startTime + term * 109, "K", true),
						new Beats(startTime + term * 113, "D", true), new Beats(startTime + term * 113, "K"),
						new Beats(startTime + term * 117, "S"), new Beats(startTime + term * 117, "L"),
						new Beats(startTime + term * 121, "D"), new Beats(startTime + term * 121, "K", true),
						new Beats(startTime + term * 125, "D", true), new Beats(startTime + term * 125, "K"),
						new Beats(startTime + term * 129, "S"), new Beats(startTime + term * 129, "L"),
						new Beats(startTime + term * 133, "D", true), new Beats(startTime + term * 133, "K"),
						new Beats(startTime + term * 137, "D"), new Beats(startTime + term * 137, "K"),
						new Beats(startTime + term * 141, "S", true), new Beats(startTime + term * 141, "L"),
						new Beats(startTime + term * 145, "D"), new Beats(startTime + term * 145, "K"),
						new Beats(startTime + term * 149, "D"), new Beats(startTime + term * 149, "K"),
						new Beats(startTime + term * 153, "S"), new Beats(startTime + term * 153, "L"),
						new Beats(startTime + term * 157, "D"), new Beats(startTime + term * 157, "K"),
						new Beats(startTime + term * 161, "D"), new Beats(startTime + term * 161, "K", true),
						new Beats(startTime + term * 165, "S", true), new Beats(startTime + term * 165, "L"),
						new Beats(startTime + term * 169, "D"), new Beats(startTime + term * 169, "K"),
						new Beats(startTime + term * 173, "D", true), new Beats(startTime + term * 173, "K"),
						new Beats(startTime + term * 177, "S"), new Beats(startTime + term * 177, "L"),
						new Beats(startTime + term * 181, "D"), new Beats(startTime + term * 181, "K"),
						new Beats(startTime + term * 185, "D", true), new Beats(startTime + term * 185, "K"),
						new Beats(startTime + term * 189, "S"), new Beats(startTime + term * 189, "L"),
						new Beats(startTime + term * 193, "D"), new Beats(startTime + term * 193, "K", true),
						new Beats(startTime + term * 197, "D"), new Beats(startTime + term * 197, "K"),
						new Beats(startTime + term * 201, "S"), new Beats(startTime + term * 201, "L"),
						new Beats(startTime + term * 205, "D", true), new Beats(startTime + term * 205, "K"),
						new Beats(startTime + term * 209, "D"), new Beats(startTime + term * 209, "K"),
						new Beats(startTime + term * 213, "S"), new Beats(startTime + term * 213, "L"),
						new Beats(startTime + term * 217, "D"), new Beats(startTime + term * 217, "K", true),
						new Beats(startTime + term * 221, "D"), new Beats(startTime + term * 221, "K"),
						new Beats(startTime + term * 225, "S", true), new Beats(startTime + term * 225, "L"),
						new Beats(startTime + term * 229, "D"), new Beats(startTime + term * 229, "K"),
						new Beats(startTime + term * 233, "D"), new Beats(startTime + term * 233, "K"),
						new Beats(startTime + term * 237, "S"), new Beats(startTime + term * 237, "L"),
						new Beats(startTime + term * 241, "D"), new Beats(startTime + term * 241, "K"),
						new Beats(startTime + term * 245, "D"), new Beats(startTime + term * 245, "K"),
						new Beats(startTime + term * 249, "S"), new Beats(startTime + term * 249, "L", true),
						new Beats(startTime + term * 253, "D"), new Beats(startTime + term * 253, "K"),
						new Beats(startTime + term * 257, "D"), new Beats(startTime + term * 257, "K"),
						new Beats(startTime + term * 261, "S", true), new Beats(startTime + term * 261, "L"),
						new Beats(startTime + term * 265, "D"), new Beats(startTime + term * 265, "K"),
						new Beats(startTime + term * 269, "D"), new Beats(startTime + term * 269, "K"),
						new Beats(startTime + term * 273, "S", true), new Beats(startTime + term * 273, "L"),
						new Beats(startTime + term * 277, "D"), new Beats(startTime + term * 277, "K"),
						new Beats(startTime + term * 281, "D"), new Beats(startTime + term * 281, "K"),
						new Beats(startTime + term * 285, "S"), new Beats(startTime + term * 285, "L"),
						new Beats(startTime + term * 289, "D"), new Beats(startTime + term * 289, "K", true),
						new Beats(startTime + term * 293, "D"), new Beats(startTime + term * 293, "K"),
						new Beats(startTime + term * 297, "S"), new Beats(startTime + term * 297, "L"),
						new Beats(startTime + term * 301, "D"), new Beats(startTime + term * 301, "K"),
						new Beats(startTime + term * 305, "D", true), new Beats(startTime + term * 305, "K"),
						new Beats(startTime + term * 309, "S"), new Beats(startTime + term * 309, "L"),
						new Beats(startTime + term * 313, "D"), new Beats(startTime + term * 313, "K"),
						new Beats(startTime + term * 317, "D"), new Beats(startTime + term * 317, "K"),
						new Beats(startTime + term * 321, "S"), new Beats(startTime + term * 321, "L"),
						new Beats(startTime + term * 325, "D"), new Beats(startTime + term * 325, "K", true),
						new Beats(startTime + term * 329, "D"), new Beats(startTime + term * 329, "K"),
						new Beats(startTime + term * 333, "S"), new Beats(startTime + term * 333, "L"),
						new Beats(startTime + term * 337, "D", true), new Beats(startTime + term * 337, "K"),
						new Beats(startTime + term * 341, "D"), new Beats(startTime + term * 341, "K"),
						new Beats(startTime + term * 345, "S"), new Beats(startTime + term * 345, "L", true),
						new Beats(startTime + term * 349, "D"), new Beats(startTime + term * 349, "K"),
						new Beats(startTime + term * 353, "D"), new Beats(startTime + term * 353, "K"),
						new Beats(startTime + term * 357, "S"), new Beats(startTime + term * 357, "L"),
						new Beats(startTime + term * 361, "D"), new Beats(startTime + term * 361, "K"),
						new Beats(startTime + term * 365, "D"), new Beats(startTime + term * 365, "K"),
						new Beats(startTime + term * 369, "S"), new Beats(startTime + term * 369, "L"),
						new Beats(startTime + term * 373, "D"), new Beats(startTime + term * 373, "K"),
						new Beats(startTime + term * 377, "D"), new Beats(startTime + term * 377, "K"),
						new Beats(startTime + term * 381, "S"), new Beats(startTime + term * 381, "L"),
						new Beats(startTime + term * 385, "D", true), new Beats(startTime + term * 385, "K"),
						new Beats(startTime + term * 389, "D"), new Beats(startTime + term * 389, "K", true),
						new Beats(startTime + term * 393, "S"), new Beats(startTime + term * 393, "L"),
						new Beats(startTime + term * 397, "D", true), new Beats(startTime + term * 397, "K"),
						new Beats(startTime + term * 401, "D"), new Beats(startTime + term * 401, "K", true),
						new Beats(startTime + term * 405, "S"), new Beats(startTime + term * 405, "L"),
						new Beats(startTime + term * 409, "D", true), new Beats(startTime + term * 409, "K"),
						new Beats(startTime + term * 413, "D"), new Beats(startTime + term * 413, "K", true),
						new Beats(startTime + term * 417, "S"), new Beats(startTime + term * 417, "L"),
						new Beats(startTime + term * 421, "D"), new Beats(startTime + term * 421, "K"),
						new Beats(startTime + term * 425, "D"), new Beats(startTime + term * 425, "K"),
						new Beats(startTime + term * 429, "S"), new Beats(startTime + term * 429, "L"),
						new Beats(startTime + term * 433, "D"), new Beats(startTime + term * 433, "K"),
						new Beats(startTime + term * 437, "D"), new Beats(startTime + term * 437, "K", true),
						new Beats(startTime + term * 441, "S"), new Beats(startTime + term * 441, "L"),
						new Beats(startTime + term * 445, "D", true), new Beats(startTime + term * 445, "K"),
						new Beats(startTime + term * 449, "D"), new Beats(startTime + term * 449, "K"),
						new Beats(startTime + term * 453, "S"), new Beats(startTime + term * 453, "L"),

				};
			} // stargazer ���� ���
			else if (difficulty.equals("HARD")) {

				int startTime = 100;
				int term = 150;
				System.out.println("START");
				BEATS = new Beats[] { new Beats(startTime + term, "D", true), new Beats(startTime + term, "K"),
						new Beats(startTime + term * 5, "D"), new Beats(startTime + term * 5, "K"),
						new Beats(startTime + term * 9, "S"), new Beats(startTime + term * 9, "L"),
						new Beats(startTime + term * 13, "D"), new Beats(startTime + term * 13, "K"),
						new Beats(startTime + term * 17, "D"), new Beats(startTime + term * 17, "K"),
						new Beats(startTime + term * 21, "F"), new Beats(startTime + term * 21, "J", true),
						new Beats(startTime + term * 25, "D"), new Beats(startTime + term * 25, "K"),
						new Beats(startTime + term * 29, "D"), new Beats(startTime + term * 29, "K"),
						new Beats(startTime + term * 33, "S"), new Beats(startTime + term * 33, "L"),
						new Beats(startTime + term * 37, "D"), new Beats(startTime + term * 37, "K"),
						new Beats(startTime + term * 41, "D", true), new Beats(startTime + term * 41, "K"),
						new Beats(startTime + term * 45, "F"), new Beats(startTime + term * 45, "J"),
						new Beats(startTime + term * 49, "D"), new Beats(startTime + term * 49, "K"),
						new Beats(startTime + term * 53, "D"), new Beats(startTime + term * 53, "K", true),
						new Beats(startTime + term * 57, "Space"), new Beats(startTime + term * 61, "D"),
						new Beats(startTime + term * 61, "K"), new Beats(startTime + term * 65, "D"),
						new Beats(startTime + term * 65, "K"), new Beats(startTime + term * 69, "S"),
						new Beats(startTime + term * 69, "L"), new Beats(startTime + term * 73, "D"),
						new Beats(startTime + term * 73, "K"), new Beats(startTime + term * 77, "D"),
						new Beats(startTime + term * 77, "K"), new Beats(startTime + term * 81, "F", true),
						new Beats(startTime + term * 81, "J"), new Beats(startTime + term * 85, "D"),
						new Beats(startTime + term * 85, "K"), new Beats(startTime + term * 89, "D"),
						new Beats(startTime + term * 89, "K"), new Beats(startTime + term * 93, "S"),
						new Beats(startTime + term * 93, "L"), new Beats(startTime + term * 97, "D"),
						new Beats(startTime + term * 97, "K", true), new Beats(startTime + term * 101, "D"),
						new Beats(startTime + term * 101, "K"), new Beats(startTime + term * 105, "F"),
						new Beats(startTime + term * 105, "J"), new Beats(startTime + term * 109, "D"),
						new Beats(startTime + term * 109, "K"), new Beats(startTime + term * 113, "D", true),
						new Beats(startTime + term * 113, "K"), new Beats(startTime + term * 117, "S"),
						new Beats(startTime + term * 117, "L"), new Beats(startTime + term * 121, "D"),
						new Beats(startTime + term * 121, "K", true), new Beats(startTime + term * 125, "D"),
						new Beats(startTime + term * 125, "K"), new Beats(startTime + term * 129, "Space"),
						new Beats(startTime + term * 133, "D"), new Beats(startTime + term * 133, "K"),
						new Beats(startTime + term * 137, "D"), new Beats(startTime + term * 137, "K"),
						new Beats(startTime + term * 141, "Space"), new Beats(startTime + term * 145, "D"),
						new Beats(startTime + term * 145, "K"), new Beats(startTime + term * 149, "D"),
						new Beats(startTime + term * 149, "K"), new Beats(startTime + term * 153, "S", true),
						new Beats(startTime + term * 153, "L"), new Beats(startTime + term * 157, "D"),
						new Beats(startTime + term * 157, "K"), new Beats(startTime + term * 161, "D"),
						new Beats(startTime + term * 161, "K"), new Beats(startTime + term * 165, "F"),
						new Beats(startTime + term * 165, "J"), new Beats(startTime + term * 169, "D"),
						new Beats(startTime + term * 169, "K"), new Beats(startTime + term * 173, "D"),
						new Beats(startTime + term * 173, "K", true), new Beats(startTime + term * 177, "S"),
						new Beats(startTime + term * 177, "L"), new Beats(startTime + term * 181, "D"),
						new Beats(startTime + term * 181, "K"), new Beats(startTime + term * 185, "D"),
						new Beats(startTime + term * 185, "K"), new Beats(startTime + term * 189, "S"),
						new Beats(startTime + term * 189, "L"), new Beats(startTime + term * 193, "D"),
						new Beats(startTime + term * 193, "K"), new Beats(startTime + term * 197, "D"),
						new Beats(startTime + term * 197, "K"), new Beats(startTime + term * 201, "F"),
						new Beats(startTime + term * 201, "J"), new Beats(startTime + term * 205, "D"),
						new Beats(startTime + term * 205, "K"), new Beats(startTime + term * 209, "D", true),
						new Beats(startTime + term * 209, "K"), new Beats(startTime + term * 213, "S"),
						new Beats(startTime + term * 213, "L"), new Beats(startTime + term * 217, "D"),
						new Beats(startTime + term * 217, "K"), new Beats(startTime + term * 221, "D"),
						new Beats(startTime + term * 221, "K"), new Beats(startTime + term * 225, "S"),
						new Beats(startTime + term * 225, "L"), new Beats(startTime + term * 229, "D"),
						new Beats(startTime + term * 229, "K", true), new Beats(startTime + term * 233, "D"),
						new Beats(startTime + term * 233, "K"), new Beats(startTime + term * 237, "Space"),
						new Beats(startTime + term * 241, "D"), new Beats(startTime + term * 241, "K"),
						new Beats(startTime + term * 245, "D"), new Beats(startTime + term * 245, "K"),
						new Beats(startTime + term * 249, "S"), new Beats(startTime + term * 249, "L"),
						new Beats(startTime + term * 253, "D"), new Beats(startTime + term * 253, "K"),
						new Beats(startTime + term * 257, "D"), new Beats(startTime + term * 257, "K"),
						new Beats(startTime + term * 261, "S"), new Beats(startTime + term * 261, "L"),
						new Beats(startTime + term * 265, "D"), new Beats(startTime + term * 265, "K"),
						new Beats(startTime + term * 269, "D"), new Beats(startTime + term * 269, "K"),
						new Beats(startTime + term * 273, "Space", true), new Beats(startTime + term * 277, "D"),
						new Beats(startTime + term * 277, "K"), new Beats(startTime + term * 281, "D"),
						new Beats(startTime + term * 281, "K"), new Beats(startTime + term * 285, "S"),
						new Beats(startTime + term * 285, "L"), new Beats(startTime + term * 289, "D"),
						new Beats(startTime + term * 289, "K"), new Beats(startTime + term * 293, "D", true),
						new Beats(startTime + term * 293, "K"), new Beats(startTime + term * 297, "S"),
						new Beats(startTime + term * 297, "L"), new Beats(startTime + term * 301, "D"),
						new Beats(startTime + term * 301, "K"), new Beats(startTime + term * 305, "D"),
						new Beats(startTime + term * 305, "K"), new Beats(startTime + term * 309, "F"),
						new Beats(startTime + term * 309, "J"), new Beats(startTime + term * 313, "D"),
						new Beats(startTime + term * 313, "K"), new Beats(startTime + term * 317, "D"),
						new Beats(startTime + term * 317, "K", true), new Beats(startTime + term * 321, "Space"),
						new Beats(startTime + term * 325, "D"), new Beats(startTime + term * 325, "K"),
						new Beats(startTime + term * 329, "D"), new Beats(startTime + term * 329, "K"),
						new Beats(startTime + term * 333, "S", true), new Beats(startTime + term * 333, "L"),
						new Beats(startTime + term * 337, "D"), new Beats(startTime + term * 337, "K"),
						new Beats(startTime + term * 341, "D"), new Beats(startTime + term * 341, "K"),
						new Beats(startTime + term * 345, "Space"), new Beats(startTime + term * 349, "D"),
						new Beats(startTime + term * 349, "K"), new Beats(startTime + term * 353, "D"),
						new Beats(startTime + term * 353, "K"), new Beats(startTime + term * 357, "S"),
						new Beats(startTime + term * 357, "L"), new Beats(startTime + term * 361, "D"),
						new Beats(startTime + term * 361, "K"), new Beats(startTime + term * 365, "D"),
						new Beats(startTime + term * 365, "K", true), new Beats(startTime + term * 369, "F"),
						new Beats(startTime + term * 369, "J"), new Beats(startTime + term * 373, "D", true),
						new Beats(startTime + term * 373, "K"), new Beats(startTime + term * 377, "D"),
						new Beats(startTime + term * 377, "K"), new Beats(startTime + term * 381, "F"),
						new Beats(startTime + term * 381, "J"), new Beats(startTime + term * 385, "D"),
						new Beats(startTime + term * 385, "K"), new Beats(startTime + term * 389, "D"),
						new Beats(startTime + term * 389, "K"), new Beats(startTime + term * 393, "Space"),
						new Beats(startTime + term * 397, "D"), new Beats(startTime + term * 397, "K"),
						new Beats(startTime + term * 401, "D"), new Beats(startTime + term * 401, "K"),
						new Beats(startTime + term * 405, "S"), new Beats(startTime + term * 405, "L", true),
						new Beats(startTime + term * 409, "D"), new Beats(startTime + term * 409, "K"),
						new Beats(startTime + term * 413, "D"), new Beats(startTime + term * 413, "K"),
						new Beats(startTime + term * 417, "Space"), new Beats(startTime + term * 421, "F"),
						new Beats(startTime + term * 421, "J"), new Beats(startTime + term * 425, "F"),
						new Beats(startTime + term * 425, "J"), new Beats(startTime + term * 429, "D"),
						new Beats(startTime + term * 429, "K"), new Beats(startTime + term * 433, "F", true),
						new Beats(startTime + term * 433, "J"), new Beats(startTime + term * 437, "F"),
						new Beats(startTime + term * 437, "J"), new Beats(startTime + term * 441, "S"),
						new Beats(startTime + term * 441, "L"), new Beats(startTime + term * 445, "F"),
						new Beats(startTime + term * 445, "J"), new Beats(startTime + term * 449, "F"),
						new Beats(startTime + term * 449, "J"), new Beats(startTime + term * 453, "Space", true),
						new Beats(startTime + term * 457, "F"), new Beats(startTime + term * 457, "J"),
						new Beats(startTime + term * 461, "S"), new Beats(startTime + term * 461, "L"),
						new Beats(startTime + term * 465, "F"), new Beats(startTime + term * 465, "J"),
						new Beats(startTime + term * 469, "F"), new Beats(startTime + term * 469, "J"),
						new Beats(startTime + term * 473, "Space", true),

				};
				System.out.println("INIT");
			} // ����� ���
		} // STARGAZER�� �Ǻ�

		else if (titleName.equals("WE WILL ROCK YOU")) {// WE WILL ROCK YOU ��
			if (difficulty.equals("EASY")) {
				int startTime = 4200;// ���� �ð� ����
				int term = 125;//
				System.out.println("START");
				BEATS = new Beats[] { new Beats(startTime + term, "D"), new Beats(startTime + term, "K"),
						new Beats(startTime + term * 5, "D"), new Beats(startTime + term * 5, "K"),
						new Beats(startTime + term * 9, "S"), new Beats(startTime + term * 9, "L"),
						new Beats(startTime + term * 13, "D", true), new Beats(startTime + term * 13, "K"),
						new Beats(startTime + term * 17, "D"), new Beats(startTime + term * 17, "K"),
						new Beats(startTime + term * 21, "S"), new Beats(startTime + term * 21, "L"),
						new Beats(startTime + term * 25, "D"), new Beats(startTime + term * 25, "K"),
						new Beats(startTime + term * 29, "D"), new Beats(startTime + term * 29, "K", true),
						new Beats(startTime + term * 33, "S"), new Beats(startTime + term * 33, "L"),
						new Beats(startTime + term * 37, "D"), new Beats(startTime + term * 37, "K"),
						new Beats(startTime + term * 41, "D"), new Beats(startTime + term * 41, "K"),
						new Beats(startTime + term * 45, "S"), new Beats(startTime + term * 45, "L", true),
						new Beats(startTime + term * 49, "D"), new Beats(startTime + term * 49, "K"),
						new Beats(startTime + term * 53, "D"), new Beats(startTime + term * 53, "K"),
						new Beats(startTime + term * 57, "S", true), new Beats(startTime + term * 57, "L"),
						new Beats(startTime + term * 61, "D"), new Beats(startTime + term * 61, "K"),
						new Beats(startTime + term * 65, "D"), new Beats(startTime + term * 65, "K"),
						new Beats(startTime + term * 69, "S"), new Beats(startTime + term * 69, "L"),
						new Beats(startTime + term * 73, "D", true), new Beats(startTime + term * 73, "K"),
						new Beats(startTime + term * 77, "D"), new Beats(startTime + term * 77, "K"),
						new Beats(startTime + term * 81, "S"), new Beats(startTime + term * 81, "L"),
						new Beats(startTime + term * 85, "D"), new Beats(startTime + term * 85, "K"),
						new Beats(startTime + term * 89, "D"), new Beats(startTime + term * 89, "K"),
						new Beats(startTime + term * 93, "S"), new Beats(startTime + term * 93, "L", true),
						new Beats(startTime + term * 97, "D"), new Beats(startTime + term * 97, "K"),
						new Beats(startTime + term * 101, "D"), new Beats(startTime + term * 101, "K"),
						new Beats(startTime + term * 105, "S"), new Beats(startTime + term * 105, "L"),
						new Beats(startTime + term * 109, "D"), new Beats(startTime + term * 109, "K"),
						new Beats(startTime + term * 113, "D"), new Beats(startTime + term * 113, "K"),
						new Beats(startTime + term * 117, "S", true), new Beats(startTime + term * 117, "L"),
						new Beats(startTime + term * 121, "D"), new Beats(startTime + term * 121, "K"),
						new Beats(startTime + term * 125, "D"), new Beats(startTime + term * 125, "K"),
						new Beats(startTime + term * 129, "S"), new Beats(startTime + term * 129, "L"),
						new Beats(startTime + term * 133, "D"), new Beats(startTime + term * 133, "K", true),
						new Beats(startTime + term * 137, "D"), new Beats(startTime + term * 137, "K"),
						new Beats(startTime + term * 141, "S"), new Beats(startTime + term * 141, "L"),
						new Beats(startTime + term * 145, "D"), new Beats(startTime + term * 145, "K", true),
						new Beats(startTime + term * 149, "D"), new Beats(startTime + term * 149, "K"),
						new Beats(startTime + term * 153, "S"), new Beats(startTime + term * 153, "L"),
						new Beats(startTime + term * 157, "D", true), new Beats(startTime + term * 157, "K"),
						new Beats(startTime + term * 161, "D"), new Beats(startTime + term * 161, "K"),
						new Beats(startTime + term * 165, "S"), new Beats(startTime + term * 165, "L"),
						new Beats(startTime + term * 169, "D"), new Beats(startTime + term * 169, "K"),
						new Beats(startTime + term * 173, "D"), new Beats(startTime + term * 173, "K"),
						new Beats(startTime + term * 177, "S"), new Beats(startTime + term * 177, "L"),
						new Beats(startTime + term * 181, "D", true), new Beats(startTime + term * 181, "K"),
						new Beats(startTime + term * 185, "D"), new Beats(startTime + term * 185, "K"),
						new Beats(startTime + term * 189, "S"), new Beats(startTime + term * 189, "L"),
						new Beats(startTime + term * 193, "D"), new Beats(startTime + term * 193, "K"),
						new Beats(startTime + term * 197, "D", true), new Beats(startTime + term * 197, "K"),
						new Beats(startTime + term * 201, "S"), new Beats(startTime + term * 201, "L", true),
						new Beats(startTime + term * 205, "D"), new Beats(startTime + term * 205, "K"),
						new Beats(startTime + term * 209, "D", true), new Beats(startTime + term * 209, "K"),
						new Beats(startTime + term * 213, "S"), new Beats(startTime + term * 213, "L"),
						new Beats(startTime + term * 217, "D"), new Beats(startTime + term * 217, "K"),
						new Beats(startTime + term * 221, "D"), new Beats(startTime + term * 221, "K"),
						new Beats(startTime + term * 225, "S"), new Beats(startTime + term * 225, "L"),
						new Beats(startTime + term * 229, "D"), new Beats(startTime + term * 229, "K", true),
						new Beats(startTime + term * 233, "D"), new Beats(startTime + term * 233, "K"),
						new Beats(startTime + term * 237, "S"), new Beats(startTime + term * 237, "L"),
						new Beats(startTime + term * 241, "D"), new Beats(startTime + term * 241, "K"),
						new Beats(startTime + term * 245, "D", true), new Beats(startTime + term * 245, "K"),
						new Beats(startTime + term * 249, "S"), new Beats(startTime + term * 249, "L"),
						new Beats(startTime + term * 253, "D"), new Beats(startTime + term * 253, "K"),
						new Beats(startTime + term * 257, "D"), new Beats(startTime + term * 257, "K"),
						new Beats(startTime + term * 261, "S"), new Beats(startTime + term * 261, "L"),
						new Beats(startTime + term * 265, "D"), new Beats(startTime + term * 265, "K"),
						new Beats(startTime + term * 269, "D"), new Beats(startTime + term * 269, "K", true),
						new Beats(startTime + term * 273, "S"), new Beats(startTime + term * 273, "L"),
						new Beats(startTime + term * 277, "D"), new Beats(startTime + term * 277, "K"),
						new Beats(startTime + term * 281, "D", true), new Beats(startTime + term * 281, "K"),
						new Beats(startTime + term * 285, "S"), new Beats(startTime + term * 285, "L"),
						new Beats(startTime + term * 289, "D"), new Beats(startTime + term * 289, "K"),
						new Beats(startTime + term * 293, "D"), new Beats(startTime + term * 293, "K"),
						new Beats(startTime + term * 297, "S"), new Beats(startTime + term * 297, "L", true),
						new Beats(startTime + term * 301, "D"), new Beats(startTime + term * 301, "K"),
						new Beats(startTime + term * 305, "D"), new Beats(startTime + term * 305, "K"),
						new Beats(startTime + term * 309, "S", true), new Beats(startTime + term * 309, "L"),
						new Beats(startTime + term * 313, "D"), new Beats(startTime + term * 313, "K"),
						new Beats(startTime + term * 317, "D"), new Beats(startTime + term * 317, "K"),
						new Beats(startTime + term * 321, "S"), new Beats(startTime + term * 321, "L"),
						new Beats(startTime + term * 325, "D"), new Beats(startTime + term * 325, "K"),
						new Beats(startTime + term * 329, "D"), new Beats(startTime + term * 329, "K", true),
						new Beats(startTime + term * 333, "S"), new Beats(startTime + term * 333, "L"),
						new Beats(startTime + term * 337, "D"), new Beats(startTime + term * 337, "K"),
						new Beats(startTime + term * 341, "D", true), new Beats(startTime + term * 341, "K"),
						new Beats(startTime + term * 345, "S"), new Beats(startTime + term * 345, "L"),
						new Beats(startTime + term * 349, "D"), new Beats(startTime + term * 349, "K"),
						new Beats(startTime + term * 353, "D"), new Beats(startTime + term * 353, "K"),
						new Beats(startTime + term * 357, "S"), new Beats(startTime + term * 357, "L"),
						new Beats(startTime + term * 361, "D"), new Beats(startTime + term * 361, "K"),
						new Beats(startTime + term * 365, "D"), new Beats(startTime + term * 365, "K"),
						new Beats(startTime + term * 369, "S"), new Beats(startTime + term * 369, "L"),
						new Beats(startTime + term * 373, "D"), new Beats(startTime + term * 373, "K"),
						new Beats(startTime + term * 377, "D", true), new Beats(startTime + term * 377, "K"),
						new Beats(startTime + term * 381, "S"), new Beats(startTime + term * 381, "L"),
						new Beats(startTime + term * 385, "D"), new Beats(startTime + term * 385, "K", true),
						new Beats(startTime + term * 389, "D"), new Beats(startTime + term * 389, "K"),
						new Beats(startTime + term * 393, "S"), new Beats(startTime + term * 393, "L"),
						new Beats(startTime + term * 397, "D"), new Beats(startTime + term * 397, "K"),
						new Beats(startTime + term * 401, "D"), new Beats(startTime + term * 401, "K"),
						new Beats(startTime + term * 405, "S"), new Beats(startTime + term * 405, "L"),
						new Beats(startTime + term * 409, "D", true), new Beats(startTime + term * 409, "K"),
						new Beats(startTime + term * 413, "D"), new Beats(startTime + term * 413, "K"),
						new Beats(startTime + term * 417, "S"), new Beats(startTime + term * 417, "L"),
						new Beats(startTime + term * 421, "D"), new Beats(startTime + term * 421, "K"),
						new Beats(startTime + term * 425, "D"), new Beats(startTime + term * 425, "K"),
						new Beats(startTime + term * 429, "S"), new Beats(startTime + term * 429, "L"),
						new Beats(startTime + term * 433, "D", true), new Beats(startTime + term * 433, "K"),
						new Beats(startTime + term * 437, "D"), new Beats(startTime + term * 437, "K"),
						new Beats(startTime + term * 441, "S"), new Beats(startTime + term * 441, "L"),
						new Beats(startTime + term * 445, "D"), new Beats(startTime + term * 445, "K"),
						new Beats(startTime + term * 449, "D"), new Beats(startTime + term * 449, "K", true),
						new Beats(startTime + term * 453, "S"), new Beats(startTime + term * 453, "L"),

				};
				// ��Ʈ ĭ
			} // ����
			else if (difficulty.equals("HARD")) {
				int startTime = 4200;// ���� �ð� ����
				int term = 125;// ��Ʈ �� 125
				System.out.println("START");
				BEATS = new Beats[] { new Beats(startTime + term, "D"), new Beats(startTime + term, "K"),
						new Beats(startTime + term * 5, "D"), new Beats(startTime + term * 5, "K"),
						new Beats(startTime + term * 9, "S"), new Beats(startTime + term * 9, "L"),
						new Beats(startTime + term * 13, "D"), new Beats(startTime + term * 13, "K"),
						new Beats(startTime + term * 17, "D"), new Beats(startTime + term * 17, "K"),
						new Beats(startTime + term * 21, "F"), new Beats(startTime + term * 21, "J"),
						new Beats(startTime + term * 25, "D"), new Beats(startTime + term * 25, "K"),
						new Beats(startTime + term * 29, "D"), new Beats(startTime + term * 29, "K"),
						new Beats(startTime + term * 33, "S"), new Beats(startTime + term * 33, "L"),
						new Beats(startTime + term * 37, "D"), new Beats(startTime + term * 37, "K"),
						new Beats(startTime + term * 41, "D"), new Beats(startTime + term * 41, "K"),
						new Beats(startTime + term * 45, "F"), new Beats(startTime + term * 45, "J"),
						new Beats(startTime + term * 49, "D"), new Beats(startTime + term * 49, "K"),
						new Beats(startTime + term * 53, "D"), new Beats(startTime + term * 53, "K"),
						new Beats(startTime + term * 57, "Space"), new Beats(startTime + term * 61, "D"),
						new Beats(startTime + term * 61, "K"), new Beats(startTime + term * 65, "D"),
						new Beats(startTime + term * 65, "K"), new Beats(startTime + term * 69, "S"),
						new Beats(startTime + term * 69, "L"), new Beats(startTime + term * 73, "D"),
						new Beats(startTime + term * 73, "K"), new Beats(startTime + term * 77, "D"),
						new Beats(startTime + term * 77, "K"), new Beats(startTime + term * 81, "F"),
						new Beats(startTime + term * 81, "J"), new Beats(startTime + term * 85, "D"),
						new Beats(startTime + term * 85, "K"), new Beats(startTime + term * 89, "D"),
						new Beats(startTime + term * 89, "K"), new Beats(startTime + term * 93, "S"),
						new Beats(startTime + term * 93, "L"), new Beats(startTime + term * 97, "D"),
						new Beats(startTime + term * 97, "K"), new Beats(startTime + term * 101, "D"),
						new Beats(startTime + term * 101, "K"), new Beats(startTime + term * 105, "F"),
						new Beats(startTime + term * 105, "J"), new Beats(startTime + term * 109, "D"),
						new Beats(startTime + term * 109, "K"), new Beats(startTime + term * 113, "D"),
						new Beats(startTime + term * 113, "K"), new Beats(startTime + term * 117, "S"),
						new Beats(startTime + term * 117, "L"), new Beats(startTime + term * 121, "D"),
						new Beats(startTime + term * 121, "K"), new Beats(startTime + term * 125, "D"),
						new Beats(startTime + term * 125, "K"), new Beats(startTime + term * 129, "Space"),
						new Beats(startTime + term * 133, "D"), new Beats(startTime + term * 133, "K"),
						new Beats(startTime + term * 137, "D"), new Beats(startTime + term * 137, "K"),
						new Beats(startTime + term * 141, "Space"), new Beats(startTime + term * 145, "D"),
						new Beats(startTime + term * 145, "K"), new Beats(startTime + term * 149, "D"),
						new Beats(startTime + term * 149, "K"), new Beats(startTime + term * 153, "S"),
						new Beats(startTime + term * 153, "L"), new Beats(startTime + term * 157, "D"),
						new Beats(startTime + term * 157, "K"), new Beats(startTime + term * 161, "D"),
						new Beats(startTime + term * 161, "K"), new Beats(startTime + term * 165, "F"),
						new Beats(startTime + term * 165, "J"), new Beats(startTime + term * 169, "D"),
						new Beats(startTime + term * 169, "K"), new Beats(startTime + term * 173, "D"),
						new Beats(startTime + term * 173, "K"), new Beats(startTime + term * 177, "S"),
						new Beats(startTime + term * 177, "L"), new Beats(startTime + term * 181, "D"),
						new Beats(startTime + term * 181, "K"), new Beats(startTime + term * 185, "D"),
						new Beats(startTime + term * 185, "K"), new Beats(startTime + term * 189, "S"),
						new Beats(startTime + term * 189, "L"), new Beats(startTime + term * 193, "D"),
						new Beats(startTime + term * 193, "K"), new Beats(startTime + term * 197, "D"),
						new Beats(startTime + term * 197, "K"), new Beats(startTime + term * 201, "F"),
						new Beats(startTime + term * 201, "J"), new Beats(startTime + term * 205, "D"),
						new Beats(startTime + term * 205, "K"), new Beats(startTime + term * 209, "D"),
						new Beats(startTime + term * 209, "K"), new Beats(startTime + term * 213, "S"),
						new Beats(startTime + term * 213, "L"), new Beats(startTime + term * 217, "D"),
						new Beats(startTime + term * 217, "K"), new Beats(startTime + term * 221, "D"),
						new Beats(startTime + term * 221, "K"), new Beats(startTime + term * 225, "S"),
						new Beats(startTime + term * 225, "L"), new Beats(startTime + term * 229, "D"),
						new Beats(startTime + term * 229, "K"), new Beats(startTime + term * 233, "D"),
						new Beats(startTime + term * 233, "K"), new Beats(startTime + term * 237, "Space"),
						new Beats(startTime + term * 241, "D"), new Beats(startTime + term * 241, "K"),
						new Beats(startTime + term * 245, "D"), new Beats(startTime + term * 245, "K"),
						new Beats(startTime + term * 249, "S"), new Beats(startTime + term * 249, "L"),
						new Beats(startTime + term * 253, "D"), new Beats(startTime + term * 253, "K"),
						new Beats(startTime + term * 257, "D"), new Beats(startTime + term * 257, "K"),
						new Beats(startTime + term * 261, "S"), new Beats(startTime + term * 261, "L"),
						new Beats(startTime + term * 265, "D"), new Beats(startTime + term * 265, "K"),
						new Beats(startTime + term * 269, "D"), new Beats(startTime + term * 269, "K"),
						new Beats(startTime + term * 273, "Space"), new Beats(startTime + term * 277, "D"),
						new Beats(startTime + term * 277, "K"), new Beats(startTime + term * 281, "D"),
						new Beats(startTime + term * 281, "K"), new Beats(startTime + term * 285, "S"),
						new Beats(startTime + term * 285, "L"), new Beats(startTime + term * 289, "D"),
						new Beats(startTime + term * 289, "K"), new Beats(startTime + term * 293, "D"),
						new Beats(startTime + term * 293, "K"), new Beats(startTime + term * 297, "S"),
						new Beats(startTime + term * 297, "L"), new Beats(startTime + term * 301, "D"),
						new Beats(startTime + term * 301, "K"), new Beats(startTime + term * 305, "D"),
						new Beats(startTime + term * 305, "K"), new Beats(startTime + term * 309, "F"),
						new Beats(startTime + term * 309, "J"), new Beats(startTime + term * 313, "D"),
						new Beats(startTime + term * 313, "K"), new Beats(startTime + term * 317, "D"),
						new Beats(startTime + term * 317, "K"), new Beats(startTime + term * 321, "Space"),
						new Beats(startTime + term * 325, "D"), new Beats(startTime + term * 325, "K"),
						new Beats(startTime + term * 329, "D"), new Beats(startTime + term * 329, "K"),
						new Beats(startTime + term * 333, "S"), new Beats(startTime + term * 333, "L"),
						new Beats(startTime + term * 337, "D"), new Beats(startTime + term * 337, "K"),
						new Beats(startTime + term * 341, "D"), new Beats(startTime + term * 341, "K"),
						new Beats(startTime + term * 345, "Space"), new Beats(startTime + term * 349, "D"),
						new Beats(startTime + term * 349, "K"), new Beats(startTime + term * 353, "D"),
						new Beats(startTime + term * 353, "K"), new Beats(startTime + term * 357, "S"),
						new Beats(startTime + term * 357, "L"), new Beats(startTime + term * 361, "D"),
						new Beats(startTime + term * 361, "K"), new Beats(startTime + term * 365, "D"),
						new Beats(startTime + term * 365, "K"), new Beats(startTime + term * 369, "F"),
						new Beats(startTime + term * 369, "J"), new Beats(startTime + term * 373, "D"),
						new Beats(startTime + term * 373, "K"), new Beats(startTime + term * 377, "D"),
						new Beats(startTime + term * 377, "K"), new Beats(startTime + term * 381, "F"),
						new Beats(startTime + term * 381, "J"), new Beats(startTime + term * 385, "D"),
						new Beats(startTime + term * 385, "K"), new Beats(startTime + term * 389, "D"),
						new Beats(startTime + term * 389, "K"), new Beats(startTime + term * 393, "Space"),
						new Beats(startTime + term * 397, "D"), new Beats(startTime + term * 397, "K"),
						new Beats(startTime + term * 401, "D"), new Beats(startTime + term * 401, "K"),
						new Beats(startTime + term * 405, "S"), new Beats(startTime + term * 405, "L"),
						new Beats(startTime + term * 409, "D"), new Beats(startTime + term * 409, "K"),
						new Beats(startTime + term * 413, "D"), new Beats(startTime + term * 413, "K"),
						new Beats(startTime + term * 417, "Space"), new Beats(startTime + term * 421, "F"),
						new Beats(startTime + term * 421, "J"), new Beats(startTime + term * 425, "F"),
						new Beats(startTime + term * 425, "J"), new Beats(startTime + term * 429, "D"),
						new Beats(startTime + term * 429, "K"), new Beats(startTime + term * 433, "F"),
						new Beats(startTime + term * 433, "J"), new Beats(startTime + term * 437, "F"),
						new Beats(startTime + term * 437, "J"), new Beats(startTime + term * 441, "S"),
						new Beats(startTime + term * 441, "L"), new Beats(startTime + term * 445, "F"),
						new Beats(startTime + term * 445, "J"), new Beats(startTime + term * 449, "F"),
						new Beats(startTime + term * 449, "J"), new Beats(startTime + term * 453, "Space"), };

			}

		} // WE WILL ROCK YOU �Ǻ�
		else { // MIRROR NIGHT (REFLECTION) ��
			if (difficulty.equals("EASY")) {

				int startTime = 0;
				int term = 170;
				System.out.println("START");
				BEATS = new Beats[] { new Beats(startTime + term, "D"), new Beats(startTime + term, "K"),
						new Beats(startTime + term * 5, "D"), new Beats(startTime + term * 5, "K"),
						new Beats(startTime + term * 9, "S"), new Beats(startTime + term * 9, "L"),
						new Beats(startTime + term * 13, "D"), new Beats(startTime + term * 13, "K"),
						new Beats(startTime + term * 17, "D"), new Beats(startTime + term * 17, "K"),
						new Beats(startTime + term * 21, "S"), new Beats(startTime + term * 21, "L"),
						new Beats(startTime + term * 25, "D"), new Beats(startTime + term * 25, "K"),
						new Beats(startTime + term * 29, "D"), new Beats(startTime + term * 29, "K"),
						new Beats(startTime + term * 33, "S"), new Beats(startTime + term * 33, "L"),
						new Beats(startTime + term * 37, "D"), new Beats(startTime + term * 37, "K"),
						new Beats(startTime + term * 41, "D"), new Beats(startTime + term * 41, "K"),
						new Beats(startTime + term * 45, "S"), new Beats(startTime + term * 45, "L"),
						new Beats(startTime + term * 49, "D"), new Beats(startTime + term * 49, "K"),
						new Beats(startTime + term * 53, "D"), new Beats(startTime + term * 53, "K"),
						new Beats(startTime + term * 57, "S"), new Beats(startTime + term * 57, "L"),
						new Beats(startTime + term * 61, "D"), new Beats(startTime + term * 61, "K"),
						new Beats(startTime + term * 65, "D"), new Beats(startTime + term * 65, "K"),
						new Beats(startTime + term * 69, "S"), new Beats(startTime + term * 69, "L"),
						new Beats(startTime + term * 73, "D"), new Beats(startTime + term * 73, "K"),
						new Beats(startTime + term * 77, "D"), new Beats(startTime + term * 77, "K"),
						new Beats(startTime + term * 81, "S"), new Beats(startTime + term * 81, "L"),
						new Beats(startTime + term * 85, "D"), new Beats(startTime + term * 85, "K"),
						new Beats(startTime + term * 89, "D"), new Beats(startTime + term * 89, "K"),
						new Beats(startTime + term * 93, "S"), new Beats(startTime + term * 93, "L"),
						new Beats(startTime + term * 97, "D"), new Beats(startTime + term * 97, "K"),
						new Beats(startTime + term * 101, "D"), new Beats(startTime + term * 101, "K"),
						new Beats(startTime + term * 105, "S"), new Beats(startTime + term * 105, "L"),
						new Beats(startTime + term * 109, "D"), new Beats(startTime + term * 109, "K"),
						new Beats(startTime + term * 113, "D"), new Beats(startTime + term * 113, "K"),
						new Beats(startTime + term * 117, "S"), new Beats(startTime + term * 117, "L"),
						new Beats(startTime + term * 121, "D"), new Beats(startTime + term * 121, "K"),
						new Beats(startTime + term * 125, "D"), new Beats(startTime + term * 125, "K"),
						new Beats(startTime + term * 129, "S"), new Beats(startTime + term * 129, "L"),
						new Beats(startTime + term * 133, "D"), new Beats(startTime + term * 133, "K"),
						new Beats(startTime + term * 137, "D"), new Beats(startTime + term * 137, "K"),
						new Beats(startTime + term * 141, "S"), new Beats(startTime + term * 141, "L"),
						new Beats(startTime + term * 145, "D"), new Beats(startTime + term * 145, "K"),
						new Beats(startTime + term * 149, "D"), new Beats(startTime + term * 149, "K"),
						new Beats(startTime + term * 153, "S"), new Beats(startTime + term * 153, "L"),
						new Beats(startTime + term * 157, "D"), new Beats(startTime + term * 157, "K"),
						new Beats(startTime + term * 161, "D"), new Beats(startTime + term * 161, "K"),
						new Beats(startTime + term * 165, "S"), new Beats(startTime + term * 165, "L"),
						new Beats(startTime + term * 169, "D"), new Beats(startTime + term * 169, "K"),
						new Beats(startTime + term * 173, "D"), new Beats(startTime + term * 173, "K"),
						new Beats(startTime + term * 177, "S"), new Beats(startTime + term * 177, "L"),
						new Beats(startTime + term * 181, "D"), new Beats(startTime + term * 181, "K"),
						new Beats(startTime + term * 185, "D"), new Beats(startTime + term * 185, "K"),
						new Beats(startTime + term * 189, "S"), new Beats(startTime + term * 189, "L"),
						new Beats(startTime + term * 193, "D"), new Beats(startTime + term * 193, "K"),
						new Beats(startTime + term * 197, "D"), new Beats(startTime + term * 197, "K"),
						new Beats(startTime + term * 201, "S"), new Beats(startTime + term * 201, "L"),
						new Beats(startTime + term * 205, "D"), new Beats(startTime + term * 205, "K"),
						new Beats(startTime + term * 209, "D"), new Beats(startTime + term * 209, "K"),
						new Beats(startTime + term * 213, "S"), new Beats(startTime + term * 213, "L"),
						new Beats(startTime + term * 217, "D"), new Beats(startTime + term * 217, "K"),
						new Beats(startTime + term * 221, "D"), new Beats(startTime + term * 221, "K"),
						new Beats(startTime + term * 225, "S"), new Beats(startTime + term * 225, "L"),
						new Beats(startTime + term * 229, "D"), new Beats(startTime + term * 229, "K"),
						new Beats(startTime + term * 233, "D"), new Beats(startTime + term * 233, "K"),
						new Beats(startTime + term * 237, "S"), new Beats(startTime + term * 237, "L"),
						new Beats(startTime + term * 241, "D"), new Beats(startTime + term * 241, "K"),
						new Beats(startTime + term * 245, "D"), new Beats(startTime + term * 245, "K"),
						new Beats(startTime + term * 249, "S"), new Beats(startTime + term * 249, "L"),
						new Beats(startTime + term * 253, "D"), new Beats(startTime + term * 253, "K"),
						new Beats(startTime + term * 257, "D"), new Beats(startTime + term * 257, "K"),
						new Beats(startTime + term * 261, "S"), new Beats(startTime + term * 261, "L"),
						new Beats(startTime + term * 265, "D"), new Beats(startTime + term * 265, "K"),
						new Beats(startTime + term * 269, "D"), new Beats(startTime + term * 269, "K"),
						new Beats(startTime + term * 273, "S"), new Beats(startTime + term * 273, "L"),
						new Beats(startTime + term * 277, "D"), new Beats(startTime + term * 277, "K"),
						new Beats(startTime + term * 281, "D"), new Beats(startTime + term * 281, "K"),
						new Beats(startTime + term * 285, "S"), new Beats(startTime + term * 285, "L"),
						new Beats(startTime + term * 289, "D"), new Beats(startTime + term * 289, "K"),
						new Beats(startTime + term * 293, "D"), new Beats(startTime + term * 293, "K"),
						new Beats(startTime + term * 297, "S"), new Beats(startTime + term * 297, "L"),
						new Beats(startTime + term * 301, "D"), new Beats(startTime + term * 301, "K"),
						new Beats(startTime + term * 305, "D"), new Beats(startTime + term * 305, "K"),
						new Beats(startTime + term * 309, "S"), new Beats(startTime + term * 309, "L"),
						new Beats(startTime + term * 313, "D"), new Beats(startTime + term * 313, "K"),
						new Beats(startTime + term * 317, "D"), new Beats(startTime + term * 317, "K"),
						new Beats(startTime + term * 321, "S"), new Beats(startTime + term * 321, "L"),
						new Beats(startTime + term * 325, "D"), new Beats(startTime + term * 325, "K"),
						new Beats(startTime + term * 329, "D"), new Beats(startTime + term * 329, "K"),
						new Beats(startTime + term * 333, "S"), new Beats(startTime + term * 333, "L"),
						new Beats(startTime + term * 337, "D"), new Beats(startTime + term * 337, "K"),
						new Beats(startTime + term * 341, "D"), new Beats(startTime + term * 341, "K"),
						new Beats(startTime + term * 345, "S"), new Beats(startTime + term * 345, "L"),
						new Beats(startTime + term * 349, "D"), new Beats(startTime + term * 349, "K"),
						new Beats(startTime + term * 353, "D"), new Beats(startTime + term * 353, "K"),
						new Beats(startTime + term * 357, "S"), new Beats(startTime + term * 357, "L"),
						new Beats(startTime + term * 361, "D"), new Beats(startTime + term * 361, "K"),
						new Beats(startTime + term * 365, "D"), new Beats(startTime + term * 365, "K"),
						new Beats(startTime + term * 369, "S"), new Beats(startTime + term * 369, "L"),
						new Beats(startTime + term * 373, "D"), new Beats(startTime + term * 373, "K"),
						new Beats(startTime + term * 377, "D"), new Beats(startTime + term * 377, "K"),
						new Beats(startTime + term * 381, "S"), new Beats(startTime + term * 381, "L"),
						new Beats(startTime + term * 385, "D"), new Beats(startTime + term * 385, "K"),
						new Beats(startTime + term * 389, "D"), new Beats(startTime + term * 389, "K"),
						new Beats(startTime + term * 393, "S"), new Beats(startTime + term * 393, "L"),
						new Beats(startTime + term * 397, "D"), new Beats(startTime + term * 397, "K"),
						new Beats(startTime + term * 401, "D"), new Beats(startTime + term * 401, "K"),
						new Beats(startTime + term * 405, "S"), new Beats(startTime + term * 405, "L"),
						new Beats(startTime + term * 409, "D"), new Beats(startTime + term * 409, "K"),
						new Beats(startTime + term * 413, "D"), new Beats(startTime + term * 413, "K"),
						new Beats(startTime + term * 417, "S"), new Beats(startTime + term * 417, "L"),
						new Beats(startTime + term * 421, "D"), new Beats(startTime + term * 421, "K"),
						new Beats(startTime + term * 425, "D"), new Beats(startTime + term * 425, "K"),
						new Beats(startTime + term * 429, "S"), new Beats(startTime + term * 429, "L"),
						new Beats(startTime + term * 433, "D"), new Beats(startTime + term * 433, "K"),
						new Beats(startTime + term * 437, "D"), new Beats(startTime + term * 437, "K"),
						new Beats(startTime + term * 441, "S"), new Beats(startTime + term * 441, "L"),
						new Beats(startTime + term * 445, "D"), new Beats(startTime + term * 445, "K"),
						new Beats(startTime + term * 449, "D"), new Beats(startTime + term * 449, "K"),
						new Beats(startTime + term * 453, "S"), new Beats(startTime + term * 453, "L"),

				};
			} else {
				int startTime = 0;
				int term = 170;
				System.out.println("START");
				BEATS = new Beats[] { new Beats(startTime + term, "D"), new Beats(startTime + term, "K"),
						new Beats(startTime + term * 5, "D"), new Beats(startTime + term * 5, "K"),
						new Beats(startTime + term * 9, "S"), new Beats(startTime + term * 9, "L"),
						new Beats(startTime + term * 13, "D"), new Beats(startTime + term * 13, "K"),
						new Beats(startTime + term * 17, "D"), new Beats(startTime + term * 17, "K"),
						new Beats(startTime + term * 21, "F"), new Beats(startTime + term * 21, "J"),
						new Beats(startTime + term * 25, "D"), new Beats(startTime + term * 25, "K"),
						new Beats(startTime + term * 29, "D"), new Beats(startTime + term * 29, "K"),
						new Beats(startTime + term * 33, "S"), new Beats(startTime + term * 33, "L"),
						new Beats(startTime + term * 37, "D"), new Beats(startTime + term * 37, "K"),
						new Beats(startTime + term * 41, "D"), new Beats(startTime + term * 41, "K"),
						new Beats(startTime + term * 45, "F"), new Beats(startTime + term * 45, "J"),
						new Beats(startTime + term * 49, "D"), new Beats(startTime + term * 49, "K"),
						new Beats(startTime + term * 53, "D"), new Beats(startTime + term * 53, "K"),
						new Beats(startTime + term * 57, "Space", true), new Beats(startTime + term * 61, "D"),
						new Beats(startTime + term * 61, "K"), new Beats(startTime + term * 65, "D"),
						new Beats(startTime + term * 65, "K"), new Beats(startTime + term * 69, "S"),
						new Beats(startTime + term * 69, "L", true), new Beats(startTime + term * 73, "D"),
						new Beats(startTime + term * 73, "K"), new Beats(startTime + term * 77, "D"),
						new Beats(startTime + term * 77, "K"), new Beats(startTime + term * 81, "F"),
						new Beats(startTime + term * 81, "J"), new Beats(startTime + term * 85, "D"),
						new Beats(startTime + term * 85, "K"), new Beats(startTime + term * 89, "D"),
						new Beats(startTime + term * 89, "K"), new Beats(startTime + term * 93, "S"),
						new Beats(startTime + term * 93, "L"), new Beats(startTime + term * 97, "D", true),
						new Beats(startTime + term * 97, "K"), new Beats(startTime + term * 101, "D"),
						new Beats(startTime + term * 101, "K"), new Beats(startTime + term * 105, "F"),
						new Beats(startTime + term * 105, "J"), new Beats(startTime + term * 109, "D"),
						new Beats(startTime + term * 109, "K"), new Beats(startTime + term * 113, "D"),
						new Beats(startTime + term * 113, "K", true), new Beats(startTime + term * 117, "S"),
						new Beats(startTime + term * 117, "L"), new Beats(startTime + term * 121, "D"),
						new Beats(startTime + term * 121, "K"), new Beats(startTime + term * 125, "D"),
						new Beats(startTime + term * 125, "K"), new Beats(startTime + term * 129, "Space"),
						new Beats(startTime + term * 133, "D"), new Beats(startTime + term * 133, "K"),
						new Beats(startTime + term * 137, "D"), new Beats(startTime + term * 137, "K"),
						new Beats(startTime + term * 141, "Space", true), new Beats(startTime + term * 145, "D"),
						new Beats(startTime + term * 145, "K"), new Beats(startTime + term * 149, "D"),
						new Beats(startTime + term * 149, "K"), new Beats(startTime + term * 153, "S"),
						new Beats(startTime + term * 153, "L"), new Beats(startTime + term * 157, "D"),
						new Beats(startTime + term * 157, "K"), new Beats(startTime + term * 161, "D"),
						new Beats(startTime + term * 161, "K", true), new Beats(startTime + term * 165, "F"),
						new Beats(startTime + term * 165, "J"), new Beats(startTime + term * 169, "D"),
						new Beats(startTime + term * 169, "K"), new Beats(startTime + term * 173, "D"),
						new Beats(startTime + term * 173, "K", true), new Beats(startTime + term * 177, "S"),
						new Beats(startTime + term * 177, "L"), new Beats(startTime + term * 181, "D", true),
						new Beats(startTime + term * 181, "K"), new Beats(startTime + term * 185, "D"),
						new Beats(startTime + term * 185, "K"), new Beats(startTime + term * 189, "S"),
						new Beats(startTime + term * 189, "L"), new Beats(startTime + term * 193, "D"),
						new Beats(startTime + term * 193, "K"), new Beats(startTime + term * 197, "D"),
						new Beats(startTime + term * 197, "K"), new Beats(startTime + term * 201, "F", true),
						new Beats(startTime + term * 201, "J"), new Beats(startTime + term * 205, "D"),
						new Beats(startTime + term * 205, "K"), new Beats(startTime + term * 209, "D"),
						new Beats(startTime + term * 209, "K"), new Beats(startTime + term * 213, "S"),
						new Beats(startTime + term * 213, "L"), new Beats(startTime + term * 217, "D"),
						new Beats(startTime + term * 217, "K"), new Beats(startTime + term * 221, "D"),
						new Beats(startTime + term * 221, "K", true), new Beats(startTime + term * 225, "S"),
						new Beats(startTime + term * 225, "L"), new Beats(startTime + term * 229, "D", true),
						new Beats(startTime + term * 229, "K"), new Beats(startTime + term * 233, "D"),
						new Beats(startTime + term * 233, "K"), new Beats(startTime + term * 237, "Space"),
						new Beats(startTime + term * 241, "D"), new Beats(startTime + term * 241, "K"),
						new Beats(startTime + term * 245, "D"), new Beats(startTime + term * 245, "K"),
						new Beats(startTime + term * 249, "S"), new Beats(startTime + term * 249, "L"),
						new Beats(startTime + term * 253, "D"), new Beats(startTime + term * 253, "K"),
						new Beats(startTime + term * 257, "D"), new Beats(startTime + term * 257, "K"),
						new Beats(startTime + term * 261, "S"), new Beats(startTime + term * 261, "L"),
						new Beats(startTime + term * 265, "D"), new Beats(startTime + term * 265, "K"),
						new Beats(startTime + term * 269, "D"), new Beats(startTime + term * 269, "K"),
						new Beats(startTime + term * 273, "Space"), new Beats(startTime + term * 277, "D"),
						new Beats(startTime + term * 277, "K"), new Beats(startTime + term * 281, "D"),
						new Beats(startTime + term * 281, "K"), new Beats(startTime + term * 285, "S", true),
						new Beats(startTime + term * 285, "L"), new Beats(startTime + term * 289, "D"),
						new Beats(startTime + term * 289, "K"), new Beats(startTime + term * 293, "D"),
						new Beats(startTime + term * 293, "K", true), new Beats(startTime + term * 297, "S"),
						new Beats(startTime + term * 297, "L"), new Beats(startTime + term * 301, "D"),
						new Beats(startTime + term * 301, "K", true), new Beats(startTime + term * 305, "D"),
						new Beats(startTime + term * 305, "K"), new Beats(startTime + term * 309, "F", true),
						new Beats(startTime + term * 309, "J"), new Beats(startTime + term * 313, "D"),
						new Beats(startTime + term * 313, "K"), new Beats(startTime + term * 317, "D"),
						new Beats(startTime + term * 317, "K"), new Beats(startTime + term * 321, "Space"),
						new Beats(startTime + term * 325, "D"), new Beats(startTime + term * 325, "K"),
						new Beats(startTime + term * 329, "D"), new Beats(startTime + term * 329, "K"),
						new Beats(startTime + term * 333, "S"), new Beats(startTime + term * 333, "L"),
						new Beats(startTime + term * 337, "D", true), new Beats(startTime + term * 337, "K"),
						new Beats(startTime + term * 341, "D"), new Beats(startTime + term * 341, "K"),
						new Beats(startTime + term * 345, "Space", true), new Beats(startTime + term * 349, "D"),
						new Beats(startTime + term * 349, "K"), new Beats(startTime + term * 353, "D"),
						new Beats(startTime + term * 353, "K"), new Beats(startTime + term * 357, "S"),
						new Beats(startTime + term * 357, "L"), new Beats(startTime + term * 361, "D"),
						new Beats(startTime + term * 361, "K"), new Beats(startTime + term * 365, "D"),
						new Beats(startTime + term * 365, "K"), new Beats(startTime + term * 369, "F"),
						new Beats(startTime + term * 369, "J"), new Beats(startTime + term * 373, "D"),
						new Beats(startTime + term * 373, "K", true), new Beats(startTime + term * 377, "D"),
						new Beats(startTime + term * 377, "K"), new Beats(startTime + term * 381, "F"),
						new Beats(startTime + term * 381, "J"), new Beats(startTime + term * 385, "D"),
						new Beats(startTime + term * 385, "K"), new Beats(startTime + term * 389, "D"),
						new Beats(startTime + term * 389, "K"), new Beats(startTime + term * 393, "Space"),
						new Beats(startTime + term * 397, "D"), new Beats(startTime + term * 397, "K"),
						new Beats(startTime + term * 401, "D"), new Beats(startTime + term * 401, "K"),
						new Beats(startTime + term * 405, "S"), new Beats(startTime + term * 405, "L"),
						new Beats(startTime + term * 409, "D", true), new Beats(startTime + term * 409, "K"),
						new Beats(startTime + term * 413, "D"), new Beats(startTime + term * 413, "K"),
						new Beats(startTime + term * 417, "Space"), new Beats(startTime + term * 421, "F"),
						new Beats(startTime + term * 421, "J"), new Beats(startTime + term * 425, "F"),
						new Beats(startTime + term * 425, "J"), new Beats(startTime + term * 429, "D"),
						new Beats(startTime + term * 429, "K"), new Beats(startTime + term * 433, "F"),
						new Beats(startTime + term * 433, "J"), new Beats(startTime + term * 437, "F"),
						new Beats(startTime + term * 437, "J", true), new Beats(startTime + term * 441, "S"),
						new Beats(startTime + term * 441, "L"), new Beats(startTime + term * 445, "F"),
						new Beats(startTime + term * 445, "J"), new Beats(startTime + term * 449, "F"),
						new Beats(startTime + term * 449, "J"), new Beats(startTime + term * 453, "Space"),

				};

			}
		} // REFELECTION �Ǻ�
		System.out.println("DROPS");
		// ����Ʈ���⸦ �����ϱ� ���ܰ��̴�.
		PutThingsIn();// ��ũ ���߱�
		// ���� �Ǻ��� �޾����� �� �Ǻ��� ���� ��Ʈ�� ����Ʈ���ָ� �ȴ�.

	}// START

	/// PutThingsIn�� �Ǻ��� ������ �׿� ���� ����Ʈ���ټ� �ֵ��� ��ũ�� �����.
	// �ּ� �Ϸ�
	public void PutThingsIn() {
		int i = 0;
		gameMusic.start();
		// ���������� �����ϰ� BEATS�迭 ó������ �����Ͽ�
		while (i < BEATS.length && !this.isInterrupted()) {
			// i�� 0���� BEATS���������̸� game�� interrupted�� �ƴҽÿ�
			boolean dropped = false;// �ʱ� dropped�� �������� ����
			if (BEATS[i].getTime() <= gameMusic.getTime()) {
				// ��Ʈ�� ���� ���� �ð��� ���� ����ð��� �޾ƿ� ���Ͽ� ����Ʈ���ִ� ������� ��ũ�� �����.
				boolean special = BEATS[i].getSpecial();// ��Ʈ�� special�Ǻ����θ� �޾Ƽ�
				if (special == true) {// ���� special�̸�
					Note note = new Note(BEATS[i].getNoteName(), special);
					// ��Ʈ �����ڿ� special ���� true���� �ְ� �� �����ڷ� ������
					note.start();// ��Ʈ ����
					noteList.add(note);// �����ϱ� ���ϰ� ����Ʈ�� �ִ´�.
					i++;// ���� ���� ��Ʈ�� �����Ҽ� �ֵ��� i�� ������Ų��.
				} else {
					Note note = new Note(BEATS[i].getNoteName());
					// �Ϲݳ�Ʈ�� ��� �Ķ���͸� �ϳ��� �޴� �����ڸ� ����Ͽ�
					note.start();// ���۽�Ű��
					noteList.add(note);// ����Ʈ�� �߰���Ų��.
					i++;// ���� ���� ��Ʈ�� �����Ҽ� �ֵ��� i�� ������Ų��.
				}
				dropped = true;
			} // �ð� ������ ������ �ֵ���
			if (!dropped) {// �ȶ���������
				try {
					if (!Thread.interrupted())// �����尡 �ȸ�������
					{
						Thread.sleep(3);// ��� ����
						// ��������ÿ� �ʹ� ���� ����
					}
				} catch (Exception e) {
					e.getMessage();
				}
			}
		} // while��
		MusicFlag = true;
		// ���߿� ������ �������� �˷��ֱ� ���� flag
		// �̰��� Ȯ�� ���ϸ� �����Ҷ� ��¿������ �������� ���۵Ǵ� ��찡 �ֱ⿡
		// �װ��� ���� ���Ͽ� ����Ͽ���.
	}

	// JUDGELEFT�� JUDGERIGHT�� ���̴� sdf������ �����ϴ��� �ƴϳ��̴�.
	// ���� Ű���� ���õǴ� ��찡 �־ ��������.
	// �� �Լ��� �Է¹��� Ű���� noteList���� �ִ� ���� ����� ���� Ű�� ���Ͽ� ������ ���� ���̴�.
	// LEFT�� RIGHT�Լ��� ������ �����ϹǷ� ������ LEFT�����ؼ��� �Ͽ���./
	public void judgeLeft(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);// ��Ʈ ����Ʈ ������ ��Ʈ�� �ϳ� �޾ƿͼ�
			if (input.equals(note.getNoteType())) {// �Է¹��� ��Ʈ�� ��Ʈ�� Ÿ���� �������
				boolean special = note.special;
				returnedValueOfNoteJudgement = note.judge();//
				if (returnedValueOfNoteJudgement.equals("EARLY")) {
					if (special) {
						JUDGEMENT = "SPECIAL EARLY";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {
						Gamescore += EARLY;
						JUDGEMENT = "EARLY";
						combo++;
					}
				} else if (returnedValueOfNoteJudgement.equals("GOOD")) {
					if (special) {
						JUDGEMENT = "SPECIAL GOOD";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {
						Gamescore += GOOD;
						JUDGEMENT = "GOOD";
						combo++;
					}
				} else if (returnedValueOfNoteJudgement.equals("LATE")) {

					if (special) {
						JUDGEMENT = "SPECIAL LATE";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {
						Gamescore += LATE;
						JUDGEMENT = "LATE";
						combo++;
					}

				} else if (returnedValueOfNoteJudgement.equals("PERFECT")) {
					if (special) {
						JUDGEMENT = "SPECIAL PERFECT";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {
						Gamescore += PERFECT;
						JUDGEMENT = "PERFECT";
						combo++;
					}

				} else if (returnedValueOfNoteJudgement.equals("GREAT")) {
					if (special) {
						JUDGEMENT = "SPECIAL GREAT";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {

						Gamescore += GREAT;
						JUDGEMENT = "GREAT";
						combo++;
					}

				} else {// y�� 535���� ������ �����ų� MISS�� ���
					combo = 0;
					if (special) {
						JUDGEMENT = "SPECIAL MISS";

					}
				}
				if (combo >= Maxcombo)
					Maxcombo = combo;

				break;
			}
		}
	}

	public void judgeRight(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);// ��Ʈ ����Ʈ ������ ��Ʈ�� �ϳ� �޾ƿͼ�
			if (input.equals(note.getNoteType())) {// �Է¹��� ��Ʈ�� ��Ʈ�� Ÿ���� �������
				boolean special = note.special;
				returnedValueOfNoteJudgement = note.judge();//
				if (returnedValueOfNoteJudgement.equals("EARLY")) {
					if (special) {
						JUDGEMENT = "SPECIAL EARLY";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {
						Gamescore += EARLY;
						JUDGEMENT = "EARLY";
						combo++;
					}
				} else if (returnedValueOfNoteJudgement.equals("GOOD")) {
					if (special) {
						JUDGEMENT = "SPECIAL GOOD";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {
						Gamescore += GOOD;
						JUDGEMENT = "GOOD";
						combo++;
					}
				} else if (returnedValueOfNoteJudgement.equals("LATE")) {

					if (special) {
						JUDGEMENT = "SPECIAL LATE";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {
						Gamescore += LATE;
						JUDGEMENT = "LATE";
						combo++;
					}

				} else if (returnedValueOfNoteJudgement.equals("PERFECT")) {
					if (special) {
						JUDGEMENT = "SPECIAL PERFECT";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {
						Gamescore += PERFECT;
						JUDGEMENT = "PERFECT";
						combo++;
					}

				} else if (returnedValueOfNoteJudgement.equals("GREAT")) {
					if (special) {
						JUDGEMENT = "SPECIAL GREAT";
						Gamescore += SpecialNotes;
						combo = combo + 5;
					} else {

						Gamescore += GREAT;
						JUDGEMENT = "GREAT";
						combo++;
					}

				} else {// y�� 535���� ������ �����ų� MISS�� ���
					combo = 0;
					if (special) {
						JUDGEMENT = "SPECIAL MISS";

					}
				}
				if (combo >= Maxcombo)
					Maxcombo = combo;

				break;
			}
		}
	}
}

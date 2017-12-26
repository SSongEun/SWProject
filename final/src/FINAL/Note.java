package FINAL;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	// ��Ʈ �̹���
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	// Beats�� Special
	private Image noteSpecialImage = new ImageIcon(Main.class.getResource("../images/specialImage.png")).getImage();
	private Image noteYellowImage = new ImageIcon(Main.class.getResource("../images/yellow.png")).getImage();
	private Image notePurpleImage = new ImageIcon(Main.class.getResource("../images/purple.png")).getImage();

	// ��Ʈ�� �̹����̴�.
	// �⺻ ��Ʈ�̹���, ������̹���,Ư���̹���,���� �̹������� �����Ͽ���
	// ����ϴ°��� noteBasicImage�μ� ���� �ʿ��ϴٸ� �� �̹����� �ٲ㼭 ��Ÿ����./

	private int x, y = -100;// ��� ��Ʈ���� ���� -100���� ���������.
	// x�� � ��ġ�� �׷����� ���� ���̰�, y�� ������ -100���� ����ڰ� �Ⱥ��̴°��������� ��Ʈ�� ���������� �ϴ�
	// �ʱⰪ�̴�.

	private String noteType;
	private boolean proceeding = true;
	public boolean special = false;
	// Ÿ��, ������, ����� ��Ʈ���� ��Ÿ����.

	public boolean isProceeding() {
		return proceeding;
	}
	// ���������� ���� ��� ���� method

	public String getNoteType() {
		return noteType;
	}
	// ��ƮŸ�� �޴¿�

	public void close() {
		proceeding = false;
	}
	// �����Ȳ�� �������ش�

	public void KILLTHREAD() {
		if (this != null) {
			interrupt();
		}
	}
	// noteList���� ��Ʈ�� ������ ������
	// ���ӵ��߿� ���ð�� ��� �����ϰ� �ǹǷ�
	// �װ��� �����ϱ� ���Ͽ� �����Ͽ���.

	public Note(String noteType) {
		// �⺻ �������̴�.
		// noteType�� ���� ��� ��ġ�� �׷����� ��������
		// �׿� ���� � �̹����� �׷����� ���Ѵ�.
		if (noteType.equals("S")) {
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
			noteBasicImage = noteYellowImage;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
			noteBasicImage = notePurpleImage;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
			noteBasicImage = noteYellowImage;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;

	}

	public Note(String noteType, boolean special) {
		// special�� �߰��� �������̴�
		// ��ġ�� ���ϰ�
		// �̹����� Ư���̹����� �����.

		if (noteType.equals("S")) {
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
		this.special = special;
		noteBasicImage = noteSpecialImage;
		// ������ �⺻ �����ڿ� ����
	}

	// ��Ʈ�� � ������ �׷����� ���ϴ� �Լ��̴�.
	public void screenDraw(Graphics2D g) {
		if (noteType.contains("Space")) {
			g.drawImage(noteBasicImage, x + 100, y, null);
			g.drawImage(noteBasicImage, x, y, null);

		} else {
			g.drawImage(noteBasicImage, x, y, null);
		}
	}
	// space�� ���� x,x+100��ġ�� ��� �׷�����
	// �Ϲ� ���� �ϳ��� ��Ʈ�� �׷�����.

	public void drop() {
		y = y + Main.NOTE_SPEED;
		if (y > 625) {
			System.out.println("MISS");
			Game.Gamescore += -100;
			Game.JUDGEMENT = "MISS";
			Game.combo = 0;
			Game.Life += Game.Wrong;
			close();
		}
	}

	// gamescore,judgement,combo,life,wrong���� static ���� �����߱⿡
	// Game.���� �ٷ� �����Ҽ� �ֵ��� �Ͽ���.
	// ��Ʈ �ӵ���ŭ ��� �����ش�.
	// ���� y���� 625���� �Ѿ��� miss�� ���� ������ �ϸ鼭
	// combo�� gamescore,life� ���� ����� �����ϰ�
	// �� ��Ʈ�� interrupt�ϸ鼭 ������.
	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeding) {
					Thread.sleep(Main.SLEEP_TIME);
				} // �������� ���
				else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	// �⺻ run �Լ������� ��Ʈ�� ��Ʈ���������ϋ� �� interrupt�Ǳ� ������
	// ��� drop �Լ��� ȣ���Ͽ� y���� NOTESPEED��ŭ ���ϰ�
	// sleeptime��ŭ ����.

	// judge�Լ��� ��Ʈ ������ � ��ġ�� �ִ����� �����Ͽ�
	// �ش��ϴ� ���� �����ϰ�
	// ��Ʈ�� ���࿩�θ� ��Ÿ���� proceeding���� ������ �־�
	// run method�ȿ��� ��Ʈ�� ���� interrupt�� ȣ���ϰ� �����
	public String judge() {
		if (y >= 620) {
			System.out.println("LATE");
			close();
			return "LATE";
		} else if (y >= 613) {
			System.out.println("LATE");
			close();
			return "LATE";
		} else if (y >= 600) {
			System.out.println("Good");
			close();
			return "GOOD";
		} else if (y >= 590) {
			System.out.println("Great");
			close();
			return "GREAT";
		} else if (y >= 575) {
			System.out.println("Perfect");
			close();
			return "PERFECT";
		} else if (y >= 560) {
			System.out.println("Great");
			close();
			return "GREAT";
		} else if (y >= 550) {
			System.out.println("Good");
			close();
			return "GOOD";
		} else if (y >= 535) {
			System.out.println("Early");
			close();
			return "EARLY";
		}
		return "";
	}
	// �����ڷξ ��ƮŸ�������� �޴´� --> � ��ġ�� ��Ʈ�� �������� �޴´�.
	// run���� drop�Լ��� ȣ���Ͽ� �������̸� ����Ÿ�Ӹ�ŭ ���߾��ٰ� ��� �����ϰ�
	// �������� �ƴϸ� ��Ʈ�� ���δ�.
	// judge�Լ��� ����Ͽ� ������ ��Ÿ����.
	// �׸� �׸��°Ϳ� ���ؼ��� space��� x�� 100��ŭ ������ ��ġ�� �ϳ� �� �׷��� ��� �����
	// �������� �״�� ������ �Ѵ�.
}

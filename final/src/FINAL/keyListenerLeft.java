package FINAL;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//�� Ŭ������ �ܺ� Ŭ������ ���ǵǾ��� ����ڰ� Ű�� �Է��Կ� ���� game�ȿ� �ִ�
//Ű���� ���� �ൿ������ trigger�Ҽ� �ִ� Ŭ�����̴�. game�� public static���� �����߱⿡ �ܺο�����
//������ �����ϹǷ� ����ϱ� ���ϰ� �ܺ� Ŭ������ ��Ÿ������.
//game�� null�϶��� �ƹ��� �ൿ�� �������� �ʴ´�.
//game�� null�� �ƴ� ��쿡�� �� �� �ش��ϴ� Ű���� ���� �̺�Ʈ�� ���� 
//ó���� �����Ҽ� �ֵ��� �ϴ� �ൿ���� trigger��Ų��.
public class keyListenerLeft extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		if (RhythmGameMusicSelection.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) { // SŰ
			RhythmGameMusicSelection.game.PS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) { // DŰ
			RhythmGameMusicSelection.game.PD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) { // FŰ
			RhythmGameMusicSelection.game.PF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) { // SpaceBarŰ
			RhythmGameMusicSelection.game.PSP();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (RhythmGameMusicSelection.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {// SŰ
			RhythmGameMusicSelection.game.RS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {// DŰ
			RhythmGameMusicSelection.game.RD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {// FŰ
			RhythmGameMusicSelection.game.RF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {// SpaceBarŰ
			RhythmGameMusicSelection.game.RSP();
		}

	}
	// ��Ŭ������ ����ڿ��� Ű�� �Է¹ް� �� �Ŀ� ������ �׼ǰ� ���� �׼��� �����ų�� �ֵ��� �Ѵ�
}

package FINAL;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//�� Ŭ������ �ܺ� Ŭ������ ���ǵǾ��� ����ڰ� Ű�� �Է��Կ� ���� game�ȿ� �ִ�
//Ű���� ���� �ൿ������ trigger�Ҽ� �ִ� Ŭ�����̴�. game�� public static���� �����߱⿡ �ܺο�����
//������ �����ϹǷ� ����ϱ� ���ϰ� �ܺ� Ŭ������ ��Ÿ������.
//game�� null�϶��� �ƹ��� �ൿ�� �������� �ʴ´�.
//game�� null�� �ƴ� ��쿡�� �� �� �ش��ϴ� Ű���� ���� �̺�Ʈ�� ���� 
//ó���� �����Ҽ� �ֵ��� �ϴ� �ൿ���� trigger��Ų��.
public class keyListenerRight extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		if (RhythmGameMusicSelection.game == null) {
			return;
		} else if (e.getKeyCode() == KeyEvent.VK_J) {// JŰ
			RhythmGameMusicSelection.game.PJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {// KŰ
			RhythmGameMusicSelection.game.PK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {// LŰ
			RhythmGameMusicSelection.game.PL();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (RhythmGameMusicSelection.game == null) {
			return;
		} else if (e.getKeyCode() == KeyEvent.VK_J) {// JŰ
			RhythmGameMusicSelection.game.RJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {// KŰ
			RhythmGameMusicSelection.game.RK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {// LŰ
			RhythmGameMusicSelection.game.RL();
		}
	}
	// ��Ŭ������ ����ڿ��� Ű�� �Է¹ް� �� �Ŀ� ������ �׼ǰ� ���� �׼��� �����ų�� �ֵ��� �Ѵ�
}

package FINAL;

//��Ʈ�� �����ϱ� ������ � ��Ʈ�� � �ð��� �������ٸ� �˷��ִ� Ŭ�����̴�.
//�����ڴ� 2���� ������ �ִµ� Ư�� ��Ʈ�� ���� boolean���� ���ǵ� �����ڿ�
//�׳� ������ �̷��� 2���� �������.
//��Ʈ �迭�� �Ǻ�ó�� ���� ����ϹǷ�
//� �ð��� � ��Ʈ�� ���������� �˾ƾ��ϱ� ������ getNoteName�� getTime�� �����Ͽ���.
//��Ʈ�� Ư������ �˾ƾ��Ҷ��� ���ؼ� getSpecial()���� �����Ͽ���.
public class Beats {
	private int time;
	private String Name;
	boolean special;

	public Beats(int t, String name) {
		this.time = t;
		this.Name = name;
	}
	public Beats(int t,String name,boolean special) {
		this.time = t;
		this.Name = name;
		this.special=special;
	}

	public String getNoteName() {
		return Name;
	}

	public int getTime() {
		return time;
	}
	public boolean getSpecial() {
		return special;
	}
	//���///////////////////////////////////
	//�� Ŭ������ �ð��� � Ÿ���� ��Ʈ�� ���������� �����Ѵ�./
	//� ��Ʈ���� Ư���Կ� ���ؼ� �����Ѵ�.////////////
	////////////////////////////////////////
}

package New;

public class AppManager
{
	private TwoGames twoGames;
	private LevelReversi levelPanel;
	private PlayReversi playPanel;
	private static AppManager appManager;
	
	public TwoGames getTwoGames()	{ return twoGames; }
	public LevelReversi getLevelReversi()	{ return levelPanel; }
	public PlayReversi getPlayReversi()	{ return playPanel; }

	public void setTwoGames(TwoGames t)	{ twoGames = t; }
	public void setLevelReversi(LevelReversi l)	{ levelPanel = l; }
	public void setPlayReversi(PlayReversi p)	{ playPanel = p; }
	
	public AppManager getInstance()
	{
		if (appManager == null) appManager = new AppManager();
		return appManager;
	}
}
package games.mgd.archery.logic.control;

import games.mgd.archery.logic.control.MultiTouchSensor;

public class SensorInterface 
{
	private MultiTouchSensor m_touch;
	
	private static SensorInterface m_instance = null;
	
	private boolean m_initialized;
	
	protected SensorInterface() 
	{
		m_initialized = false;;
	}
	public static SensorInterface GetInstance()
	{
		if(m_instance == null) 
		{
			m_instance = new SensorInterface();
		}
		return m_instance;
	}
	
	public void Initialize(int screenWidth, int screenHeight, float scaleX, float scaleY)
	{
		m_touch = new MultiTouchSensor(screenWidth, screenHeight, scaleX, scaleY);
		
		m_initialized = true;
	}
	/*
	public void Update(Game game)
	{
		m_touch.GetInput(game.GetInputManager());
	}
	*/
	public MultiTouchSensor GetTouch()
	{
		return m_touch;
	}
	public boolean Initialized()
	{
		return m_initialized;
	}
}

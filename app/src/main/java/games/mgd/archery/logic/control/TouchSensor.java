package com.engine.sensor;

import java.util.List;

import games.mgd.archery.game.input.IEvent.TouchEvent;
import games.mgd.archery.game.input.InputManager;

public class TouchSensor 
{
	int m_numTouches;
	protected boolean m_touched;
	
	protected float m_screenWidth;
	protected float m_screenHeight;
	protected float m_scaleX;
	protected float m_scaleY;
	protected float m_halfScaleX;
	protected float m_halfScaleY;
	
	float [] m_position;
	
	public TouchSensor(int screenWidth, int screenHeight, float scaleX, float scaleY)
	{	
		m_numTouches = 0;
		m_screenWidth = screenWidth;
		m_screenHeight = screenHeight;
		m_scaleX = scaleX / (float)screenWidth;
		m_scaleY = scaleY / (float)screenHeight;
		
		m_halfScaleX = scaleX * 0.5f;
		m_halfScaleY = scaleY * 0.5f;
		
		m_position = new float[2];
	}
	
	public void GetInput(InputManager events)
	{
		m_touched = false;
		List<TouchEvent> touchEvents = events.GetTouchEvents();
		
		for(int j = 0; j < m_numTouches; ++j)
		{
			for (int i = 0; i < touchEvents.size(); i++)
			{
		
			}
		}
		
		/*
		for(int i = 0; i < events.GetTouchPointerCount(); ++i)
		{
			if(events.IsTouchDown(i))
				m_touched = true;
		}
		
		if(m_touched)
		{
			m_numTouches = events.GetTouchPointerCount();
		}
		else
		{
			m_numTouches = 0;
		}
		*/
		if(touchEvents.size() >= 1)
		{		
			m_position[0] = (touchEvents.get(0).x * m_scaleX - (m_halfScaleX));
			m_position[1] = -(touchEvents.get(0).y * m_scaleY - (m_halfScaleY));
		}
	}
	
	public float [] Getposition()
	{
		return m_position;
	}
	public float GetX()
	{
		return m_position[0];
	}
	public float GetY()
	{
		return -m_position[1];
	}
	public boolean IsTouched()
	{
		return m_touched;
	}
}

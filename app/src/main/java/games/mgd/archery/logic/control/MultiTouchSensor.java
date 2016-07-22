package games.mgd.archery.logic.control;
// package com.engine.sensor;

import games.mgd.archery.game.input.InputManager;
import games.mgd.archery.game.input.TouchPoint;

public class MultiTouchSensor 
{
	int m_currentTouches = 0;
	int m_previousTouches = 0;
	
	private int m_numTouches;
	private boolean m_touched;

	private float m_scaleX;
	private float m_scaleY;
	private float m_halfScaleX;
	private float m_halfScaleY;
	
	private TouchPoint m_touch1;
	private TouchPoint m_touch2;
	
	public MultiTouchSensor(int screenWidth, int screenHeight, float scaleX, float scaleY)
	{	
		m_numTouches = 0;

		m_scaleX = scaleX / (float)screenWidth;
		m_scaleY = scaleY / (float)screenHeight;
		
		m_halfScaleX = scaleX * 0.5f;
		m_halfScaleY = scaleY * 0.5f;
		
		m_touch1 = new TouchPoint();
		m_touch2 = new TouchPoint();
	}
	
	public void GetInput(InputManager events)
	{
		m_currentTouches = events.GetCurrentTouches();
	
		if(m_currentTouches == 1)
		{
			if(!m_touch1.IsTouched())
				m_touch1.Set();
			
			m_touch1.SetTouchPoint(events.GetTouchX(0), events.GetTouchY(0), 
					m_scaleX, m_scaleY, m_halfScaleX, m_halfScaleY);
		}
		else if(m_currentTouches == 2)
		{
			if(!m_touch2.IsTouched())
				m_touch2.Set();
			
			m_touch2.SetTouchPoint(events.GetTouchX(1), events.GetTouchY(1), 
					m_scaleX, m_scaleY, m_halfScaleX, m_halfScaleY);
		}
		
		if(m_currentTouches > 2)
		{
			m_currentTouches = 2;
		}
		if(m_currentTouches == 1 && m_previousTouches == 2)
		{
			m_touch2.Release();
		}
		else if(m_currentTouches == 0 && m_previousTouches == 1)
		{
			m_touch1.Release();
		}
		else if(m_currentTouches == 0 && m_previousTouches == 0)
		{
			m_touch1.Release();
			m_touch2.Release();
		}

		m_previousTouches = m_currentTouches;
	}
	
	public float [] Getposition1()
	{
		return m_touch1.GetCurrentTouch();
	}
	public float [] Getposition2()
	{
		return m_touch2.GetCurrentTouch();
	}
	public boolean IsTouched()
	{
		return m_touched;
	}
	public boolean TwoTouches()
	{
		return(m_numTouches > 1);
	}
	public boolean IsTouch1Set()
	{
		return m_touch1.IsTouched();
	}
	public boolean IsTouch2Set()
	{
		return (m_touch2.IsTouched() && m_currentTouches == 2);
	}
	public float [] GetStart1()
	{
		return m_touch1.GetFirstTouch();
	}
	public float [] GetStart2()
	{
		return m_touch2.GetFirstTouch();
	}
	public int GetNumTouches()
	{
		return m_numTouches;
	}
}

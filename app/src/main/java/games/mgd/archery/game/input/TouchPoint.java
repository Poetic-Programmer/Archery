package games.mgd.archery.game.input;



public class TouchPoint 
{
	enum TouchState
	{
		None,
		Press,
		Drag,
		Up
	};
	
	private TouchState m_currentTouchState;
	
	private float [] m_firstTouchPoint;
	private float [] m_currentTouchPoint;
	
	private boolean m_touched;
	
	public TouchPoint()
	{
		m_currentTouchState = TouchState.None;
		
		m_firstTouchPoint = new float[2];
		m_currentTouchPoint = new float[2];
		
		m_touched = false;
	}
	
	public void SetTouchPoint(float x, float y)
	{		
		if(m_currentTouchState == TouchState.None)
		{
			m_firstTouchPoint[0] = x;
			m_firstTouchPoint[1] = y;
			
			m_currentTouchPoint[0] = x;
			m_currentTouchPoint[1] = y;
			
			m_currentTouchState = TouchState.Press;
			return;
		}
		else if(m_currentTouchState == TouchState.Press)
		{
			if(m_currentTouchPoint[0] != x && m_currentTouchPoint[1] != y)
			{
				m_firstTouchPoint[0] = x;
				m_firstTouchPoint[1] = y;
				
				m_currentTouchState = TouchState.Drag;
			}
			
			m_currentTouchPoint[0] = x;
			m_currentTouchPoint[1] = y;
			return;
		}
		else if(m_currentTouchState == TouchState.Drag)
		{
			m_currentTouchPoint[0] = x;
			m_currentTouchPoint[1] = y;
			return;
		}
	}
	
	public void SetTouchPoint(float x, float y,
			float scaleX, float scaleY, float offsetX, float offsetY)
	{	
		if(m_currentTouchState == TouchState.None)
		{
			m_firstTouchPoint[0] = x;
			m_firstTouchPoint[1] = y;
			m_firstTouchPoint[0] *= scaleX;
			m_firstTouchPoint[1] *= -scaleY;	
			m_firstTouchPoint[0] -= offsetX;
			m_firstTouchPoint[1] += offsetY;
			
			m_currentTouchPoint[0] = x;
			m_currentTouchPoint[1] = y;
			m_currentTouchPoint[0] *= scaleX;
			m_currentTouchPoint[1] *= -scaleY;	
			m_currentTouchPoint[0] -= offsetX;
			m_currentTouchPoint[1] += offsetY;
			
			m_currentTouchState = TouchState.Press;
			return;
		}
		else if(m_currentTouchState == TouchState.Press)
		{
			if(m_currentTouchPoint[0] != x && m_currentTouchPoint[1] != y)
			{
				m_firstTouchPoint[0] = x;
				m_firstTouchPoint[1] = y;
				m_firstTouchPoint[0] *= scaleX;
				m_firstTouchPoint[1] *= -scaleY;	
				m_firstTouchPoint[0] -= offsetX;
				m_firstTouchPoint[1] += offsetY;
				
				m_currentTouchState = TouchState.Drag;
			}
			
			m_currentTouchPoint[0] = x;
			m_currentTouchPoint[1] = y;
			m_currentTouchPoint[0] *= scaleX;
			m_currentTouchPoint[1] *= -scaleY;	
			m_currentTouchPoint[0] -= offsetX;
			m_currentTouchPoint[1] += offsetY;
			return;
		}
		else if(m_currentTouchState == TouchState.Drag)
		{
			m_currentTouchPoint[0] = x;
			m_currentTouchPoint[1] = y;
			m_currentTouchPoint[0] *= scaleX;
			m_currentTouchPoint[1] *= -scaleY;	
			m_currentTouchPoint[0] -= offsetX;
			m_currentTouchPoint[1] += offsetY;
			return;
		}
	}
	
	public void Set()
	{
		m_currentTouchState = TouchState.Press;
		m_touched = true;
	}
	public void Release()
	{
		m_currentTouchState = TouchState.Up;
		m_touched = false;
	}
	public boolean IsTouched()
	{
		return m_touched;
	}
	public float [] GetFirstTouch()
	{
		return m_firstTouchPoint;
	}
	public float [] GetFirstTouchCpy()
	{
		float [] temp =
			{
				m_firstTouchPoint[0],
				m_firstTouchPoint[1],
			};
		return temp;
	}
	public float [] GetCurrentTouch()
	{
		return m_currentTouchPoint;
	}
	public float [] GetCurrentTouchCpy()
	{
		float [] temp =
			{
				m_currentTouchPoint[0],
				m_currentTouchPoint[1],
			};
		return temp;
	}
	public String GetInfo()
	{
		String info = "STATE: ";
		
		switch(m_currentTouchState)
		{
		case None:
			info += "None \n";
			if(m_touched)
				info += "Touched \n";
			else
				info += "Released \n";
			info += "Start Position   < , > \n";
			info += "Current Position < , >";
			break;
		case Press:
			info += "Pressed \n";
			if(m_touched)
				info += "Touched \n";
			else
				info += "Released \n";
			info += "Start Position   <" + (int)m_firstTouchPoint[0] + ", " + (int)m_firstTouchPoint[1] + ">" +  "\n";
			info += "Current Position <" + (int)m_currentTouchPoint[0] + ", " + (int)m_currentTouchPoint[1] + ">" +  "\n";
			break;
		case Drag:
			info += "Dragging \n";
			if(m_touched)
				info += "Touched \n";
			else
				info += "Released \n";
			info += "Start Position   <" + (int)m_firstTouchPoint[0] + ", " + (int)m_firstTouchPoint[1] + ">" +  "\n";
			info += "Current Position <" + (int)m_currentTouchPoint[0] + ", " + (int)m_currentTouchPoint[1] + ">" +  "\n";
			break;
		case Up:
			info += "Up \n";
			if(m_touched)
				info += "Touched \n";
			else
				info += "Released \n";
			info += "Start Position   <" + (int)m_firstTouchPoint[0] + ", " + (int)m_firstTouchPoint[1] + ">" +  "\n";
			info += "Current Position <" + (int)m_currentTouchPoint[0] + ", " + (int)m_currentTouchPoint[1] + ">" +  "\n";
			break;
		}
		return info;
	}
}
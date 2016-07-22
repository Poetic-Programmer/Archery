package games.mgd.archery.game.input;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import games.mgd.archery.game.input.IEvent.TouchEvent;
import games.mgd.archery.util.Pool;

public class EvtTouch implements ITouchHandler 
{
	private int m_currentTouches = 0;
	
    boolean[] isTouched = new boolean[20];
    float[] touchX = new float[20];
    float[] touchY = new float[20];
    int[] type = new int[20];
    
    Pool<TouchEvent> touchEventPool;
    List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
    float scaleX;
    float scaleY;

    public EvtTouch(View view, float scaleX, float scaleY) 
    {
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
    	fillValues(event);
    	
		switch (event.getAction() & MotionEvent.ACTION_MASK) 
		{
		case MotionEvent.ACTION_DOWN:

			break;
			
		case MotionEvent.ACTION_POINTER_DOWN:

			break;
			
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:	
			if(m_currentTouches > 0)
				m_currentTouches--;
		
			//m_touch1Text.setText(GetText(m_point, mode));
			break;
			
		case MotionEvent.ACTION_MOVE:
			break;
		}

		return true; // indicate event was handled
    }

    private void fillValues(MotionEvent event)
    {    
        for (int i = 0; i < event.getPointerCount(); i++) {
        	touchX[i] = event.getX(i);
        	touchY[i] = event.getY(i);
        	
        }

		m_currentTouches = event.getPointerCount();
    }
    
    @Override
    public boolean IsTouchDown(int pointer) 
    {	
        synchronized (this) {
            if (pointer < 0 || pointer >= 20)
                return false;
            else
                return isTouched[pointer];
        }
    }

    @Override
    public float GetTouchX(int pointer) 
    {
        synchronized (this) {
            if (pointer < 0 || pointer >= 20)
                return 0;
            else
                return touchX[pointer];
        }
    }

    @Override
    public float GetTouchY(int pointer)
    {
        synchronized (this) 
        {
            if (pointer < 0 || pointer >= 20)
                return 0;
            else
                return touchY[pointer];
        }
    }

    @Override
    public List<TouchEvent> GetTouchEvents() 
    {
        synchronized (this)
        {
            int len = touchEvents.size();
            for (int i = 0; i < len; i++)
                touchEventPool.free(touchEvents.get(i));
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

	@Override
	public int GetCurrentTouches() {
		return m_currentTouches;
	}
}

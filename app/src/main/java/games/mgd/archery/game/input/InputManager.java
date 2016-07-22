package games.mgd.archery.game.input;

import java.util.List;

import android.content.Context;
import android.view.View;

public class InputManager implements IEvent
{
    ITouchHandler touchHandler;
    
    public InputManager(Context context, View view, int scaleX, int scaleY) 
    {             
        touchHandler = new EvtTouch(view, scaleX, scaleY);        
    }
	
	@Override
	public boolean IsTouchDown(int pointer)
	{
		return touchHandler.IsTouchDown(pointer);
	}

	@Override
	public int GetCurrentTouches()
	{
		return touchHandler.GetCurrentTouches();
	}
	@Override
	public float GetTouchX(int pointer) 
	{
		return touchHandler.GetTouchX(pointer);
	}

	@Override
	public float GetTouchY(int pointer) 
	{
		return touchHandler.GetTouchY(pointer);
	}

	@Override
	public List<TouchEvent> GetTouchEvents()
	{
		return touchHandler.GetTouchEvents();
	}
}

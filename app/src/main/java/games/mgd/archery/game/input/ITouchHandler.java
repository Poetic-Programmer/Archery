package games.mgd.archery.game.input;

import java.util.List;

import android.view.View.OnTouchListener;

import games.mgd.archery.game.input.IEvent.TouchEvent;

public interface ITouchHandler extends OnTouchListener
{
	int GetCurrentTouches();
	
    boolean IsTouchDown(int pointer);
    
    float GetTouchX(int pointer);
    
    float GetTouchY(int pointer);
    
    List<TouchEvent> GetTouchEvents();
}

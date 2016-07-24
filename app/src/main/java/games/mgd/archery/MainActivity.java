package games.mgd.archery;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    MainRenderer renderer;
    GLSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        renderer = new MainRenderer(this);
        surfaceView = new GLSurfaceView(this);

        if(hasOpenGLES2(this)){
            surfaceView.setEGLContextClientVersion(2);
            surfaceView.setRenderer(renderer);
        }
        else{
            Log.e(this.toString(), "No ES20 detected!");
        }

        setContentView(surfaceView);
    }

    @Override
    public void onPause()
    {
        synchronized(GameThread.INSTANCE.getLock()) {
            if(isFinishing())
                GameThread.INSTANCE.finish();
            else {
                GameThread.INSTANCE.pause();
            }

            while(true) {
                try {
                    // wait for renderer to close...
                    GameThread.INSTANCE.getLock().wait();
                    break;
                } catch(InterruptedException e) {

                }
            }
        }
        surfaceView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        surfaceView.onResume();
    }

    private boolean hasOpenGLES2(Context context){
        ActivityManager am =
                (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x20000);
    }
}

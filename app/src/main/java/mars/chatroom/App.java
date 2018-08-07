package mars.chatroom;

import android.app.Application;

import org.xutils.x;

/**
 * Created by hluo on 2017/4/25.
 */

public class App extends Application{

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        x.Ext.init(this);
    }

    public static App getmInstance(){
        return mInstance;
    }
}

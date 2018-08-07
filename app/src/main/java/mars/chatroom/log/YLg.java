package mars.chatroom.log;

import android.util.Log;
import android.widget.Toast;

import mars.chatroom.App;


/**
 * Created by hluo on 2017/4/10 11:43
 */

public class YLg {
    public static boolean isDebug = true;
    private static String TAG = "Yohyo";
    public static void Tst(String msg){
        if (isDebug){
            Toast.makeText(App.getmInstance(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void LogI(String msg){
        if (isDebug){
            Log.i(TAG,msg);
        }
    }
}

package mars.chatroom.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

import mars.chatroom.App;
import mars.chatroom.R;

/**
 * Created by hluo on 2017/5/2.
 */

public class TimeUtils {

    public TimeUtils() {
    }

    public static String formatData(long timeMillis) {
        if(timeMillis == 0L) {
            return "";
        } else {
            String result = null;
            int targetDay = (int)(timeMillis / 86400000L);
            int nowDay = (int)(System.currentTimeMillis() / 86400000L);
            if(targetDay == nowDay) {
                result = fromatDate(timeMillis, "HH:mm");
            } else if(targetDay + 1 == nowDay) {
                Context context = App.getmInstance();
                String formatString = context.getResources().getString(R.string.rc_yesterday_format);
                result = String.format(formatString, new Object[]{fromatDate(timeMillis, "HH:mm")});
            } else {
                result = fromatDate(timeMillis, "yyyy-MM-dd");
            }

            return result;
        }
    }

    public static String formatTime(long timeMillis) {
        if(timeMillis == 0L) {
            return "";
        } else {
            String result = null;
            int targetDay = (int)(timeMillis / 86400000L);
            int nowDay = (int)(System.currentTimeMillis() / 86400000L);
            if(targetDay == nowDay) {
                result = fromatDate(timeMillis, "HH:mm");
            } else if(targetDay + 1 == nowDay) {
                Context context = App.getmInstance();
                String formatString = context.getResources().getString(R.string.rc_yesterday_format);
                result = String.format(formatString, new Object[]{fromatDate(timeMillis, "HH:mm")});
            } else {
                result = fromatDate(timeMillis, "yyyy-MM-dd HH:mm");
            }

            return result;
        }
    }

    private static String fromatDate(long timeMillis, String fromat) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        return sdf.format(new Date(timeMillis));
    }

}

package mars.chatroom.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import org.xutils.image.AsyncDrawable;

/**
 * Created by santa on 16/8/3.
 */
public class ScaleImageView extends ImageView {
    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.FIT_START);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.d("DEBUG", "heightMeasureSpec = "+MeasureSpec.getSize(heightMeasureSpec));
//        Log.d("DEBUG", "widthMeasureSpec = "+MeasureSpec.getSize(widthMeasureSpec));
        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable){
            bitmap = ((BitmapDrawable)getDrawable()).getBitmap();
        }else if (drawable instanceof AsyncDrawable){
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();
            Bitmap.Config config =
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                            : Bitmap.Config.RGB_565;
            bitmap = Bitmap.createBitmap(w,h,config);
        }

//        Log.d("DEBUG", "h = "+bitmap.getHeight());
//        Log.d("DEBUG", "w = "+bitmap.getWidth());
        int w = this.getMeasuredWidth();
        int h = this.getMeasuredHeight();

        if (bitmap.getWidth() >= bitmap.getHeight()) {
            w = getMeasuredWidth();
            h = (bitmap.getHeight()*w/bitmap.getWidth());
        } else {
            h = getMeasuredWidth();
            w = (bitmap.getWidth()*h/bitmap.getHeight());
        }
        setMeasuredDimension(w, h);
    }
}

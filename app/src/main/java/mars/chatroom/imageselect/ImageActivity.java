package mars.chatroom.imageselect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import mars.chatroom.R;


/**
 * Created by santa on 16/8/18.
 */
public class ImageActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TextView tv_cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mViewPager = (ViewPager) findViewById(R.id.image_viewpager);
        tv_cancel = (TextView) findViewById(R.id.image_cancel);

        Intent intent = getIntent();
        List<String> images = intent.getStringArrayListExtra("images");
        int cur = intent.getIntExtra("curPosition", 0);

        mViewPager.setAdapter(new ImageAdapter(this, images));
        mViewPager.setCurrentItem(cur);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    private static class ImageAdapter extends PagerAdapter {


        private LayoutInflater inflater;
        private ImageOptions imageOptions;
        private Activity imageActivity;
        private List<String> mImages;

        ImageAdapter(Context context, List<String> images) {
            inflater = LayoutInflater.from(context);
            imageActivity = (Activity)context;
            mImages = images;
            imageOptions = new ImageOptions.Builder()
                    .setUseMemCache(true)
                    .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                    .setFailureDrawableId(R.mipmap.picture)
                    .setLoadingDrawableId(R.mipmap.picture)
                    .build();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.pager_image, view, false);
            assert imageLayout != null;
            ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);

            imageView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    imageActivity.finish();
                }
            });

            x.image().bind(imageView, mImages.get(position), imageOptions);

            view.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

    }

}

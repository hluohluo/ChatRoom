package mars.chatroom.quan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import mars.chatroom.R;
import mars.chatroom.utils.CircleImageView;

/**
 * Created by hluo on 2017/5/4.
 */

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mHeads;
    private ImageOptions imageOptions;

    public MemberAdapter(Context context, List<String> mHeads) {

        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.mHeads = mHeads;
        imageOptions = new ImageOptions.Builder()
                .setUseMemCache(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFailureDrawableId(R.mipmap.icon)
                .setLoadingDrawableId(R.mipmap.icon)
                .setCircular(true)
                .build();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.headicon,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String headurl = mHeads.get(position);
        if (!headurl.isEmpty()){
            x.image().bind(holder.mImg,headurl,imageOptions);
        }
    }

    @Override
    public int getItemCount() {
        return mHeads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mImg;
        public ViewHolder(View view)
        {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.iv_head);
        }

    }

}

package mars.chatroom.emoji;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mars.chatroom.R;
import mars.chatroom.log.YLg;

/**
 * Created by hluo on 2017/5/2.
 */

public class EmojiGridAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mEmojiNames;
    private int mEmojiType;
    private int mItemWidth;
    private OnClickEmojiListener mListener;

    public EmojiGridAdapter(Context context, List<String> emojis, int emojiType, int itemWidth, OnClickEmojiListener listener) {

        YLg.LogI("size : "+emojis.size()+"---"+emojis.get(emojis.size()-1));
        mContext = context;
        mEmojiNames = emojis;
        mEmojiType = emojiType;
        mItemWidth = itemWidth;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mEmojiNames.size();
    }

    @Override
    public Object getItem(int position) {
        return mEmojiNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmojiHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_emoji, parent, false);
            holder= new EmojiHolder();
            holder.emojiText = (TextView) convertView.findViewById(R.id.emoji_text);
            convertView.setTag(holder);
        } else {
            holder = (EmojiHolder) convertView.getTag();
        }
        YLg.LogI("position: "+position+"--- name: "+mEmojiNames.get(position));
        if (position == mEmojiNames.size()-1) {
            holder.emojiText.setText(SpanStringUtils.getEmojiDelete(mContext, mItemWidth));
            holder.emojiText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onDelete();
                    }
                }
            });
        } else {
            holder.emojiText.setText(SpanStringUtils.getEmojiContent(mEmojiType, mContext, mItemWidth, mEmojiNames.get(position)));
            holder.emojiText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onClick(((TextView) v).getText());
                    }
                }
            });
        }
        return convertView;
    }


    public void setOnClickEmoji(OnClickEmojiListener listener){
        mListener = listener;
    }


    private class EmojiHolder{
        private TextView emojiText;
    }


    public interface OnClickEmojiListener{
        void onClick(CharSequence text);
        void onDelete();
    }
}

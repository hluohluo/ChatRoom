package mars.chatroom.chat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import mars.chatroom.R;
import mars.chatroom.bean.ChatItem;
import mars.chatroom.emoji.SpanStringUtils;
import mars.chatroom.imageselect.ImageActivity;
import mars.chatroom.log.YLg;
import mars.chatroom.record.MediaManager;
import mars.chatroom.redbag.RedBagDetail;

/**
 * Created by hluo on 2017/4/26.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ChatItem> mData;
    private int minRecordWidth;
    private int maxRecordWidth;
    private LayoutInflater inflater;
    private ImageOptions imageOptions;

    public ChatAdapter(Context context, List<ChatItem> list){
        mContext = context;
        mData = list;

        inflater = LayoutInflater.from(mContext);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        minRecordWidth = (int) (displayMetrics.widthPixels * 0.15f);
        maxRecordWidth = (int) (displayMetrics.widthPixels * 0.7f);

        imageOptions = new ImageOptions.Builder()
                .setUseMemCache(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFailureDrawableId(R.mipmap.picture)
                .setLoadingDrawableId(R.mipmap.picture)
                .build();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getDirection();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ChatStroke.DIR_LEFT) {
            view = inflater.inflate(R.layout.item_chatleft, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_chatright, parent, false);
        }
        return new ItemHolder(view);
    }


    public class ItemHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private TextView text;
        private ImageView icon;
        private ImageView voice;
        private ImageView chatImg;
        private ChatStroke chatStroke,chatVoice,strokeImg,strokeRed;
        private TextView voice_time;
        private TextView red_des;
        public ItemHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.chat_time);
            text = (TextView) itemView.findViewById(R.id.chat_text);
            voice_time = (TextView) itemView.findViewById(R.id.voice_time);
            red_des = (TextView) itemView.findViewById(R.id.red_des);
            icon = (ImageView) itemView.findViewById(R.id.chat_icon);
            voice = (ImageView) itemView.findViewById(R.id.voice_pic);
            chatImg = (ImageView) itemView.findViewById(R.id.chat_image);
            chatStroke = (ChatStroke) itemView.findViewById(R.id.chat_stroke);
            chatVoice = (ChatStroke) itemView.findViewById(R.id.chat_voice);
            strokeImg = (ChatStroke) itemView.findViewById(R.id.stroke_img);
            strokeRed = (ChatStroke) itemView.findViewById(R.id.stroke_red);
        }
    }




    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ChatItem chatItem = mData.get(position);
        final ItemHolder itemHolder = ((ItemHolder) holder);

        //初始化状态
        itemHolder.chatStroke.setVisibility(View.VISIBLE);
        itemHolder.text.setVisibility(View.VISIBLE);
        itemHolder.strokeImg.setVisibility(View.GONE);
        itemHolder.strokeRed.setVisibility(View.GONE);
        itemHolder.chatVoice.setVisibility(View.GONE);
        itemHolder.voice_time.setVisibility(View.GONE);

        if (chatItem.getTime() != null && chatItem.getTime() != "") {
            itemHolder.time.setVisibility(View.VISIBLE);
            itemHolder.time.setText(chatItem.getTime());
        } else {
            itemHolder.time.setVisibility(View.GONE);
        }
        itemHolder.chatStroke.setVisibility(View.VISIBLE);
        itemHolder.strokeImg.setVisibility(View.GONE);
        if (chatItem.getChatType() == ChatStroke.CHAT_TEXT){ //纯文本
            itemHolder.text.setText(SpanStringUtils.getEmojiContent(1, mContext, (int)(itemHolder.text.getTextSize() * 15 / 12),chatItem.getText()));
        }else if(chatItem.getChatType() == ChatStroke.CHAT_VOICE){//纯语音
            itemHolder.chatStroke.setVisibility(View.GONE);
            itemHolder.chatVoice.setVisibility(View.VISIBLE);
            itemHolder.voice_time.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = ((ItemHolder) holder).chatStroke.getLayoutParams();
            params.width = (int) (minRecordWidth + (maxRecordWidth / 60f * chatItem.getVoice_time()));
            String ms = mContext.getResources().getString(R.string.voice_time);
            String msg = String.format(ms,chatItem.getVoice_time());
            ((ItemHolder) holder).voice_time.setText(msg);
            itemHolder.chatVoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    YLg.LogI(chatItem.getFilePath());
                    YLg.Tst("语音："+chatItem.getFilePath());
                    MediaManager.playSound(chatItem.getFilePath(), new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if (chatItem.getDirection() == ChatStroke.DIR_RIGHT){
                                itemHolder.voice.setImageResource(R.mipmap.v_right3);
                            }else{
                                itemHolder.voice.setImageResource(R.mipmap.v_left3);
                            }

                        }
                    });

                    if (chatItem.getDirection() == ChatStroke.DIR_RIGHT){
                        itemHolder.voice.setImageResource(R.drawable.play_rightanim);
                    }else{
                        itemHolder.voice.setImageResource(R.drawable.play_leftanim);
                    }
                    AnimationDrawable anim = (AnimationDrawable)itemHolder.voice.getDrawable();
                    anim.start();
                }
            });
        }else if(chatItem.getChatType() == ChatStroke.CHAT_REDBAG){ //发红包
            itemHolder.chatStroke.setVisibility(View.GONE);
            itemHolder.strokeImg.setVisibility(View.GONE);
            itemHolder.strokeRed.setVisibility(View.VISIBLE);
            itemHolder.red_des.setText(chatItem.getText());
        }else{//纯图片
            itemHolder.chatStroke.setVisibility(View.GONE);
            itemHolder.strokeImg.setVisibility(View.VISIBLE);
            x.image().bind(itemHolder.chatImg,chatItem.getPictureUri(),imageOptions);
            itemHolder.chatImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new ImagesCount().execute(chatItem.getPictureUri());
                }
            });
        }

        /**
         * 如果是语音则根据时长改变长度
         */


        itemHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YLg.Tst("点击了头像");
            }
        });

        itemHolder.strokeRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YLg.Tst("点击了红包");
                Intent intent = new Intent(mContext, RedBagDetail.class);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class ImagesCount extends AsyncTask<String , Void, ArrayList<String>> {
        private int cur = 0;

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String clickpic = null;
            if (null != params) {
                clickpic = params[0];
            }
            YLg.LogI("clickpic: "+clickpic);
            ArrayList<String> images = new ArrayList<>();
            for (ChatItem item : mData) {
                String uri = item.getPictureUri();
                if (uri != null) {
                    images.add(uri);
                }
            }
            YLg.LogI("imgs: "+images.size());
            for (int i=0;i<images.size();i++){
                if (clickpic.equals(images.get(i))){
                    cur = i;
                }
            }
            YLg.LogI("cur: "+cur);
            return images;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);

            Intent intent = new Intent(mContext, ImageActivity.class);
            intent.putExtra("curPosition", cur);
            intent.putStringArrayListExtra("images", strings);
            mContext.startActivity(intent);

        }
    }

}



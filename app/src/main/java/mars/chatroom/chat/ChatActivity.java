package mars.chatroom.chat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import mars.chatroom.R;
import mars.chatroom.bean.ChatItem;
import mars.chatroom.bean.RedBag;
import mars.chatroom.emoji.EmojiGridAdapter;
import mars.chatroom.emoji.EmojiUtils;
import mars.chatroom.emoji.SpanStringUtils;
import mars.chatroom.helper.CreateData;
import mars.chatroom.imageselect.ImageSelectorActivity;
import mars.chatroom.log.YLg;
import mars.chatroom.record.RecordTextView;
import mars.chatroom.redbag.RedBagActivity;

/**
 * Created by hluo on 2017/4/25.
 */

public class ChatActivity extends AppCompatActivity implements RedBagActivity.RedBagInterface{
    private Intent intent;
    private LinearLayout chat_back;
    private TextView chat_title;
    private TextView chat_num;
    private TextView chat_send;

    private List<ChatItem> mData;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private EditText editText;
    private InputMethodManager mImm;

    //plus
    private ImageView emoji;
    private ImageView chat_plus;
    private LinearLayout plus_content;
    private ImageView plus_pic;
    private ImageView plus_camera;
    private ImageView plus_redbag;

    //record
    private ImageView voice;
    private RecordTextView mRecordText;

    //emoji
    private LinearLayout emoji_menu;
    private ViewPager emoji_pager;
    private LinearLayout pagePointLayout;
    private GridView[] allPageViews;
    private RadioButton[] pointViews;
    private ArrayMap<String ,Integer> emojiMap;
    private Set<String> emojisAll;
    private ArrayList<String> emojiList;
    private int mEmojiType = EmojiUtils.EMOTION_CLASSIC_TYPE;

    private static final int ITEM_PAGE_COUNT = EmojiUtils.MAXNUM_ONEPAGER;

    //camera
    private final static int TAKE_PHOTO = 1;
    private final static int PICK_PHOTO = 2;

    private Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        intent = getIntent();

        mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        initTopBar();
        initRecyclerView();
        initPlus();
        initEmoji();
        initEdit();
        initVoice();
        initDatas();
    }

    private void initPlus() {
        chat_plus = (ImageView) findViewById(R.id.plus_iv);
        plus_content = (LinearLayout) findViewById(R.id.plus_content);
        plus_pic = (ImageView) findViewById(R.id.plus_pic);
        plus_camera = (ImageView) findViewById(R.id.plus_camera);
        plus_redbag = (ImageView) findViewById(R.id.plus_redbag);
        chat_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetVoice();
                if (plus_content.getVisibility() == View.GONE){
                    hideAll();
                    plus_content.setVisibility(View.VISIBLE);
                }else{
                    hideAll();
                    plus_content.setVisibility(View.GONE);
                    softInputShow();
                }
                setEditFocus();
            }
        });
        plus_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickPhoto(view);
            }
        });
        plus_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto(view);
            }
        });
        plus_redbag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedBagActivity.setRedBadInterface(ChatActivity.this);
                Intent intent = new Intent(ChatActivity.this, RedBagActivity.class);
//                intent.putExtra("redtype",ChatStroke.RED_TYPE);
                startActivity(intent);
            }
        });

    }

    private String getFileName() {
        return String.valueOf(System.currentTimeMillis()) + ".jpg";
    }

    //选取照片
    private void pickPhoto(View view) {
        Intent intent = new Intent(this, ImageSelectorActivity.class);
        startActivityForResult(intent, PICK_PHOTO);
    }

    private void takePhoto(View view) {
        String dirStr = Environment.getExternalStorageDirectory()+"/redhard/photo";
        File dir = new File(dirStr);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File outputImage = new File(dirStr, getFileName());
        if (outputImage.exists()) {
            outputImage.delete();
        }
        try {
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    private void initEmoji() {
        emojiMap = EmojiUtils.getEmojis(mEmojiType);
        emojisAll = emojiMap.keySet();
        emojiList = new ArrayList<>();
        for (String name : emojisAll){
            emojiList.add(name);
        }
        emoji = (ImageView) findViewById(R.id.emoji);
        emoji_menu = (LinearLayout) findViewById(R.id.emoji_menu);
        emoji_pager = (ViewPager) findViewById(R.id.emoji_face);
        pagePointLayout = (LinearLayout)findViewById(R.id.emoji_point);

        emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetVoice();
                if (emoji_menu.getVisibility() == View.GONE){
                    hideAll();
                    ((ImageView)view).setImageResource(R.mipmap.chatting_setmode_keyboard_btn_normal);
                    emoji_menu.setVisibility(View.VISIBLE);
                }else {
                    hideAll();
                    ((ImageView)view).setImageResource(R.mipmap.emoji);
                    softInputShow();
                }
                setEditFocus();
            }
        });

        int total = emojiList.size();
        int pages = total / ITEM_PAGE_COUNT
                + (total % ITEM_PAGE_COUNT == 0 ? 0 : 1);
        allPageViews = new GridView[pages];
        pointViews = new RadioButton[pages];

        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        // 获取屏幕宽度
        int screenWidth = outMetrics.widthPixels;
        // item的间距
        int spacing = (int) (outMetrics.density * 12);
        // 动态计算item的宽度和高度
        int itemWidth = (screenWidth - spacing * 8) / 8;
        //动态计算gridview的总高度
        int gvHeight = itemWidth * 3 + spacing * 6;
        int i = 0;
        for (int x = 0; x < pages; x++) {
            int start = x * ITEM_PAGE_COUNT;
            YLg.LogI("stat1 = "+ start);
            if (start>0){
                i++;
                start = start-i;
            }
            YLg.LogI("stat2 = "+ start);
            int end = (start + ITEM_PAGE_COUNT) > total ? total
                    : (start + ITEM_PAGE_COUNT);
            YLg.LogI("end1 = "+ end);
            if (end>0 && end<emojiList.size()){
                end = end-1;
            }
            YLg.LogI("end2 = "+ end);
            List<String> itemDatas =new ArrayList<String>(emojiList.subList(start,end));
            itemDatas.add(EmojiUtils.EDELETE);
            GridView view = new GridView(this);
            //设置emoji点击事件
            EmojiGridAdapter.OnClickEmojiListener listener = new EmojiGridAdapter.OnClickEmojiListener() {
                @Override
                public void onClick(CharSequence text) {
//                Toast.makeText(MainActivity.this, "emoji click", Toast.LENGTH_SHORT).show();
                    setText(mEmojiType, text);
                }

                @Override
                public void onDelete() {
                    deleteText();
                }
            };
            EmojiGridAdapter faceAdapter = new EmojiGridAdapter(this,itemDatas,EmojiUtils.EMOTION_CLASSIC_TYPE,itemWidth,listener);

            view.setNumColumns(7);
            view.setHorizontalSpacing(spacing);
            view.setVerticalSpacing(spacing);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setPadding(spacing, spacing, spacing, spacing);
            view.setBackgroundResource(android.R.color.transparent);
            view.setSelector(android.R.color.transparent);
            view.setVerticalScrollBarEnabled(false);
            view.setGravity(Gravity.CENTER);
//            view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT));
            view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,gvHeight));
            view.setAdapter(faceAdapter);

            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                }
            });
            allPageViews[x] = view;

            RadioButton tip = new RadioButton(this);
            tip.setBackgroundResource(R.drawable.selector_bg_tip);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                    8, 8);
            layoutParams.leftMargin = 10;
            pagePointLayout.addView(tip, layoutParams);
            if (x == 0) {
                tip.setChecked(true);
            }
            pointViews[x] = tip;
        }

        PagerAdapter facePagerAdapter = new FacePagerAdapter(allPageViews);
        emoji_pager.setAdapter(facePagerAdapter);
        emoji_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int index) {
                pointViews[index].setChecked(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    public void setText(int emojiType, CharSequence text) {
        // 获取当前光标位置,在指定位置上添加表情图片文本
        int curPosition = editText.getSelectionStart();
        StringBuilder sb = new StringBuilder(editText.getText().toString());
        sb.insert(curPosition, text);
        // 特殊文字处理,将表情等转换一下
        editText.setText(SpanStringUtils.getEmojiContent(emojiType,
                this, (int) editText.getTextSize(), sb.toString()));
        // 将光标设置到新增完表情的右侧
        editText.setSelection(curPosition + text.length());
    }

    public void deleteText() {
        editText.dispatchKeyEvent(new KeyEvent(
                KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
    }



    public class FacePagerAdapter extends PagerAdapter {
        private final GridView[] gridViewList;

        public FacePagerAdapter(GridView[] gridViewList) {
            this.gridViewList = gridViewList;
        }

        @Override
        public int getCount() {
            return gridViewList.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(gridViewList[arg1]);
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(gridViewList[arg1]);
            return gridViewList[arg1];
        }
    }


    private void initTopBar() {
        chat_back = (LinearLayout) this.findViewById(R.id.chat_back);
        chat_title = (TextView) this.findViewById(R.id.chat_title);
        chat_num = (TextView) this.findViewById(R.id.chat_num);
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.chat_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CreateData createData = new CreateData();
        mData = createData.getChatList();
        recyclerView.setAdapter(mAdapter = new ChatAdapter(this, mData));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    hideAll();
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        recyclerView.setOnTouchListener(getOnTouchListener());

    }

    private void initEdit() {
        editText = (EditText) findViewById(R.id.mess_et);
        chat_send = (TextView) findViewById(R.id.chat_send);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    plus_content.setVisibility(View.GONE);
                    recyclerView.smoothScrollToPosition(mData.size() - 1);
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                chat_plus.setVisibility(View.GONE);
                chat_send.setVisibility(View.VISIBLE);
            }
        });

        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  str = editText.getText().toString();
                //do something;
                if(str == null || "".equals(str)){
                    Toast.makeText(ChatActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                }else{
                    YLg.Tst("输入内容："+str);

                    ChatItem chatItem = new ChatItem();
                    chatItem.setChatType(ChatStroke.CHAT_TEXT);
                    chatItem.setDirection(ChatStroke.DIR_RIGHT);
                    chatItem.setText(str);
                    addChatItem(chatItem);

                    editText.setText("");
                    hideAll();
                }
                chat_plus.setVisibility(View.VISIBLE);
                chat_send.setVisibility(View.GONE);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YLg.Tst("点击了输入框");
                hideAll();
                editText.setVisibility(View.VISIBLE);
                softInputShow();
            }
        });
    }


    private void initVoice() {
        voice = (ImageView) this.findViewById(R.id.voice_iv);
        mRecordText = (RecordTextView) findViewById(R.id.chat_recordText);
        mRecordText.setOnFinishRecordListener(new RecordTextView.OnFinishRecordListener() {
            @Override
            public void onFinish(int time, String filePath) {

                if ( null != filePath) {
                    ChatItem chatItem = new ChatItem();
                    chatItem.setChatType(ChatStroke.CHAT_VOICE);
                    chatItem.setVoice_time(time);
                    chatItem.setImageId(R.mipmap.v_right3);
                    chatItem.setDirection(ChatStroke.DIR_RIGHT);
                    chatItem.setFilePath(filePath);
                    addChatItem(chatItem);
                }
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecordText.getVisibility() == View.GONE){
                    hideAll();
                    editText.setVisibility(View.GONE);
                    ((ImageView)view).setImageResource(R.mipmap.chatting_setmode_keyboard_btn_normal);
                    mRecordText.setVisibility(View.VISIBLE);
                }else{
                    hideAll();
                    mRecordText.setVisibility(View.GONE);
                    ((ImageView)view).setImageResource(R.mipmap.voice_btn_normal);
                    editText.setVisibility(View.VISIBLE);
                    softInputShow();
                }
            }
        });

    }

    private void initDatas() {
        String title = intent.getStringExtra("title");
        YLg.Tst("title: "+title);

        String ms = getResources().getString(R.string.messge);
        int num = (int)(Math.random()*5+1);
        String msg = String.format(ms,num);
        chat_num.setText(msg);
        chat_title.setText(title);
        chat_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void addChatItem(ChatItem chatItem) {
        mData.add(chatItem);
        mAdapter.notifyItemInserted(mData.size() - 1);
        recyclerView.smoothScrollToPosition(mData.size() - 1);
    }


    private void hideAll() {
        plus_content.setVisibility(View.GONE);
        emoji_menu.setVisibility(View.GONE);
        softInputHide();
    }

    private void resetVoice(){
        editText.setVisibility(View.VISIBLE);
        mRecordText.setVisibility(View.GONE);
        voice.setImageResource(R.mipmap.voice_btn_normal);
    }

    private void setEditFocus() {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }

    /**
     * 若软键盘或表情键盘弹起，点击上端空白处应该隐藏输入法键盘
     *
     * @return 会隐藏输入法键盘的触摸事件监听器
     */
    private View.OnTouchListener getOnTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                emoji.setImageResource(R.mipmap.emoji);
                hideAll();
                return false;
            }
        };
    }



    private void softInputShow() {
        setEditFocus();
        mImm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    private void softInputHide() {
        mImm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("DEBUG", "imageUri = "+imageUri);

        switch (requestCode) {
            case TAKE_PHOTO:
                if (RESULT_OK == resultCode) {
                    addPictureItem(imageUri.toString());
                }
                break;
            case PICK_PHOTO:
                if (RESULT_OK == resultCode) {
                    List<String> images = data.getStringArrayListExtra("images");
                    for (String uri : images) {
                        addPictureItem(uri);
                    }
                }
                break;
            default:
                Log.e("DEBUG", "requestCode = "+requestCode);
                break;
        }
        plus_content.setVisibility(View.GONE);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addPictureItem(String imageUri) {
        ChatItem chatItem = new ChatItem();
        chatItem.setChatType(ChatStroke.CHAT_PICTURE);
        chatItem.setDirection(ChatStroke.DIR_RIGHT);
        chatItem.setPictureUri(imageUri);
        addChatItem(chatItem);
    }

    @Override
    public void sendRedBag(RedBag redBag) {
        ChatItem chatItem = new ChatItem();
        chatItem.setChatType(ChatStroke.CHAT_REDBAG);
        chatItem.setDirection(ChatStroke.DIR_RIGHT);
        chatItem.setText(redBag.getDes());
        YLg.Tst("你发了一个"+redBag.getPrice()+"元红包");
        addChatItem(chatItem);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onDestroy() {
        hideAll();
        super.onDestroy();
    }
}

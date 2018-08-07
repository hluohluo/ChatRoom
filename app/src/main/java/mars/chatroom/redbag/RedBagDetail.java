package mars.chatroom.redbag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import mars.chatroom.R;
import mars.chatroom.chat.ChatStroke;

/**
 * Created by hluo on 2017/5/5.
 */

public class RedBagDetail extends AppCompatActivity{

    private LinearLayout back;
    private LinearLayout red_je_layout;
    private TextView title;
    private TextView red_own;
    private TextView red_des;
    private TextView red_je;
    private TextView red_info;
    private ScrollView scrollView;
    private ListView redListView;

    private Intent intent;
    private int red_type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddetail);
        intent = getIntent();
        if (null != intent){
            red_type = intent.getIntExtra("redtype",0);
        }
        initViews();
    }

    private void initViews() {
        back = (LinearLayout) findViewById(R.id.back);
        red_je_layout = (LinearLayout) findViewById(R.id.red_je_layout);
        title = (TextView) findViewById(R.id.title);
        red_own = (TextView) findViewById(R.id.red_own);
        red_des = (TextView) findViewById(R.id.red_des);
        red_je = (TextView) findViewById(R.id.red_je);
        red_info = (TextView) findViewById(R.id.red_info);
        scrollView = (ScrollView) findViewById(R.id.dampview);
        redListView = (ListView) findViewById(R.id.redListView);
        if (red_type == ChatStroke.RED_TYPE){
            red_je_layout.setVisibility(View.VISIBLE);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

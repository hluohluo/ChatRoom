package mars.chatroom.quan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mars.chatroom.R;
import mars.chatroom.chat.ChatActivity;
import mars.chatroom.log.YLg;
import mars.chatroom.utils.DampView;

/**
 * Created by hluo on 2017/5/4.
 */

public class QuanDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout back;
    private TextView title;
    private ImageView imageview;
    private DampView damp ;
    private RelativeLayout quan_member_layout;
    private RecyclerView quan_member;

    private RelativeLayout quan_master_layout;
    private LinearLayout quan_add;
    private TextView quan_level;
    private TextView quan_des;
    private TextView quan_create_time;
    private TextView quan_master_name;

    private List<String> mHeads = new ArrayList<>();

    private MemberAdapter memberAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quandetail);
        initViews();
    }

    private void createIcon(){
        mHeads.add("http://touxiang.qqzhi.com/uploads/2012-11/1111013310118.jpg");
        mHeads.add("http://touxiang.qqzhi.com/uploads/2012-11/1111011002403.jpg");
        mHeads.add("http://touxiang.qqzhi.com/uploads/2012-11/1111020022653.jpg");
        mHeads.add("http://touxiang.qqzhi.com/uploads/2012-11/1111010753443.jpg");
        mHeads.add("http://touxiang.qqzhi.com/uploads/2012-11/1111024004604.jpg");
    }

    private void initViews() {
        back = (LinearLayout) findViewById(R.id.back);
        title = (TextView)findViewById(R.id.title);
        quan_level = (TextView)findViewById(R.id.quan_level);
        quan_des = (TextView)findViewById(R.id.quan_des);
        quan_create_time = (TextView)findViewById(R.id.quan_create_time);
        quan_master_name = (TextView)findViewById(R.id.quan_master_name);
        imageview = (ImageView) findViewById(R.id.imageview);
        damp = (DampView) findViewById(R.id.dampview);
        quan_member_layout = (RelativeLayout) findViewById(R.id.quan_member_layout);
        quan_master_layout = (RelativeLayout) findViewById(R.id.quan_master_layout);
        quan_member = (RecyclerView) findViewById(R.id.quan_member);
        quan_add = (LinearLayout)findViewById(R.id.quan_add);


        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        quan_member.setLayoutManager(linearLayoutManager);
        createIcon();
        quan_member.setAdapter(new MemberAdapter(this,mHeads));

        damp.setImageView(imageview);
        back.setOnClickListener(this);
        quan_member_layout.setOnClickListener(this);
        quan_master_layout.setOnClickListener(this);
        quan_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.back:
                finish();
                break;
            case R.id.quan_member_layout:
                YLg.Tst("进入圈子成员界面");
                break;
            case R.id.quan_master_layout:
                YLg.Tst("进入圈主详情页面");
                break;
            case R.id.quan_add:
                YLg.Tst("进入圈子聊天室");
                Intent intent = new Intent(this,ChatActivity.class);
                intent.putExtra("title","男神女生");
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }
}

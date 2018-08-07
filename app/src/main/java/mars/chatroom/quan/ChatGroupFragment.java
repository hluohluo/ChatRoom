package mars.chatroom.quan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import mars.chatroom.R;
import mars.chatroom.bean.Group;
import mars.chatroom.chat.ChatActivity;
import mars.chatroom.helper.CreateData;
import mars.chatroom.log.YLg;
import mars.chatroom.utils.CommonAdapter;
import mars.chatroom.utils.ViewHolder;

/**
 * Created by hluo on 2017/4/25.
 */

public class ChatGroupFragment extends Fragment{
    private Context context;
    private ListView listView;
    private List<Group> groupList;
    private CommonAdapter<Group> mAdapter;

    public ChatGroupFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group,container,false);
        initViews(view);
        initDatas();
        YLg.Tst("显示圈子");
        return view;
    }
    private void initViews(View view) {
        listView = (ListView) view.findViewById(R.id.list_group);

    }
    private void initDatas() {
        CreateData createData = new CreateData();
        groupList = createData.getGList();
        YLg.Tst("groupList = "+groupList.size());
        mAdapter = new CommonAdapter<Group>(context,groupList,R.layout.item_group) {
            @Override
            public void convert(ViewHolder viewHolder, Group item) {
                TextView tvTitle = viewHolder.getView(R.id.gTitle);
                TextView tvPerson = viewHolder.getView(R.id.gPerson);
                TextView tvDes = viewHolder.getView(R.id.gDes);

                tvTitle.setText(item.getgName());
                tvPerson.setText(item.getgPerson()+"");
                tvDes.setText(item.getgDes());
            }
        };
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posion, long id) {
                    YLg.Tst("你点击了第"+posion+"行--"+groupList.get(posion).getgName());
//                Intent intent = new Intent(context,ChatActivity.class);
                Intent intent = new Intent(context,QuanDetailActivity.class);
                intent.putExtra("title",groupList.get(posion).getgName());
                startActivity(intent);

            }
        });

    }


}

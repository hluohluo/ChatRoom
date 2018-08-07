package mars.chatroom.helper;

import java.util.ArrayList;
import java.util.List;

import mars.chatroom.R;
import mars.chatroom.bean.ChatItem;
import mars.chatroom.bean.Group;
import mars.chatroom.chat.ChatStroke;

/**
 * Created by hluo on 2017/4/25.
 */

public class CreateData {
    private List<Group> groupList;
    private List<ChatItem> chatList;
    public CreateData(){}
    public List<Group> getGList(){
        groupList = new ArrayList<>();
        Group group1 = new Group("","单身男女交友",543,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group2 = new Group("","今夜不说NO",254,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group3 = new Group("","90后情感交流群",384,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group4 = new Group("","想找妹子的进来呃",281,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group5 = new Group("","单身男女交友",543,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group6 = new Group("","单身男女交友",543,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group7 = new Group("","单身男女交友",543,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group8 = new Group("","单身男女交友",543,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group9 = new Group("","单身男女交友",543,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group10 = new Group("","单身男女交友",543,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        Group group11 = new Group("","单身男女交友",543,"别在该奋斗的年纪选择安逸，你那么好那么好那么好");
        groupList.add(group1);
        groupList.add(group2);
        groupList.add(group3);
        groupList.add(group4);
        groupList.add(group5);
        groupList.add(group6);
        groupList.add(group7);
        groupList.add(group8);
        groupList.add(group9);
        groupList.add(group10);
        groupList.add(group11);
        return groupList;
    }

    public List<ChatItem> getChatList(){
        chatList = new ArrayList<>();
        ChatItem chatItem = new ChatItem();
        chatItem.setText("我们已经是好友啦，一起来聊天吧！[e1f608]");
        chatItem.setTime("05:24");
        chatList.add(chatItem);
        ChatItem chatItem2 = new ChatItem();
        chatItem2.setText("两个还没长大的奶油小生，就吸引下小女生罢了，敢和真正的型男乐福比，要奶油味，没长胡子之前的乐福更帅，乐福能驾驭多种风格，每种风格都帅，这才是NBA第一帅哥！");
        chatItem2.setTime("11:24");
        chatList.add(chatItem2);
        ChatItem chatItem3 = new ChatItem();
        chatItem3.setChatType(ChatStroke.CHAT_VOICE);
        chatItem3.setVoice_time(3);
        chatItem3.setImageId(R.mipmap.v_left3);
        chatItem3.setDirection(ChatStroke.DIR_LEFT);
        chatItem3.setFilePath("/storage/emulated/0/redhard/record/1493283630368.amr");
        chatList.add(chatItem3);

        ChatItem chatItem4 = new ChatItem();
        chatItem4.setChatType(ChatStroke.CHAT_REDBAG);
        chatItem4.setDirection(ChatStroke.DIR_LEFT);
        chatItem4.setText("长长久久，久久长长");
        chatList.add(chatItem4);
        return chatList;
    }
}

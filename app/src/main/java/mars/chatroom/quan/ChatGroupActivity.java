package mars.chatroom.quan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mars.chatroom.R;

/**
 * Created by hluo on 2017/4/25.
 */

public class ChatGroupActivity extends AppCompatActivity{
    private ChatGroupFragment chatGroupFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        chatGroupFragment = new ChatGroupFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, chatGroupFragment, "ChatGroupFragment")
                .commit();
    }
}

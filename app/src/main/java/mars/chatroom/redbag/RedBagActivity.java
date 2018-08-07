package mars.chatroom.redbag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import mars.chatroom.R;
import mars.chatroom.bean.RedBag;
import mars.chatroom.chat.ChatStroke;
import mars.chatroom.log.YLg;


/**
 * Created by hluo on 2017/5/3.
 */

public class RedBagActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView red_tips;
    private TextView red_cancel;
    private TextView red_jine;
    private TextView red_yuan;
    private TextView red_dis;
    private TextView red_send;
    private EditText red_edit;
    private EditText red_des;
    private ImageView red_gray;
    private RelativeLayout red_etlayout;
    private RelativeLayout red_layout;
    private InputMethodManager mImm;
    private double price;
    private int redNum;
    public static RedBagInterface redBagInterface;


    //群红包显示
    private int red_type;
    private RelativeLayout red_numlayout;
    private TextView red_geshu;
    private TextView red_type_des;
    private TextView red_num_yuan;
    private TextView red_qun_person;
    private EditText red_num_edit;

    private Intent intent;

    public static void setRedBadInterface(RedBagInterface redInterface){
        redBagInterface = redInterface;
    }

    public interface RedBagInterface{
        void sendRedBag(RedBag redBag);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redbag);

        intent = getIntent();
        if (null != intent){
            red_type = intent.getIntExtra("redtype",0);
        }

        mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViews();
        initQunViews();

    }



    private void initViews() {
        red_tips = (TextView) findViewById(R.id.red_tips);
        red_cancel = (TextView) findViewById(R.id.red_cancel);
        red_jine = (TextView) findViewById(R.id.red_jine);
        red_yuan = (TextView) findViewById(R.id.red_yuan);
        red_dis = (TextView) findViewById(R.id.red_dis);
        red_send = (TextView) findViewById(R.id.red_send);
        red_edit = (EditText) findViewById(R.id.red_edit);
        red_des = (EditText) findViewById(R.id.red_des);
        red_gray = (ImageView) findViewById(R.id.red_gray);
        red_etlayout = (RelativeLayout) findViewById(R.id.red_etlayout);
        red_layout = (RelativeLayout) findViewById(R.id.red_layout);
        red_layout.setOnTouchListener(getOnTouchListener());
        red_cancel.setOnClickListener(this);
        red_send.setOnClickListener(this);
        red_gray.setOnClickListener(this);
        red_etlayout.setOnClickListener(this);

        red_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().isEmpty() || s.toString().equals("0") || s.toString().equals("")){
                    red_gray.setVisibility(View.VISIBLE);
                    red_dis.setText("0.00");
                }else{
                    if (s.toString().contains("."))
                    {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,s.toString().indexOf(".") + 3);
                            red_edit.setText(s);
                            red_edit.setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        red_edit.setText(s);
                        red_edit.setSelection(2);
                    }
                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            red_edit.setText(s.subSequence(0, 1));
                            red_edit.setSelection(1);
                        }
                    }
                    String result = red_edit.getText().toString();
                    if (result.equals("0")){
                        result = "0.00";
                    }else if(result.endsWith(".")){
                        result = result+"00";
                    }else{
                        int posDot  = result.indexOf(".");
                        if (posDot>0){
                            int mlength = result.substring(posDot+1,result.length()).length();
                            if (mlength == 1){
                                result = result+"0";
                            }
                        }else{
                            result = result+".00";
                        }

                    }

                    price = Double.parseDouble(result);
                    YLg.LogI("price : "+price);
                    if (price>20000){
                        red_tips.setVisibility(View.VISIBLE);
                        red_jine.setTextColor(getResources().getColor(R.color.redbag_tips_text));
                        red_edit.setTextColor(getResources().getColor(R.color.redbag_tips_text));
                        red_yuan.setTextColor(getResources().getColor(R.color.redbag_tips_text));
                    }else{
                        red_tips.setVisibility(View.GONE);
                        red_jine.setTextColor(getResources().getColor(R.color.black));
                        red_edit.setTextColor(getResources().getColor(R.color.black));
                        red_yuan.setTextColor(getResources().getColor(R.color.black));
                    }
                    red_dis.setText(result);
                    if (red_type == ChatStroke.RED_TYPE){
                        if (redNum == 0){
                            return;
                        }
                    }
                    red_gray.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initQunViews() {
        red_numlayout = (RelativeLayout) findViewById(R.id.red_numlayout);
        red_geshu = (TextView) findViewById(R.id.red_geshu);
        red_type_des = (TextView) findViewById(R.id.red_type_des);
        red_num_yuan = (TextView) findViewById(R.id.red_num_yuan);
        red_qun_person = (TextView) findViewById(R.id.red_qun_person);
        red_num_edit = (EditText) findViewById(R.id.red_num_edit);
        red_numlayout.setOnClickListener(this);
        if (red_type == ChatStroke.RED_TYPE){
            red_numlayout.setVisibility(View.VISIBLE);
            red_type_des.setVisibility(View.VISIBLE);
            red_qun_person.setVisibility(View.VISIBLE);
        }
        red_num_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().isEmpty() || s.toString().equals("0") || s.toString().equals("")){
                    red_gray.setVisibility(View.VISIBLE);
                }else{
                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            red_num_edit.setText(s.subSequence(0, 1));
                            red_num_edit.setSelection(1);
                        }
                    }else{
                        red_num_edit.setSelection(s.length());
                        redNum = Integer.parseInt(s.toString());
                        if (redNum>100){
                            red_tips.setText("一次最多发100个红包");
                            red_tips.setVisibility(View.VISIBLE);
                            red_geshu.setTextColor(getResources().getColor(R.color.redbag_tips_text));
                            red_num_yuan.setTextColor(getResources().getColor(R.color.redbag_tips_text));
                            red_num_edit.setTextColor(getResources().getColor(R.color.redbag_tips_text));
                        }else{
                            red_tips.setVisibility(View.GONE);
                            red_geshu.setTextColor(getResources().getColor(R.color.black));
                            red_num_yuan.setTextColor(getResources().getColor(R.color.black));
                            red_num_edit.setTextColor(getResources().getColor(R.color.black));
                        }
                    }
                    if (red_type == ChatStroke.RED_TYPE){
                        if (price <= 0){
                            return;
                        }
                    }
                    red_gray.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        switch (vId){
            case R.id.red_cancel:
                finish();
                break;
            case R.id.red_etlayout:
                setEditFocus();
                softInputShow(red_edit);
                break;
            case R.id.red_numlayout:
                setNumEditFocus();
                softInputShow(red_num_edit);
                break;
            case R.id.red_send:
                if (price>0){
                    String des = red_des.getText().toString();
                    if (des.isEmpty()){
                        des = "恭喜发现，大吉大利";
                    }
                    RedBag redBag = new RedBag(price,des);
                    if (red_type == ChatStroke.RED_TYPE){
                        if (redNum==0){
                            Toast.makeText(this,"请选择红包个数",Toast.LENGTH_LONG).show();
                            return;
                        }
                        redBag.setNum(redNum);
                    }
                    redBagInterface.sendRedBag(redBag);
                    softInputHide();
                    finish();
                }
                break;
            default:
                break;
        }

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
                softInputHide();
                return false;
            }
        };
    }

    private void setEditFocus() {
        red_edit.setFocusable(true);
        red_edit.setFocusableInTouchMode(true);
        red_edit.requestFocus();
    }

    private void setNumEditFocus() {
        red_num_edit.setFocusable(true);
        red_num_edit.setFocusableInTouchMode(true);
        red_num_edit.requestFocus();
    }

    private void softInputShow(EditText edt) {
        mImm.showSoftInput(edt, InputMethodManager.SHOW_FORCED);
    }

    private void softInputHide() {
        if (red_edit.isFocused()){
            mImm.hideSoftInputFromWindow(red_edit.getWindowToken(), 0);
        }
        if (red_num_edit.isFocused()){
            mImm.hideSoftInputFromWindow(red_num_edit.getWindowToken(), 0);
        }
        if (red_des.isFocused()){
            mImm.hideSoftInputFromWindow(red_des.getWindowToken(), 0);
        }

    }

    @Override
    protected void onDestroy() {
        softInputHide();
        super.onDestroy();
    }
}

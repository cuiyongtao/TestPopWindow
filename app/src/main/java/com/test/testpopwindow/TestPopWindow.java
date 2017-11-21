package com.test.testpopwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author： Victory
 * @time： 20171117
 * @email： cuiyongtao520@163.com
 * @QQ： 949021037
 * @annotation：
 */


public class TestPopWindow extends PopupWindow implements View.OnClickListener {

    EditText ed1;
    EditText ed2;
    EditText ed3;
    EditText ed4;
    EditText ed5;
    EditText ed6;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;
    TextView txt6;
    TextView txt7;
    TextView txt8;
    TextView txt9;
    TextView txtBack;
    TextView txt0;
    TextView txtFinish;
    TextView fotPassword;
    /**
     * 承继布局
     */
    private View mPopWindow;
    private Context mContext;

    /**
     * 用于存储Edittext
     */
    private EditText[] password;

    /*
     *密码
     */
    private String strPassword;
    /**
     * 用于记录当前输入密码格位置
     */
    private int currentIndex = -1;

    public TestPopWindow(Context context) {
        this.mContext = context;
        init(context);
        setmPopWindow();
    }

    /**
     * 初始化
     *
     * @param context
     */

    public void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPopWindow = inflater.inflate(R.layout.popwindow_password, null);
        ed1 = mPopWindow.findViewById(R.id.ed1);
        ed2 = mPopWindow.findViewById(R.id.ed2);
        ed3 = mPopWindow.findViewById(R.id.ed3);
        ed4 = mPopWindow.findViewById(R.id.ed4);
        ed5 = mPopWindow.findViewById(R.id.ed5);
        ed6 = mPopWindow.findViewById(R.id.ed6);
        password = new EditText[6];
        password[0] = ed1;
        password[1] = ed2;
        password[2] = ed3;
        password[3] = ed4;
        password[4] = ed5;
        password[5] = ed6;

        txt0 = mPopWindow.findViewById(R.id.txt0);
        txt1 = mPopWindow.findViewById(R.id.txt1);
        txt2 = mPopWindow.findViewById(R.id.txt2);
        txt3 = mPopWindow.findViewById(R.id.txt3);
        txt4 = mPopWindow.findViewById(R.id.txt4);
        txt5 = mPopWindow.findViewById(R.id.txt5);
        txt6 = mPopWindow.findViewById(R.id.txt6);
        txt7 = mPopWindow.findViewById(R.id.txt7);
        txt8 = mPopWindow.findViewById(R.id.txt8);
        txt9 = mPopWindow.findViewById(R.id.txt9);
        txtBack = mPopWindow.findViewById(R.id.txtBack);
        txtBack.setText("<<-");
        txtFinish = mPopWindow.findViewById(R.id.txtFinish);
        fotPassword = mPopWindow.findViewById(R.id.fotPassword);

        txt0.setOnClickListener(this);
        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);
        txt3.setOnClickListener(this);
        txt4.setOnClickListener(this);
        txt5.setOnClickListener(this);
        txt6.setOnClickListener(this);
        txt7.setOnClickListener(this);
        txt8.setOnClickListener(this);
        txt9.setOnClickListener(this);
        txtBack.setOnClickListener(this);
        txtFinish.setOnClickListener(this);
        fotPassword.setOnClickListener(this);
    }

    public void setmPopWindow() {
        // 把View添加到PopWindow中
        this.setContentView(mPopWindow);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //  设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //  设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        //   设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fotPassword:
                Toast.makeText(mContext, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt1:
                getJudge(1);
                break;
            case R.id.txt2:
                getJudge(2);
                break;
            case R.id.txt3:
                getJudge(3);
                break;
            case R.id.txt4:
                getJudge(4);
                break;
            case R.id.txt5:
                getJudge(5);
                break;
            case R.id.txt6:
                getJudge(6);
                break;
            case R.id.txt7:
                getJudge(7);
                break;
            case R.id.txt8:
                getJudge(8);
                break;
            case R.id.txt9:
                getJudge(9);
                break;
            case R.id.txtBack:
               //增加判断，看是否清除完毕，防止数组越界
                if (currentIndex - 1 >= -1) {
                    password[currentIndex--].setText("");
                }
                break;
            case R.id.txt0:
                getJudge(1);
                break;
            case R.id.txtFinish:
                Toast.makeText(mContext, getStrPassword(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /***
     * 增加判断，看是否输入够6位，防止数组越界
     * @param num
     */
    private void getJudge(int num) {
        if (currentIndex >= -1 && currentIndex < 5) {
            password[++currentIndex].setText(num + "");
        }
    }

    /**
     * 设置监听方法，在第6位输入完成后触发
     */
    public void setOnFinishInput(final OnPasswordInputFinish passwordInputFinish) {
        password[5].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    //每次触发都要先将strPassword置空，再重新获取，避免由于输入删除再输入造成混乱
                    strPassword = "";
                    for (int i = 0; i < 6; i++) {
                        strPassword += password[i].getText().toString().trim();
                    }
                }
                passwordInputFinish.inputFinish();
            }
        });
    }

    //获取密码
    public String getStrPassword() {
        return strPassword;
    }

    public interface OnPasswordInputFinish {
        void inputFinish();
    }
}

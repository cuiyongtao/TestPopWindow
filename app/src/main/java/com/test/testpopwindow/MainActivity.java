package com.test.testpopwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView mtest;
    private TestPopWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtest = (TextView) findViewById(R.id.test);

        mtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow = new TestPopWindow(MainActivity.this);
                mPopupWindow.showAtLocation(MainActivity.this.findViewById(R.id.testsss), Gravity.BOTTOM | Gravity
                        .CENTER_HORIZONTAL, 0, 0);
                darkenBackground(0.8f);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        darkenBackground(1f);
                    }
                });
                //添加密码输入完成的响应
                mPopupWindow.setOnFinishInput(new TestPopWindow.OnPasswordInputFinish() {
                    @Override
                    public void inputFinish() {
                        //输入完成后我们简单显示一下输入的密码
                        //也就是说——>实现你的交易逻辑什么的在这里写
                        Toast.makeText(MainActivity.this, mPopupWindow.getStrPassword(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    /**
     * 改变背景颜色,灰色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}

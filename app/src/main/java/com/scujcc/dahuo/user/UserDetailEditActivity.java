package com.scujcc.dahuo.user;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scujcc.dahuo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class UserDetailEditActivity extends Activity {


    @BindView(R.id.change_cancel)
    Button mChangeCancel;
    @BindView(R.id.change_done)
    Button mChangeDone;
    @BindView(R.id.user_photo)
    ShapedImageView mUserPhoto;
    @BindView(R.id.change_photo)
    TextView mChangePhoto;
    @BindView(R.id.change_name)
    TextView mChangeName;
    @BindView(R.id.change_sex)
    Button mChangeSex;
    @BindView(R.id.change_age)
    Button mChangeAge;
    @BindView(R.id.change_job)
    Button mChangeJob;
    @BindView(R.id.change_company)
    EditText mChangeCompany;
    @BindView(R.id.change_sign)
    EditText mChangeSign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail_edit);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.change_cancel, R.id.change_done, R.id.change_sex, R.id.change_age, R.id.change_job, R.id.change_company, R.id.change_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_cancel:
                finish();
                break;
            case R.id.change_done:
                finish();
                break;
            case R.id.change_sex:
                break;
            case R.id.change_age:
                break;
            case R.id.change_job:
                break;
            case R.id.change_company:
                break;
            case R.id.change_sign:
                break;
        }
    }
}

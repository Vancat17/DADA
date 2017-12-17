package com.scujcc.dahuo.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.scujcc.dahuo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by  范朝波 on 2017/12/16.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class UserDetailActivity extends Activity {

    @BindView(R.id.back_button)
    ImageButton mBackButton;
    @BindView(R.id.edit_button)
    Button mEditButton;
    @BindView(R.id.rv_user_detail)
    RecyclerView mRvUserDetail;

    private List<FunctionItem> mFunctionItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail_activity);
        ButterKnife.bind(this);

        initializeData();
        mRvUserDetail.setHasFixedSize(true);
        mRvUserDetail.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRvUserDetail.setAdapter(new Adapter(mFunctionItems));

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailActivity.this, UserDetailEditActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initializeData() {
        mFunctionItems = new ArrayList<>();
        mFunctionItems.add(new FunctionItem("普通会员", "进入会员主页", R.drawable.ic_vip));
        mFunctionItems.add(new FunctionItem("实名认证", "未认证", R.drawable.ic_verified));
        mFunctionItems.add(new FunctionItem("芝麻信用认证", "未认证", R.drawable.ic_sesame));
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.IndexHolder> {

        private final static int HEADER_TYPE = 1;
        private final static int DEFAULT_TYPE = 2;

        private List<FunctionItem> mItems;

        public Adapter(List<FunctionItem> functionItems) {
            this.mItems = functionItems;
        }

        public class IndexHolder extends RecyclerView.ViewHolder {
            public IndexHolder(View itemView) {
                super(itemView);
            }
        }

        public class HeaderHolder extends IndexHolder {

            public HeaderHolder(View itemView) {
                super(itemView);

            }
        }

        public class FunctionHolder extends IndexHolder {

            private ImageView mCvImage;
            private TextView mCvTitle;
            private TextView mCvSubtitle;

            public FunctionHolder(View itemView) {
                super(itemView);
                mCvImage = itemView.findViewById(R.id.cv_image);
                mCvTitle = itemView.findViewById(R.id.cv_title);
                mCvSubtitle = itemView.findViewById(R.id.cv_subtitle);
            }
        }

        @Override
        public IndexHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            IndexHolder holder;
            View view;
            switch (viewType) {
                case HEADER_TYPE:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_detail_header, parent, false);
                    holder = new HeaderHolder(view);
                    break;
                default:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_detail_function, parent, false);
                    holder = new FunctionHolder(view);
                    break;
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(IndexHolder holder, int position) {
            switch (getItemViewType(position)) {
                case HEADER_TYPE:
                    break;
                default:
                    FunctionHolder functionHolder = (FunctionHolder) holder;

                    int pos = (position - 1) > 0 ? (position - 1) : 0;
                    functionHolder.mCvTitle.setText(mFunctionItems.get(pos).getTitle());
                    functionHolder.mCvSubtitle.setText(mFunctionItems.get(pos).getSubTitle());
                    functionHolder.mCvImage.setImageResource(mFunctionItems.get(pos).getImageId());
            }
        }

        @Override
        public int getItemCount() {
            return mFunctionItems.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            switch (position) {
                case 0:
                    return HEADER_TYPE;
                default:
                    return DEFAULT_TYPE;
            }
        }
    }
}

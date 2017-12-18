package com.scujcc.dahuo.content;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scujcc.dahuo.R;
import com.scujcc.dahuo.TestActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.gavinliu.android.lib.shapedimageview.ShapedImageView;

/**
 * Created by  范朝波 on 2017/12/17.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class ContentMainAdapter extends RecyclerView.Adapter<ContentMainAdapter.IndexHolder> {


    private final static int BANNER_TYPE = 1;
    private final static int NULL_TYPE = 2;
    private final static int DEFAULT_TYPE = 3;
    private final static int ITEM_EXTRA = 0;

    public class IndexHolder extends RecyclerView.ViewHolder {

        public IndexHolder(View itemView) {
            super(itemView);
        }



    }

    public class BannerHolder extends IndexHolder {

        public BannerHolder(View itemView) {

            super(itemView);
        }
    }

    public class ContentHolder extends IndexHolder implements View.OnClickListener{

        @BindView(R.id.user_photo)
        ShapedImageView mUserPhoto;
        @BindView(R.id.user_name)
        TextView mUserName;
        @BindView(R.id.user_vip)
        ImageView mUserVip;
        @BindView(R.id.start_time)
        TextView mStartTime;
        @BindView(R.id.content_topic)
        TextView mContentTopic;
        @BindView(R.id.content_text)
        TextView mContentText;
        @BindView(R.id.content_image)
        ImageView mContentImage;
        @BindView(R.id.content_tolNum)
        TextView mContentTolNum;

        public ContentHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), TestActivity.class);
            v.getContext().startActivity(intent);


        }
    }

    private List<ContentItem> mContentItems;

    public ContentMainAdapter(List<ContentItem> contentItems) {
        mContentItems = contentItems;
    }

    @Override
    public IndexHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        IndexHolder holder;
        View view;
                switch (viewType) {

                    case BANNER_TYPE:
                        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main_card, parent, false);
                        holder = new ContentHolder(view);
                        break;
                    case NULL_TYPE:
                        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main_card, parent, false);
                        holder = new ContentHolder(view);
                        break;
                    default:
                        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main_card, parent, false);
                        holder = new ContentHolder(view);
                        break;
                }
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(IndexHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER_TYPE:
                break;
            case NULL_TYPE:
                break;
            default:
                ContentHolder contentHolder = (ContentHolder) holder;
                int pos = (position - ITEM_EXTRA) > 0 ? (position - ITEM_EXTRA) : 0;
                contentHolder.mUserPhoto.setImageResource(mContentItems.get(pos).getPhotoId());
                contentHolder.mUserName.setText(mContentItems.get(pos).getName());
                //          p7      contentHolder.mUserVip.
                contentHolder.mStartTime.setText(mContentItems.get(pos).getStartTime());
                contentHolder.mContentTopic.setText(mContentItems.get(pos).getTopic());
                contentHolder.mContentText.setText(mContentItems.get(pos).getContent());
                //                contentHolder.mContentImage.setImageResource(mContentItems.get(pos).get);
                contentHolder.mContentTolNum.setText(mContentItems.get(pos).getNowNum() + "/" + mContentItems.get(position).getNowNum());
        }
    }

    @Override
    public int getItemCount() {
        return mContentItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        //        switch (position) {
        //            case 0:
        //                return BANNER_TYPE;
        //            case 1:
        //                return NULL_TYPE;
        //            default:
        //                return DEFAULT_TYPE;
        //        }

        return DEFAULT_TYPE;
    }
}

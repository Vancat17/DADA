package com.scujcc.dahuo.content;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scujcc.dahuo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by  范朝波 on 2017/12/15.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class PageFragment extends Fragment {

    private static final String ARG_CONTENT_PAGE = "ARG_CONTENT_PAGE";

    @BindView(R.id.content_main_recyle)
    RecyclerView mContentMainRecyle;
    Unbinder unbinder;

    private int mPage;


    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);

        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_CONTENT_PAGE);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main_recycle, container, false);
        unbinder = ButterKnife.bind(this, view);

        addData();
        mContentMainRecyle.setHasFixedSize(true);
        mContentMainRecyle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mContentMainRecyle.setAdapter(new ContentMainAdapter(mContentItems));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    List<ContentItem> mContentItems;

    private void addData() {
        mContentItems = new ArrayList<>();
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"AAA", 1, "10/10 8:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"BBB", 1, "10/10 1:30", "约足球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"CCC", 1, "10/10 4:30", "约火锅", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"JJJ", 1, "10/10 7:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"PPP", 1, "10/10 8:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"AOO", 1, "10/10 5:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"LPL", 1, "10/10 8:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"AAA", 1, "10/10 9:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"MKL", 1, "10/10 8:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"UIO", 1, "10/10 2:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"HHH", 1, "10/10 2:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"GTF", 1, "10/10 1:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"AAA", 1, "10/10 5:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"FTD", 1, "10/10 8:40", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"HOB", 1, "10/10 6:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"AXA", 1, "10/10 5:30", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"BHU", 1, "10/10 8:10", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));
        mContentItems.add(new ContentItem(R.drawable.ic_user_photo,"FGT", 1, "10/10 3:10", "约篮球", "这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字这里好多字", 2, 4));

    }
}

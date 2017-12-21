package com.scujcc.dahuo.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.scujcc.dahuo.R;

public class WalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的钱包");
        setContentView(R.layout.user_activity_wallet);
    }

    public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.IndexHolder> {

        /**
         * 此部分待优化, 暂时无解
         */
        public class IndexHolder extends RecyclerView.ViewHolder {
            public IndexHolder(View itemView) {
                super(itemView);
            }
        }

        public class OneHolder extends IndexHolder {

            public OneHolder(View itemView) {
                super(itemView);
            }
        }

        public class TwoHolder extends IndexHolder {

            public TwoHolder(View itemView) {
                super(itemView);
            }
        }

        public class ThreeHolder extends IndexHolder {

            public ThreeHolder(View itemView) {
                super(itemView);
            }
        }



        @Override
        public WalletAdapter.IndexHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(WalletAdapter.IndexHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }


    }
}

package com.yichuizi.tiktik.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yichuizi.tiktik.R;

import java.util.ArrayList;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/4 17:27
 * 描述：
 */
public class DetailHeadVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private ArrayList<String> mDatas = new ArrayList<>();

    private View mHeaderView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void addDatas(ArrayList<String> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_fh_comment, parent, false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(viewHolder);
        final String data = mDatas.get(pos);
        if (viewHolder instanceof Holder) {
            ((Holder) viewHolder).text.setText(data);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text;

        public Holder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            text = itemView.findViewById(R.id.tv_comment);
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position, String data);
    }
}

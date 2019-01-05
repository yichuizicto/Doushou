package com.yichuizi.tiktik.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yichuizi.tiktik.R;
import com.yichuizi.tiktik.bean.VideoBean;

import java.util.List;

/**
 * 作者：一锤子打扫卫生的 on 2019/1/4 14:41
 * 描述：
 * 其实应该有一个baseAdapter的哦～算了。。抽空在弄呗
 */
public class FhMainVideoAdapter extends RecyclerView.Adapter<FhMainVideoAdapter.FhVideoHolder> {

    private final RequestOptions option2, options;
    private List<VideoBean> mData;
    private OnItemClickListenner mOnItemClickListenner;

    public FhMainVideoAdapter() {
        options = new RequestOptions()
                .placeholder(R.drawable.iv_fh_praise)
                .error(R.drawable.iv_fh_praise)
                .circleCrop()
                .override(100, 100);
        option2 = new RequestOptions()
                .placeholder(R.drawable.iv_fh_praise)
                .error(R.drawable.iv_fh_praise)
                .override(700, 700);
    }

    public void addDataAndClear(List<VideoBean> mData) {
        if (this.mData != null) {
            this.mData.clear();
            this.mData.addAll(mData);
        } else {
            this.mData = mData;
        }
        //此处该有一个优化
        notifyDataSetChanged();
    }

    public void addData(List<VideoBean> mData) {
        if (this.mData != null) {
            this.mData.addAll(mData);
        } else {
            this.mData = mData;
        }
        //此处该有一个优化
        notifyDataSetChanged();
    }

    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner) {
        this.mOnItemClickListenner = onItemClickListenner;
    }


    @NonNull
    @Override
    public FhVideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FhVideoHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_fh, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FhVideoHolder fhVideoHolder, final int i) {
        final VideoBean videoBean = mData.get(i);
        if (videoBean != null) {
            Glide.with(fhVideoHolder.mImageViewCover.getContext())
                    .load(videoBean.getmCoverUrl())
                    .apply(option2)
                    .into(fhVideoHolder.mImageViewCover);

            Glide.with(fhVideoHolder.mImageViewAvatar.getContext())
                    .load(videoBean.getmCoverUrl())
                    .apply(options)
                    .into(fhVideoHolder.mImageViewAvatar);
            fhVideoHolder.mTextViewPraise.setText(String.valueOf(videoBean.getmPraise()));
            fhVideoHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListenner != null) {
                        mOnItemClickListenner.onItemClick(videoBean, fhVideoHolder.itemView, i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public class FhVideoHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewAvatar;
        private ImageView mImageViewCover;
        private TextView mTextViewPraise;

        public FhVideoHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewAvatar = itemView.findViewById(R.id.iv_avatar);
            mImageViewCover = itemView.findViewById(R.id.iv_cover);
            mTextViewPraise = itemView.findViewById(R.id.tv_praise);
        }
    }

    public interface OnItemClickListenner {
        void onItemClick(VideoBean videoBean, View view, int postion);
    }
}

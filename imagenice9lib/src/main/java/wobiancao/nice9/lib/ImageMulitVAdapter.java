package wobiancao.nice9.lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wxy on 2017/5/31.
 */

public class ImageMulitVAdapter extends VirtualLayoutAdapter<ImageMulitVAdapter.ImageViewHolder> implements MyItemTouchCallback.ItemTouchAdapter {
    private List<String> pictures = new ArrayList<>();
    private Context context;
    private boolean canDrag;

    public ImageMulitVAdapter(@NonNull VirtualLayoutManager layoutManager, List<String> pictures, Context context, boolean canDrag) {
        super(layoutManager);
        this.pictures = pictures;
        this.context = context;
        this.canDrag = canDrag;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mulit_image, null));
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = 0, height = 0;
        int imageCount = pictures.size();
        int displayW = DisplayUtils.getDisplayWidth(context);
        if (imageCount == 1) {
            width = displayW;
            height = width;
        } else if (imageCount == 2) {
            width = displayW / 2;
            height = width;
        } else if (imageCount == 3) {
            if (position == 0) {
                width = (int) (displayW * 0.66);
                height = width;
                layoutParams.rightMargin = 10;
                layoutParams.bottomMargin = 10;
            } else {
                if (position == 1 || position == 2) {
                    if (position == 1) {
                        layoutParams.bottomMargin = 5;
                    } else {
                        layoutParams.bottomMargin = 10;
                    }
                }
                width = (int) ((displayW * 0.33));
                height = width;
            }
        } else if (imageCount == 4) {
            if (position == 0) {
                width = displayW;
                height = (int) (width * 0.5);
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 5) {
            if (position == 0 || position == 1) {
                width = (int) (displayW * 0.5);
                height = width;
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 6) {
            if (position == 0) {
                width = (int) (displayW * 0.66);
                height = width;
                layoutParams.rightMargin = 10;
                layoutParams.bottomMargin = 10;
            } else {
                if (position == 1 || position == 2) {
                    if (position == 1) {
                        layoutParams.bottomMargin = 5;
                    } else {
                        layoutParams.bottomMargin = 10;
                    }

                }
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 7) {
            if (position == 0) {
                width = displayW;
                height = (int) (width * 0.5);
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 8) {
            if (position == 0 || position == 1) {
                width = (int) (displayW * 0.5);
                height = width;
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else {
            width = (int) (displayW * 0.33);
            height = width;
        }
        layoutParams.width = width;
        layoutParams.height = height;
        holder.itemView.setLayoutParams(layoutParams);
        String imageUrl = pictures.get(position);
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(pictures, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(pictures, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public List<String> getPictures() {
        return pictures;
    }

    @Override
    public void onSwiped(int position) {
        notifyItemRemoved(position);
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_mulit_image);
        }
    }

}

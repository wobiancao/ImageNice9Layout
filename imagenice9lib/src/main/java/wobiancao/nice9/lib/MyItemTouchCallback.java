package wobiancao.nice9.lib;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.alibaba.android.vlayout.VirtualLayoutManager;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MyItemTouchCallback extends ItemTouchHelper.Callback {

    private ItemTouchAdapter itemTouchAdapter;

    public MyItemTouchCallback(ItemTouchAdapter itemTouchAdapter) {
        this.itemTouchAdapter = itemTouchAdapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //如果是ListView样式的RecyclerView
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager || recyclerView.getLayoutManager() instanceof VirtualLayoutManager) {
            //设置拖拽方向为上下左右
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            //不支持侧滑
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {//如果是GridView样式的RecyclerView

            //设置拖拽方向为上下
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            //设置侧滑方向为从左到右和从右到左都可以
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            //将方向参数设置进去
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
        int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
        itemTouchAdapter.onMove(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        itemTouchAdapter.onSwiped(position);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//在左右滑动时，让item的透明度随着移动而改变，并缩放
        float alpha = 0.6f;
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setScaleX(alpha);
            viewHolder.itemView.setScaleY(alpha);

        }
        //防止item复用出现问题，如果大家不理解下面这段代码，可以自行注释效果，
        if (alpha <= 0) {
            viewHolder.itemView.setAlpha(1);
            viewHolder.itemView.setScaleX(1);
            viewHolder.itemView.setScaleY(1);

        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            //滑动时改变Item的透明度
//            final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
//            viewHolder.itemView.setAlpha(alpha);
//            viewHolder.itemView.setTranslationX(dX);
//        } else {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
        //这句代码就是item拖拽和滑动效果的实现，所以这句不能省略
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//
//        //我们只需要在左右滑动时，将透明度和高度的值变小（1 --> 0）
//
//        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
//            float value = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
//            viewHolder.itemView.setAlpha(value);
//            viewHolder.itemView.setScaleY(value);
//        }
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (background == null && bkcolor == -1) {
                Drawable drawable = viewHolder.itemView.getBackground();
                if (drawable == null) {
                    bkcolor = 0;
                } else {
                    background = drawable;
                }
            }
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (onDragListener != null) {
            onDragListener.onFinishDrag();
        }
        viewHolder.itemView.setAlpha(1.0f);
        if (background != null) viewHolder.itemView.setBackgroundDrawable(background);
        if (bkcolor != -1) viewHolder.itemView.setBackgroundColor(bkcolor);
        //viewHolder.itemView.setBackgroundColor(0);


    }

    private Drawable background = null;
    private int bkcolor = -1;

    private OnDragListener onDragListener;

    public MyItemTouchCallback setOnDragListener(OnDragListener onDragListener) {
        this.onDragListener = onDragListener;
        return this;
    }

    public interface OnDragListener {
        void onFinishDrag();
    }

    public interface ItemTouchAdapter {
        void onMove(int fromPosition, int toPosition);

        void onSwiped(int position);
    }
}

package wobiancao.imagenice9layout.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wobiancao.nice9.lib.ImageNice9Layout;

/**
 * Created by wxy on 2017/6/14.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<DemoEntity> mDemoEntities;
    private Context mContext;
    public ListAdapter(Context mContext, List<DemoEntity> demoEntities) {
        this.mContext = mContext;
        mDemoEntities = demoEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mImageNice9Layout.bindData(mDemoEntities.get(position).pictures);
    }

    @Override
    public int getItemCount() {
        return mDemoEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageNice9Layout mImageNice9Layout;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageNice9Layout = (ImageNice9Layout) itemView.findViewById(R.id.item_nice9_image);
        }
    }
}

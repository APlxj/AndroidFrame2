package ap.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class MyAdapter extends BaseAdapter<String, MyAdapter.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener, ItemTouchCallBack.OnItemTouchListener {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private String[] colors = {"#DF554A", "#FFA854", "#69CD74", "#71B4ED",
            "#C995D3", "#5FD1DF", "#FFCF29", "#ED7DAD", "#93E195", "#C4D373",
            "#43B0EF", "#FC9A43", "#F87C7C"};

    public MyAdapter(List<String> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        //if (viewType == 1)
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        //else view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(mData.get(position));
        holder.mTextView.setText(position + 1 + "\t" + mData.get(position));
        //holder.mTextView.setBackgroundColor(Color.parseColor(colors[position % colors.length]));
        //http://att.bbs.duowan.com/forum/201501/25/085828v51w1s2vb165j11v.jpg
        //Glide.with(mContext).load("http://att.bbs.duowan.com/forum/201501/25/085828v51w1s2vb165j11v.jpg").asBitmap().into(holder.mImageView);
    }

    /*@Override
    public int getItemViewType(int position) {
        return position % 2;
    }*/

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemLongClick(v, (String) v.getTag());
        }
        return true;
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mData, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mData, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwiped(int i, int position) {
        if (position == ItemTouchHelper.LEFT) return;
        Log.i("drag", "onSwiped");
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
            mImageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);

        void onItemLongClick(View view, String data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

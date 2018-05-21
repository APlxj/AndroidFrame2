package ap.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public abstract class BaseAdapter<T, K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {

    public List<T> mData;
    public Context mContext;

    public BaseAdapter(List<T> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    public void putNewData(List<T> mData) {
        if (null == mData) this.mData.clear();
        else this.mData = mData;
        notifyDataSetChanged();
    }

    public void addItem(T data, int position) {
        if (data == null) return;
        if (mData == null) mData = new ArrayList<>();
        if (position >= mData.size()) position = mData.size();
        mData.add(position, data);
        notifyItemInserted(position);
    }

    public void addItem(List<T> data) {
        if (data == null) return;
        if (mData == null) mData = new ArrayList<>();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void removeItem(T data) {
        if (data == null) return;
        if (mData == null || mData.size() == 0) return;
        int position = mData.indexOf(data);
        if (position != -1) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}

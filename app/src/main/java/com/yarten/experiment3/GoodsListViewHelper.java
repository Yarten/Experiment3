package com.yarten.experiment3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.yarten.sgbasic.*;

/**
 * Created by yarten on 2017/10/16.
 * 用于设置RecycleView使其成为GoodsListView
 */

public class GoodsListViewHelper
{
    /**
     * 给RecyclerView加工：设置自定义适配器，布局管理器，动画管理器，分割线，等等
     * @param context
     * @param recyclerView
     */
    //region 加工函数
    public static void process(Context context, RecyclerView recyclerView)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        GoodsListAdapter adapter = new GoodsListAdapter();
        adapter.init(context);

        for(int i = 0, size = GoodsManager.goods.size(); i < size; i++)
        {
            adapter.add(GoodsManager.goods.get(i).name);
        }

        recyclerView.setAdapter(adapter);
    //    recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    //endregion

    //region 设置监听器
    public static void setOnClickListener(RecyclerView recyclerView, SGBasic.OnItemActionListener onClickListener)
    {
        for(int i = 0, size = recyclerView.getChildCount(); i < size; i++)
            ((GoodsListAdapter.GoodsItem)recyclerView.findViewHolderForAdapterPosition(i)).sgWidget.setOnItemClickListener(onClickListener);
    }

    public static void setOnLongClickListener(final RecyclerView recyclerView, SGBasic.OnItemActionListener onLongClickListener)
    {
        GoodsListAdapter adapter = (GoodsListAdapter)(recyclerView.getAdapter());

        for(int i = 0, size = recyclerView.getChildCount(); i < size; i++)
        {
            Log.i("LongClick2", String.format("%d", i));
         //   ((GoodsListAdapter.GoodsItem)recyclerView.findViewHolderForAdapterPosition(i)).sgWidget.setOnItemLongClickListener(onLongClickListener);
            ((GoodsListAdapter.GoodsItem)recyclerView.findViewHolderForAdapterPosition(i)).itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    Context context = ((GoodsListAdapter)(recyclerView.getAdapter())).context;
                    Toast.makeText(context, "AAA", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        adapter.setOnItemLongClickListener(onLongClickListener);
    }
    //endregion

    //region 适配器
    static class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsItem>
    {
        //region 维护的数据
        private List<String> names;
        public Context context;

        public void init(Context context)
        {
            names = new ArrayList<>();
            this.context = context;
        }

        public void add(final String name)
        {
            names.add(name);
        }

        @Override
        public int getItemCount()
        {
            return names.size();
        }
        //endregion


        //region 监听器
        private SGBasic.OnItemActionListener onItemClickListener;
        private SGBasic.OnItemActionListener onItemLongClickListener;

        public void setOnItemClickListener(SGBasic.OnItemActionListener onItemClickListener)
        {
            this.onItemClickListener = onItemClickListener;
        }

        public void setOnItemLongClickListener(SGBasic.OnItemActionListener onItemLongClickListener)
        {
            this.onItemLongClickListener = onItemLongClickListener;
        }
        //endregion


        //region 事件处理
        @Override
        public GoodsItem onCreateViewHolder(ViewGroup parent, int viewType)
        {
            GoodsListAdapter.GoodsItem holder = new GoodsListAdapter.GoodsItem(LayoutInflater.from(context).inflate(R.layout.goods_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final GoodsListAdapter.GoodsItem holder, final int position)
        {
            holder.pattern.setText(names.get(position).substring(0, 1));
            holder.name.setText(names.get(position));
            holder.sgWidget.bind(position);
            holder.sgWidget.setOnItemLongClickListener(onItemLongClickListener);
            holder.sgWidget.setOnItemClickListener(onItemClickListener);
        }

        public void removeItem(int position)
        {
            names.remove(position);

            notifyItemRemoved(position);
        }
        //endregion


        //region ViewHolder
        class GoodsItem extends RecyclerView.ViewHolder
        {
            public SGWidget sgWidget;

            TextView pattern, name;

            public GoodsItem(View view)
            {
                super(view);
                sgWidget = new SGWidget(super.itemView);
                pattern = view.findViewById(R.id.text_pattern);
                name = view.findViewById(R.id.text_name);
            }
        }
        //endregion
    }
    //endregion

    //region 分割线管理器
    static class DividerItemDecoration extends RecyclerView.ItemDecoration
    {
        //region 使用到的系统常量
        private static final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
        //endregion

        //region 构造器
        private Drawable divider;
        private int orientation;

        public DividerItemDecoration(Context context, int orientation)
        {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            divider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }
        //endregion

        //region 方向设置
        public void setOrientation(int orientation)
        {
            if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST)
                throw new IllegalArgumentException("Invalid Orientation");
            this.orientation = orientation;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            if(orientation == VERTICAL_LIST)
                outRect.set(0, 0, 0, divider.getIntrinsicHeight());
            else outRect.set(0, 0, divider.getIntrinsicWidth(), 0);
        }
        //endregion

        //region 渲染模块
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state)
        {
            if(orientation == VERTICAL_LIST)
                drawVertical(c, parent);
            else drawHorizontal(c, parent);
        }

        private void drawVertical(Canvas canvas, RecyclerView list)
        {
            final int left = list.getPaddingLeft();
            final int right = list.getWidth() - list.getPaddingRight();

            for(int i = 0, size = list.getChildCount()-1; i < size; i++)
            {
                final View item = list.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)item.getLayoutParams();
                final int top = item.getBottom() + params.bottomMargin;
                final int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(canvas);
            }
        }

        private void drawHorizontal(Canvas canvas, RecyclerView list)
        {
            final int top = list.getPaddingTop();
            final int bottom = list.getHeight() - list.getPaddingBottom();

            for(int i = 0, size = list.getChildCount(); i < size; i++)
            {
                final View item = list.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)item.getLayoutParams();
                final int left = item.getRight() + params.rightMargin;
                final int right = left + divider.getIntrinsicWidth();

                divider.setBounds(left, top, right, bottom);
                divider.draw(canvas);
            }
        }
        //endregion
    }
    //endregion
}

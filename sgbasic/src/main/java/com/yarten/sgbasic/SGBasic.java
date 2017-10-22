package com.yarten.sgbasic;

import android.view.MotionEvent;
import android.view.View;

import java.util.Vector;

/**
 * Created by yarten on 2017/10/8.
 */

public class SGBasic
{
    //region 列表Item端口绑定
    protected int itemPosition = -1;

    public void bind(int itemPosition)
    {
        this.itemPosition = itemPosition;
    }
    //endregion

    //region 监听接口
    public interface OnActionListener
    {
        void onAction(View view);
    }

    public interface OnMoveListener
    {
        void onMove(View view, Vector<Float> v);
    }

    public interface OnItemActionListener
    {
        void onAction(View view, int position);
    }
    //endregion

    //region 保护的监听器
    protected OnActionListener onClickListener;
    protected OnActionListener onLongClickListener;
    protected OnActionListener onDownListener;
    protected OnActionListener onLeaveListener;
    protected OnActionListener onPressListener;
    protected OnMoveListener onMoveListener;

    protected OnItemActionListener onItemClickListener;
    protected OnItemActionListener onItemLongClickListener;
    protected OnItemActionListener onItemDownListener;
    protected OnItemActionListener onItemLeaveListener;
    protected OnItemActionListener onItemPressListener;
    //endregion

    //region 监听器setters
    public void setOnClickListener(OnActionListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnActionListener onLongClickListener)
    {
        this.onLongClickListener = onLongClickListener;
    }

    public void setOnDownListener(OnActionListener onDownListener)
    {
        this.onDownListener = onDownListener;
    }

    public void setOnLeaveListener(OnActionListener onLeaveListener)
    {
        this.onLeaveListener = onLeaveListener;
    }

    public void setOnMoveListener(OnMoveListener onMoveListener)
    {
        this.onMoveListener = onMoveListener;
    }

    public void setOnPressListener(OnActionListener onPressListener)
    {
        this.onPressListener = onPressListener;
    }

    public void setOnItemClickListener(OnItemActionListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemActionListener onItemLongClickListener)
    {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemDownListener(OnItemActionListener onItemDownListener)
    {
        this.onItemDownListener = onItemDownListener;
    }

    public void setOnItemLeaveListener(OnItemActionListener onItemLeaveListener)
    {
        this.onItemLeaveListener = onItemLeaveListener;
    }

    public void setOnItemPressListener(OnItemActionListener onItemPressListener)
    {
        this.onItemPressListener = onItemPressListener;
    }
    //endregion

    //region 获得数据上下界
    /**
     * 需要给有需要的子类重写，将会被SGType调用，以作区间映射换算
     * @return 在onMove事件中，传入的Vector<Float>的下界
     */
    public  Vector<Float> getLowerBound()
    {
        return null;
    }

    /**
     * 需要给有需要的子类重写，将会被SGType调用，以作区间映射换算
     * @return 在onMove事件中，传入的Vector<Float>的上界
     */
    public Vector<Float> getUpperBound()
    {
        return null;
    }
    //endregion
}

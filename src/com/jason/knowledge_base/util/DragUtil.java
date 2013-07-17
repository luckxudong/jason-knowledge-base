package com.jason.knowledge_base.util;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;
import android.view.View.DragShadowBuilder;

/**
 * 拖曳工具
 *
 * @author JunFeng.Zheng
 */
public class DragUtil {

    /**
     * 被拖动的View
     */
    private View mDragItem;

    /**
     * 拖曳放大系数
     */
    private float mScale = 1.2f;

    public void startDrag(View dragItem) {
        startDrag(dragItem, null);
    }

    public void startDrag(View dragItem, Object myLocalState) {
        mDragItem = dragItem;
        mDragItem.startDrag(null, new MyDragShadowBuilder(mDragItem), myLocalState, 0);
    }

    private class MyDragShadowBuilder extends DragShadowBuilder {

        public MyDragShadowBuilder(View v) {
            super(v);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {

            int width, height;
            width = (int) (getView().getWidth() * mScale);
            height = (int) (getView().getHeight() * mScale);

            // Shadow宽高
            shadowSize.set(width, height);
            // 使手指在Shadow中央
            shadowTouchPoint.set(width / 2, height / 2);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {

            canvas.save();
            canvas.scale(mScale, mScale);
            mDragItem.draw(canvas);
            canvas.restore();
        }
    }
}

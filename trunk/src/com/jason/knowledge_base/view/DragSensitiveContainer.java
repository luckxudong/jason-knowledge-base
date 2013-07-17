package com.jason.knowledge_base.view;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * User: jason
 */
public class DragSensitiveContainer implements View.OnDragListener {

    public interface DragDropCallBack {
        void onDropStarted(View target, DragEvent event);

        void onDrop(View target, DragEvent event);

        void onDropEnded(View target, DragEvent event);
    }

    public void setDragDropCallBack(DragDropCallBack callBack) {
        this.dragDropCallBack = callBack;
    }

    public void startDrag(View dragView, View dragContainerView) {
        startDrag(
                dragView,
                dragContainerView,
                null,
                new View.DragShadowBuilder(dragView),
                null
        );
    }

    public void startDrag(View dragView, View dragContainerView, ClipData data,
                          View.DragShadowBuilder shadowBuilder, Object myLocalState) {
        bindDragListener(dragContainerView);

        dragView.startDrag(data, shadowBuilder, myLocalState, 0);
    }

    public void bindDragListener(View dragContainerView) {

        if (!(dragContainerView instanceof ViewGroup)) {
            if (dragContainerView.isFocusable()) {
                dragContainerView.setOnDragListener(this);
            }
            return;
        }

        ViewGroup viewGroup = (ViewGroup) dragContainerView;
        int childCount = viewGroup.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);

            bindDragListener(view);
        }

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                dragStarted(v, event);
                break;
            case DragEvent.ACTION_DROP:
                drop(v, event);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                dragEnded(v, event);
                break;
        }

        return true;
    }

    private void dragStarted(View v, DragEvent event) {
        if (!dragStarted) {
            dragStarted = true;

            drop = false;
            dragEnd = false;

            if (dragDropCallBack != null) {
                dragDropCallBack.onDropStarted(v, event);
            }
        }
    }

    private void drop(View v, DragEvent event) {
        if (!drop) {
            drop = true;

            if (dragDropCallBack != null) {
                dragDropCallBack.onDrop(v, event);
            }
        }
    }

    private void dragEnded(View v, DragEvent event) {
        if (!dragEnd) {
            dragEnd = true;
            dragStarted = false;

            if (dragDropCallBack != null) {
                dragDropCallBack.onDropEnded(v, event);
            }
        }

    }

    private DragDropCallBack dragDropCallBack;
    private boolean dragStarted, drop, dragEnd;
}

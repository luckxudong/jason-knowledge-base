package com.jason.knowledge_base.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import com.jason.knowledge_base.R;

/**
 * User: jason
 */
public class DragAndDropActivity extends Activity {

    private static final String TAG = "DragAndDropActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drag_drop);

        final DragSensitiveContainer dragSensitiveContainer = new DragSensitiveContainer();

        dragSensitiveContainer.setDragDropCallBack(new DragDropHandler());

        findViewById(R.id.drag_src).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View dragContainerView = findViewById(R.id.drag_container_view);
                dragContainerView.setVisibility(View.VISIBLE);
                dragSensitiveContainer.startDrag(v, dragContainerView);
                return true;
            }
        });

    }


    private class DragDropHandler implements DragSensitiveContainer.DragDropCallBack {
        @Override
        public void onDropStarted(View target, DragEvent event) {
            Log.w(TAG, "onDropStarted " + target);
        }

        @Override
        public void onDrop(View target, DragEvent event) {
            Log.w(TAG, "onDrop " + target);
        }

        @Override
        public void onDropEnded(View target, DragEvent event) {
            Log.w(TAG, "onDropEnded " + target);
        }
    }
}

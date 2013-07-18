package com.jason.knowledge_base.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.jason.knowledge_base.R;

/**
 * User: jason
 */
public class ListViewHeadAndFooterActivity extends Activity implements View.OnClickListener {

    private View footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_head_footer_test);

        init();
    }

    private void init() {
        ListView listView = (ListView) findViewById(R.id.list_view);
        footer = getLayoutInflater().inflate(R.layout.list_footer, null);

        listView.addFooterView(footer);

        String[] strings = new String['z' - 'a' + 1];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = Character.toString((char) ('a' + i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_list_text_item, strings);

        listView.setAdapter(adapter);

        findViewById(R.id.btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        View footerContent = footer.findViewById(R.id.footer_content);

        if (footerContent.getVisibility() != View.VISIBLE) {
            footerContent.setVisibility(View.VISIBLE);
        } else {
            footerContent.setVisibility(View.GONE);
        }
    }
}

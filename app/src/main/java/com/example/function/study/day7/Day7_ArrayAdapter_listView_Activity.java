package com.example.function.study.day7;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ldy.study.R;

public class Day7_ArrayAdapter_listView_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day7);
        ListView listView = findViewById(R.id.listView);
        //数据源
        String[] objects =new String[]{"这是第一条", "这是第二条"};
        //去展示listView
         //可以直接ArrayAdapter也可以ArrayAdapter<T>，安卓后面的版本加了范型，可以检测数据类型
         //ArrayAdapter录入参数如右(@NonNull Context context, @LayoutRes int resource,@IdRes int textViewResourceId, @NonNull List<T> objects)
         //resource：填资源文件id，为layout布局文件。里面放置条目的布局和控件
         //textViewResourceId，填写条目控件，对于arrayAdapter只能是textView。其他的adapter可以使用其他控件。
         //objects数据源
                 ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,
                         R.layout.activity_day7_arrayadapter_item,R.id.textView,objects);
                 listView.setAdapter(arrayAdapter);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Day7_ArrayAdapter_listView_Activity.this, "点击第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Day7_ArrayAdapter_listView_Activity.this, "长按第" + position + "条", Toast.LENGTH_SHORT).show();
                //true处理该事件，false，不处理，传递给上一级
                return true;
            }
        });
    }
}

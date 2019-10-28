package com.example.main;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.action.Method;
import com.example.main.adapter.MyAdapter;
import com.example.main.adapter.SubAdapter;
import com.example.main.listview.MyListView;
import com.ldy.study.R;


public class MainActivity extends Activity {
    
	private MyListView listView;
	private MyListView subListView;
	private MyAdapter myAdapter;
	private SubAdapter subAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		try {
			this.getPackageManager().getPackageInfo("com.ldy.study", 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		init();
		//创建适配器
        myAdapter=new MyAdapter(getApplicationContext(), ConstantData.GROUP_NAMES, ConstantData.IMAGES);
        listView.setAdapter(myAdapter);
    
        selectDefult();
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				final int location=position;
				myAdapter.setSelectedPosition(position);
				myAdapter.notifyDataSetInvalidated();
				subAdapter=new SubAdapter(getApplicationContext(),ConstantData.CHILD_NAMES , position);
				subListView.setAdapter(subAdapter);
				subListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), ConstantData.CHILD_NAMES[location][position], Toast.LENGTH_SHORT).show();
						Log.e("ldy","location="+location+";position="+position);
						//调用实现方法
						Method.implement_method(location,position,getApplicationContext());

					}
				});
			}
		});
        
    }
   private void init(){
	   listView=(MyListView) findViewById(R.id.listView);
	   subListView=(MyListView) findViewById(R.id.subListView);
   }
   
   private void selectDefult(){
	   final int location=0;
	 		myAdapter.setSelectedPosition(0);
	 		myAdapter.notifyDataSetInvalidated();
	 		subAdapter=new SubAdapter(getApplicationContext(), ConstantData.CHILD_NAMES, 0);
	 		subListView.setAdapter(subAdapter);
	 		subListView.setOnItemClickListener(new OnItemClickListener() {

	 			@Override
	 			public void onItemClick(AdapterView<?> arg0, View arg1,
	 					int position, long arg3) {
	 				// TODO Auto-generated method stub
	 				Toast.makeText(getApplicationContext(), ConstantData.CHILD_NAMES[location][position], Toast.LENGTH_SHORT).show();
					//调用实现方法
	 				Method.implement_method(location,position,getApplicationContext());
	 				}
	 		});
   }
  
}

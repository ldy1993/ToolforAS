package com.example.multilistview;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class MainActivity extends Activity {
    
	private MyListView listView;
	private MyListView subListView;
	private MyAdapter myAdapter;
	private SubAdapter subAdapter;
	
	String cities[][] = new String[][] {
            new String[] {"银行卡识别", "调用sdk扫码", "文字识别", "人证识别", "人脸识别","待定1","待定2","待定3","待定4","待定5","待定6","待定7"},
            new String[] {"JNI测试","待定","待定","待定","待定","待定","待定","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
          		"待定","�����","������Ϸ","������������"},
            new String[] {"ȫ������", "�ۺ��̳�", "����Ь��", "�˶�����","�鱦��Ʒ","��ױƷ","����ҵ�","���ӹ���","�Ҿӽ���"
          		,"���","���","�۾���","��ɫ����","���๺�ﳡ��","ʳƷ���","����/������","ҩ��"},
            new String[] {"USB_HOST_QR55","������","�ư�","���","KTV","��ӰԺ","��������","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
            		"DIY�ֹ���","�����","������Ϸ","������������"},
            new String[] {"ȫ","������","�ư�","���","KTV","��������","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
            		"DIY�ֹ���","�����","������Ϸ","������������"},
            new String[] {"ȫ��","������","�ư�","���","��ӰԺ","��������","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
            		"DIY�ֹ���","�����","������Ϸ","������������"},
            new String[] {"ȫ����","������","�ư�","���","KTV","��ӰԺ","��������","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
            		"DIY�ֹ���","�����","������Ϸ","������������"},
            new String[] {"ȫ������","������","�ư�","���","KTV","��ӰԺ","��������","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
            		"DIY�ֹ���","�����","������Ϸ","������������"},
            new String[] {"ȫ��������","������","�ư�","���","KTV","��ӰԺ","��������","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
            		"DIY�ֹ���","�����","������Ϸ"},
            new String[] {"ȫ����������","������","�ư�","���","KTV","��ӰԺ","��������","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
            		"DIY�ֹ���","�����","������Ϸ","������������"},
            new String[] {"ȫ������aaa","������","�ư�","���","KTV","��ӰԺ","��������","��԰","����/����","ϴԡ","��ԡ��Ħ","�Ļ�����",
            		"DIY�ֹ���","�����","������Ϸ"},
            };
		String foods[] =new String []{"摄像头","JNI","建行调用","USB","待定","待定","待定","待定","待定","待定","待定"};
		int images[] = new int[]{R.drawable.ic_category_0, R.drawable.ic_category_10, R.drawable.ic_category_30, R.drawable.ic_category_20
				, R.drawable.ic_category_60, R.drawable.ic_category_50, R.drawable.ic_category_45, R.drawable.ic_category_50, R.drawable.ic_category_70,
				R.drawable.ic_category_65, R.drawable.ic_category_80};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        myAdapter=new MyAdapter(getApplicationContext(), foods, images);
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
				subAdapter=new SubAdapter(getApplicationContext(), cities, position);
				subListView.setAdapter(subAdapter);
				subListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), cities[location][position], Toast.LENGTH_SHORT).show();
						Log.e("ldy","location="+location+";position="+position);
						//调用实现方法
						ImplementMethod.implement_method(location,position,getApplicationContext());

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
	 		subAdapter=new SubAdapter(getApplicationContext(), cities, 0);
	 		subListView.setAdapter(subAdapter);
	 		subListView.setOnItemClickListener(new OnItemClickListener() {

	 			@Override
	 			public void onItemClick(AdapterView<?> arg0, View arg1,
	 					int position, long arg3) {
	 				// TODO Auto-generated method stub
	 				Toast.makeText(getApplicationContext(), cities[location][position], Toast.LENGTH_SHORT).show();
					//调用实现方法
	 				ImplementMethod.implement_method(location,position,getApplicationContext());
	 				}
	 		});
   }
  
}

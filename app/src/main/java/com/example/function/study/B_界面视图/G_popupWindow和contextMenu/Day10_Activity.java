package com.example.function.study.B_界面视图.G_popupWindow和contextMenu;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.study.R;
@MenuActivity(menu= MenuEnum.学习功能主菜单,sonMenu = SonMenuEnum.POPUPWINDOW)
public class Day10_Activity extends Activity {
private  View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day10);
        view=LayoutInflater.from(this).inflate(R.layout.activity_day7,null);
                //把day7中listview抄过来
         ListView listView =view.findViewById(R.id.listView);
        //数据源
        String[] objects =new String[]{"这是第一条", "这是第二条"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,
                R.layout.activity_day7_arrayadapter_item,R.id.textView,objects);
        listView.setAdapter(arrayAdapter);
        initPopup();
        this.registerForContextMenu(findViewById(R.id.contentMenu));
    }
    public void initPopup()
    {
        window= new PopupWindow(this);
        window.setContentView(view);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setAnimationStyle(android.R.style.Animation_InputMethod);
        window.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    window.dismiss();
                    return true;
                } else {
                    return false;
                }
            }
        });
        ((ListView)window.getContentView().findViewById(R.id.listView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = parent.getItemAtPosition(position);
                window.dismiss();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeWindowAlpha(1);
            }
        });

    }
    private  PopupWindow window;
public void popupBt(View view)
{
    changeWindowAlpha(0.5f);
    window.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
}
    private void changeWindowAlpha(float alpha) {
        WindowManager.LayoutParams lp = Day10_Activity.this.getWindow().getAttributes();
        lp.alpha = alpha;
        Day10_Activity.this.getWindow().setAttributes(lp);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                   ContextMenu.ContextMenuInfo menuInfo) {
        // set context menu title
        menu.setHeaderTitle("条目");
        // add context menu item
        menu.add(0, 1, Menu.NONE, "按钮中的弹窗1");
        menu.add(0, 2, Menu.NONE, "按钮中的弹窗2");
  }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // 得到当前被选中的item信息
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()) {
            case 1:
            // do something
            break;
            case 2:
            // do something
            break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
}

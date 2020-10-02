package com.example.function.doubleScreenDisplay;

import android.app.Activity;
import android.app.Presentation;
import android.content.Context;
import android.media.MediaRouter;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuActivity;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.MenuEnum;
import com.example.function.study.A_了解JAVA.H_java之注解.annotation.menu.SonMenuEnum;
import com.ldy.study.R;

/**
 * @author 东阳
 */
@MenuActivity(menu= MenuEnum.有趣功能主菜单 ,sonMenu = SonMenuEnum.异屏双显)
public class AdvertisingActivity extends Activity {
    private Presentation mCurrentPresentation;
    private Display display;
    private MediaRouter mMediaRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising);
        Button bt = findViewById(R.id.bt);
        // Android系统从4.3版本开始提供了一个媒体路由服务，获取MediaRouter服务
        mMediaRouter = (MediaRouter) getSystemService(Context.MEDIA_ROUTER_SERVICE);
        //返回用户当前选择的媒体路由。该路由也必须存在。
        //可以给MediaRouter.getSelectedRoute()传一个ROUTE_TYPE_LIVE_VIDEO来获得演示的默认显示器。
        // 它将返回一个MediaRouter.RouteInfo对象，描述了系统为视频演示所选择的当前路由。
        // 如果MediaRouter.RouteInfo不空，调用getPresentationDisplay()
        // 即可获取当前连接的显示屏对象：Display。
        MediaRouter.RouteInfo route = mMediaRouter.getSelectedRoute(MediaRouter
                .ROUTE_TYPE_LIVE_VIDEO);
        if (route != null) {
            display = route.getPresentationDisplay();
        }
        if (null == display) {
            Toast.makeText(this, "dispay为空，无法启动异屏", Toast.LENGTH_SHORT).show();

        } else {
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //将这个Display对象传入Presentation的构造函数。
                    // 调用Presentation.show()方法，演示就会出现在辅助显示屏上了。
                    mCurrentPresentation = new AdvertisingPresentation(AdvertisingActivity.this, display);

                    if (null != mCurrentPresentation) {
                        mCurrentPresentation.show();
                    }
                }
            });
        }
    }
}

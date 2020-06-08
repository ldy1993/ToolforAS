package com.example.function.algorithm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.ldy.log.Log;
import com.ldy.study.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class SokobanActivity extends Activity {
    private short[][] rootMap;
    private int[] manCoordinates;
    /**
     * 空地：         00000000--->0;什么都没有， 00000100--->4;空位存在空地上也是可以走的。
     * 是否存在人：   00000001--->1;第1位
     * 是否存在箱子： 00000010--->2;第2位
     * 是否存在目的地： 00000100--->4;第3位
     * 是否存在墙：   00001000--->8;第4位
     **/
    private final static short SPACE_0 = 0;
    private final static short MAN_1 = 1;
    private final static short BOX_2 = 2;
    private final static short DESTTINATION_4 = 4;
    private final static short MAN_1_AND_DESTTINATION_4 = 5;
    private final static short BOX_2_AND_DESTTINATION_4 = 6;
    private final static short WALL_8 = 8;
    private final static int END = 0;
    private final static int CHANGE_VIEW = 1;
    /**
     * 定义上下左右常量
     */
    private final static int up = 1;
    private final static int down = 2;
    private final static int left = 3;
    private final static int right = 4;
    private int num = 0;
    private GridLayout gridLayout;
    private ImageView img;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case END:
                    Toast.makeText(SokobanActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    android.util.Log.e("ldy", "一共遍历了" + num + "个地图");
                    break;
                case CHANGE_VIEW:
                    changeView((short[][]) msg.obj);
                    break;
                default:
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sokoban);
        Button bt_sokoban = findViewById(R.id.bt_sokoban);
        if (initMap()) {
            Toast.makeText(SokobanActivity.this, "地图查找成功", Toast.LENGTH_SHORT).show();
        }
        gridLayout = findViewById(R.id.gl);
        changeView(rootMap);
        bt_sokoban.setOnClickListener(listener);
    }

    private void changeView(short[][] map) {
        if (map != null) {
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 6; y++) {
                    switch (map[x][y]) {
                        case SPACE_0:
                            img = (ImageView) gridLayout.getChildAt(x * 6 + y);
                            img.setImageDrawable(getDrawable(R.drawable.space));
                            break;
                        case MAN_1:
                            img = (ImageView) gridLayout.getChildAt(x * 6 + y);
                            img.setImageDrawable(getDrawable(R.drawable.character5));
                            break;
                        case BOX_2:
                            img = (ImageView) gridLayout.getChildAt(x * 6 + y);
                            img.setImageDrawable(getDrawable(R.drawable.crate_yellow));
                            break;
                        case DESTTINATION_4:
                            img = (ImageView) gridLayout.getChildAt(x * 6 + y);
                            img.setImageDrawable(getDrawable(R.drawable.end_point_red));
                            break;
                        case MAN_1_AND_DESTTINATION_4:
                            img = (ImageView) gridLayout.getChildAt(x * 6 + y);
                            img.setImageDrawable(getDrawable(R.drawable.character5));
                            break;

                        case BOX_2_AND_DESTTINATION_4:
                            img = (ImageView) gridLayout.getChildAt(x * 6 + y);
                            img.setImageDrawable(getDrawable(R.drawable.crate_dark_red));
                            break;
                        case WALL_8:
                            img = (ImageView) gridLayout.getChildAt(x * 6 + y);
                            img.setImageDrawable(getDrawable(R.drawable.wall_black));
                            break;
                        default:
                            img = (ImageView) gridLayout.getChildAt(x * 6 + y);
                            img.setImageDrawable(getDrawable(R.drawable.wall_black));
                            break;
                    }

                }
            }
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            NetProcessDialog.getInstance(SokobanActivity.this).show();

            Executor executor = new ScheduledThreadPoolExecutor(1);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Message message = new Message();

                        {
//                            rootMap = fillMan_1(rootMap, 2, 4);
//                            Log.e("ldy", "==============地图填充");
                            showMap(rootMap);
                            num = 0;
                            List<short[][]> list = new ArrayList<>();
                            manCoordinates = new int[]{4, 2};
                            num++;
                            if (searchSubMap(list, rootMap, 2, 4)) {
                                message.obj = "地图查找成功";
                                message.what = END;
                                handler.sendMessage(message);
                            } else {
                                message.obj = "地图无解";
                                handler.sendMessage(message);
                            }
//                            NetProcessDialog.getInstance(SokobanActivity.this).dismiss();
                        }
                    } catch (Exception e) {
//                        NetProcessDialog.getInstance(SokobanActivity.this).dismiss();
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private boolean searchSubMap(List<short[][]> list, short[][] map, int xx, int yy) {
        short[][] copyMap;
        if (map != null) {
            //查看该地图是否满足所有条件
            if (isSucceed(map, list)) {
                return true;
            }
            list.add(map);
            HashSet<short[][]> set = new HashSet<>(list);
            Boolean result = set.size() == list.size() ? true : false;
            //如果该map在list中存在，result=false,说明这条路以前走过。查找子地图失败
            if (!result) {
                return false;
            }
            copyMap = copyMap(map);
            num++;
            map = showMapAndFill(map, xx, yy);
            Log.e("ldy", "==============推箱子后的地图");
            showMap(map);
        } else {
            return false;
        }
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                //找到人，或者人+目的地
                if ((map[x][y] & MAN_1)==MAN_1) {
                    //如果人旁边有箱子，就去推他
                    if (x - 1 >= 0) {
                        if ((map[x - 1][y] & BOX_2) == BOX_2) {
                            //向上推箱子
                            short[][] subMap = pushBox(copyMap, x, y, up);
                            if (searchSubMap(list, subMap, x - 1, y)) {
                                return true;
                            } else {
                                showMapAndFill(map, x, y);
                            }
                        }
                    }
                    if (x + 1 < 6) {
                        if ((map[x + 1][y] & BOX_2) == BOX_2) {
                            //向下推箱子
                            short[][] subMap = pushBox(copyMap, x, y, down);

                            if (searchSubMap(list, subMap, x + 1, y)) {
                                return true;
                            } else {
                                showMapAndFill(map, x, y);
                            }
                        }
                    }

                    if (y - 1 >= 0) {
                        if ((map[x][y - 1] & BOX_2) == BOX_2) {
                            //向左推箱子
                            short[][] subMap = pushBox(copyMap, x, y, left);
                            if (searchSubMap(list, subMap, x, y - 1)) {
                                return true;
                            } else {
                                showMapAndFill(map, x, y);
                            }
                        }
                    }
                    if (y + 1 < 6) {
                        if ((map[x][y + 1] & BOX_2) == BOX_2) {
                            //向右推箱子
                            short[][] subMap = pushBox(copyMap, x, y, right);
                            if (searchSubMap(list, subMap, x, y + 1)) {
                                return true;
                            } else {
                                showMapAndFill(map, x, y);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private short[][] showMapAndFill(short[][] map, int xx, int yy) {
        clearMan(xx, yy, map);
        sendMsg(map);
        map = fillMan_1(map, xx, yy);
        return map;
    }

    private void sendMsg(short[][] copyMap) {
        Message message = new Message();
        message.obj = copyMap;
        message.what = CHANGE_VIEW;
        handler.sendMessage(message);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clearMan(int xx, int yy, short[][] copyMap) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                //把所有除了传入坐标外的位置都置为没有人xxx0
                if ((i != xx) || (j != yy)) {
//                    xxxx&1110-->xxx0
                    copyMap[i][j] = (short) (copyMap[i][j] & 14);
                }
            }
        }
    }

    private short[][] copyMap(short[][] map) {
        short[][] subMap = new short[6][6];
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                subMap[x][y] = map[x][y];
            }
        }
        return subMap;
    }

    /**
     * 初始化地图
     */
    public boolean initMap() {
//        rootMap = new short[][]{
//                new short[]{SPACE_0, SPACE_0, SPACE_0, WALL_8, WALL_8, WALL_8},
//                new short[]{SPACE_0, SPACE_0, BOX_2_AND_DESTTINATION_4, SPACE_0, WALL_8, WALL_8},
//                new short[]{WALL_8, SPACE_0, BOX_2_AND_DESTTINATION_4, SPACE_0, MAN_1, SPACE_0},
//                new short[]{WALL_8, SPACE_0, BOX_2_AND_DESTTINATION_4, SPACE_0, SPACE_0, SPACE_0},
//                new short[]{WALL_8, BOX_2, DESTTINATION_4, BOX_2, WALL_8, WALL_8},
//                new short[]{WALL_8, SPACE_0, DESTTINATION_4, SPACE_0, WALL_8, WALL_8}};
        rootMap = new short[][]{
                new short[]{WALL_8, WALL_8, WALL_8, WALL_8, WALL_8, WALL_8},
                new short[]{WALL_8, WALL_8, WALL_8, WALL_8, WALL_8, WALL_8},
                new short[]{WALL_8, WALL_8, SPACE_0, SPACE_0, SPACE_0, WALL_8},
                new short[]{WALL_8, WALL_8, DESTTINATION_4, BOX_2, MAN_1, WALL_8},
                new short[]{WALL_8, WALL_8, SPACE_0, SPACE_0, SPACE_0, WALL_8},
                new short[]{WALL_8, WALL_8, WALL_8, WALL_8, WALL_8, WALL_8}};

        //判断该地图是否满足条件
        return isSucceed(rootMap, null);
    }

    private boolean isSucceed(short[][] map, List<short[][]> list) {
        if (((map[1][2] & BOX_2) == BOX_2)
                && ((map[2][2] & BOX_2) == BOX_2)
                && ((map[3][2] & BOX_2) == BOX_2)
                && ((map[4][2] & BOX_2) == BOX_2)
                && ((map[5][2] & BOX_2) == BOX_2)
        ) {
            return true;
        } else {

            return false;
        }
    }

    /**
     * 填充方法，把1相连的所有0变成1；
     * 代表人可以在没有障碍物的通道上随意走路。
     *
     * @param map
     */
    private short[][] fillMan_1(short[][] map, int x, int y) {
        //TODO 复杂度指数增长，待优化

        //检查他的上下左右是否有空位
        short[][] mapTemp = checkAndChange0To1(map, x, y);
        if (mapTemp != null) {
            return mapTemp;
        }

        return map;
    }


    private void showMap(short[][] mapTemp) {
        for (int x = 0; x < 6; x++) {
            Log.e("ldy", "mapTemp，第" + x + "行：  "
                    + mapTemp[x][0] + "  "
                    + mapTemp[x][1] + "  "
                    + mapTemp[x][2] + "  "
                    + mapTemp[x][3] + "  "
                    + mapTemp[x][4] + "  "
                    + mapTemp[x][5]);

        }
    }

    private short[][] pushBox(short[][] map, int x, int y, int direction) {
        //-1代表左，1代表右
        int directionY = 0;
        //-1代表上，1代表下
        int directionX = 0;
        switch (direction) {
            case up:
                directionY = 0;
                directionX = -1;
                break;
            case down:
                directionY = 0;
                directionX = 1;
                break;
            case left:
                directionY = -1;
                directionX = 0;
                break;
            case right:
                directionY = 1;
                directionX = 0;
                break;
            default:

        }
        return move(map, x, y, directionY, directionX);
    }

    private short[][] move(short[][] map, int x, int y, int directionY, int directionX) {
        if (map == null) {
            return null;
        }
        //推出的箱子是否撞到边界
        if ((y + (2 * directionY)) > 0 && (y + (2 * directionY)) < 6 && (x + (2 * directionX)) > 0 && (x + (2 * directionX)) < 6) {
            //推出的箱子是否撞到墙
            if ((map[x + (2 * directionX)][y + (2 * directionY)] & WALL_8) != WALL_8) {
                //推出的箱子是否撞到其他箱子
                if ((map[x + (2 * directionX)][y + (2 * directionY)] & BOX_2) != BOX_2) {
                    //推出的箱子所在位置状态加上箱子,减去人
                    map[x + (2 * directionX)][y + (2 * directionY)] = (short) ((map[x + (2 * directionX)][y + (2 * directionY)] | BOX_2) & 14);

                    //推出的箱子原来位置状态减去箱子，加上人
                    //这里xx10&1101---xx01;
                    map[x + directionX][y + directionY] = (short) (map[x + directionX][y + directionY] & 13 | 1);
                    return map;
                }
            }
        }
        return null;

    }


    /**
     * 检查他的上下左右是否有空位,有的话0变成1
     *
     * @param map
     * @param i
     * @param j
     */
    private short[][] checkAndChange0To1(short[][] map, int i, int j) {
        short[][] mapTemp = null;
        //只有单纯的空地或者目的地才可以站人
        if (i - 1 >= 0) {
            if ((map[i - 1][j] == SPACE_0)
                    || (map[i - 1][j] == DESTTINATION_4)) {
                //1的上边如果是0或者4，则把这个0变成1;4变成5；
                map[i - 1][j] = (short) (map[i - 1][j] | MAN_1);
                mapTemp = checkAndChange0To1(map, i - 1, j);
            }
        }
        if (i + 1 < 6) {
            if ((map[i + 1][j] == SPACE_0)
                    || (map[i + 1][j] == DESTTINATION_4)) {
                //1的下边如果是0或者4，则把这个0变成1;4变成5；
                map[i + 1][j] = (short) (map[i + 1][j] | MAN_1);
                mapTemp = checkAndChange0To1(map, i + 1, j);
            }
        }

        if (j - 1 >= 0) {
            if ((map[i][j - 1] == SPACE_0)
                    || (map[i][j - 1] == DESTTINATION_4)) {
                //1的左边如果是0或者4，则把这个0变成1;4变成5；
                map[i][j - 1] = (short) (map[i][j - 1] | MAN_1);
                mapTemp = checkAndChange0To1(map, i, j - 1);
            }
        }
        if (j + 1 < 6) {
            if ((map[i][j + 1] == SPACE_0)
                    || (map[i][j + 1] == DESTTINATION_4)) {
                //1的右边如果是0或者4，则把这个0变成1;4变成5；
                map[i][j + 1] = (short) (map[i][j + 1] | MAN_1);
                mapTemp = checkAndChange0To1(map, i, j + 1);
            }
        }
        if (mapTemp == null) {
            return map;
        }
        return mapTemp;
    }
}

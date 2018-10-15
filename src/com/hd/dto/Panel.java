package com.hd.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chuanhao.zhao@hand-china.com
 * @version 1.0
 * @name Panel
 * @description 棋盘类，即整个游戏
 * @date 2018/10/9
 */
public class Panel {
    public static final int WIDTH = 8;  // 棋盘的宽
    public static final int HEIGHT = 8;  // 棋盘的高
    public static final int KIND = 4;  // 棋盘中元素的种类

    private Object[][] objects;  // 所有棋盘的元素装在二维数组中

    public Object[][] getObjects() {
        return objects;
    }

    public void setObjects(Object[][] objects) {
        this.objects = objects;
    }

    public Panel(){
        //初始化棋盘时，在外层加一圈通道(所以上下各加2)
        this.objects = new Object[Panel.WIDTH + 2][Panel.HEIGHT + 2];
        int count = (WIDTH * HEIGHT) / KIND;
        if(count % 2 != 0){
            count--;
        }
        //临时初始化元素
        Map<Object, Integer> map = new HashMap<>();
        map.put("a", count);
        map.put("b", count);
        map.put("c", count);
        map.put("d", WIDTH * HEIGHT - count * (KIND - 1));
        Set<Object> set = map.keySet();
        Object[] objects = set.toArray();
        for (int i = 1; i <= Panel.WIDTH; i++){
            for (int j = 1; j <= Panel.HEIGHT; j++){
                int temp = (int)Math.round(Math.random() * (Panel.KIND - 1));
                if (map.get(objects[temp]) != 0){
                    this.objects[i][j] = objects[temp];
                    map.put(objects[temp], map.get(objects[temp]) - 1);
                }else {
                    j--;
                }
            }
        }

    }
}

package com.hd.dto;

import java.awt.*;

/**
 * @author chuanhao.zhao@hand-china.com
 * @version 1.0
 * @name Panel
 * @description 点类，即每个元素
 * @date 2018/10/9
 */
public class MyPoint extends Point {
    public int step;  //起点到该点的步数
    public int change;  //起点到该点的折点数量
    public int direction;  //该点现在所走的方向，0123分别代表上下左右
    public MyPoint prev;  //指向上一个点
    public static boolean flag = false;  // flag为true时证明点可连接
    public static int[][] next = {{-1,0},{1,0},{0,-1},{0,1}};

    public MyPoint(){
        step = 0;
        change = -1;
        direction = -1;
        prev = null;
    }

}

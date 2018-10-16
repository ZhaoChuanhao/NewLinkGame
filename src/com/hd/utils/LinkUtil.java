package com.hd.utils;

import com.hd.dto.MyPoint;
import com.hd.dto.Panel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chuanhao.zhao@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2018/10/10
 */
public class LinkUtil {

    public static List<MyPoint> list = new ArrayList<>();

    /**
     * 判断两点能否相连
     * @param objects  棋盘
     * @param point1  点1
     * @param point2  点2
     * @return
     */
    public static boolean link(Object[][] objects, MyPoint point1, MyPoint point2){

        if(point1.x <= 0 || point1.x > Panel.WIDTH || point1.y <= 0 || point1.y > Panel.HEIGHT
                || point2.x <= 0 || point2.x > Panel.WIDTH || point2.y <= 0 || point2.y > Panel.HEIGHT){
            System.out.println("坐标输入不正确！");
            return false;
        }
        if(point1.x == point2.x && point1.y == point2.y){
            System.out.println("不能输入同一个元素！");
            return false;
        }
        if(!objects[point1.x][point1.y].equals(objects[point2.x][point2.y])){
            System.out.println("两个元素不相同！");
            return false;
        }

        if(shortestPath(objects, point1, point2)){
            printPath(list);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 深度优先遍历最短路径
     * @param objects
     * @param point1
     * @param point2
     * @return
     */
    public static boolean shortestPath(Object[][] objects, MyPoint point1, MyPoint point2){
        if (point1.change > 2){
            return false;
        }
        MyPoint point = (MyPoint) point1.clone();

        //让点开始进行深度优先遍历,i表示方向，0123分别代表上下左右
        for (int i = 0; i < 4; i++){
            //如果已经两折了，则不允许再走其他方向
            if (point.change > 2){
                break;
            }else if (point.change == 2 && point.direction != i){
                continue;
            }

            //开始走
            MyPoint temp = new MyPoint();
            temp.x = point.x + MyPoint.next[i][0];
            temp.y = point.y + MyPoint.next[i][1];
            temp.step = point.step + 1;
            temp.direction = i;
            temp.change = point.change;
            temp.prev = point;

            //如果当前方向不等于该点保存的方向
            if(i != point.direction){
                temp.change = point.change + 1;
            }

            //如果等于点2的位置，返回true
            if (temp.x == point2.x && temp.y == point2.y){
                if (!list.isEmpty()){
                    MyPoint myPoint = list.get(list.size() - 1);
                    if (temp.step < myPoint.step){
                        list.add(temp);
                    }
                }else {
                    list.add(temp);
                }
                MyPoint.flag = true;
                return true;
            }
            //如果走出界了，改变方向，进行下一轮循环
            if (temp.x < 0 || temp.x > Panel.WIDTH || temp.y < 0 || temp.y > Panel.HEIGHT){
                continue;
            }
            //如果被堵住了，改变方向，进行下一轮循环
            if(!" ".equals(objects[temp.x][temp.y]) && objects[temp.x][temp.y] != null){
                continue;
            }

            //如果该点的折点数<=2，递归调用函数
            if (temp.change <= 2){
                shortestPath(objects, temp, point2);
            }
        }
        if (MyPoint.flag == false){
            return false;
        }
        return true;
    }

    /**
     * 打印路径
     * @param list
     */
    public static void printPath(List<MyPoint> list){
        //point就是最短路径连接成功的点
        MyPoint point = list.get(list.size() - 1);
        System.out.println("步数为：" + point.step);
        while (point.prev != null){
            System.out.print(point.x + "," + point.y + "  ");
            point = point.prev;
        }
        System.out.println();
    }
}

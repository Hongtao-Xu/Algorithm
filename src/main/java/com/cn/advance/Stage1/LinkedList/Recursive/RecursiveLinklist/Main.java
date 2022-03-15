package com.cn.advance.Stage1.LinkedList.Recursive.RecursiveLinklist;

/*
 *@program:Algorithm
 *@author: xu-hongtao
 *@Time: 2021/12/7  23:30
 */
public class Main {
    public static void main(String[] args) {
        LinkedListR<Integer> list = new LinkedListR<>();
        for (int i = 0; i < 6; i++)
            list.addFirst(i);

        System.out.println(list);
//        while (!list.isEmpty())
//            System.out.println("removed " + list.removeLast());
//        System.out.println(list.get(3));

//        list.set(list.head, 3, 5);
//        System.out.println(list);
//        System.out.println("是否包含66：" + list.contains(67));
        //测试删除
//        list.removeElement(list.head, 2);
//        System.out.println(list);

        Integer removed = list.remove(0);
        System.out.println(removed);
//        list.removeLast();
//        list.removeLast();
        System.out.println(list);
    }
}

package com.cn.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xm20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 */
public class OOM01 {
    static class OOMObject {

    }

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

}

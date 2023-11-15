package com.libutil.test;

public class Log {

  public static void d(Object o) {
    out(o);
  }

  public static void out(Object o) {
    if (o == null) {
      System.out.println(o);
    } else if (o.getClass().isArray()) {
      byte[] arr = (byte[]) o;
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < arr.length; i++) {
        sb.append("[" + i + "] " + arr[i] + "\n");
      }
      System.out.println(sb);
    } else {
      System.out.println(o);
    }
  }

  public static long timeStart() {
    return System.nanoTime();
  }

  public static void timeEnd(long start) {
    long delta = System.nanoTime() - start;
    if (delta < 1000) {
      Log.d(delta + "ns");
    } else if (delta < 1000000) {
      Log.d((double) delta / 1000 + "us");
    } else {
      Log.d((double) delta / 1000000 + "ms");
    }
  }

}

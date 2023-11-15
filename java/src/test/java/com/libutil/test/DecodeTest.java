package com.libutil.test;

import com.libutil.Base64s;

public class DecodeTest {

  public static void main(String args[]) {
    decodeTest();
    Log.out("----");
    decodeTestJa();
    Log.out("----");
    decodeBytesTest();
  }

  private static void decodeTest() {
    test("", "", "");
    test("", "x", "");
    test("YWJj", "", "abc");
    test("ABkaGw==", "x", "abc");
    test("ABkbGQ==", "xyz", "abc");
    test("ARkbGc4=", "xyz1", "abc");
    test("/iDNzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczL3A==", "A2345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234#", "a");
  }

  private static void decodeTestJa() {
    test("44GC44GE44GG", "", "あいう");
    test("AJv5+pv5/Jv5/g==", "x", "あいう");
    test("AJv4+Jv4/pv4/A==", "xyz", "あいう");
    test("AZv4+NKzt9e0sJ4=", "xyz123456a", "あいう");
  }

  private static void decodeBytesTest() {
    byte[] exp = { (byte) 0x61, (byte) 0x62, (byte) 0x63 };
    test("YWJj", "", exp);
    test("ABkaGw==", "x", exp);
    test("ABkbGQ==", "xyz", exp);
    test("AxkbGc7NzA==", "xyz123", exp);
  }

  private static void test(String s, String k, String expected) {
    String r = Base64s.decodeString(s, k);
    assertEquals(k, expected, r);
  }

  private static void test(String s, String k, byte[] expected) {
    byte[] r = Base64s.decode(s, k);
    Log.out("k=" + k);
    for (int i = 0; i < r.length; i++) {
      Log.out("EXP=" + expected[i] + " : ACTUAL=" + r[i]);
    }
  }

  public static boolean assertEquals(String message, Object expected, Object actual) {
    boolean ok = false;
    String op = "!=";
    String strExpected = "" + expected;
    String strActual = "" + actual;
    if (equals(strExpected, strActual)) {
      ok = true;
      op = "==";
    }
    Log.out("[" + (ok ? "OK" : "NG") + "] " + message + ("".equals(message) ? "" : ": ") + "EXP=" + strExpected + " " + op + " ACTUAL=" + strActual);
    return ok;
  }

  public static boolean equals(String s1, String s2) {
    if ((s1 == null) && (s2 == null)) {
      return true;
    }
    if (s1 == null) {
      return false;
    }
    return s1.equals(s2);
  }

}

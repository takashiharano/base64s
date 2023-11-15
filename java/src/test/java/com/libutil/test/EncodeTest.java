package com.libutil.test;

import com.libutil.Base64s;

public class EncodeTest {

  public static void main(String args[]) {
    encodeTest();
    Log.out("----");
    encodeTestJa();
    Log.out("----");
    encodeBytesTest();
  }

  private static void encodeTest() {
    test("", "", "");
    test("", "x", "");
    test("abc", "", "YWJj");
    test("abc", "x", "ABkaGw==");
    test("abc", "xyz", "ABkbGQ==");
    test("abc", "xyz1", "ARkbGc4=");
    test("a", "A2345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234#", "/iDNzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczLysnIx8bPzs3My8rJyMfGz87NzMvKycjHxs/OzczL3A==");
  }

  private static void encodeTestJa() {
    test("あいう", "", "44GC44GE44GG");
    test("あいう", "x", "AJv5+pv5/Jv5/g==");
    test("あいう", "xyz", "AJv4+Jv4/pv4/A==");
    test("あいう", "xyz123456a", "AZv4+NKzt9e0sJ4=");
  }

  private static void encodeBytesTest() {
    byte[] b = { (byte) 0x61, (byte) 0x62, (byte) 0x63 };
    test(b, "", "YWJj");
    test(b, "x", "ABkaGw==");
    test(b, "xyz", "ABkbGQ==");
    test(b, "xyz1", "ARkbGc4=");
  }

  private static void test(String s, String key, String expected) {
    String r = Base64s.encode(s, key);
    assertEquals(key, expected, r);
  }

  private static void test(byte[] b, String key, String expected) {
    String r = Base64s.encode(b, key);
    assertEquals(key, expected, r);
  }

  public static boolean assertEquals(Object expected, Object actual) {
    return assertEquals("", expected, actual);
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



public class Test {

  static int default_size = 10 * 1024 * 1024 - 1;
  static int default_runs = 30;

  public static void main(String[] args) {
    System.out.println("Testing array performance:");

    System.out.println(" * int array reverse");
    testIntArrayReverse(ArrayOps.INT, 0l, default_size, default_runs);

    System.out.println(" * miniboxed array reverse (before hitting double)");
    testMboxedArrayReverse(ArrayOps.INT, 0l, default_size, default_runs);

    System.out.println(" * miniboxed array reverse (after hitting double)");
    testMboxedArrayReverse(ArrayOps.DOUBLE, 0l, 1000, 1);
    testMboxedArrayReverse(ArrayOps.FLOAT, 0l, 1000, 1);
    testMboxedArrayReverse(ArrayOps.INT, 0l, default_size, default_runs);

    return;
  }

  static void testIntArrayReverse(byte tag, long value, int size, int runs) {
    // step 1: Creating the array + populating it
    IntArrayBuffer arr = new IntArrayBuffer();
    int i = 0;
    while (i < size) {
      arr.append(value);
      i += 1;
    }

    // step 2: Benchmark speed
    int counter = 0;
    while (counter < runs) {
      long start = System.currentTimeMillis();
      arr.reverse();
      long stop = System.currentTimeMillis();
      System.out.println("     -> reverse took " + (stop-start) + " ms (tag = " + tag + ")");
      counter += 1;
    }
  }

  static void testMboxedArrayReverse(byte tag, long value, int size, int runs) {
    // step 1: Creating the array + populating it
    MiniboxedArrayBuffer arr = new MiniboxedArrayBuffer(tag);
    int i = 0;
    while (i < size) {
      arr.append(value);
      i += 1;
    }

    // step 2: Benchmark speed
    int counter = 0;
    while (counter < runs) {
      long start = System.currentTimeMillis();
      arr.reverse();
      long stop = System.currentTimeMillis();
      System.out.println("     -> reverse took " + (stop-start) + " ms (tag = " + tag + ")");
      counter += 1;
    }
  }

}
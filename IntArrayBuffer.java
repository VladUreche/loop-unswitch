
public class IntArrayBuffer {

  private int size;
  private int alloc;
  private int[] array;

  public IntArrayBuffer() {
    size = 0;
    alloc = 8;
    array = new int[alloc];
  }

  public void append(long value) {
    if (size < alloc) {
      array[size] = (int)value;
      size += 1;
    } else {
      int n_alloc = alloc * 2;
      int[] n_array = new int[n_alloc];
      System.arraycopy(array, 0, n_array, 0, alloc);
      array = n_array;
      alloc = n_alloc;
      append(value);
    }
  }

  public void reverse() {
    int idx = 0;
    int xdi = size;

    // needs loop unswitching:
    while (idx < xdi) {
      long tmp1 = array[idx];
      long tmp2 = array[xdi];
      array[idx] = (int)tmp1;
      array[xdi] = (int)tmp2;
      idx += 1;
      xdi -= 1;
    }
  }
}
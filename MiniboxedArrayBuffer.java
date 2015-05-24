
public class MiniboxedArrayBuffer {

  private byte tag;
  private int size;
  private int alloc;
  private Object array;

  public MiniboxedArrayBuffer(byte tag) {
    this.tag = tag;
    size = 0;
    alloc = 8;
    array = ArrayOps.array_new(tag, alloc);
  }

  public void append(long value) {
    if (size < alloc) {
      ArrayOps.array_update(tag, array, size, value);
      size += 1;
    } else {
      int n_alloc = alloc * 2;
      Object n_array = ArrayOps.array_new(tag, n_alloc);
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
      long tmp1 = ArrayOps.array_get(tag, array, idx);
      long tmp2 = ArrayOps.array_get(tag, array, xdi);
      ArrayOps.array_update(tag, array, idx, tmp2);
      ArrayOps.array_update(tag, array, xdi, tmp1);
      idx += 1;
      xdi -= 1;
    }
  }
}
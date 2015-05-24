

public class ArrayOps {

  final static byte BOOLEAN = 0;
  final static byte BYTE    = 1;
  final static byte CHAR    = 2;
  final static byte SHORT   = 3;
  final static byte INT     = 4;
  final static byte LONG    = 5;
  final static byte FLOAT   = 6;
  final static byte DOUBLE  = 7;

  final static Object array_new(byte tag, int size) {
    switch (tag) {
      case BOOLEAN:
        return new boolean[size];
      case BYTE:
        return new byte[size];
      case CHAR:
        return new char[size];
      case SHORT:
        return new short[size];
      case INT:
        return new int[size];
      case LONG:
        return new long[size];
      case FLOAT:
        return new float[size];
      case DOUBLE:
        return new double[size];
      default:
        throw new RuntimeException("Incorrect tag");
    }
  }

  final static int array_length(byte tag, Object array) {
    switch (tag) {
      case BOOLEAN:
        return ((boolean[])array).length;
      case BYTE:
        return ((byte[])array).length;
      case CHAR:
        return ((char[])array).length;
      case SHORT:
        return ((short[])array).length;
      case INT:
        return ((int[])array).length;
      case LONG:
        return ((long[])array).length;
      case FLOAT:
        return ((float[])array).length;
      case DOUBLE:
        return ((double[])array).length;
      default:
        throw new RuntimeException("Incorrect tag");
    }
  }

  final static long array_get(byte tag, Object array, int idx) {
    switch (tag) {
      case BOOLEAN:
        if (((boolean[])array)[idx] == false)
          return 1l;
        else
          return 0l;
      case BYTE:
        return ((byte[])array)[idx];
      case CHAR:
        return ((char[])array)[idx];
      case SHORT:
        return ((short[])array)[idx];
      case INT:
        return ((int[])array)[idx];
      case LONG:
        return ((long[])array)[idx];
      case FLOAT:
        return Float.floatToIntBits(((float[])array)[idx]);
      case DOUBLE:
        return Double.doubleToLongBits(((double[])array)[idx]);
      default:
        throw new RuntimeException("Incorrect tag");
    }
  }

  final static void array_update(byte tag, Object array, int idx, long value) {
    switch (tag) {
      case BOOLEAN:
        if (value == 0l)
          ((boolean[])array)[idx] = false;
        else
          ((boolean[])array)[idx] = true;
        return;
      case BYTE:
        ((byte[])array)[idx] = (byte)value;
        return;
// disabled to allow loop unswitching, otherwise we get:
//
//  1607    9 %           MiniboxedArrayBuffer::reverse @ 7 (76 bytes)
//                  x@ 21   ArrayOps::array_get (155 bytes)   inline (hot)
//                  x@ 34   ArrayOps::array_get (155 bytes)   inline (hot)
//                  x@ 50   ArrayOps::array_update (196 bytes)   already compiled into a big method
//                  x@ 63   ArrayOps::array_update (196 bytes)   already compiled into a big method
//      case CHAR:
//        ((char[])array)[idx] = (char)value;
//        return;
//      case SHORT:
//        ((short[])array)[idx] = (short)value;
//        return;
      case INT:
        ((int[])array)[idx] = (int)value;
        return;
      case LONG:
        ((long[])array)[idx] = (long)value;
        return;
      case FLOAT:
        ((float[])array)[idx] = Float.intBitsToFloat((int)value);
        return;
      case DOUBLE:
        ((double[])array)[idx] = Double.longBitsToDouble(value);
        return;
      default:
        throw new RuntimeException("Incorrect tag");
    }
  }
}
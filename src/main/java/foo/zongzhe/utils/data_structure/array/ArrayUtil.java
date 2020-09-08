package foo.zongzhe.utils.data_structure.array;


public class ArrayUtil {

    public static void printArray(int[][] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                System.out.print(inputArray[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Initialize Array with default values
     *
     * @param array        Array to be initialized
     * @param defaultValue default value
     */
    public static int[][] initArray(int[][] array, int defaultValue) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = defaultValue;
            }
        }
        return array;
    }

    /**
     * Calculates "size" of an array, generally it is row * col
     *
     * @param array     Array to be calculate
     * @param arrayDesc Array Description
     */
    public static void printArraySize(int[][] array, String arrayDesc) {
        int row = array.length;
        int col = array[0].length;
        System.out.println(String.format("Size of %s is (%d * %d =) %d ", arrayDesc, row, col, (row * col)));
    }
}

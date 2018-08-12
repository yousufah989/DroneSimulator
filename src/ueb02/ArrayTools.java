package ueb02;

/**
 * Helping methods for analyzing an array.
 * @author klk
 */
public class ArrayTools {
    /**
     * this method determines if a certain {@code value} is containing in a
     * given {@code array} and returns the position of the first occurrence.
     *
     * @param array the given array
     * @param value the value to look for
     * @return the position of the first occurrence of the value, if not then -1
     */
    public static int containsAt(int[] array, int value) {
        //TODO insert code that makes sense
        
        for(int i = 0; i < array.length; i++)
        {
            if (array[i] == value)
            {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * this method deletes in a given array the element at position {@code idx}
     *
     * @param array the given array
     * @param idx the position to delete at
     * @return a new array not containing the {@code idx}-th value, null if
     * invalid params
     */
    public static int[] deleteElementAt(int[] array, int idx) {
        //TODO insert code that makes sense
        int[] new_array = new int[array.length - 1];
        int counter = 0;
        
        //System.out.println("IN ArrayTools");
        for(int i = 0; i < array.length; i++)
        {
            //System.out.println("IN - FOR ArrayTools");
            
            if(i == idx)
            {
                continue;
            }
            else
            {
                new_array[counter] = array[i];
            }
            counter++;
        }
        
        //System.out.println("length of new array : " + new_array.length);
        
        if(counter == new_array.length - 1)
        {
            //System.out.println("Length of the array : " + new_array.length);
            /*if (counter == 0)
            {
                new_array = new int[1];
                new_array[0] = -1;
            }*/
            //System.out.println("ArrayTools - length : " + new_array.length);
            return new_array;
        }
        return null;
    }

    /**
     * this method inserts in a given array the element at position {@code idx}
     *
     * @param array the given array
     * @param idx the position to insert at
     * @param value the inserting value
     * @return a new array containing at {@code idx} the given {@code value},
     * null if invalid params
     */
    public static int[] insertElementAt(int[] array, int idx, int value) {
        //TODO insert code that makes sense
        int[] new_array = new int[array.length + 1];
        int counter = 0;
        
        if((idx <= (array.length - 1)) && (idx >= (array.length - 1)))
        {
            for(int i = 0; i < new_array.length; i++)
            {
                if(i == idx)
                {
                    new_array[i] = value;
                    continue;
                }
                new_array[i] = array[counter];
                counter++;
            }
            return new_array;
        }
        
        return null;
    }
    
    /**
     * Gets the length of the longest array in given array.
     * @param array 2-dimensional array to look in
     * @return length of longest array in given array, 
     *         -1 if array is null or length of array is 0
     */
    public static int getLengthOfLongestArray(int[][] array) {
        //TODO insert code that makes sense
        int len = 0;
        if(array.length != 0)
        {
            for (int[] array1 : array) 
            {
                len = array1.length > len ? array1.length : len;
            }
            return len;
        }
        
        return -1;
    }
}

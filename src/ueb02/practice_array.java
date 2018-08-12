/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ueb02;

/**
 *
 * @author Hitesh Mohite
 */
public class practice_array {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        // TODO code application logic here
        int[][][] MAP = {
            {{1, 1, 3, 3, 4, 4, 4, 4}, {}, {}, {2, 2}, {}, {}, {}}, //first column
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {0}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {3, 4}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}},
            {{}, {}, {}, {}, {}, {}, {}}
        };
        
        int[][][] ORDER_SERIES = {
            { //series 0
                {1, 2, 0, 1}, //customer at (1,2) orders product 0, 1 times
            },
            { //series 1
                {1, 2, 0, 1}, //customer at (1,2) orders product 0, 1 times
                {1, 2, 1, 1}, //customer at (1,2) orders product 1, 1 times
                {8, 5, 3, 3} //customer at (8,5) orders product 3, 3 times
            },
            { //series 2
                {7, 4, 4, 5}, //customer at (7,4) orders product 4, 5 times
                {0, 6, 0, 1} //customer at (0,6) orders product 0, 1 times
            },
            { //series 3
                {7, 4, 0, 5} //customer at (7,4) orders product 0, 5 times
            }
        };
        int[][] temp = ORDER_SERIES[0];
        System.out.println(MAP[6][3].length);
        //MAP[6][3] = ;
        System.out.println(MAP[6][3].length);
    }
    
}

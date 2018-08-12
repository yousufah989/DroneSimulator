package ueb02;

/**
 * Simulating the delivery of an order-series by a drone.
 * @author Capt'n Mo himself
 */
public class Grpxx_ueb02 {

    /**
     * This program calculates for every given order scenario the end-snapshot
     * and prints the results to stdout.
     *
     * @param args the command line arguments (not used yet)
     */
    public static void main(String[] args) {

        System.out.println("This is main class of this program!!!");
        for (int no = 0; no < Data.getNoOfSeries(); no++) {
            int[][] order = Data.getOrderSeries(no);
            Analyze.resetToOrigState();
            System.out.println("***************************************");
            System.out.println("Warehouses (initial):");
            Analyze.printCurrentState();
            System.out.println("Orders of series " + no + ":");
            Analyze.transportOrdersOfOneSeries(order);
            Analyze.printCurrentState();
        }
    }

}

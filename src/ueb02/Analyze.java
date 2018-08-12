package ueb02;

import java.util.Arrays;
import static ueb02.ArrayTools.*;

/**
 * Methods to manage the transport from products from warehouses to customers by drone.
 * 
 * @author Capt'n Mo, klk
 */
public class Analyze {
    
//<editor-fold defaultstate="collapsed" desc="constants">
    /** signs to show for printing the map. */
    //TODO insert code that makes sense
    
    /** position of service-station of the drone {@code POS_SERVICE}*/
    //TODO insert code that makes sense
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="attributes">
    /**
     * the amount of units the drone flew. Default is 0. {@code units}
     */
    //TODO insert code that makes sense
    private static int flown_distance = 0;
    /**
     * the current map working on. Default is the Map from Data. {@code map}
     */
    //TODO insert code that makes sense
    private static final int[][][] temp_map = Data.getMap().clone();
    private static int[][][] updated_map = new int[][][]{};
    /**
     * the current position of the Drone. Default is POS_SERVICE. {@code posDrone}
     */
    //TODO insert code that makes sense
    private static int[] current_position_of_drone = new int[2];
    
    //private static int[][] warehouse_positions;
    private static int[] dimensions;
    private static int[][][] warehouse_location_map;
    private static int[][][] customer_location_map;
    private static int[] column_width;
    
    //private static int[] box_dimensions = {0,0};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="helping methods">
    /**
     * Calculate the Euclidean distance between two points.
     *
     * @param pos1 the first point
     * @param pos2 the second point
     * @return the Euclidean distance between those two points,
     *         Integer.MAX_VALUE if invalid params
     */
    private static int calcDistanceBetween(int[] pos1, int[] pos2) {
        //Math.ceil(), Math.sqrt(), Math.pow() may be used
        //TODO insert code that makes sense
        int distance = (int) Math.ceil(Math.sqrt(Math.pow((pos1[0] - pos2[0]), 2) + Math.pow((pos1[1] - pos2[1]), 2)));
        return distance;
    }
    
    /**
     * Checks if the given position is valid in the map.
     *
     * @param pos
     * @return true, if pos is a valid position in the map
     */
    private static boolean isValidPosition(int[] pos) {
        //TODO insert code that makes sense
        int[] box_dimensions =  Data.getBoxDimensions();
        if ((pos[0] >= 0 && pos[0] < box_dimensions[0]) && (pos[1] >= 0 && pos[1] < box_dimensions[1]))
        {
            return true;
        }
        return false;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="working methods">
    /**
     * Flies the drone from current position to a given position via the
     * Euclidean distance. Prints the destination and flewn distance.
     * Changes the attributes {@code units} and {@code posDrone}.
     * If the given position isn_t valid, a message on serr is shown.
     *
     * @param pos the position to fly to
     * @return the distance flewn by the drone; if the drone cannot fly -1 is returned
     */
    private static int flyDroneTo(int[] pos) {
        //TODO insert code that makes sense
        int distance = 0;
        if (isValidPosition(pos))
        {
            distance = calcDistanceBetween(current_position_of_drone, pos);
            current_position_of_drone = pos;
            System.out.println("fly drone to " + pos[0] + "/" + pos[1] +" distance " + distance);
            return distance;
        }
        else
        {
            System.err.println("fly drone to invalid position distance -1");
            return -1; 
        }
    }
    
    /**
     * Transports one product of an order to a specified position with the drone.
     * Flies drone to from, collects count of ordered products at from and
     * flies drone to to. If there aren't enough products at from, the
     * remaining count of the order is given as result.
     *
     * @param from the position of the warehouse to get the product at
     * @param to the position to transport to
     * @param product product to transport
     * @param count count of products to transport
     * @return count of products still to transport
     */
    private static int transportSameProducts(int[] from, int[] to, int product, int count) {
        //TODO insert code that makes sense
        int[] products_at_warehouse = {}, products_at_customer = {};
        int current_products_at_from = 0, current_products_at_to = 0;
        
        if(isValidPosition(from) && isValidPosition(to))
        {
            products_at_warehouse = updated_map[from[0]][from[1]];
            products_at_customer = updated_map[to[0]][to[1]];
            
            if(warehouse_location_map[from[0]][from[1]][0] == 1)
            {
                for(int i = 0; i < products_at_warehouse.length; i++)
                {
                    if(products_at_warehouse[i] == product)
                    {
                        if(current_products_at_from < count)
                        {
                            //System.out.println("IN Analyze");
                            //System.out.println("Analyze - length BEFORE : " + updated_map[from[0]][from[1]].length);
                            updated_map[from[0]][from[1]] = ArrayTools.deleteElementAt(updated_map[from[0]][from[1]], i);
                            if(updated_map[from[0]][from[1]] == null)
                            {
                                updated_map[from[0]][from[1]] = new int[0]; 
                                //System.out.println("TRUE");
                            }
                            //System.out.println("Analyze - length AFTER : " + updated_map[from[0]][from[1]].length);
                            //updated_map[to[0]][to[1]] = ArrayTools.insertElementAt(updated_map[to[0]][to[1]], i, product);
                            //customer_location_map[to[0]][to[1]][0] = 1;
                        }
                        current_products_at_from++;
                    }
                }
            }
            else
            {
                System.err.println("This is not a warehouse : [" + from[0] + ", "+ from[1] +"]");
            }
            
            /*if(warehouse_location_map[to[0]][to[1]][0] == 0 && (customer_location_map[to[0]][to[1]][0] == 0 || customer_location_map[to[0]][to[1]][0] == 1))
            {
                customer_location_map[to[0]][to[1]][0] = 1;
                for(int j = 0; j < products_at_customer.length; j++)
                {
                    if(products_at_customer[j] == product)
                    {
                        current_products_at_to++;
                    }
                }
            }
            else
            {
                System.err.println("check your customer : [" + to[0] + ", "+ to[1] +"]");
            }*/
            
            if ((count - current_products_at_to) <= current_products_at_from)
            {
                return 0;
            }
            else
            {
                //System.out.println("count : " + (count - current_products_at_to - current_products_at_from));
                return count - current_products_at_to - current_products_at_from;
            }
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * Determines the nearest warehouse for a specified position and product.
     *
     * @param pos the starting point
     * @param product the product
     * @return the nearest (Euclidean distance) warehouse position having the
     * {@code product}; null if there is no warehouse having the product
     */
    private static int[] findNearestWarehouse(int[] pos, int product) {
        //TODO insert code that makes sense
        int[] box_dimensions = new int[2];
        int[] temp = new int[2];
        //int[] temp_1 = new int[]{};
        int[] nearest = new int[2];
        //nearest = null;
        int distance = 0, counter = 0;
        boolean present = false;
        
        box_dimensions = Data.getBoxDimensions();
        for(int i = 0; i < box_dimensions[0]; i++)
        {
            for(int j = 0; j < box_dimensions[1]; j++)
            {
                temp[0] = i;
                temp[1] = j;
                present = false;
                //if (temp[0] != pos[0] || temp[1] != pos[1])
                if(true)
                {
                    if(updated_map[i][j].length > 0)
                    {
                        
                        //temp_1 = updated_map[i][j];
                        //check if a product is present
                        for(int k = 0; k < updated_map[i][j].length; k++)
                        {
                            if (updated_map[i][j][k] == product)
                            {
                                //System.out.println("product on map: " + updated_map[i][j][k] + " product present: " + product);
                                present = true;
                                break;
                            }
                        }
                        
                        //there will be a conflict in future where this location
                        //on map might be a customer and cannot be treated like a
                        //warehouse.
                        if (present)
                        {
                            if (counter == 0)
                            {
                                distance = calcDistanceBetween(pos, temp);
                                //prev_distance = distance;
                                nearest[0] = i;
                                nearest[1] = j;
                                counter++;
                            }
                            else
                            {
                                if(calcDistanceBetween(pos, temp) < distance)
                                {
                                    //System.out.println("condition true");
                                    distance = calcDistanceBetween(pos, temp);
                                    nearest[0] = i;
                                    nearest[1] = j;
                                    counter++;
                                }
                            }    
                        }    
                    }
                }
            }
        }
        current_position_of_drone = nearest;
        //System.out.println("position of drone: " + current_position_of_drone[0] + " " + current_position_of_drone[1]);
        //System.out.println("position of drone: " + nearest[0] + " " + nearest[1]);
        return current_position_of_drone;
    }
    
    /**
     * Transports an order-series by the drone.
     * Process every order of the series. Prints the values of the order.
     * Searches for the nearest warehouse with the product, 
     * transports the ordered number of the product to the target adress.
     * If the first detected warehouse doesn_t hold enough of the product,
     * the next warehouse with the product has to be used.
     * If there is no warehouse with the product, a message on serr is printed.
     * After all orders have been delivered, the drone flies to the service-station.
     *
     * @param orders the order-series working on
     * @return false if there is no warehouse with the product/not enough of the product; true otherwise
     */
    public static boolean transportOrdersOfOneSeries(int[][] orders) {
        //TODO insert code that makes sense
        int total_delivery_orders = orders.length, products_left = 0, distance = 0;
        int[] to = new int[2];
        int[] temp = new int[2];
        int[] origin = new int[2];
        temp = current_position_of_drone;
        //System.out.println("total orders: " + total_delivery_orders);
        for(int i = 0; i < total_delivery_orders; i++)
        {
            int[] current_order = orders[i];
            //System.out.println(current_order.length);
            to[0] = current_order[0];
            to[1] = current_order[1];
            System.out.println("deliver " + current_order[3] + " of product " + current_order[2] + " to (" + to[0] + "/" + to[1] + ")");
            current_position_of_drone = findNearestWarehouse(current_position_of_drone, current_order[2]);
            distance = calcDistanceBetween(temp, current_position_of_drone);
            System.out.println("fly drone to " + current_position_of_drone[0] + "/" + current_position_of_drone[1] + " distance " + distance);
            flown_distance += distance;
            temp = current_position_of_drone;
            distance = calcDistanceBetween(current_position_of_drone, to);
            System.out.println("fly drone to " + to[0] + "/" + to[1] + " distance " + distance);
            flown_distance += distance;
            products_left = transportSameProducts(current_position_of_drone, to, current_order[2], current_order[3]);
            //System.out.println(" in FOR product left : " + products_left);
            current_position_of_drone = to;
            temp = current_position_of_drone;
            
            //System.out.println("in FOR");
            
            
            
            while(products_left != 0)
            {
                //System.out.println("in WHILE");
                current_position_of_drone = findNearestWarehouse(current_position_of_drone, current_order[2]);
                distance = calcDistanceBetween(temp, current_position_of_drone);
                System.out.println("fly drone to " + current_position_of_drone[0] + "/" + current_position_of_drone[1] + " distance " + distance);
                flown_distance += distance;
                temp = current_position_of_drone;
                distance = calcDistanceBetween(current_position_of_drone, to);
                System.out.println("fly drone to " + to[0] + "/" + to[1] + " distance " + distance);
                flown_distance += distance;
            
                products_left = transportSameProducts(current_position_of_drone, to, current_order[2], products_left);
                //System.out.println(" in WHILE product left : " + products_left);
                current_position_of_drone = to;
                temp = current_position_of_drone;
            }
            
            if ((products_left == 0)  && i == (total_delivery_orders - 1))
            {
                distance = calcDistanceBetween(origin, current_position_of_drone);
                flown_distance += distance;
                //System.out.println("in IF");
                System.out.println("fly drone to " + origin[0] + "/" + origin[1] + " distance " + distance);
            }
            else if(i < (total_delivery_orders - 1))
            {
            }
            else
            {
                return false;
            }
        }
        return true;
    }
    
    public static void printTotalDistance() {
        //TODO insert code that makes sense
        
        if(flown_distance != 0)
        {
            System.out.println("Drone now at 0/0 flew " + flown_distance + " units");
        }
    }
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Output">
    /**
     * Prints the current map to sout.
     * The signs declared in constants at begin of the class are used to 
     * visualize the cells.
     */
    public static void printCurrentState() {
        //TODO insert code that makes sense
        int[] dimension = new int[]{};
        int[] temp = new int[]{};
        int[] temp_width;
        int width = 0, counter = 0;
        String indent = "";
        //System.out.println("here i am");
        //System.out.println(Data.getBoxDimensions());
        dimension = Data.getBoxDimensions();
        column_width = new int[dimension[0]];
        temp_width = new int[dimension[0]];
        
        for(int i = 0; i < dimension[0]; i++)
        {
            for(int j = 0; j < dimension[1]; j++)
            {
                temp = updated_map[i][j];
                /*if(temp == null)
                {
                    temp_width[j] = 0;
                }
                else
                {
                    temp_width[j] = temp.length;
                }*/
                
                //System.out.println("The current position : " + i + " " + j);
                /*if(temp.length == 1 && temp[0] == -1)
                {
                    temp_width[j] = 0;
                }
                else
                {
                    temp_width[j] = temp.length;
                }*/
                temp_width[j] = temp.length;
                column_width[i] = temp_width[j] > column_width[i]? temp_width[j] : column_width[i];
            }
        }
        
        /*for(int i = 0; i < column_width.length; i++)
        {
            System.out.println(column_width[i]);
        }*/
        
        for(int i = 0; i < dimension[1]; i++)
        {
            for(int j = 0; j < dimension[0]; j++)
            {
                temp = updated_map[j][i];
                indent = "";
                counter = 0;
                width = getPrintWidthPerColumn(j);
                //System.out.print(width);
                    
                if(temp.length > 0)
                {
                    indent = "";
                    while(counter < width)
                    {
                        if(counter < temp.length)
                        {
                            indent += temp[counter] + "";
                        }
                        else
                        {
                            indent += " ";
                        }
                        counter++;
                    }
                    
                    //if(warehouse_location_map[j][i][0] == 1)
                    //{
                        indent = "W" + indent + " ";
                        System.out.print(indent);
                    /*}
                    else if(customer_location_map[j][i][0] == 0)
                    {
                        indent = "C" + indent + " ";
                        System.out.print(indent);
                    }*/
                }
                else if(temp.length == 0 && warehouse_location_map[j][i][0] == 1)
                {
                    indent = "W" + indent + "  ";
                    System.out.print(indent);
                }
                else if(temp.length == 0)
                {
                    while(counter < (width))
                    {
                        indent += " ";
                        counter++;
                    }
                    indent = "E" + indent + " ";
                    System.out.print(indent);
                }
            }
            System.out.println();
        }
        printTotalDistance();
    }

    /**
     * Determines the maximum length of a given
     * {@code column} in the map-array. Used for nice output only.
     *
     * @param column the given column
     * @return the maximum of the length of all cells in the given
     * {@code column} plus 1 (for the sign of one's cell)
     */
    private static int getPrintWidthPerColumn(int column) {
        //TODO insert code that makes sense
        return column_width[column] + 1;
    }
    //</editor-fold>

    /**
     * resetting every value to its default
     */
    public static void resetToOrigState() {
        //TODO insert code that makes sense
        updated_map = new int[][][]{};
        updated_map =  temp_map.clone();
        //System.out.println("length of 4 6: " + updated_map[4][6].length);
        dimensions = null;
        dimensions = Data.getBoxDimensions().clone();
        flown_distance = 0;
        current_position_of_drone[0] = 0;
        current_position_of_drone[1] = 0;
        //warehouse_positions = new int[][]{};
        warehouse_location_map = null;
        warehouse_location_map = new int[dimensions[0]][dimensions[1]][1];
        customer_location_map = null;
        customer_location_map = new int[dimensions[0]][dimensions[1]][1];
        loadWarehouseLocations();
    }

    private static void loadWarehouseLocations() {
        
        int [] box_dimensions = Data.getBoxDimensions().clone();
        
        //int counter = 0;
        
        for(int i = 0; i < box_dimensions[0]; i++)
        {
            for(int j = 0; j < box_dimensions[1]; j++)
            {
                //System.out.println("[" + i + " " + j + "]");
                if (updated_map[i][j].length > 0)
                {
                    //warehouse_positions[counter][0] = i;
                    //warehouse_positions[counter][1] = j;
                    warehouse_location_map[i][j][0] = 1;
                    //counter++;
                }
                else
                {
                    warehouse_location_map[i][j][0] = 0;
                }
                customer_location_map[i][j][0] = 0;
            }
        }
    }
}

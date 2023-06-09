package Order;
import java.util.ArrayList;

public class Order {
    //temporary storage for current customer order details
    //used for displaying order receipt in receipt window
    private static ArrayList<Integer> quantity_order = new ArrayList<Integer>();
    private static int current_customer_no;
    private static ArrayList<String> food_order = new ArrayList<String>();
    private static ArrayList<Integer> total_price_order = new ArrayList<Integer>();
    private static ArrayList<Integer> food_id = new ArrayList<Integer>();
    private static ArrayList<Integer> stock_list = new ArrayList<>();
    
    //getters and setters
    public ArrayList<Integer> get_quantity_order(){
            return quantity_order;
    }
    public ArrayList<String> get_food_order(){
        return food_order;
    }
    public ArrayList<Integer> get_total_price_order(){
        return total_price_order;
    }
    public ArrayList<Integer> get_food_id(){
        return food_id;
    }
    public int get_food_id(int i){
        return food_id.get(i);
    }
    public int get_quantity_order(int i){
         return quantity_order.get(i);
    }
    public String get_food_order(int i){
        return food_order.get(i);
    }
    public int get_total_price_order(int i){
        return total_price_order.get(i);
    }

    public static ArrayList<Integer> getStock_list() {
        return stock_list;
    }
    
    public static int getCurrent_customer_no() {
        return current_customer_no;
    }
    
    public static void setCurrent_customer_no(int current_customer_no) {
        Order.current_customer_no = current_customer_no;
    }
    
    //to clear data in arraylists 
    //used once the customer order window starts
    public void clean(){
        food_order.clear();
        quantity_order.clear();
        total_price_order.clear(); 
    }
    
    //displaying total value in receipt 
    public int get_total_of_total_price(){
        int total = 0;
        for(int number : total_price_order){
            total += number;
        }
        return total;
    }
    

}

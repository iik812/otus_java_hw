package homework;


import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    Deque<Customer> dequeCustomer = new ArrayDeque<>();

    public void add(Customer customer) {
        dequeCustomer.push(customer);
    }

    public Customer take() {
        return dequeCustomer.pop();
    }

}

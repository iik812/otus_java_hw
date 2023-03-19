package homework;


import java.util.*;

public class CustomerService {

    TreeMap<Customer, String> customerMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> getSmallerCustomerMap = customerMap.firstEntry();
        return getSmallerCustomerMap != null ? Map.entry(getSmallerCustomerMap.getKey().getCopy(), getSmallerCustomerMap.getValue()) : null;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> getNextCustomerMap = customerMap.higherEntry(customer);
        return getNextCustomerMap != null ? Map.entry(getNextCustomerMap.getKey().getCopy(), getNextCustomerMap.getValue()) : null;
    }

    public void add(Customer customer, String data) {
        customerMap.put(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
    }

}

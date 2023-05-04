import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, Integer> consumer = new HashMap<>();
        Bankomat bankomat = new Bankomat();
        consumer.put(2, 1);
        consumer.put(5, 3);
        consumer.put(10, 1);

        bankomat.inputMoney(consumer);
        System.out.println("Balance is: " + bankomat.getBalance());

        System.out.println(bankomat.outputMoney(10));
        System.out.println("Balance is: " + bankomat.getBalance());

        System.out.println(bankomat.outputMoney(4));
        System.out.println("Balance is: " + bankomat.getBalance());

    }
}

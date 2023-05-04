import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Bankomat {
    private Balance balance = new Balance();
    private TreeMap<Integer, Integer> buffer = new TreeMap<>(Collections.reverseOrder());

    public String inputMoney(HashMap<Integer, Integer> money) {
        for (Map.Entry<Integer, Integer> entry : money.entrySet()) {
            if (buffer.containsKey(entry.getKey())) {
                buffer.put(entry.getKey(), buffer.get(entry.getKey()) + entry.getValue());
            } else {
                buffer.put(entry.getKey(), entry.getValue());
            }

        }
        return "The money was deposited successfully";
    }

    public String outputMoney(int countMoney) {
        if (countMoney > getBalance()) return "The amount is greater than the ATM debit";
        if (buffer.containsKey(countMoney)) {
            buffer.put(countMoney, buffer.get(countMoney) - 1);
            return "Money has been issued";
        } else {
            return calculateOptimalSumm(countMoney);
        }
    }

    private String calculateOptimalSumm(int countMoney) {
        int remains = 0;
        HashMap<Integer, Integer> outputResult = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : buffer.entrySet()) {
            while ((entry.getKey() <= countMoney) && (entry.getValue() > 0)) {
                remains++;
                countMoney = countMoney - entry.getKey();
                entry.setValue(entry.getValue() - 1);
            }
            outputResult.put(entry.getKey(), remains);
            remains = 0;
        }
        if (countMoney > 0) return "Issued: " + outputResult + " the balance cannot be issued: " + countMoney;
        return "Issued: " + outputResult;

    }


    public long getBalance() {
        balance.setCount(0);
        for (Map.Entry<Integer, Integer> entry : buffer.entrySet()) {
            balance.setCount(balance.getCount() + entry.getKey() * entry.getValue());
        }

        return balance.getCount();
    }


}

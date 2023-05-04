import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
//        выдавать запрошенную сумму минимальным количеством банкнот или ошибку, если сумму нельзя выдать.
//        Это задание не на алгоритмы, а на проектирование.
//        Поэтому оптимизировать выдачу не надо.
//        выдавать сумму остатка денежных средств
//        В этом задании больше думайте об архитектуре приложения.
//        Не отвлекайтесь на создание таких объектов как: пользователь, авторизация, клавиатура, дисплей, UI (консольный, Web, Swing), валюта, счет, карта, т.д.
//        Все это не только не нужно, но и вредно!

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

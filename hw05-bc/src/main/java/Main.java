import ru.otus.Ioc;
import ru.otus.TestLoggingInterface;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface logClass = Ioc.createLogClass();
        logClass.calculation();
        logClass.calculation(1);
        logClass.calculation(1, 2);
    }
}

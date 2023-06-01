import java.util.ArrayList;
import java.util.List;

public class Summator {
    private int sum = 0;
    private int prevValue = 0;
    private int prevPrevValue = 0;
    private int sumLastThreeValues = 0;
    private int someValue = 0;

    private final List<Data> listValues = new ArrayList<>();
    private int cunter = 0;
    private int size = 0;

    //!!! сигнатуру метода менять нельзя
    public void calc(Data data) {
        cunter = data.getValue();
        size = listValues.size();
        listValues.add(data);
        if (size % 6_600_000 == 0) {
            listValues.clear();
        }
        sum += cunter;

        sumLastThreeValues = cunter + prevValue + prevPrevValue;

        prevPrevValue = prevValue;
        prevValue = cunter;

        for (var idx = 0; idx < 3; idx++) {
            someValue += (sumLastThreeValues * sumLastThreeValues / (cunter + 1) - sum);
            someValue = Math.abs(someValue) + size;
        }
    }

    public Integer getSum() {
        return sum;
    }

    public Integer getPrevValue() {
        return prevValue;
    }

    public Integer getPrevPrevValue() {
        return prevPrevValue;
    }

    public Integer getSumLastThreeValues() {
        return sumLastThreeValues;
    }

    public Integer getSomeValue() {
        return someValue;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Summator {
    private Integer sum = 0;
    private Integer prevValue = 0;
    private Integer prevPrevValue = 0;
    private Integer sumLastThreeValues = 0;
    private Integer someValue = 0;
    private final List<Data> listValues = new ArrayList<>();
    private Integer cunter = 0;
    private Integer size = 0;

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

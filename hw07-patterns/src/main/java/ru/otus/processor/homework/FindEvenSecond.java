package ru.otus.processor.homework;

import ru.otus.exception.MessagePerSecondException;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class FindEvenSecond implements Processor {

    public FindEvenSecond(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    private final TimeProvider timeProvider;

    @Override
    public Message process(Message message) {
        if (findEvenSecond(timeProvider.getTime().getSecond())) {
            throw new MessagePerSecondException("Cообщение было обработано за четную секунду");
        }
        return message;
    }

    private boolean findEvenSecond(int number) {
        return (number & 1) == 0;
    }
}
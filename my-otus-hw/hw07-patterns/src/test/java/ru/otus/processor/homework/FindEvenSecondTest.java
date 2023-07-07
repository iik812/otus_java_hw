package ru.otus.processor.homework;

import org.junit.jupiter.api.Test;
import ru.otus.exception.MessagePerSecondException;
import ru.otus.model.Message;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FindEvenSecondTest {

    private FindEvenSecond processor;
    private final Message message = new Message.Builder(1L)
            .field1("test")
            .build();

    @Test
    void testProcessSuccess() {
        processor = new FindEvenSecond(() -> LocalTime.ofSecondOfDay(1));
        assertThat(processor.process(message)).isEqualTo(message);
    }

    @Test
    void successTestThrownException() {
        processor = new FindEvenSecond(() -> LocalTime.ofSecondOfDay(2));
        assertThatExceptionOfType(MessagePerSecondException.class)
                .isThrownBy(() -> processor.process(message))
                .withMessage("Cообщение было обработано за четную секунду");
    }
}
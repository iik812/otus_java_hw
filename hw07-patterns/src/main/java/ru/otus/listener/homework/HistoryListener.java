package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.HashMap;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final HashMap<Long, Message> hystoryListener = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        hystoryListener.put(msg.getId(), msg.copy());
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.of(hystoryListener.get(id));
    }
}

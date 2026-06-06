package PubSubSystem.core;

import PubSubSystem.model.Message;

public interface Subscriber {
    void onMessage(Message message);
}

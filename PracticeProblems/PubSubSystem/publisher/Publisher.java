package PubSubSystem.publisher;

import PubSubSystem.core.MessageBroker;

public class Publisher {
    private final String name;
    private final MessageBroker messageBroker;

    public Publisher(String name) {
        this.name = name;
        this.messageBroker = MessageBroker.getInstance();
    }

    public void publish(String topicName, String payload){
        messageBroker.publish(topicName, payload);
    }
}

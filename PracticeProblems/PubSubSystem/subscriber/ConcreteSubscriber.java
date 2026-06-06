package PubSubSystem.subscriber;

import PubSubSystem.core.Subscriber;
import PubSubSystem.model.Message;

public class ConcreteSubscriber implements Subscriber {
    private final String name;

    public ConcreteSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Subscriber " + name + " received message: " + message);
    }
}

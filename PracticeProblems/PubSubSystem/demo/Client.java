package PubSubSystem.demo;

import PubSubSystem.core.MessageBroker;
import PubSubSystem.core.Subscriber;
import PubSubSystem.publisher.Publisher;
import PubSubSystem.subscriber.ConcreteSubscriber;

public class Client {
    public static void main(String[] args) {
        MessageBroker messageBroker = MessageBroker.getInstance();
        messageBroker.createTopic("sports");
        messageBroker.createTopic("tech");


        Subscriber Alice = new ConcreteSubscriber("Alice");
        Subscriber Bob = new ConcreteSubscriber("Bob");
        Subscriber Charlie = new ConcreteSubscriber("Charlie");

        messageBroker.subscribe("sports", Alice);
        messageBroker.subscribe("tech", Alice);
        messageBroker.subscribe("sports", Bob);
        messageBroker.subscribe("tech", Charlie);

        Publisher espn = new Publisher("ESPN");
        Publisher techCrunch = new Publisher("TechCrunch");

        espn.publish("sports", "India won the match");
        techCrunch.publish("tech", "AI is the future");
    }
}

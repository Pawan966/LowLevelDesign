package PubSubSystem.core;

import PubSubSystem.model.Message;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Topic {
    private final String name;
    private final List<Subscriber> subscribers;

    public Topic(String name) {
        this.name = name;
        this.subscribers = new CopyOnWriteArrayList<>();
    }

    public String getName() {
        return name;
    }

    public synchronized void subscribe(Subscriber subscriber) {
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
            System.out.println("Subscriber subscribed to " + name);
        }
    }

    public synchronized void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println("Subscriber unsubscribed from " + name);
    }

    public void notifySubscribers(Message message) {
        // If we want async delivery then just use executor service here
        for(Subscriber subscriber : subscribers){
            try{
                subscriber.onMessage(message);
            }catch (Exception e){
                // there should be retry mechanism here like adding dead letter queue
                System.out.println("Failed to notify subscriber " + e.getMessage());
            }
        }
    }
}

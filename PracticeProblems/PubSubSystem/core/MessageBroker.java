package PubSubSystem.core;

import PubSubSystem.model.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageBroker {
    private static final MessageBroker INSTANCE = new MessageBroker();
    private MessageBroker() {}
    public static MessageBroker getInstance() {
        return INSTANCE;
    }

    private final Map<String, Topic> topics = new ConcurrentHashMap<>();

    public Topic createTopic(String name){
        // putIfAbsent is atomic operation hence thread safe
        topics.putIfAbsent(name, new Topic(name));
        return topics.get(name);
    }

    public Topic getTopic(String name){
        return topics.get(name);
    }

    public void subscribe(String topicName, Subscriber subscriber){
        Topic topic = getOrThrow(topicName);
        topic.subscribe(subscriber);
    }

    public void unsubscribe(String topicName, Subscriber subscriber){
        Topic topic = getOrThrow(topicName);
        topic.unsubscribe(subscriber);
    }

    public void publish(String topicName, String payload){
        Topic topic = getOrThrow(topicName);
        Message message = new Message(topicName, payload);
        topic.notifySubscribers(message);
    }

    private Topic getOrThrow(String topicName) {
        Topic topic = topics.get(topicName);
        if(topic == null){
            throw new IllegalArgumentException("Topic not found: " + topicName);
        }
        return topic;
    }
}

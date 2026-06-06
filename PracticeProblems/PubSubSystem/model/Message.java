package PubSubSystem.model;

public class Message {
    private final String topicName;
    private final String payload;
    private final long timestamp;

    public Message(String topicName, String payload) {
        this.topicName = topicName;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }

    public String getTopicName() {
        return topicName;
    }

    public String getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "topicName='" + topicName + '\'' +
                ", payload='" + payload + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

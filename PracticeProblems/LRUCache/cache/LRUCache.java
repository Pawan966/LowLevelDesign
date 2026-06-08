package LRUCache.cache;

import LRUCache.datastructure.DoublyLinkedList;
import LRUCache.model.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class LRUCache<K, V> {
    private final int capacity;
    private final DoublyLinkedList<K, V> nodeList;
    private final Map<K, Node<K, V>> nodeMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.nodeList = new DoublyLinkedList<>();
        this.nodeMap = new HashMap<>();
    }

    public synchronized V get(K key) {
        if(!nodeMap.containsKey(key)) {
            return null;
        }

        Node<K, V> node = nodeMap.get(key);
        nodeList.moveToHead(node);
        return node.value;
    }

    public synchronized void put(K key, V value) {
        if (nodeMap.containsKey(key)) {
            Node<K, V> node = nodeMap.get(key);
            node.value = value;
            nodeList.moveToHead(node);
        } else {
            if(nodeMap.size() >= capacity) {
                Node<K, V> removed = nodeList.removeAtTail();
                if(removed != null) {
                    nodeMap.remove(removed.key);
                }
            }
            Node<K, V> node = new Node<>(key, value);
            nodeList.addAtHead(node);
            nodeMap.put(key, node);
        }
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(" → ", "[", "]");
        Node<K, V> current = nodeList.getHead().next;
        while(current != nodeList.getTail()) {
            joiner.add(current.key + ":" + current.value);
            current = current.next;
        }
        return joiner.toString();
    }
}

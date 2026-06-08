package LRUCache.datastructure;

import LRUCache.model.Node;

public class DoublyLinkedList<K, V> {
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public DoublyLinkedList() {
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void addAtHead(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void moveToHead(Node<K, V> node) {
        removeNode(node);
        addAtHead(node);
    }

    public Node<K, V> removeAtTail() {
        if(tail.prev == head) {
            return null;
        }

        Node<K, V> node = tail.prev;
        removeNode(node);
        return node;
    }

    public Node<K, V> getHead() {
        return head;
    }

    public Node<K, V> getTail() {
        return tail;
    }
}

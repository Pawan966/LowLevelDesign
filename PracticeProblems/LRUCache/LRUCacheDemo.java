package LRUCache;

import LRUCache.cache.LRUCache;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(4, "D");
        cache.get(2);
        cache.put(5, "E");
        cache.get(3);
    }
}

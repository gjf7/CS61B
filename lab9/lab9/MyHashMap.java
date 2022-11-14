package lab9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Hao Chen
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;
    private static final double MIN_LF = 0.25;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    public MyHashMap(int bucketNum) {
        buckets = new ArrayMap[bucketNum];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hashCode = hash(key);
        return buckets[hashCode].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (loadFactor() >= MAX_LF) {
            resize(buckets.length * 2);
        }
        int hashCode = hash(key);
        size -= buckets[hashCode].size;
        buckets[hashCode].put(key, value);
        size += buckets[hashCode].size;
    }

    private void resize(int capacity) {
        MyHashMap<K, V> newHashMap = new MyHashMap<>(capacity);
        for (ArrayMap<K, V> bucket : buckets) {
            for (K key : bucket) {
                newHashMap.put(key, bucket.get(key));
            }
        }
        this.buckets = newHashMap.buckets;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (ArrayMap<K,V> bucket: buckets) {
            keys.addAll(bucket.keySet());
        }
        return keys;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (size > DEFAULT_SIZE && loadFactor() > MIN_LF) {
            resize(buckets.length / 2);
        }
        int hashCode = hash(key);
        V removedVal = buckets[hashCode].remove(key);
        if (removedVal != null) size -= 1;
        return removedVal;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (value == get(key)) {
            remove(key);
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}

package com.study.implement.design.SystemDesign;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheImplementation {

    public class LRUCacheImplicit extends LinkedHashMap{

        private final int capacity;

        public LRUCacheImplicit(int capacity) {
            super(capacity + 1, 0.75F, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest){
            return size()>capacity;
        }
    }


    public class LRUCacheExplicit{

        public class Node{
            int key, value;
            Node prev, next;
            Node(int key, int value){
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final HashMap<Integer, Node> cache;
        private final Node head,tail;

        public LRUCacheExplicit(int capacity){
            this.capacity = capacity;
            this.cache = new HashMap<>();
            head = new Node(0,0);
            tail = new Node(0,0);

            head.next = tail;
            tail.prev = head;
        }

        public void put(int key, int value){

            if(cache.containsKey(key)){
                Node node = cache.get(key);
                node.value = value;
                moveToHead(node);
            }else{
                Node node = new Node(key,value);
                cache.put(key,node);
                addNode(node);

                if(cache.size() > capacity){
                    Node lru = popTail();
                    cache.remove(lru.key);
                }
            }
        }

        public int get(int key){
            if(!cache.containsKey(key)){
                return -1;
            }

            Node node = cache.get(key);
            moveToHead(node);
            return node.value;
        }

        public void moveToHead(Node node){
            removeNode(node);
            addNode(node);
        }

        public void addNode(Node node){
            node.prev = head;
            node.next = head.next;

            head.next.prev = node;
            head.next = node;
        }

        public void removeNode(Node node){
            Node prev = node.prev;
            Node next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        public Node popTail(){
            Node res = tail.prev;
            removeNode(res);
            return res;
        }

    }
}

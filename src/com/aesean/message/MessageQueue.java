package com.aesean.message;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * MessageQueue
 *
 * @author xl
 * @version V1.0
 * @since 17/03/2017
 */
public class MessageQueue {
    private final Queue<Message> mQueue = new ArrayDeque<>();

    public void post(Message message) {
        synchronized (mQueue) {
            mQueue.notify();
            mQueue.add(message);
        }
    }

    public void remove(Message message) {
        synchronized (mQueue) {
            mQueue.notify();
            mQueue.remove(message);
        }
    }

    public Message next() {
        while (true) {
            synchronized (mQueue) {
                try {
                    if (!mQueue.isEmpty()) {
                        return mQueue.poll();
                    }
                    mQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.aesean.message;

/**
 * Looper
 *
 * @author xl
 * @version V1.0
 * @since 07/02/2017
 */
public class Looper {
    private static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    private final MessageQueue mMessageQueue;

    private static Looper sMainLooper;
    private Thread mThread;

    private Looper() {
        mMessageQueue = new MessageQueue();
        mThread = Thread.currentThread();
    }

    public static void prepareMainThread() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
        sMainLooper = sThreadLocal.get();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    private static final int MSG_EXIT = 10010;

    public MessageQueue getMessageQueue() {
        return mMessageQueue;
    }

    public static Looper getMainLooper() {
        return sMainLooper;
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public Thread getThread() {
        return mThread;
    }

    public static void loop() {
        Looper looper = myLooper();
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Message next = looper.mMessageQueue.next();
                if (next == null) {
                    continue;
                }
                Runnable runnable = next.getRunnable();
                if (runnable != null) {
                    runnable.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

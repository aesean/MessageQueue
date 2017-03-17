package com.aesean.message;

/**
 * HandlerThread
 *
 * @author xl
 * @version V1.0
 * @since 17/03/2017
 */
public class HandlerThread extends Thread {
    private Looper mLooper;

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        synchronized (this) {
            mLooper = Looper.myLooper();
            notifyAll();
        }
        Looper.loop();
    }

    public Looper getLooper() {
        if (!isAlive()) {
            return null;
        }
        synchronized (this) {
            while (isAlive() && mLooper == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
        return mLooper;
    }
}

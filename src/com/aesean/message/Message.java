package com.aesean.message;

/**
 * Message
 *
 * @author xl
 * @version V1.0
 * @since 17/03/2017
 */
public class Message {

    private Runnable mRunnable;

    private int arg;

    public Message(Runnable runnable) {
        mRunnable = runnable;
    }

    public Message(int arg) {
        this.arg = arg;
    }

    public Message(Runnable runnable, int arg) {
        mRunnable = runnable;
        this.arg = arg;
    }

    public Message() {
    }

    public void setRunnable(Runnable runnable) {
        mRunnable = runnable;
    }

    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
    }

    public Runnable getRunnable() {
        return mRunnable;
    }

}

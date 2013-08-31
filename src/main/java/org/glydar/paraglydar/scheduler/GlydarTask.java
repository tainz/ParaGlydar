package org.glydar.paraglydar.scheduler;

import org.glydar.paraglydar.plugin.Plugin;

/**
 * @author YoshiGenius
 */
class GlydarTask implements Runnable {

    private volatile GlydarTask next = null;
    /**
     * -1 means no repeating <br>
     * -2 means cancel <br>
     * -3 means processing for Future <br>
     * -4 means done for Future <br>
     * Never 0 <br>
     * >0 means number of ticks to wait between each execution
     */
    private volatile long interval;
    private long nextRun;
    private final Runnable task;
    private final Plugin plugin;
    private final int id;

    GlydarTask() {
        this(null, null, -1, -1);
    }

    GlydarTask(final Runnable task) {
        this(null, task, -1, -1);
    }

    GlydarTask(final Plugin plugin, final Runnable task, final int id, final long interval) {
        this.plugin = plugin;
        this.task = task;
        this.id = id;
        this.interval = interval;
    }

    public final int getID() {
        return id;
    }

    public final Plugin getPlugin() {
        return plugin;
    }

    public void run() {
        task.run();
    }

    long getInterval() {
        return interval;
    }

    void setInterval(long interval) {
        this.interval = interval;
    }

    long getNextRun() {
        return nextRun;
    }

    void setNextRun(long nextRun) {
        this.nextRun = nextRun;
    }

    GlydarTask getNext() {
        return next;
    }

    void setNext(GlydarTask next) {
        this.next = next;
    }

    Class<? extends Runnable> getTaskClass() {
        return task.getClass();
    }

    public void cancel() {
        GlydarScheduler.getInstance().cancelTask(id);
    }

    boolean cancel0() {
        setInterval(-2l);
        return true;
    }
}

package org.glydar.paraglydar.scheduler;

import org.glydar.paraglydar.plugin.Plugin;

/**
 * GlydarRunnable copied from CraftRunnable by the Bukkit project.
 * @author YoshiGenius
 */
public abstract class GlydarRunnable implements Runnable {
    private int taskId = -1;


    public synchronized void cancel() throws IllegalStateException {
        GlydarScheduler.getInstance().cancelTask(getTaskId());
    }

    public synchronized GlydarTask runTask(Plugin plugin) throws IllegalArgumentException, IllegalStateException {
        checkState();
        return setupId(GlydarScheduler.getInstance().runTask(plugin, this));
    }

    public synchronized GlydarTask runTaskLater(Plugin plugin, long delay) throws IllegalArgumentException, IllegalStateException  {
        checkState();
        return setupId(GlydarScheduler.getInstance().runTaskLater(plugin, this, delay));
    }

    public synchronized GlydarTask runTaskTimer(Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException  {
        checkState();
        return setupId(GlydarScheduler.getInstance().runTaskTimer(plugin, this, delay, period));
    }

    public synchronized int getTaskId() throws IllegalStateException {
        final int id = taskId;
        if (id == -1) {
            throw new IllegalStateException("Not scheduled yet");
        }
        return id;
    }

    private void checkState() {
        if (taskId != -1) {
            throw new IllegalStateException("Already scheduled as " + taskId);
        }
    }

    private GlydarTask setupId(final GlydarTask task) {
        this.taskId = task.getID();
        return task;
    }
}

package controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import model.TamagoState;

public class TamagoController {

    private static final int CHANGE_INTERVAL_TICKS = 3;
    private static final int MIN_ATTRIBUTE_VALUE = 0;
    private static final int MAX_ATTRIBUTE_VALUE = 100;

    public interface Listener {
        void onStateUpdated(TamagoState state);

        void onExpired(TamagoState finalState);
    }

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
        Thread thread = new Thread(runnable, "tamago-loop");
        thread.setDaemon(true);
        return thread;
    });

    private final TamagoState state;
    private final Listener listener;

    private final AtomicBoolean running = new AtomicBoolean(false);
    private int changeTick;
    private boolean lightsOn = true;

    public TamagoController(TamagoState initialState, Listener listener) {
        this.state = new TamagoState(initialState);
        this.listener = listener;
    }

    public void start() {
        if (!running.compareAndSet(false, true)) {
            return;
        }

        notifyState();
        scheduler.scheduleAtFixedRate(this::tick, 1, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        if (!running.getAndSet(false)) {
            return;
        }
        scheduler.shutdownNow();
    }

    public void pet() {
        synchronized (state) {
            state.setHappiness(Math.min(MAX_ATTRIBUTE_VALUE, state.getHappiness() + 1));
        }
        notifyState();
    }

    public void setLightsOn(boolean lightsOn) {
        this.lightsOn = lightsOn;
    }

    public TamagoState snapshot() {
        synchronized (state) {
            return new TamagoState(state);
        }
    }

    private void tick() {
        if (!running.get()) {
            return;
        }

        TamagoState current;
        boolean expired = false;

        synchronized (state) {
            state.setTime(Math.max(MIN_ATTRIBUTE_VALUE, state.getTime() - 1));
            changeTick++;

            if (changeTick >= CHANGE_INTERVAL_TICKS) {
                changeTick = 0;
                state.setHappiness(Math.max(MIN_ATTRIBUTE_VALUE, state.getHappiness() - 1));

                int warmthDelta = lightsOn ? 1 : -1;
                int warmth = Math.max(MIN_ATTRIBUTE_VALUE,
                        Math.min(MAX_ATTRIBUTE_VALUE, state.getWarmth() + warmthDelta));
                state.setWarmth(warmth);
            }

            if (state.getTime() <= MIN_ATTRIBUTE_VALUE) {
                expired = true;
                running.set(false);
            }

            current = new TamagoState(state);
        }

        listener.onStateUpdated(current);

        if (expired) {
            listener.onExpired(current);
            scheduler.shutdownNow();
        }
    }

    private void notifyState() {
        listener.onStateUpdated(snapshot());
    }
}

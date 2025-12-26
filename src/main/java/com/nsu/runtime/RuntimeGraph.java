package com.nsu.runtime;

import com.nsu.runtime.model.node.RuntimeNode;
import java.util.ArrayList;
import java.util.List;

public final class RuntimeGraph {

    private final List<Thread> threads = new ArrayList<>();

    public void addNode(RuntimeNode node) {
        threads.add(new Thread(node, node.id));
    }

    public void start() {
        threads.forEach(Thread::start);
    }

    public void join() throws InterruptedException {
        for (Thread t : threads) {
            t.join();
        }
    }
}
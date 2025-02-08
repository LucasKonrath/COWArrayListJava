package org.ldamaceno.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SynchronizedListBenchmark {

    private List<Integer> sharedList;

    @Setup(Level.Iteration)
    public void setup() {
        sharedList = Collections.synchronizedList(new ArrayList<>());
        sharedList.add(20);
    }

    @Benchmark
    @Group("syncListGroup")
    @GroupThreads(2)
    public void addElement() {
        sharedList.add(1);
    }

    @Benchmark
    @Group("syncListGroup")
    @GroupThreads(4)
    public Integer getElement(){
        return sharedList.get(0);
    }
}
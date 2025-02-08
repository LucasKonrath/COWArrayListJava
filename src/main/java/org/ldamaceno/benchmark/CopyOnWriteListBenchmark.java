package org.ldamaceno.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class CopyOnWriteListBenchmark {

    private List<Integer> sharedList;

    @Setup(Level.Iteration)
    public void setup() {
        sharedList = new CopyOnWriteArrayList<>();
        sharedList.add(20);
    }

    @Benchmark
    @Group("copyOnWriteListGroup")
    @GroupThreads(2)
    public void addElement() {
        sharedList.add(1);
    }

    @Benchmark
    @Group("copyOnWriteListGroup")
    @GroupThreads(4)
    public Integer getElement(){
        return sharedList.get(0);
    }

}
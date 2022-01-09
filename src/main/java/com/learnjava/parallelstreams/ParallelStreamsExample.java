package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import com.learnjava.util.LoggerUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;

public class ParallelStreamsExample {

    public static void main(String[] args) {
        List<String> names = DataSet.namesList();
        ParallelStreamsExample streamsExample = new ParallelStreamsExample();
        startTimer();
        List<String> resultList = streamsExample.stringTransform(names);
        LoggerUtil.log("resultList : " + resultList);
        timeTaken();
    }

    public List<String> stringTransform(List<String> names) {
        return names.
                parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> stringTransform_1(List<String> names, boolean isParallel) {
        Stream<String> namesStream = names.stream();
        if (isParallel) namesStream.parallel();
        return namesStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }
}

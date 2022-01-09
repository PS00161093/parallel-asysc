package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import com.learnjava.util.LoggerUtil;

import java.util.List;
import java.util.stream.Collectors;

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

    private List<String> stringTransform(List<String> names) {
        return names.
                parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - " + name;
    }
}

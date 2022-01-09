import com.learnjava.parallelstreams.ParallelStreamsExample;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParallelStreamsExampleTest {

    ParallelStreamsExample streamsExample = new ParallelStreamsExample();

    @Test
    void testStringTransform() {
        List<String> names = DataSet.namesList();
        startTimer();
        List<String> result = streamsExample.stringTransform(names);
        timeTaken();

        assertEquals(names.size(), result.size());
        result.forEach(name -> assertTrue(name.contains("-")));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testStringTransform_1(boolean isParallel) {
        List<String> names = DataSet.namesList();
        List<String> result = streamsExample.stringTransform_1(names, isParallel);

        assertEquals(names.size(), result.size());
        result.forEach(name -> assertTrue(name.contains("-")));
    }

}
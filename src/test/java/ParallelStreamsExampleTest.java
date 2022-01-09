import com.learnjava.parallelstreams.ParallelStreamsExample;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParallelStreamsExampleTest {

    ParallelStreamsExample streamsExample = new ParallelStreamsExample();

    @Test
    void testStringTransform() {
        List<String> names = DataSet.namesList();
        List<String> result = streamsExample.stringTransform(names);

        assertEquals(names.size(), result.size());
        result.forEach(name -> assertTrue(name.contains("-")));
    }

}
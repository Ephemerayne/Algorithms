import java.util.ArrayList;
import java.util.List;

public class TestJava {
    void test() throws Exception {
        final List<?> list1 = new ArrayList<>();
        final var firstElem = list1.get(0);

        firstElem.lo9();
    }
}

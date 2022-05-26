import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;


public class JavaBasicTest {

    @Test
    public void stringBasic() {
        String apple1 = "Apple";
        String apple2 = "Apple";
        String apple3 = new String("Apple");
    
        Assertions.assertSame(apple1, apple2);
        Assertions.assertNotSame(apple1, apple3);
    
        String apple4 = apple3.intern();
    
        Assertions.assertSame(apple1, apple4);
    }

    @Test
    public void optional() {
        Optional<String> emptyOptional = Optional.empty();
        Optional<String> nameOptional  = Optional.of("Tom");

        nameOptional.ifPresent(s -> System.out.println("Hi " + s));

        System.out.println("Hi " + nameOptional.orElse(""));
        System.out.println("Hi " + emptyOptional.orElse(""));
    }

    @Test
    public void lambdas() {
        List<String> userList = List.of("Tom", "Sophia", "Ford", "Max");

        // consumer, have input no return
        Consumer<String> printConsumer = System.out::println;
        userList.forEach(printConsumer);

        // function
        Function<Integer, Integer> doubleFunction = n -> n * n;
        Assertions.assertEquals(64, doubleFunction.apply(8));

        // suplier

        //
    }
}
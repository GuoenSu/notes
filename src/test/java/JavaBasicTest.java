import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;


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
        var userList = List.of("Tom", "Sophia", "Ford", "Max");

        // consumer, have input no return
        Consumer<String> printConsumer = System.out::println;
        userList.forEach(printConsumer);

        // function
        Function<Integer, Integer> doubleFunction = n -> n * n;
        Assertions.assertEquals(64, doubleFunction.apply(8));

        // suplier

        //
    }

    @Test
    public void palindromeTest() {
        var testString = "civic";

        assertFalse(palindrome3(testString));
    }

    public boolean palindrome(String original) {
        var size = original.length();

        for (int i = 0; i < size / 2; i++) {
            Character charAtHead = original.charAt(i);
            Character charAtTail = original.charAt(size - i - 1);
            
            if (!charAtHead.equals(charAtTail)) {
                return false;
            }
            
        }

        return true;
    }

    public boolean palindrome2(String original) {

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = original.length() - 1; i >= 0; i--) {
            stringBuffer.append(original.charAt(i));
        }


        return original.equals(stringBuffer.toString());
    }

    public boolean palindrome3(String original) {
        var size = original.length();
        return IntStream.range(0, size)
            .allMatch(i -> original.charAt(i) == original.charAt(size - i - 1));
    }

    @Test
    public void vowelCounter() {
        var vowels = "aeiouy";
        var original = "Hello";

        long vowelsCounts = original.chars()
            .boxed()
            .filter(i -> vowels.indexOf(i) > -1)
            .count();

        assertEquals(2, vowelsCounts);
    }

    @Test
    public void integerTest() {
        Integer integer1 = 100;
        Integer integer2 = 100;
        assertSame(integer1, integer2); // test true here, refer to the same object when value bewteen [-128,127]

        Integer integer3 = 200;
        Integer integer4 = 200;
        assertSame(integer3, integer4); // test false here, refer to different object 

        System.out.println(integer1 == integer2);   // true
        System.out.println(integer3 == integer4);   // false
    }


}
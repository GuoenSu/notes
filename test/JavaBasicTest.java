package test;

import org.junit.Assert;
import org.junit.Test;

public class JavaBasicTest {

    @Test
    public void stringBasic() {
        String apple1 = "Apple";
        String apple2 = "Apple";
        String apple3 = new String("Apple");
    
        Assert.assertSame(apple1, apple2);
        Assert.assertNotSame(apple1, apple3);
    
        String apple4 = apple3.intern();
    
        Assert.assertSame(apple1, apple4);
    }
}
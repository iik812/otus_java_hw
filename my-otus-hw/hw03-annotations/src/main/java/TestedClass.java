import java.io.IOException;

public class TestedClass {

    @Before
    public void getPaused() {
        System.out.println("Do something BeforeTest");
    }
    @Test
    public void startedMethod(){
        System.out.println("SomethingTest");
    }

    @Test
    public void throwException() {
        throw new RuntimeException("Exception");

    }

    @After
    public void afterMethod() {
        System.out.println("After test");
    }



}

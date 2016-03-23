import org.junit.*;
import org.jboss.aesh.cl.*;
import org.jboss.aesh.cl.internal.*;
import org.jboss.aesh.cl.parser.*;

public class MainTest {
    @Test
    public void testMyOptionWithValue() throws Exception {
        CommandLine<?> cl = Main.doit("mycmd --myoption value --abc 123");
        assert cl.hasOption("abc") : "Should have abc";
        assert "123".equals(cl.getOptionValue("abc")) : "bad abc value";
        assert cl.hasOption("myoption") : "Should have myoption";
        assert "value".equals(cl.getOptionValue("myoption")) : "bad myoption value";
    }

    @Test
    public void testMyOptionWithoutValue() throws Exception {
        CommandLine<?> cl = Main.doit("mycmd --myoption --abc 123");
        assert cl.hasOption("abc") : "Should have abc";
        assert "123".equals(cl.getOptionValue("abc")) : "bad abc value";
        assert cl.hasOption("myoption") : "Should have myoption";
        assert "".equals(cl.getOptionValue("myoption")) : "bad myoption value";
    }

    @Test
    public void testNoMyOption() throws Exception {
        CommandLine<?> cl = Main.doit("mycmd --abc 123");
        assert cl.hasOption("abc") : "Should have abc";
        assert "123".equals(cl.getOptionValue("abc")) : "bad abc value";
        assert !cl.hasOption("myoption") : "Should not have myoption";
    }

    // specify --myoption at the end of the cmdline
    @Test
    public void testMyOptionAtEndWithValue() throws Exception {
        CommandLine<?> cl = Main.doit("mycmd --abc 123 --myoption value");
        assert cl.hasOption("abc") : "Should have abc";
        assert "123".equals(cl.getOptionValue("abc")) : "bad abc value";
        assert cl.hasOption("myoption") : "Should have myoption";
        assert "value".equals(cl.getOptionValue("myoption")) : "bad myoption value";
    }

    // specify --myoption at the end of the cmdline
    @Test
    public void testMyOptionAtEndWithoutValue() throws Exception {
        CommandLine<?> cl = Main.doit("mycmd --abc 123 --myoption");
        assert cl.hasOption("abc") : "Should have abc";
        assert "123".equals(cl.getOptionValue("abc")) : "bad abc value";
        assert cl.hasOption("myoption") : "Should have myoption";
        assert "".equals(cl.getOptionValue("myoption")) : "bad myoption value";
    }
}

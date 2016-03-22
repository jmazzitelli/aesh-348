import java.io.*;
import java.net.*;
import java.util.*;

import org.jboss.aesh.cl.*;
import org.jboss.aesh.cl.internal.*;
import org.jboss.aesh.cl.parser.*;

public class Main {
    public static final void main(String[] args) throws Exception {
        ProcessedCommand<?> options = options = buildCommandLineOptions();
        CommandLineParser<?> parser = new CommandLineParserBuilder().processedCommand(options).create();
        StringBuilder argLine = new StringBuilder("mycmd");
        for (String str : args) {
            argLine.append(' ').append(str);
        }

        CommandLine<?> commandLine = parser.parse(argLine.toString());
        if (commandLine.getParserException() != null) {
            throw commandLine.getParserException();
        }

        System.out.println("hasOption(myoption)=" + commandLine.hasOption("abc"));
        System.out.println("abc=" + commandLine.getOptionValue("abc", "<not set>"));
        System.out.println("hasOption(myoption)=" + commandLine.hasOption("myoption"));
        System.out.println("myoption=" + commandLine.getOptionValue("myoption", "<not set>"));
    }

    private static ProcessedCommand<?> buildCommandLineOptions() throws Exception {
        ProcessedCommandBuilder cmd = new ProcessedCommandBuilder();

        cmd.name("mycmd");

        cmd.addOption(new ProcessedOptionBuilder()
                .name("abc")
                .optionType(OptionType.NORMAL)
                .type(String.class)
                .create());
        cmd.addOption(new ProcessedOptionBuilder()
                .name("myoption")
                .optionType(OptionType.NORMAL)
                .type(String.class)
                .addDefaultValue("")
                .create());

        return cmd.create();
    }
}

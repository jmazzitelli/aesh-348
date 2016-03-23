import java.io.*;
import java.net.*;
import java.util.*;

import org.jboss.aesh.cl.*;
import org.jboss.aesh.cl.internal.*;
import org.jboss.aesh.cl.parser.*;

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder argLine = new StringBuilder("mycmd");
        for (String str : args) {
            argLine.append(' ').append(str);
        }
        doit(argLine.toString());
    }

    public static CommandLine<?> doit(String argLine) throws Exception {
        ProcessedCommand<?> options = options = buildCommandLineOptions();
        CommandLineParser<?> parser = new CommandLineParserBuilder().processedCommand(options).create();
        CommandLine<?> commandLine = parser.parse(argLine);
        if (commandLine.getParserException() != null) {
            throw commandLine.getParserException();
        }

        System.out.println("cmdline ==> " + argLine);
        System.out.println("hasOption(abc)=" + commandLine.hasOption("abc"));
        System.out.println("abc=" + commandLine.getOptionValue("abc", "<not set>"));
        System.out.println("hasOption(myoption)=" + commandLine.hasOption("myoption"));
        System.out.println("myoption=" + commandLine.getOptionValue("myoption", "<not set>"));
        System.out.println();

        return commandLine;
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

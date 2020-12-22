package com.carlosware.kjanitor.commandline;

import static java.util.Objects.requireNonNull;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import javax.annotation.Nonnull;

public class JCommanderCommandLineParser implements CommandLineParser {
    private final CommandLineJCommanderArguments commandLineArguments;
    private final JCommander jcommander;

    public JCommanderCommandLineParser() {
        this.commandLineArguments = new CommandLineJCommanderArguments();
        this.jcommander = JCommander.newBuilder()
            .addObject(commandLineArguments)
            .build();
    }

    @Override
    public CommandLine parse(@Nonnull String[] arguments) throws CommandLineException {
        requireNonNull(arguments);
        try {
            jcommander.parse(arguments);
            return buildCommandLine();
        } catch (ParameterException pe) {
            throw new CommandLineException("Parsing of the command line failed", pe);
        }
    }

    private Boolean isNull(String parameter) {
        return parameter == null;
    }

    private Integer countIfNotNull(String parameter) {
        return isNull(parameter) ? 0 : 1;
    }

    private void isValid() throws CommandLineException {
        Integer options = 0;
        options += countIfNotNull(commandLineArguments.toClean);
        options += countIfNotNull(commandLineArguments.toCreate);
        options += countIfNotNull(commandLineArguments.toRemove);
        if (options != 1) {
            throw new CommandLineException("Only one mode of operation can be specified");
        }
    }

    private CommandLine buildCommandLine() throws CommandLineException {
        isValid();
        CommandLineModes mode = detectMode();
        String modeConfigurationFile = getModeConfigurationFile(mode);
        return CommandLine.from(commandLineArguments.kafkaConfigurationFile, mode, modeConfigurationFile);
    }

    private CommandLineModes detectMode() throws CommandLineException {
        if (!isNull(commandLineArguments.toClean)) {
            return CommandLineModes.CLEANUP;
        }
        else if (!isNull(commandLineArguments.toCreate)) {
            return CommandLineModes.CREATION;
        }
        else if (!isNull(commandLineArguments.toRemove)) {
            return CommandLineModes.REMOVAL;
        }
        else {
            throw new CommandLineException("No mode of operation was specified");
        }
    }

    private String getModeConfigurationFile(CommandLineModes mode) {
        switch(mode) {
            case CLEANUP:
                return commandLineArguments.toClean;
            case CREATION:
                return commandLineArguments.toCreate;
            case REMOVAL:
                return commandLineArguments.toRemove;
            default:
                return "";
        }
    }

}
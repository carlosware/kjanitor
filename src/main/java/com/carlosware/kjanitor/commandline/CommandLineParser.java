package com.carlosware.kjanitor.commandline;

import javax.annotation.Nonnull;

public interface CommandLineParser {
    /**
     * Parses the command line
     * @param arguments the specified command line
     * @throws CommandLineException
     */
    CommandLine parse(@Nonnull String[] arguments) throws CommandLineException;
}

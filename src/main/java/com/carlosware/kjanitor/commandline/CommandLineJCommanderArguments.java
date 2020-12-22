package com.carlosware.kjanitor.commandline;

import com.beust.jcommander.Parameter;

class CommandLineJCommanderArguments {
  @Parameter(names = {"--create", "-c"})
  String toCreate;

  @Parameter(names = {"--remove", "-r"})
  String toRemove;

  @Parameter(names = {"--spring-cleaning", "-s"})
  String toClean;

  @Parameter(names = {"--kafka-configuration", "-k"}, required = true)
  String kafkaConfigurationFile;
}

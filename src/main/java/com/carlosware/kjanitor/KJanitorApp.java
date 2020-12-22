package com.carlosware.kjanitor;

import java.util.Properties;

import com.carlosware.kjanitor.commandline.CommandLine;
import com.carlosware.kjanitor.commandline.CommandLineException;
import com.carlosware.kjanitor.commandline.JCommanderCommandLineParser;
import com.carlosware.kjanitor.configuration.Configuration;
import com.carlosware.kjanitor.configuration.ConfigurationReaderException;
import com.carlosware.kjanitor.configuration.ConfigurationReader;
import com.carlosware.kjanitor.factories.FactoryException;
import com.carlosware.kjanitor.kafka.KafkaAdminClient;
import com.carlosware.kjanitor.operations.Operation;
import com.carlosware.kjanitor.operations.OperationFactory;
import com.carlosware.kjanitor.operations.OperationFactoryException;
import com.carlosware.kjanitor.commandline.CommandLineModes;
import com.carlosware.kjanitor.commandline.CommandLineParser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

class KJanitorApp {
    static Logger logger = Logger.getLogger(KJanitorApp.class);

    public static void main(String arguments[]) {
        logger.info("KJanitor is starting");
        try {
            CommandLine commandLine = processCommandLine(arguments);
            ObjectMapper objectMapper = createObjectMapper();
            Configuration configuration = processConfiguration(commandLine, objectMapper);
            KafkaAdminClient kafkaClient = createKafkaClient(configuration.getKafkaConfiguration());
            Operation operation = createOperation(commandLine.getMode(), configuration, kafkaClient);
            KJanitor kjanitor = new KJanitor(operation);
            kjanitor.run();
        } catch (CommandLineException cle) {
            usage();
        } catch (ConfigurationReaderException cre) {
            printException("Invalid configuration", cre);
        } catch (KJanitorException kje) {
            printException("Exception while running KJanitor", kje);
        } catch (OperationFactoryException ofe) {
            printException("Exception while creating operation", ofe);
        } catch (Exception exception) {
            printException("Exception at startup, aborting", exception);
        }
        logger.info("KJanitor is shut down");
    }

    static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return objectMapper;
    }

    static CommandLine processCommandLine(String[] arguments) throws CommandLineException {
        CommandLineParser commandLineParser = new JCommanderCommandLineParser();
        CommandLine commandLine = commandLineParser.parse(arguments);
        logger.info("Kafka configuration file is " + commandLine.getKafkaConfigurationFile());
        logger.info("Requested mode of operation is " + commandLine.getMode().toString());
        logger.info("Mode configuration file is " + commandLine.getModeConfigurationFile());
        return commandLine;
    }

    static Configuration processConfiguration(CommandLine commandLine, ObjectMapper objectMapper) throws ConfigurationReaderException {
        ConfigurationReader configurationReader = new ConfigurationReader(objectMapper);
        return configurationReader.read(commandLine);
    }

    static Operation createOperation(CommandLineModes commandLineMode, Configuration configuration, KafkaAdminClient kafkaClient) throws FactoryException {
        OperationFactory operationFactory = new OperationFactory(commandLineMode, configuration, kafkaClient);
        return operationFactory.build();
    }

    static KafkaAdminClient createKafkaClient(Properties kafkaConfiguration) {
        return null;
    }

    static void usage() {
        System.out.println("Usage: kjanitor --create <creation configuration file> --kafka-configuration <kafka configuration file>");
        System.out.println("       kjanitor -c <creationconfiguration file> -k <kafka configuration file>");
        System.out.println("         Creates the requested topics in the kafka cluster. The creation configuration file specifies the topics and their configuration.");
        System.out.println("       kjanitor --remove <removal configuration file> --kafka-configuration <kafka configuration file>");
        System.out.println("       kjanitor -r <removal configuration file> -k <kafka configuration file>");
        System.out.println("         Removes the specified topics from the kafka cluster.");
        System.out.println("       kjanitor --spring-cleaning <cleanup configuration file> --kafka-configuration <kafka configuration file>");
        System.out.println("       kjanitor -s <cleanup configuration file> -k <kafka configuration file>");
        System.out.println("         Cleans up topics that have no messages, except for those in the to preserve list.");
    }

    static void printException(String message, Exception exception) {
        logger.error(message);
        logger.error("Details:\n", exception);
    }
}
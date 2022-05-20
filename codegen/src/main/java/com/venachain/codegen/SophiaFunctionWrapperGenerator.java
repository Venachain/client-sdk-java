package com.venachain.codegen;

import static com.venachain.codegen.Console.exitError;
import static com.venachain.utils.Collection.tail;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.venachain.protocol.ObjectMapperFactory;
import com.venachain.protocol.core.methods.response.AbiDefinition;
import com.venachain.utils.Files;
import com.venachain.utils.Strings;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Java wrapper source code generator for Solidity ABI format.
 */
public class SophiaFunctionWrapperGenerator extends FunctionWrapperGenerator {

    private static final String USAGE = "wasm generate "
            + "[--javaTypes|--solidityTypes] "
            + "<input abi file>.abi "
            + "-p|--package <base package name> "
            + "-o|--output <destination base directory>"
            ;

    private final String absFileLocation;

    private SophiaFunctionWrapperGenerator(
            String absFileLocation,
            String destinationDirLocation,
            String basePackageName,
            boolean useJavaNativeTypes) {

        super(destinationDirLocation, basePackageName, useJavaNativeTypes);
        this.absFileLocation = absFileLocation;
    }

    public static void run(String[] args) throws Exception {
        if (args.length < 1 || !args[0].equals("generate")) {
            exitError(USAGE);
        } else {
            main(tail(args));
        }
    }

    public static void main(String[] args) throws Exception {

        String[] fullArgs;
        if (args.length == 7) {
            fullArgs = new String[args.length + 1];
            fullArgs[0] = JAVA_TYPES_ARG;
            System.arraycopy(args, 0, fullArgs, 1, args.length);
        } else {
            fullArgs = args;
        }

//        if (fullArgs.length != 8) {
//            exitError(USAGE);
//        }

        boolean useJavaNativeTypes = useJavaNativeTypes(fullArgs[0], USAGE);

        String absFileLocation = parsePositionalArg(fullArgs, 1);
        String destinationDirLocation = parseParameterArgument(fullArgs, "-o", "--outputDir");
        String basePackageName = parseParameterArgument(fullArgs, "-p", "--package");

//        if (absFileLocation.equals("")
//                || destinationDirLocation.equals("")
//                || basePackageName.equals("")
//        ) {
//            exitError(USAGE);
//        }

        new SophiaFunctionWrapperGenerator(
                absFileLocation,
                destinationDirLocation,
                basePackageName,
                useJavaNativeTypes)
                .generate();
    }

    static List<AbiDefinition> loadContractDefinition(File absFile)
            throws IOException {
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        AbiDefinition[] abiDefinition = objectMapper.readValue(absFile, AbiDefinition[].class);
        return Arrays.asList(abiDefinition);
    }

    private void generate() throws IOException, ClassNotFoundException {
        File absFile = new File(absFileLocation);
        if (!absFile.exists() || !absFile.canRead()) {
            exitError("Invalid input ABI file specified: " + absFileLocation);
        }

        String fileName = absFile.getName();
        String contractName = getFileNameNoExtension(fileName);
        byte [] bytes = Files.readBytes(new File(absFile.toURI()));
        String abi = new String(bytes);
        
        abi = abi.replaceAll("\r\n", "");
        abi = abi.replaceAll("\n", "");
        abi = abi.replaceAll(" ", "");

        List<AbiDefinition> functionDefinitions = loadContractDefinition(absFile);

        if (functionDefinitions.isEmpty()) {
            exitError("Unable to parse input ABI file");
        } else {
            String className = Strings.capitaliseFirstLetter(contractName);
            System.out.printf("Generating " + basePackageName + "." + className + " ... ");

            new SophiaFunctionWrapper(useJavaNativeTypes).generateJavaFiles(contractName, abi, destinationDirLocation.toString(), basePackageName);
            System.out.println("File written to " + destinationDirLocation.toString() + "\n");
        }
    }


}
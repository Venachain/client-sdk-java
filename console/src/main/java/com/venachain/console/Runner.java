package com.venachain.console;

import com.venachain.utils.Collection;
import com.venachain.utils.Version;
import com.venachain.codegen.Console;
import com.venachain.codegen.SolidityFunctionWrapperGenerator;
import com.venachain.codegen.SophiaFunctionWrapperGenerator;
import com.venachain.codegen.TruffleJsonFunctionWrapperGenerator;

/**
 * Main entry point for running command line utilities.
 */
public class Runner {

    private static String USAGE = "Usage: client-sdk version|wallet|solidity|truffle|wasm ...";

    private static String LOGO = "\n" // generated at http://patorjk.com/software/taag
            + "              _      _____ _     _        \n"
            + "             | |    |____ (_)   (_)       \n"
            + "__      _____| |__      / /_     _   ___  \n"
            + "\\ \\ /\\ / / _ \\ '_ \\     \\ \\ |   | | / _ \\ \n"
            + " \\ V  V /  __/ |_) |.___/ / | _ | || (_) |\n"
            + "  \\_/\\_/ \\___|_.__/ \\____/| |(_)|_| \\___/ \n"
            + "                         _/ |             \n"
            + "                        |__/              \n";

    public static void main(String[] args) throws Exception {
        System.out.println(LOGO);

        if (args.length < 1) {
            Console.exitError(USAGE);
        } else {
            switch (args[0]) {
                case "wallet":
                    WalletRunner.run(Collection.tail(args));
                    break;
                case "solidity":
                    SolidityFunctionWrapperGenerator.run(Collection.tail(args));
                    break;
                case "truffle":
                    TruffleJsonFunctionWrapperGenerator.run(Collection.tail(args));
                    break;
                case "wasm":
                	SophiaFunctionWrapperGenerator.run(Collection.tail(args));
                    break;
                case "version":
                    Console.exitSuccess("Version: " + Version.getVersion() + "\n"
                            + "Build timestamp: " + Version.getTimestamp());
                    break;
                default:
                    Console.exitError(USAGE);
            }
        }
    }
}
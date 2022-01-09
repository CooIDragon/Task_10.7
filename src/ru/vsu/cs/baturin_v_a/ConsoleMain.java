package ru.vsu.cs.baturin_v_a;

import ru.vsu.cs.baturin_v_a.util.ArrayUtils;

import java.io.PrintStream;

public class ConsoleMain {

    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            params.inputFile = args[0];
            if (args.length > 1) {
                params.outputFile = args[1];
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static void main(String[] args) throws Exception {
        ConsoleMain.CmdParams params = parseArgs(args);
        FindingPointsWithMinPerimeter findingPointsWithMinPerimeter = new FindingPointsWithMinPerimeter();
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }

        if (params.window) {
            GUIMain.winMain();
        } else {
            int[][] arr2 = ArrayUtils.readIntArray2FromFile(params.inputFile);

            if (arr2 == null) {
                System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                System.exit(2);
            }

            for (int i = 0; i < arr2.length; i++){
                if (arr2[i].length > 2){
                    System.out.println("Extra coordinates deleted for row number " + (i + 1));
                }
            }

            int[][] result = findingPointsWithMinPerimeter.findResult(arr2);

            if (result != null) {
                PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
                out.println(ArrayUtils.toString(result));
                out.close();
            }
        }
    }
}

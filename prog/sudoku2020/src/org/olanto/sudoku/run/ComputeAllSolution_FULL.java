/** ********
 * Copyright © 2020 Olanto Foundation Geneva
 *
 * This file is part of mySUDOKU.
 *
 * mySUDOKU is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * mySUDOKU is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with mySUDOKU.  If not, see <http://www.gnu.org/licenses/>.
 *
 *********
 */
package org.olanto.sudoku.run;

import java.io.*;
import org.olanto.sudoku.util.B1;
import org.olanto.sudoku.util.B2B3Restrict;
import org.olanto.sudoku.util.BasicFrame;
import org.olanto.sudoku.util.Permutation;
import org.olanto.sudoku.util.SDKFrame;
import org.olanto.sudoku.util.Timer;

// -server -Xmx512m -dsa -Xnoclassgc -Xshare:off -XX:CompileThreshold=10 -XX:+PrintCompilation
/**
 * 
 * this class compute all the solutions
 * adjust SDKroot="C:/SDK/";
 * many instance of this program could be executed at the same time
 * 
 */


public class ComputeAllSolution_FULL {

    static FileWriter out;
    
    static String SDKroot="C:/SDK/";

    static SDKFrame sdkf = new SDKFrame(); // to init
    static BasicFrame bf = new BasicFrame(sdkf);

    public static void main(String[] args) {

        // init all permutation for a block and normalisation
        Permutation.initPermutation();
        // init B1 block and all block compatible in line normalisation (B1Line)
        B1.initB1();
        //
        B2B3Restrict.initB2B3Restrict();

//       compute all solution
        for (int i = 0; i < 10080L; i++) {
            runRandomInFile(i);
        }
    }

    public static void runRandomInFile(int start) {

        int i = start;
        if (!openFile( SDKroot+ i)) {
            try {
                int block5 = Permutation.getValueOfNormId(i);
                initFile(SDKroot + i);
                Timer t1 = new Timer("root:" + i + "-" + block5);
                B2B3Restrict.reset();
                for (int j = 0; j < Permutation.NORMFACT9; j++) {
                    int block9 = Permutation.getValueOfNormId(j);
                    //if (j%1000==0)System.out.println(i+"-"+j);               
                    String problem = "B5\t" + block5 + "\t" + "B9:" + block9 + "\t";

                    String res = B2B3Restrict.countAll(sdkf, bf, i, j);
                    out.write(problem + res + "\n");

                }
                out.write("#####ENDOFDETAIL#####V1.0\n");
                out.write(B2B3Restrict.finish());
                t1.stop();
                closeFile();
            } catch (Exception e) {
                System.err.println("IO error in file");
                e.printStackTrace();
            }
        } else {
            System.out.println("skip:" + "C:/SDK/" + i);
        }

    }

    public static void initFile(String filename) {

        try {
            out = new FileWriter(filename + ".sdk");
        } catch (Exception e) {
            System.err.println("IO error open sdk");
            e.printStackTrace();
        }
        System.out.println("open " + filename);
    }

    public static boolean openFile(String filename) {
        FileReader in;
        try {
            in = new FileReader(filename + ".sdk");
            in.close();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static void closeFile() {

        try {
            out.write("#####ENDOFFILE#####V1.0\n");
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println("IO error close");
            e.printStackTrace();
        }
    }

}

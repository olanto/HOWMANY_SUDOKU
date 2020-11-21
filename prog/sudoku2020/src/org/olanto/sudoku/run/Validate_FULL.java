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
import java.math.BigInteger;



// -server -Xmx512m -dsa -Xnoclassgc -Xshare:off -XX:CompileThreshold=10 -XX:+PrintCompilation
/**
 * 
 * this class collect all the solutions
 * adjust SDKroot="C:/SDK/";
 * 
 */
public class Validate_FULL {

    static BufferedReader in;
    static InputStreamReader isr;
    static BigInteger sumbig = new BigInteger("0");


    public static void main(String[] args) {

        for (int i = 0; i < 10080L; i++) {
            validateFile(i);
        }
 SomeComputation();
    }
    

    public static boolean validateFile(int index) {

        int i = index;
        if (openFile("C:/SDK/" + i)) {
            try {
                String w = in.readLine();
                while (w != null && !(w.startsWith("#####ENDOFDETAIL#####"))) {
                    w = in.readLine();
                }
                if (w == null) {
                    in.close();
                    System.out.println("partial:" + "C:/SDK/" + i);
                    return false;
                }
                String totalw = in.readLine().replace(":", "\t");
                String[] part=totalw.split("\t");
                sumbig=sumbig.add(new BigInteger(part[1]));
                System.out.println(i + "\t" + part[1]+"\t"+sumbig);
                
                w = in.readLine();
                if (!w.startsWith("#####ENDOFFILE#####")) {
                    in.close();
                    System.out.println("no eof:" + "C:/SDK/" + i);
                    return false;
                }
                //System.out.println(i+"\t"+totalw);
                in.close();
            } catch (Exception e) {
                System.err.println("IO error in fileC:/SDK/" + i);
                e.printStackTrace();
            }
        } else {
            System.out.println("missing:" + "C:/SDK/" + i);
        }

        return true;
    }

    public static boolean openFile(String filename) {

        try {
            isr = new InputStreamReader(new FileInputStream(filename + ".sdk"));
            in = new BufferedReader(isr);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

      public static void SomeComputation() {
        BigInteger totsdk = new BigInteger("6670903752021072936960");
        BigInteger fact9 = new BigInteger("362880");
        BigInteger perm2x3 = new BigInteger("36");
        BigInteger permN9 = new BigInteger("10080");

        BigInteger resultN = new BigInteger("14184585201152");

        System.out.println("totsdk: " + totsdk);
        System.out.println("fact9: " + fact9);
        System.out.println("perm2x3: " + perm2x3);
        
        BigInteger nbsymetries = fact9.multiply(perm2x3).multiply(perm2x3);
        BigInteger nbsudokuJG = sumbig.multiply(nbsymetries);

        System.out.println("nbsymetries   : " + nbsymetries);
        System.out.println("nbsudokuJG    : " + nbsudokuJG);
        System.out.println("totsdk_founded: " + totsdk);
      

         ;

    }

    
    
}

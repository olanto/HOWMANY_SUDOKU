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

/**
 * 
 * this class collect all the half the solutions
 * adjust SDKroot="C:/SDK/";
 * 
 */public class Validate_HALF {

    static BufferedReader in;
    static InputStreamReader isr;
    static BigInteger sumbig = new BigInteger("0");
    static BigInteger sumdiag = new BigInteger("0");

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
                String w = in.readLine().replace(":", "\t");
                String[] part = w.split("\t"); // first item is diagonal (no symetry)
                sumdiag = sumdiag.add(new BigInteger(part[5]));
                while (w != null && !(w.startsWith("#####ENDOFDETAIL#####"))) {
                    w = in.readLine();
                }
                if (w == null) {
                    in.close();
                    System.out.println("partial:" + "C:/SDK/" + i);
                    return false;
                }
                String totalw = in.readLine().replace(":", "\t");
                part = totalw.split("\t");
                sumbig = sumbig.add(new BigInteger(part[1]));
                System.out.println(i + "\t" + part[1] + "\t" + sumbig + "\t" + sumdiag);

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
        BigInteger permB5B9 = new BigInteger("2");

        System.out.println("totsdk: " + totsdk);
        System.out.println("fact9: " + fact9);
        System.out.println("perm2x3: " + perm2x3);
        System.out.println("permB5B9: " + permB5B9);

        BigInteger nbsymetries = fact9.multiply(perm2x3).multiply(perm2x3);

        BigInteger nbsudokuJG = ((sumbig.multiply(permB5B9)).subtract(sumdiag)).multiply(nbsymetries);

        BigInteger diff = (nbsudokuJG.subtract(totsdk));

        System.out.println("sumdiag          : " + sumdiag);
        System.out.println("sumbig           : " + sumbig);
        System.out.println("nbsymetries      : " + nbsymetries);
        System.out.println("permB5B9         : " + permB5B9);
        System.out.println("nbsudokuJG       : " + nbsudokuJG);

        System.out.println("totsdkto be found: " + totsdk);
        ;

    }

}

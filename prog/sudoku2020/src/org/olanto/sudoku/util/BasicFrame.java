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
package org.olanto.sudoku.util;


public class BasicFrame {

    static final int MAX = 9 * 5184;

    int[][][][] baseframe = new int[9][9][9][64];

    private int[][] m = new int[9][9];

    private SDKFrame f;

    /**
     * Creates a new instance of buildSuDoKu
     */

    public BasicFrame(SDKFrame f) {
        this.f = f;
        start();
    }

    public static void main(String[] args) {
        SDKFrame sdkf = new SDKFrame(); // to init
        BasicFrame bf = new BasicFrame(sdkf);
        bf.dump();
    }

    public void start() {
        System.out.println("init  Basic frames: ");
        for (int i = 0; i < 9; i++) {
            System.out.println("init frame: " + i);
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    init(i, j, k);
                    check(baseframe[i][j][k]);
                }
            }
        }

    }

    void init(int A, int B, int C) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[i][j] = 9;  // A
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                m[i][j] = 9;  // B
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                m[i][j] = 9;  // C
            }
        }
        m[A / 3][A % 3] = 1;
        m[B / 3 + 3][B % 3 + 3] = 1;
        m[C / 3 + 6][C % 3 + 6] = 1;

        //dump();
    }

    void check(int[] newpos) {
        int count = 0;
        for (int i = 0; i < MAX; i++) {
            if (isOK(i)) {
                //System.out.println("ok: "+n+" -> "+i);
                newpos[count] = i;
                count++;
            }
        }
        if (count != 64) {
            System.out.println("error in check count!=64 : " + count);
        }
    }

    boolean isOK(int fid) {
        //System.out.println("test: "+n+" -> "+fid);
        for (int li = 0; li < 9; li++) {
            for (int co = 0; co < 9; co++) {
                if (m[li][co] == 1) {
                    if (!f.frame[fid].getM(li, co)) {
                        return false;
                    }
                } else if (f.frame[fid].getM(li, co)) {
                    if (m[li][co] != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    final void dump() {
        for (int li = 0; li < 9; li++) {
            for (int co = 0; co < 9; co++) {
                if (m[li][co] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print(m[li][co]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}

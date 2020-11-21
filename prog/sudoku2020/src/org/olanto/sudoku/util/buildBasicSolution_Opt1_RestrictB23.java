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

import static org.olanto.sudoku.util.Permutation.*;

/**
 *
 * @author Administrateur
 */
public class buildBasicSolution_Opt1_RestrictB23 {

    static final int[] p10 = new int[]{
        100000000,
        10000000,
        1000000,
        100000,
        10000,
        1000,
        100,
        10,
        1};

    static private int[][] m9 = new int[9][9];

    static long[] bp0A = new long[8];
    static long[] bp1A = new long[8];
    static long[] bp2A = new long[8];
    static long[] bp3A = new long[8];
    static long[] bp4A = new long[8];
    static long[] bp5A = new long[8];
    static long[] bp6A = new long[8];
    static long[] bp7A = new long[8];
    static long[] bp8A = new long[8];
    static long[] bp0B = new long[8];
    static long[] bp1B = new long[8];
    static long[] bp2B = new long[8];
    static long[] bp3B = new long[8];
    static long[] bp4B = new long[8];
    static long[] bp5B = new long[8];
    static long[] bp6B = new long[8];
    static long[] bp7B = new long[8];
    static long[] bp8B = new long[8];

    static long[] bp0A64 = new long[64];
    static long[] bp1A64 = new long[64];
    static long[] bp2A64 = new long[64];
    static long[] bp3A64 = new long[64];
    static long[] bp4A64 = new long[64];
    static long[] bp5A64 = new long[64];
    static long[] bp6A64 = new long[64];
    static long[] bp7A64 = new long[64];
    static long[] bp8A64 = new long[64];
    static long[] bp0B64 = new long[64];
    static long[] bp1B64 = new long[64];
    static long[] bp2B64 = new long[64];
    static long[] bp3B64 = new long[64];
    static long[] bp4B64 = new long[64];
    static long[] bp5B64 = new long[64];
    static long[] bp6B64 = new long[64];
    static long[] bp7B64 = new long[64];
    static long[] bp8B64 = new long[64];

    static private int totalNormalized;
    static private int totalSolution;

    public static long rootSolution;
    public static long rootNormalized;

    static private M9x9[] frame;

    static private int b5, b9, b6, b8;
    static private int minb5b9;
    static private int minIdxNormTerm9;

    static private long[] a = new long[8];
    static private long[] b = new long[8];

    static private int[] expl0;
    static private int[] expl1;
    static private int[] expl2;
    static private int[] expl3;
    static private int[] expl4;
    static private int[] expl5;
    static private int[] expl6;
    static private int[] expl7;
    static private int[] expl8;
    static private int[] ABC0 = new int[9];
    static private int[] ABC1 = new int[9];
    static private int[] B2 = new int[9];
    static private int[] B3 = new int[9];

    public static final void reset() {
        rootSolution = 0;
        rootNormalized = 0;
    }

    /**
     * Creates a new instance of buildSuDoKu
     */
    public static final void countAll(SDKFrame _f, BasicFrame bs, int _b5, int _b9, int _b2, int _b3) {
        b5 = _b5;
        b9 = _b9;

        for (int i = 8; i >= 0; i--) {
            ABC0[_b5 % 10 - 1] = i;
            _b5 /= 10;
            ABC1[_b9 % 10 - 1] = i;
            _b9 /= 10;
        }
        for (int i = 8; i >= 0; i--) {
            B2[_b2 % 10 - 1] = i;
            _b2 /= 10;
            B3[_b3 % 10 - 1] = i;
            _b3 /= 10;
        }
        if (b5 != normalize(b5)) {
            System.out.println("b5 is not normalized:" + b5 + " norm is:" + normalize(b5));
        }
        if (b9 != normalize(b9)) {
            System.out.println("b9 is not normalized:" + b9 + " norm is:" + normalize(b9));
        }
        if (b5 < b9) {
            minb5b9 = b5;
        } else {
            minb5b9 = b9;
        }
        minIdxNormTerm9 = getIdxOfValueOfNormPerm9(minb5b9);

        //System.out.println("max is:"+minb5b9+" max idx:"+minIdxNormTerm9);
        frame = _f.frame;
        totalNormalized = 0;
        totalSolution = 0;

        initBasicPermutation(bs);
        chekB2B3(B2, B3);
        build();
        rootNormalized += totalNormalized;
        rootSolution += totalSolution;
        //                    System.out.println("totalSolution:"+totalSolution);
        //                    System.out.println("totalNormalized:"+totalNormalized);
    }

    private static final void chekB2B3(int[] B2, int[] B3) {
        int count;
        count = 0;
        //System.out.println("---------------------:");
        for (int i = 0; i < 64; i++) {
            if (((bp0A64[i] & 1L << (18 + B2[0])) != 0) && ((bp0A64[i] & 1L << (27 + B3[0])) != 0)) {
                bp0A[count] = bp0A64[i];
                bp0B[count] = bp0B64[i];
                count++;
            }
        }
        //System.out.println("0:"+count);
        count = 0;
        for (int i = 0; i < 64; i++) {
            if (((bp1A64[i] & 1L << (18 + B2[1])) != 0) && ((bp1A64[i] & 1L << (27 + B3[1])) != 0)) {
                bp1A[count] = bp1A64[i];
                bp1B[count] = bp1B64[i];
                count++;
            }
        }
        //System.out.println("1:"+count);
        count = 0;
        for (int i = 0; i < 64; i++) {
            if (((bp2A64[i] & 1L << (18 + B2[2])) != 0) && ((bp2A64[i] & 1L << (27 + B3[2])) != 0)) {
                bp2A[count] = bp2A64[i];
                bp2B[count] = bp2B64[i];
                count++;
            }
        }
        //System.out.println("2:"+count);
        count = 0;
        for (int i = 0; i < 64; i++) {
            if (((bp3A64[i] & 1L << (18 + B2[3])) != 0) && ((bp3A64[i] & 1L << (27 + B3[3])) != 0)) {
                bp3A[count] = bp3A64[i];
                bp3B[count] = bp3B64[i];
                count++;
            }
        }
        //System.out.println("3:"+count);
        count = 0;
        for (int i = 0; i < 64; i++) {
            if (((bp4A64[i] & 1L << (18 + B2[4])) != 0) && ((bp4A64[i] & 1L << (27 + B3[4])) != 0)) {
                bp4A[count] = bp4A64[i];
                bp4B[count] = bp4B64[i];
                count++;
            }
        }
        //System.out.println("4:"+count);
        count = 0;
        for (int i = 0; i < 64; i++) {
            if (((bp5A64[i] & 1L << (18 + B2[5])) != 0) && ((bp5A64[i] & 1L << (27 + B3[5])) != 0)) {
                bp5A[count] = bp5A64[i];
                bp5B[count] = bp5B64[i];
                count++;
            }
        }
        //System.out.println("5:"+count);
        count = 0;
        for (int i = 0; i < 64; i++) {
            if (((bp6A64[i] & 1L << (18 + B2[6])) != 0) && ((bp6A64[i] & 1L << (27 + B3[6])) != 0)) {
                bp6A[count] = bp6A64[i];
                bp6B[count] = bp6B64[i];
                count++;
            }
        }
        //System.out.println("6:"+count);
        count = 0;
        for (int i = 0; i < 64; i++) {
            if (((bp7A64[i] & 1L << (18 + B2[7])) != 0) && ((bp7A64[i] & 1L << (27 + B3[7])) != 0)) {
                bp7A[count] = bp7A64[i];
                bp7B[count] = bp7B64[i];
                count++;
            }
        }
        //System.out.println("7:"+count);
        count = 0;
        for (int i = 0; i < 64; i++) {
            if (((bp8A64[i] & 1L << (18 + B2[8])) != 0) && ((bp8A64[i] & 1L << (27 + B3[8])) != 0)) {
                bp8A[count] = bp8A64[i];
                bp8B[count] = bp8B64[i];
                count++;
            }
        }
        //System.out.println("8:"+count);
    }

    private static final void initBasicPermutation(BasicFrame bs) {
        expl0 = bs.baseframe[0][ABC0[0]][ABC1[0]];
        expl1 = bs.baseframe[1][ABC0[1]][ABC1[1]];
        expl2 = bs.baseframe[2][ABC0[2]][ABC1[2]];
        expl3 = bs.baseframe[3][ABC0[3]][ABC1[3]];
        expl4 = bs.baseframe[4][ABC0[4]][ABC1[4]];
        expl5 = bs.baseframe[5][ABC0[5]][ABC1[5]];
        expl6 = bs.baseframe[6][ABC0[6]][ABC1[6]];
        expl7 = bs.baseframe[7][ABC0[7]][ABC1[7]];
        expl8 = bs.baseframe[8][ABC0[8]][ABC1[8]];
        for (int j = 0; j < 64; j++) {
            bp0A64[j] = frame[expl0[j]].A;
            bp1A64[j] = frame[expl1[j]].A;
            bp2A64[j] = frame[expl2[j]].A;
            bp3A64[j] = frame[expl3[j]].A;
            bp4A64[j] = frame[expl4[j]].A;
            bp5A64[j] = frame[expl5[j]].A;
            bp6A64[j] = frame[expl6[j]].A;
            bp7A64[j] = frame[expl7[j]].A;
            bp8A64[j] = frame[expl8[j]].A;
            bp0B64[j] = frame[expl0[j]].B;
            bp1B64[j] = frame[expl1[j]].B;
            bp2B64[j] = frame[expl2[j]].B;
            bp3B64[j] = frame[expl3[j]].B;
            bp4B64[j] = frame[expl4[j]].B;
            bp5B64[j] = frame[expl5[j]].B;
            bp6B64[j] = frame[expl6[j]].B;
            bp7B64[j] = frame[expl7[j]].B;
            bp8B64[j] = frame[expl8[j]].B;
        }
    }

    private static final void build() {

        long mA = 0;
        long mB = 0;

        for (int k0 = 0; k0 < 8; k0++) {
            mA |= bp0A[k0];
            mB |= bp0B[k0];
            for (int k1 = 0; k1 < 8; k1++) {
                if ((mA & bp1A[k1]) == 0 && (mB & bp1B[k1]) == 0) {
                    mA |= bp1A[k1];
                    mB |= bp1B[k1];
                    for (int k2 = 0; k2 < 8; k2++) {
                        //System.out.println("k0: "+k0+", k1: "+k1);
                        if ((mA & bp2A[k2]) == 0 && (mB & bp2B[k2]) == 0) {
                            mA |= bp2A[k2];
                            mB |= bp2B[k2];
                            for (int k3 = 0; k3 < 8; k3++) {
                                //System.out.println("k0: "+k0+", k1: "+k1+", k2: "+k2);
                                if ((mA & bp3A[k3]) == 0 && (mB & bp3B[k3]) == 0) {
                                    mA |= bp3A[k3];
                                    mB |= bp3B[k3];
                                    for (int k4 = 0; k4 < 8; k4++) {
                                        if ((mA & bp4A[k4]) == 0 && (mB & bp4B[k4]) == 0) {
                                            mA |= bp4A[k4];
                                            mB |= bp4B[k4];
                                            for (int k5 = 0; k5 < 8; k5++) {
                                                if ((mA & bp5A[k5]) == 0 && (mB & bp5B[k5]) == 0) {
                                                    mA |= bp5A[k5];
                                                    mB |= bp5B[k5];
                                                    for (int k6 = 0; k6 < 8; k6++) {
                                                        if ((mA & bp6A[k6]) == 0 && (mB & bp6B[k6]) == 0) {
                                                            mA |= bp6A[k6];
                                                            mB |= bp6B[k6];
                                                            for (int k7 = 0; k7 < 8; k7++) {
                                                                if ((mA & bp7A[k7]) == 0 && (mB & bp7B[k7]) == 0) {
                                                                    mA |= bp7A[k7];
                                                                    mB |= bp7B[k7];
                                                                    for (int k8 = 0; k8 < 8; k8++) {
                                                                        if ((mA & bp8A[k8]) == 0 && (mB & bp8B[k8]) == 0) {
                                                                            //m.fill(bp8[k8]]);
                                                                            totalSolution++;
                                                                            if (checkMinOptM9(
                                                                                    bp0A[k0],
                                                                                    bp1A[k1],
                                                                                    bp2A[k2],
                                                                                    bp3A[k3],
                                                                                    bp4A[k4],
                                                                                    bp5A[k5],
                                                                                    bp6A[k6],
                                                                                    bp7A[k7])) {
                                                                                totalNormalized++;
                                                                            }
                                                                            //m.empty(bp8[k8]]);
                                                                            break;
                                                                        }
                                                                    }
                                                                    mA &= ~bp7A[k7];
                                                                    mB &= ~bp7B[k7];
                                                                }
                                                            }
                                                            mA &= ~bp6A[k6];
                                                            mB &= ~bp6B[k6];
                                                        }
                                                    }
                                                    mA &= ~bp5A[k5];
                                                    mB &= ~bp5B[k5];
                                                }
                                            }
                                            mA &= ~bp4A[k4];
                                            mB &= ~bp4B[k4];
                                        }
                                    }
                                    mA &= ~bp3A[k3];
                                    mB &= ~bp3B[k3];
                                }
                            }
                            mA &= ~bp2A[k2];
                            mB &= ~bp2B[k2];
                        }
                    }
                    mA &= ~bp1A[k1];
                    mB &= ~bp1B[k1];
                }
            }
            mA &= ~bp0A[k0];
            mB &= ~bp0B[k0];
        }
    }

    private static final boolean checkMinOptM9( // ne marche que si b6 et b8 sont complets !!!
            long A0,
            long A1,
            long A2,
            long A3,
            long A4,
            long A5,
            long A6,
            long A7) {
        a[0] = A0;
        a[1] = A1;
        a[2] = A2;
        a[3] = A3;
        a[4] = A4;
        a[5] = A5;
        a[6] = A6;
        a[7] = A7;

        b6 = 999999999;
        b8 = 999999999;

        for (int k = 0; k < 8; k++) {  // b6
            for (int i = 0; i < 9; i++) {
                if ((a[k] & (1L << i)) != 0) {
                    b6 = b6 + p10[i] * (k - 8);
                    break;
                }
            }
        }
        for (int k = 0; k < 8; k++) {  // b8
            for (int i = 0; i < 9; i++) {
                if ((a[k] & (1L << i + 9)) != 0) {
                    b8 = b8 + p10[i] * (k - 8);
                    break;
                }
            }
        }

        return ((minb5b9 <= getNormOf(b6) && minb5b9 <= getNormOf(b8)));
    }

    private static final void dump() {
        for (int li = 0; li < 9; li++) {
            for (int co = 0; co < 9; co++) {
                if (m9[li][co] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print(m9[li][co]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }


}

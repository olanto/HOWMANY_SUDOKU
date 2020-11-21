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


/**
 * Simple block represntation
 */
public class M3x3 {

    private int[][] m = new int[3][3];

    public M3x3() {
    }

    /**
     * Creates a new instance of M3x3
     *  bloc 123
     *       456
     *       789
     */
    public M3x3(int n) {
        for (int li = 2; li >= 0; li--) {
            for (int co = 2; co >= 0; co--) {
                m[li][co] = n % 10;
                n /= 10;
            }
        }
        //dump();
    }

    public static void main(String[] args) {

        M3x3 m33 = new M3x3(487954321);
        m33.dump();
        m33.normalize();
        m33.dump();

        m33 = new M3x3(789456231);
        m33.dump();
        m33.normalize();
        m33.dump();
    }

    /**
     * seting an instance of M3x3
     */
    public final void set(int n) {
        for (int li = 2; li >= 0; li--) {
            for (int co = 2; co >= 0; co--) {
                m[li][co] = n % 10;
                n /= 10;
            }
        }
        //dump();
    }

    public final boolean isLineCompatible(M3x3 x) {
        for (int li = 0; li < 3; li++) {
            for (int co = 0; co < 3; co++) {
                for (int cox = 0; cox < 3; cox++) {
                    if (m[li][co] == x.m[li][cox]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final boolean isColumnCompatible(M3x3 x) {
        for (int co = 0; co < 3; co++) {
            for (int li = 0; li < 3; li++) {
                for (int lix = 0; lix < 3; lix++) {
                    if (m[li][co] == x.m[lix][co]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static final boolean isSumLineOK(M3x3 a, M3x3 b, M3x3 c) {
        for (int li = 0; li < 3; li++) {
            int s = 0;
            for (int co = 0; co < 3; co++) {
                s += a.m[li][co] + b.m[li][co] + c.m[li][co];
            }
            if (s != 45) {
                return false;
            }

        }
        return true;
    }

    public static final boolean isSumColOK(M3x3 a, M3x3 b, M3x3 c) {
        for (int co = 0; co < 3; co++) {
            int s = 0;
            for (int li = 0; li < 3; li++) {
                s += a.m[li][co] + b.m[li][co] + c.m[li][co];
            }
            if (s != 45) {
                return false;
            }

        }
        return true;
    }

    final void normalize() {
        int li = 0, co = 0;
        // cherche le 1
        for (li = 0; li < 3; li++) {
            for (co = 0; co < 3; co++) {
                if (m[li][co] == 1) {
                    break;
                }
            }
            if (co != 3 && m[li][co] == 1) {
                break;
            }
        }
        // met le 1 en premi�re position
        permuteLine(0, li);
        permuteColumn(0, co);
        //dump();
        // ordonne pos 2 3 ligne 1
        if (m[0][1] > m[0][2]) {
            permuteColumn(1, 2);
        }
        //dump();
        // ordonne pos 2 3 colonne 1
        if (m[1][0] > m[2][0]) {
            permuteLine(1, 2);
        }
        //dump();
    }

    final int toInt() {
        int nb = 0;
        for (int li = 0; li < 3; li++) {
            for (int co = 0; co < 3; co++) {
                nb = 10 * nb + m[li][co];
            }
        }
        return nb;
    }

    final void dump() {
        for (int li = 0; li < 3; li++) {
            for (int co = 0; co < 3; co++) {
                System.out.print(m[li][co]);
            }
            System.out.println();
        }
        System.out.println();
    }

    final void permuteLine(int i1, int i2) {
        for (int co = 0; co < 3; co++) {
            int temp = m[i2][co];
            m[i2][co] = m[i1][co];
            m[i1][co] = temp;
        }
    }

    final void permuteColumn(int c1, int c2) {
        for (int li = 0; li < 3; li++) {
            int temp = m[li][c2];
            m[li][c2] = m[li][c1];
            m[li][c1] = temp;
        }
    }

}

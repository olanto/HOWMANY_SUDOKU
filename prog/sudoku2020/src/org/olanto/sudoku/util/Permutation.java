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

import java.util.HashMap;

/**
 *
 * permutation inside a block and normalisation
 */
public class Permutation {

    final static int FACT9 = 362880;
    final static int FACT9M1 = 362880 - 1;
    public final static int NORMFACT9 = 362880 / 36;
    final static int NORMFACT9M1 = NORMFACT9 - 1;
    static public HashMap<Integer, Integer> revFact9 = new HashMap<>(362880);
    static private int[] start = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    static public int[] perm9 = new int[FACT9];
    static private int[] normOf = new int[FACT9];  //pointeur sur la normalisation
    static public int[] normPerm9 = new int[NORMFACT9]; // les nombres normalis�s et tri�s
    static int count;

    /**
     * Creates a new instance of Permutation
     */
       public static void main(String[] args) {
        initPermutation();
        System.out.println("normalize:"+perm9[221212]);
        System.out.println(normalize(perm9[221212]));
        System.out.println(perm9[normOf[221212]]);
        System.out.println(getIdxOfValueOfPerm9(125783964));
        System.out.println(perm9[normOf[getIdxOfValueOfPerm9(125783964)]]);
         System.out.println(revFact9.get(125783964));
   }
      public static void initPermutation() {
        count = 0;
        permute(start, 0, start.length);

        System.out.println("Perm9: " + count);
        java.util.Arrays.sort(perm9);
        normalizePerm9();
        keepNormalisedPerm9();
        System.out.println("init reverse");
    
        for (int i = 0; i < 362880; i++) {
            revFact9.put(perm9[i], i);
        }

        
    }

    private static void keepNormalisedPerm9() {
        int[] temp = normOf.clone();
        java.util.Arrays.sort(temp);
        for (int i = 0; i < NORMFACT9; i++) {
            normPerm9[i] = perm9[temp[36 * i]];
            // System.out.println(normPerm9[i]);

        }
    }

    private static void permute(int[] s, int from, int size) {
        //keepPermutation(s);
        //System.out.println("from: "+from+" size: "+size);
        if (size == 1) {
            keepPermutation(s);
        } else {
            for (int k = 0; k < size; k++) {
                // rotate
                int tempo = s[from];
                for (int i = from; i < from + size - 1; i++) {
                    s[i] = s[i + 1];
                }
                s[from + size - 1] = tempo;
                // permute n-1
                permute(s, from, size - 1);
            }
        }
    }

    private static void keepPermutation(int[] s) {
        //        System.out.print(count+" : ");
        //        for(int i=0;i<s.length;i++)
        //            System.out.print(s[i]+" ");
        //        System.out.println();
        int nb = 0;
        for (int i = 0; i < s.length; i++) {
            nb = 10 * nb + s[i];
        }
        perm9[count] = nb;
        count++;
    }

    private final static int getIdxOfValueOfPerm9(int v) {// search nearest upper value v in vector perm9
//        System.out.println("search for :"+v);
        int i, min = 0, max = FACT9M1;
        while (true) {
            i = (min + max) >> 1; // div 2
            //System.out.println("min:"+min+" i:"+i+" max:"+max+"  r[i]:"+r[i]);
            if (perm9[i] == v) {
                return i;
            }
            if (min + 1 == max) {
                return max;
            }
            if (perm9[i] < v) {
                min = i;
            } else {
                max = i;
            }
        }

    }
    
    private final static int getFastIdxOfValueOfPerm9(int v) {// search nearest upper value v in vector perm9

return revFact9.get(v);
    }

    public final static int getIdxOfValueOfNormPerm9(int v) {// search  value v in vector perm9
        //System.out.println("search for :"+v);
        int i, min = 0, max = NORMFACT9;
        //System.out.println(" ------ min:"+min+" max:"+max);
        while (true) {
            i = (min + max) >> 1; // div 2
            //System.out.println("min:"+min+" i:"+i+" max:"+max+"  r[i]:"+r[i]);
            if (normPerm9[i] == v) {
                return i;
            }
            if (min + 1 == max) {
                return max;
            };
            if (normPerm9[i] < v) {
                min = i;
            } else {
                max = i;
            }
        }
    }

    private final static void normalizePerm9() { // normalise
        for (int i = 0; i < FACT9; i++) {
            int norm = normalize(perm9[i]);
            normOf[i] = getIdxOfValueOfPerm9(norm); // on pointe sur la permutation normalis�e!
        }
    }

    public final static int getNormOf(int v) { // normalise
        return perm9[normOf[revFact9.get(v)]];
    }

    public final static int normalize(int v) { // normalise
        // 1 m x
        // m x x
        // x x x
        M3x3 a = new M3x3(v);
        a.normalize();
        return a.toInt();
    }

    public final static int getValueOfNormId(int i) {
        return normPerm9[i];
    }

}

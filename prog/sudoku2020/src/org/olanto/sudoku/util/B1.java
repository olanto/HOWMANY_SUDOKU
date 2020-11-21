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

import java.io.*;
import static org.olanto.sudoku.util.Permutation.*;

/**
 * init B1 blocks
 */
public class B1  {
    
    public static final  M3x3 B1=new M3x3(123456789);
    static final int B1LINEMAX=12096;
    
    static public int[] B1Line=new int[B1LINEMAX];  // trie car perm9 l'est aussi
    
    public static void initB1() {
        M3x3 B1Comptatible=new M3x3();
        // pour l'horizontal
            int countB1Comptatible=0;
            for(int j=0;j<FACT9;j++){
                B1Comptatible.set(perm9[j]);
                if (B1.isLineCompatible(B1Comptatible)) {
                    B1Line[countB1Comptatible]=perm9[j];
                    countB1Comptatible++;
                }
                
            }
            System.out.println("#B1Comptatible="+countB1Comptatible);
        
        
    }
    
    
    
}

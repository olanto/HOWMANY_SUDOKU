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
 *
 * toutes les permutations possibles pour toutes les cases du B1
 */

public class SDKFrame {
    
    int count=0;
    M9x9[] frame=new M9x9[9*5184]; 
    
    /** Creates a new instance of buildSuDoKu */
    public SDKFrame() {
        initBasicPermutation();
    }
    
    void initBasicPermutation(){
        BasicPermutation bp;
            int k=0;

        bp= new BasicPermutation(0,0);
        
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        bp= new BasicPermutation(0,1);
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        bp= new BasicPermutation(0,2);
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        bp= new BasicPermutation(1,0);
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        bp= new BasicPermutation(1,1);
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        bp= new BasicPermutation(1,2);
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        bp= new BasicPermutation(2,0);
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        bp= new BasicPermutation(2,1);
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        bp= new BasicPermutation(2,2);
        for(int i=0;i<5184;i++){frame[k]=bp.sp[i];k++;}
        
        for (int i=0;i<9*5184;i++) {frame[i].setAltRep();}
    }
    
    

}

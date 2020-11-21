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
 * toutes les permutations possibles par rapport à une case de B1
 */

public class BasicPermutation {
    
    int count=0;
    M9x9[] sp=new M9x9[5184]; // 5184=6*6*3*3*4*2*2  !
    
    
       public static void main(String[] args) {
           BasicPermutation bp= new BasicPermutation(0,0);
           bp.dump();
          }
 
    
    /** Creates a new instance of BasicPermutation */
       
       public void dump(){
         for (int i=0;i<10;i++) 
             sp[i].dump();
       }
       
    public BasicPermutation(int li, int co) {
        
        M9x9 m=new M9x9();
        m.add(li,co);
        
        //  1  2  3
        //  4  5  6
        //  7  8  9
        
        // block 4
        for (int li4=3;li4<6;li4++)
            if(m.isLinePossible(li4)){
            for (int co4=0;co4<3;co4++)
                if(m.isColumnPossible(co4)){
                m.add(li4,co4);
                //block 2
                for (int li2=0;li2<3;li2++)
                    if(m.isLinePossible(li2)){
                    for (int co2=3;co2<6;co2++)
                        if(m.isColumnPossible(co2)){
                        m.add(li2,co2);
                        //block 3
                        for (int li3=0;li3<3;li3++)
                            if(m.isLinePossible(li3)){
                            for (int co3=6;co3<9;co3++)
                                if(m.isColumnPossible(co3)){
                                m.add(li3,co3);
                                //block 7
                                for (int li7=6;li7<9;li7++)
                                    if(m.isLinePossible(li7)){
                                    for (int co7=0;co7<3;co7++)
                                        if(m.isColumnPossible(co7)){
                                        m.add(li7,co7);
                                        //block 5
                                        for (int li5=3;li5<6;li5++)
                                            if(m.isLinePossible(li5)){
                                            for (int co5=3;co5<6;co5++)
                                                if(m.isColumnPossible(co5)){
                                                m.add(li5,co5);
                                                //block6
                                                for (int li6=3;li6<6;li6++)
                                                    if(m.isLinePossible(li6)){
                                                    for (int co6=6;co6<9;co6++)
                                                        if(m.isColumnPossible(co6)){
                                                        m.add(li6,co6);
                                                        //block8
                                                        for (int li8=6;li8<9;li8++)
                                                            if(m.isLinePossible(li8)){
                                                            for (int co8=3;co8<6;co8++)
                                                                if(m.isColumnPossible(co8)){
                                                                m.add(li8,co8);
                                                                //block9
                                                                for (int li9=6;li9<9;li9++)
                                                                    if(m.isLinePossible(li9)){
                                                                    for (int co9=6;co9<9;co9++)
                                                                        if(m.isColumnPossible(co9)){
                                                                        
                                                                        m.add(li9,co9);
                                                                        // on a trouv� une solution partielle
                                                                         // m.dump();
                                                                        sp[count]=m.getACopy();
                                                                                
                                                                        count++;
                                                                        //
                                                                        m.remove(li9,co9);
                                                                        }}
                                                                m.remove(li8,co8);
                                                                }}
                                                        m.remove(li6,co6);
                                                        }}
                                                m.remove(li5,co5);
                                                }}
                                        m.remove(li7,co7);
                                        }}
                                m.remove(li3,co3);
                                }}
                        m.remove(li2,co2);
                        }}
                m.remove(li4,co4);
                }}
        
        System.out.println("count:"+count);
        
    }
    
    
}

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
 * SDK representation and boolean representation
 */
public class M9x9 {
    
    private boolean[] fco=new boolean[9];
    private boolean[] fli=new boolean[9];
    private boolean[][] m =new boolean [9][9];
    
    long A,B;
    
    public static long countIsPossible;
    public static long countFill;
    
    
    /** Creates a new instance of M9x9 */
    public M9x9() {
    }
    
    // 0B 2A 3A
    // 4A 5A 0A
    // 1B 1A 2B
   
     public static void main(String[] args) {

        
        M9x9 m99 = new M9x9();
        m99.setAltRep();
        System.out.println(m99.A+"."+m99.B);
        m99.fill(0,0);
        m99.fill(0,5);
        m99.fill(8,5);
        m99.fill(8,8);
        m99.dump();
        m99.setAltRep();
        System.out.println(m99.A+"."+m99.B);
      }
  
    
    final void setAltRep(){
        LocalBitSet altrep=new LocalBitSet();
        setAltRep33(altrep,0, 0, 64);  //0B
        setAltRep33(altrep,1, 2, 0);  //2A
        setAltRep33(altrep,2, 3, 0);  //3A
        setAltRep33(altrep,3, 4, 0);  //4A  
        setAltRep33(altrep,4, 5, 0);  //5A
        setAltRep33(altrep,5, 1, 0);  //0A
        setAltRep33(altrep,6, 1, 64);  //1B  
        setAltRep33(altrep,7, 0, 0);  //1A
        setAltRep33(altrep,8, 2, 64);  //2B
         A=altrep.A;
       B=altrep.B;
    }
    
    final void setAltRep33(LocalBitSet altrep,int blockid, int blockbin, int offset){
        for (int li=0;li<3;li++)
            for (int co=0;co<3;co++)
                if(m[li+(blockid/3)*3][co+(blockid%3)*3])
                    altrep.set(li*3+co+blockbin*9+offset);
    }
    
    
    final void add(int li, int co){
        if (fco[co])System.out.println("error add column:"+co);
        if (fli[li])System.out.println("error add line:"+li);
        m[li][co]=true;
        fco[co]=true;
        fli[li]=true;
    }
    
    final void fill(int li, int co){
        //    if (m[li][co])System.out.println("error filling:"+li+","+co);
        m[li][co]=true;
    }
    
//    final void fill(M9x9 x){
//        countFill++;
//        for (int li=0;li<9;li++)
//            for (int co=0;co<9;co++)
//                if (x.m[li][co])
//                    fill(li,co);
//        setAltRep();
//        
//    }

        final void fill(M9x9 x){
    //    countFill++;
    //    altrep.or(x.altrep);

        A |= x.A;
        B |= x.B;

    }

    
    final void empty(int li, int co){
        //    if (!m[li][co])System.out.println("error empty:"+li+","+co);
        m[li][co]=false;
    }
    
//    final void empty(M9x9 x){
//        for (int li=0;li<9;li++)
//            for (int co=0;co<9;co++)
//                if (x.m[li][co])
//                    empty(li,co);
//        setAltRep();
//    }
    final void empty(M9x9 x){
    //    altrep.andNot(x.altrep);
        
        A &= ~x.A;
        B &= ~x.B;

        
    }
 
    
    //    final boolean isPossible(M9x9 x){
    //        countIsPossible++;
    //        for (int li=0;li<9;li++)
    //            for (int co=0;co<9;co++)
    //                if (x.m[li][co]&& this.m[li][co])return false;
    //        return true;
    //    }
    
    final boolean isPossible(M9x9 x){
   //     countIsPossible++;
        
//        tempo.clear();
//        tempo.or(x.altrep);
//        tempo.and(altrep);
//        return tempo.isEmpty();
        
//        return BitSet.ok(x.altrep,altrep);
        
      return ((x.A & A)==0 && (x.B & B)==0) ;
    }
    
    
    final void dump(){
        for (int li=0;li<9;li++){
            for (int co=0;co<9;co++)
                if (m[li][co])System.out.print("X");
                else System.out.print("-");
            System.out.println();
        }
        System.out.println();
    }
    
    
    final void  remove(int li, int co){
        if (!fco[co])System.out.println("error removing column:"+co);
        if (!fli[li])System.out.println("error removing line:"+li);
        m[li][co]=false;
        fco[co]=false;
        fli[li]=false;
    }
    
    final boolean isPossible(int li, int co){
        return (!fco[co]&&!fli[li]);
    }
    
    final boolean isLinePossible(int li){
        return (!fli[li]);
    }
    
    final boolean isColumnPossible(int co){
        return (!fco[co]);
    }
    
    final M9x9 getACopy(){
        M9x9 copy =new M9x9();
        for (int li=0;li<9;li++)
            for (int co=0;co<9;co++)
                if(m[li][co])
                    copy.add(li,co);
        return copy;
    }
    
    final boolean getM(int li, int co){
        return m[li][co];
    }
}

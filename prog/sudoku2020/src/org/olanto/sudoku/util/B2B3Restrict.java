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
 * init B2 et B3 with fixed B5 and B9
 */
public class B2B3Restrict  {
    
    // b1 - b5/9 is determine by normPerm9[i] - [][j] point on b2/3 compatible vertical b5/b9
    static int[][] B1B59Compatible=new int[NORMFACT9][]; // 448 is a maximum (400,392)
    
    static private int totalNormalized;
    static private int totalSolution;
    
    static private long rootSolution;
    static private long rootNormalized;
    
    public static void main(String[] args) {
        initB2B3Restrict();
    }
        

    
    public static final void reset(){
        rootSolution=0;
        rootNormalized=0;
    }
    
    
    public static final String finish(){
        return "SS:"+rootSolution+"\t"+
               "SN:"+rootNormalized+"\n" ;
    }
    
    
    public static void initB2B3Restrict() {
        initHVCompatible();
        System.out.println("initB2B3Restrict OK");
    }
    
    public static String countAll( SDKFrame sdkf, BasicFrame bf,int b5, int b9) {
        //Timer t1;
        
        //t1=new Timer("initB5B9");
        
        initB5B9(sdkf, bf,b5,b9);
        
        //t1.stop();
        totalNormalized=(int)buildBasicSolution_Opt1_RestrictB23.rootNormalized;
        totalSolution=(int)buildBasicSolution_Opt1_RestrictB23.rootSolution;
        rootNormalized+=totalNormalized;
        rootSolution+=totalSolution;
        //                    System.out.println("totalSolution:"+totalSolution);
        //                    System.out.println("totalNormalized:"+totalNormalized);
        return "TS:"+totalSolution+"\t"+
                "TN:"+totalNormalized+"\t" ;
        
        
    }
    public static void initB5B9(SDKFrame sdkf, BasicFrame bf,int b5, int b9) {
        int count=0;
        int[] B2set=B1B59Compatible[b5];
        int[] B3set=B1B59Compatible[b9];
        M3x3 B3=new M3x3();
        M3x3 B2=new M3x3();
          
//        System.out.println("-------- b5:"+b5 +" b9:"+b9);    
//        System.out.println("B2set size:"+B2set.length);
//        System.out.println("B3set size:"+B3set.length);
        
        buildBasicSolution_Opt1_RestrictB23.reset();
        for(int i=0;i<B2set.length;i++){
            B2.set(B2set[i]);
            for(int j=0;j<B3set.length;j++){
                B3.set(B3set[j]);
                if (B2.isLineCompatible(B3)){
                    //System.out.println("B2:"+B2set[i]+" B3:"+B3set[j]);
                    buildBasicSolution_Opt1_RestrictB23.countAll(sdkf, bf, normPerm9[b5], normPerm9[b9], B2set[i],B3set[j]);
                    count++;
                }
            }
        }
        //System.out.println("B5:"+b5+" B9:"+b9+" #B2B3: "+count);
       
        
    }
    
    public static void initHVCompatible() {
        M3x3 B3=new M3x3();
        M3x3 B2=new M3x3();
        for(int i=0;i<NORMFACT9;i++){
            B1B59Compatible[i]=new int[448];
            int countB2=0;
            B3.set(normPerm9[i]);
            for(int j=0;j<B1.B1LINEMAX;j++){
                B2.set(B1.B1Line[j]);
                if (B3.isColumnCompatible(B2)) {
                    B1B59Compatible[i][countB2]=B1.B1Line[j];
                    //System.out.println(B1.B1Line[j]);
                    countB2++;
                }
            }
            B1B59Compatible[i]=copyVector(countB2,B1B59Compatible[i]);
        }
    }
    
    public static final int[] copyVector(int l, int[] p) {
        int[] r = new int[l];
        int maxi = Math.min(l,p.length); //  if p.lenght<l last will be fill with 0
        System.arraycopy(p, 0, r, 0, maxi);
        return r;
    }
    
    /** comput intersection between 2 vectors. vector needs to be sorted
     * @param r1 vecteur1
     * @param r2 vecteur2
     * @return intersection of 1 and 2
     */
    
    public static int[] and(int[] r1, int[] r2) { // migrate
        if (r2==null||r1==null) return new int[0];
        int id = 0;
        int il1 = r1.length;
        int il2 = r2.length;
        int wc1 = 0;int wc2 = 0;
        int doc[] = new int[Math.min(il1,il2)];
        while (true) { // merge sort  r1 and r2 must be ordered !!!!!
            if (wc1 >= il1) break;
            if (wc2 >= il2) break;
            // System.out.println(wc1+", "+r1[wc1]+", "+wc2+", "+r2[wc2]);
            if (r1[wc1] == r2[wc2]) { // and ok
                doc[id] = r1[wc1];// the first operand stay the reference for occurrences
                wc1 ++; wc2 ++; id++;
            } else
                if (r1[wc1] < r2[wc2]) wc1 ++; else wc2 ++;
        }
        return copyVector(id, doc);
    }
    
    
    
}

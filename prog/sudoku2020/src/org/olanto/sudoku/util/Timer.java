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
 * Gestion de timer en milli seconde.
* Une classe pour d�clencher un chronom�tre et pour mesurer facilement l'efficacit� du code.
* Par exemple: 
* <pre> 
* Timer t1 = new Timer("section A du code");  // le chrono a d�marrer!
*    ...section A ... 
* t1.stop(); // affiche le temps en milliseconde ...
* </pre> 
* On peut r�utiliser le m�me chronom�tre plusieurs fois, avec la m�thode restart()
* @author Jacques Guyot 
* @version 1.1 
*/ 
public class Timer{

private long start;
private String activity;

/**
* cr�e un chronom�tre. Et puis le d�marre et affiche dans 
* la console le commentaire associ�
* 
* @param s le commentaire associ� au chrono. 
*/
  public Timer(String s){
	   activity=s;
	   start=System.currentTimeMillis();
	   System.out.println("START: "+activity);
	}

/**
* stope le chronom�tre. Et affiche dans la console le commentaire associ�
* et le temps mesur� en milliseconde
* 
*/ 

   public void  stop() {
		start=System.currentTimeMillis()-start;
		 System.out.println("STOP: "+activity+" - "+start+" ms");
	}
   /**
    * red�marre le chronom�tre. Et affiche dans la console le commentaire associ�
    * @param s le commentaire associ� avec le chronom�tre
    */ 
	public void  restart(String s) {
	   activity=s;
	   start=System.currentTimeMillis();
	   System.out.println("START: "+activity);
	}
	}
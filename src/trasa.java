

import java.awt.* ;
//import java.awt.List;
import java.util.*;
import java.lang.Math;

public class trasa {
	
	ArrayList<Integer> tX = new ArrayList<Integer>();
	ArrayList<Integer> tY = new ArrayList<Integer>();
	
	public int[] tabX;
	public int[] tabY;
	
	
	public void generujKolo(int promien, int srX, int srY){
		int pX, pY;
		float wspX=0, wspY=0;
		
		pX = srX+promien;
		pY = srY;
		float pX2=pX, pY2=pY;
		
		int obw = (int)(Math.PI*promien*2);
		int petla = obw/4;
		for(int i=1; i<petla; i++){
			wspX = (float)((petla-(float)i)/petla);
			wspY = (float)i/petla;
			pX2 += wspX;
			pY2 += wspY;
			pX = (int)Math.floor(pX2);
			pY = (int)Math.floor(pY2);
			if(i>1){
				if(tX.get(tX.size()-1)!=pX && tY.get(tY.size()-1)!=pY){
					tX.add(pX);
					tY.add(pY);
				}
			}
			else {
				tX.add(pX);
				tY.add(pY);				
			}
		}
		for(int i=0; i<petla; i++){
			wspY = (float)((petla-(float)i)/petla);
			wspX = (float)i/petla;
			pX2 -= wspX;
			pY2 += wspY;
			pX = (int)Math.floor(pX2);
			pY = (int)Math.floor(pY2);
			if(i>1){
				if(tX.get(tX.size()-1)!=pX && tY.get(tY.size()-1)!=pY){
					tX.add(pX);
					tY.add(pY);
				}
			}
			else {
				tX.add(pX);
				tY.add(pY);				
			}
		}
		for(int i=0; i<petla; i++){
			wspX = (float)((petla-(float)i)/petla);
			wspY = (float)i/petla;
			pX2 -= wspX;
			pY2 -= wspY;
			pX = (int)Math.floor(pX2);
			pY = (int)Math.floor(pY2);
			if(i>1){
				if(tX.get(tX.size()-1)!=pX && tY.get(tY.size()-1)!=pY){
					tX.add(pX);
					tY.add(pY);
				}
			}
			else {
				tX.add(pX);
				tY.add(pY);				
			}
		}
		for(int i=0; i<petla; i++){
			wspY = (float)((petla-(float)i)/petla);
			wspX = (float)i/petla;
			pX2 += wspX;
			pY2 -= wspY;
			pX = (int)Math.floor(pX2);
			pY = (int)Math.floor(pY2);
			if(i>1){
				if(tX.get(tX.size()-1)!=pX && tY.get(tY.size()-1)!=pY){
					tX.add(pX);
					tY.add(pY);
				}
			}
			else {
				tX.add(pX);
				tY.add(pY);				
			}
		}
		
		
	    tabX = new int[tX.size()];
	    tabY = new int[tY.size()];
	     
	    for(int i=0; i<tX.size(); i++){
	    	tabX[i] = tX.get(i);
	    	tabY[i] = tY.get(i);
	    }
		
		
	}

	 public void BresenhamLine(int x1, int y1, int x2, int y2)
	 { 
	     // zmienne pomocnicze

		
		 int d, dx, dy, ai, bi, xi, yi;
	     int x = x1, y = y1;
	     // ustalenie kierunku rysowania
	     if (x1 < x2)
	     { 
	         xi = 1;
	         dx = x2 - x1;
	     } 
	     else
	     { 
	         xi = -1;
	         dx = x1 - x2;
	     }
	     // ustalenie kierunku rysowania
	     if (y1 < y2)
	     { 
	         yi = 1;
	         dy = y2 - y1;
	     } 
	     else
	     { 
	         yi = -1;
	         dy = y1 - y2;
	     }
	     // pierwszy piksel
	     
	     tX.add(x);
	     tY.add(y);
	     
	     
	     // oœ wiod¹ca OX
	     if (dx > dy)
	     {
	         ai = (dy - dx) * 2;
	         bi = dy * 2;
	         d = bi - dx;
	         // pêtla po kolejnych x
	         while (x != x2)
	         { 
	             // test wspó³czynnika
	             if (d >= 0)
	             { 
	                 x += xi;
	                 y += yi;
	                 d += ai;
	             } 
	             else
	             {
	                 d += bi;
	                 x += xi;
	             }
	    	     tX.add(x);
	    	     tY.add(y);
	         }
	     } 
	     // oœ wiod¹ca OY
	     else
	     { 
	         ai = ( dx - dy ) * 2;
	         bi = dx * 2;
	         d = bi - dy;
	         // pêtla po kolejnych y
	         while (y != y2)
	         { 
	             // test wspó³czynnika
	             if (d >= 0)
	             { 
	                 x += xi;
	                 y += yi;
	                 d += ai;
	             }
	             else
	             {
	                 d += bi;
	                 y += yi;
	             }
	    	     tX.add(x);
	    	     tY.add(y);
	    	     
	    	     
	    	     
	         }
	     }
	     tabX = new int[tX.size()];
	     tabY = new int[tY.size()];
	     
	     for(int i=0; i<tX.size(); i++){
	    	 tabX[i] = tX.get(i);
	    	 tabY[i] = tY.get(i);
	     }
	     
	     //tabX[] = tX.toArray(new int[tX.size]);
	     //tY.toArray();

	 }
	 
	 public void wyswietl(){
		 for(int i=0; i<tabX.length; i++){
			 System.out.println(tabX[i]+"  "+tabY[i]);
		 }
	 }
}

import javax.swing.*;
import  java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GUI extends JFrame {
	
	public boolean resetter = false  ;
	
	public boolean flagger = false ; 
	
	 Date startDate = new Date() ; 
	 int spacing = 2 ; 
	 int neighs = 0 ; 
	 public int mx = -100 , my = -100; 
	 
	 // The Coedoni painting to Samily Face
	 public int   smileyx = 605  ; 
	 public int   smileyy = 5  ; 
	 
	 // The Coedoni painting to timePlace

	 public int timeX = 1130 ; 
	 public int timeY = 5 ;
	 
	 public int smileyCenterX = smileyx + 35  ; 
	 public int smileyCenterY = smileyy + 35  ; 
	 
	 
	 // Cordo Flage place
	 public int flaggerX = 445 ; 
	 public int flaggerY  = 5; 
	 
	 public int flaggerCenterX = flaggerX + 35 ; 
	 public int flaggerCenterY = flaggerY + 35 ; 
	 
	 
	 
	 public int vicMesX = 700 ; 
	 public int vicMesY = -50 ; 
	 Date endDate; 
	 
	 public int sec = 0 ; 
	 
	 
	 public boolean happiness = true ; 
	 public boolean victory = false  ;
	 public boolean defeat = false  ;
	 String vicMes = "Nothing Yet !"; 
	 
	 
	Random rand = new Random(); 
	int [][]mines = new int [16][9]; // if non container a mine v is 0 // if contant a bonb is = 1 
	int [][]neighbours = new int [16][9]; // 0 up to 8 = 9  how many of the neighbor how box container mine  
	
	boolean [][] revealed = new boolean[16][9]; //Boxes is Clicked Or Elemeni
	boolean [][] flagged = new boolean[16][9]; 
	   
	 
	
	//-------------------Constructeur---------------------------------------------  
     public GUI(){
    	this.setTitle("Démineur *_*");
    	this.setSize(1286 , 829); 
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
    	 //this.setResizable(false);
         Board board = new Board();
         this.setContentPane(board); 
         //--------Move Mouse---------
         Move move = new Move() ; 
         this.addMouseMotionListener(move);
         //--------Click Mouse---------
         Click  click = new Click();
          this.addMouseListener(click);
          
        //----Creating binb and normal Boxes-----------------------  
      	for(int i =0 ; i<16;i++  ){
	 		for(int j=0;j<9;j++ ){
	 		 	if (rand.nextInt(100) < 20) { // if any nember less thene 20 is a bonb  // 20% is bonbe
					mines[i][j] = 1 ;
				}else{
					mines[i][j] = 0 ;
				} 
	 		 	revealed[i][j] = false ; 
	 		 	
	 		}	
	 	}
      	
      	
      	//--------------------
    	for(int i =0 ; i<16;i++  ){
	 		for(int j=0;j<9;j++ ){
	 			neighs =  0 ; 
	 			for(int m=0 ; m<16;m++  ){
	 		 		for(int n=0;n<9;n++ ){
	 		 	       if(!(m == i && n == j )){
	 		 	          if(isN(i,j,m,n) == true)
	 		 	    	neighs ++ ; 
	 		 		}	
	 		 	}
	 		}
	 	      	neighbours[i][j] = neighs ; 
	 		 	
	 		}	
	 	}
      	
      	 
     } 
     
     
     
     //-----------BOARD--------inSide The JPANEL--------------------------------------------------------------------
     
     public class Board extends JPanel {
    	 public void paintComponent(Graphics g){
    		 g.setColor(new Color(170, 201, 252));	 
    		 
    //	 g.setColor(Color.DARK_GRAY);
    	 g.fillRect(0, 0, 1280, 800);
    	
    	 	for(int i =0 ; i<16;i++  ){  // Big for 
    	 		for(int j=0;j<9;j++ ){
    	 		  g.setColor(new Color(136, 140, 205));	 
    	 			// g.setColor(Color.GRAY);
    	 		/*	 if (mines[i][j] == 1) {
						g.setColor(Color.yellow);
					}*/
    	 			 
    	 			  if (revealed[i][j] == true) {
 						g.setColor(Color.white);
 						if(mines[i][j] == 1 ){
 							g.setColor(Color.red);
 							
 						}
 					} 
    	 			 
    	 			 
    	 			 //-- Replay V3   for color Our Boxes  __One By One
   if (mx >= spacing+i*80 && mx < spacing+i*80+80-2*spacing && my >= spacing+j*80+80+26 && my < spacing+j*80+26+80+80-2*spacing) 
    	 			       { 
    	 				  g.setColor(Color.lightGray);
    	 				   }  
				 
    	 			
    	 			g.fillRect(spacing+i*80, spacing+j*80+80, 80-2*spacing, 80-2*spacing); 
                    
    	 			
    	 			
    	 			
    	 			if(revealed[i][j] == true){
                        	g.setColor(Color.black);
                        	if(mines[i][j] == 0 && neighbours[i][j] != 0) {
                        		if(neighbours[i][j] == 1){
                        			g.setColor(Color.BLUE);
                        			}else if(neighbours[i][j] == 2 ){ 
                        				g.setColor(Color.green);
                        			} else if(neighbours[i][j] == 3 ){ 
                        				g.setColor(Color.red);
                        			} else if(neighbours[i][j] == 4){ 
                        				g.setColor(new Color(0, 0, 128));
                        			} else if(neighbours[i][j] == 5 ){ 
                        				g.setColor(new Color(178, 34, 34));
                        			} else if(neighbours[i][j] == 6 ){ 
                        				g.setColor(new Color(72, 209, 204));
                        			} /*else if(neighbours[i][j] == 7 ){ 
                        				g.setColor(  Color.black);
                        			}*/  else if(neighbours[i][j] == 8 ){ 
                        				g.setColor(Color.DARK_GRAY);
                        			}   
                        		
                        	g.setFont(new Font("Tahoma", Font.BOLD, 40));
                        	g.drawString(Integer.toString(neighbours[i][j]), i*80+27 , j*80+80+55);
                        }else if(mines[i][j]  == 1){
                        	// BOmbe
                        	g.fillRect(i*80+10+20,j*80+80+20,20,40);
                        	g.fillRect(i*80+20,j*80+80+10+20,40,20);
                        	g.fillRect(i*80+5+20,j*80+80+5+20,30,30);
                        	g.fillRect(i*80+38 ,j*80+80+15 ,4,50);
                        	g.fillRect(i*80+15 ,j*80+80+38 ,50,4);
                        	
                        	
                        }
    	 			
                        }  
    	 			
    	 			 // flags creation
    	 			 if(flagged[i][j] == true) {
    	 			    g.setColor(Color.black);
    	 	    	 	g.fillRect(i*80+32, j*80+80+15, 5, 40);
    	 	    	 	g.fillRect(i*80+20, j*80+80+50, 30, 10);
    	 	    	 	
    	 	    	 	g.setColor(Color.red);
    	 	    	 	g.fillRect(i*80+16, j*80+80+15, 20, 15);
    	 	    	 	
    	 	    	 	g.setColor(Color.black);
    	 	    	 	g.drawRect(i*80+16, j*80+80+15, 20, 15);
    	 	    	 	g.drawRect(i*80+17, j*80+80+16, 18, 13);
    	 	    	 	g.drawRect(i*80+18, j*80+80+17, 16, 11); 
    	 	    	 	
    	 			 }
                        
    	 		}
    	 		
    	 	} // Big for  
    	 	
    	 	// Smiley Creating 
    	 	g.setColor(Color.YELLOW);
    	 	g.fillOval(smileyx, smileyy, 70, 70);
    	 	g.setColor(Color.black);
    	 	g.fillOval(smileyx+15, smileyy+20, 10, 10);
    	 	g.fillOval(smileyx+45, smileyy+20, 10, 10);
    	 	if(happiness == true){
    	 		g.fillRect(smileyx+20, smileyy+50, 30, 5);
    	 		g.fillRect(smileyx+17, smileyy+45, 5, 5);
    	 		g.fillRect(smileyx+48, smileyy+45, 5, 5);
    	 		
    	 	}else{
    	 		g.fillRect(smileyx+20, smileyy+45, 30, 5);
    	 		g.fillRect(smileyx+17, smileyy+50, 5, 5);
    	 		g.fillRect(smileyx+48, smileyy+50, 5, 5);
    	 		
    	 	}
    	 	
    		// Flagger Creating 
    	 	
    	    g.setColor(Color.black);
    	   // g.setColor(new Color(136, 140, 205));	 
    	 	g.fillRect(flaggerX+32, flaggerY+15, 5, 40);
    	 	g.fillRect(flaggerX+20, flaggerY+50, 30, 10);
    	 	
    	 	g.setColor(Color.red);
    	 	g.fillRect(flaggerX+16, flaggerY+15, 20, 15);
    	 	
    	 	g.setColor(Color.black);
    	 	g.drawRect(flaggerX+16, flaggerY+15, 20, 15);
    	 	g.drawRect(flaggerX+17, flaggerY+16, 18, 13);
    	 	g.drawRect(flaggerX+18, flaggerY+17, 16, 11);
    	    
    	 	
    	   if (flagger == true) {
				g.setColor(Color.red);
			}
    	 	g.drawOval(flaggerX, flaggerY, 70, 70);
    	 	g.drawOval(flaggerX+1, flaggerY+1, 68, 68);
    	 	g.drawOval(flaggerX+2, flaggerY+2, 66, 68); 
    	 
    	 	
    	 	
    	 	
    		 // Time counter painting :: 
    	// 	g.setColor(Color.black);
    	 	g.setColor(new Color(136, 140, 205));	 
    	 	g.fillRect(timeX, timeY, 140, 70);
    	 	if(defeat == false && victory == false ){
    	 	sec = (int)((new Date().getTime()-startDate.getTime()) / 1000 ) ; 
    	 	}
    	 	if (sec > 999) {
				sec = 999 ; 
			}
    	 	
    	 	g.setColor(Color.white);
    	 	if( victory == true){
    	 		g.setColor(Color.green);
    	 	}
    	 	if( defeat == true){
    	 		g.setColor(Color.red);
    	 	}
    	 	g.setFont(new Font("Tahoma",Font.PLAIN , 80));
    	 	if(sec < 10 ){
    
    	      g.drawString("00"+ Integer.toString(sec), timeX, timeY+65);
    	 	}else if(sec < 100){
    	 		g.drawString("0"+Integer.toString(sec), timeX, timeY+65);
    	 	}else  {
    	 		g.drawString(Integer.toString(sec), timeX, timeY+65);
    	 		
    	 	}
    	 	
    	 	// Victory Message paint 
    	 	if (victory == true) {
				g.setColor(Color.green);
				vicMes = "YOU WIN"; 
			}else if(defeat == true){
				g.setColor(Color.red);
				vicMes = "YOU LOSE "; 
			}
    	 	if (victory == true || defeat == true) {
				 vicMesY =   -50 + (int)(new Date().getTime()-endDate.getTime())/10 ;
				if(vicMesY > 67){
					vicMesY = 67 ; 
				}
				 g.setFont(new Font("Tahoma",Font.PLAIN ,70 ));
				 g.drawString(vicMes, vicMesX, vicMesY);
			} 
    	 
    	 	
     	 }
    	 
     }
     
    
     
     
     
     //------------------- Mouve Mouse ----------------------------------------
	  public class Move implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
 			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
 		//	System.out.println("The Mouve Was Moved !");
 			mx = e.getX();
 			my = e.getY();
    //     System.out.println("X => "+ mx + "  Y => " + my);

		}
		  
		  
	  }
	  
	
	  
	  
	  
	  //------------------------Click Mouse-----------------------------------
	public class Click implements MouseListener {
		           //--Qst are u  inSide or outSide 
		@Override
		public void mouseClicked(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
		  /* if (inBoxX() != -1 && inBoxY() != -1) {
			  revealed[inBoxX()][inBoxY()] = true; 
		}*/
			
			
			if (inBoxX() != -1 && inBoxY() != -1 ) {
            System.out.println("The Mouse is in the ["+ inBoxX() + "," + inBoxY() +"], Number of mine neighs : " + neighbours[inBoxX()][inBoxY()]);	
			if (flagger == true &&  revealed[inBoxX()][inBoxY()] == false) {
				if ( flagged[inBoxX()][inBoxY()] == false) {
					 flagged[inBoxX()][inBoxY()] = true; 
				}else {
					 flagged[inBoxX()][inBoxY()] = false; 
				}
				
			}else {
				 if (flagged[inBoxX()][inBoxY()] == false ) {
					 revealed[inBoxX()][inBoxY()] = true; 
				}
				  
			}
			
			}else {
				
				 System.out.println("The Pointer is not inside of any Box ! ");	
			}
			
			if (inSmiley() == true) {
				 resetAll();
			} 
			
			if (inFlagger() == true) {
			//	flagger = true ; 
				// System.out.println("In Flagger ture ");

			 if(flagger  == false) {
				 flagger = true ; 
				 System.out.println("In Flagger ture ");
			 } else {
				 
				 flagger = false ; 
				 
				 System.out.println("In Flagger false  ");

				 
			 }
				
			} 
			
			 
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		 
	}
	
	
	
	
	
	//-------------Our Method we use ----------------------
	   public void checkVictorySattus(){
		   if (defeat == false ) {
			for(int i =0 ; i<16;i++  ){
		 		for(int j=0;j<9;j++ ){
		 			 if(revealed[i][j] == true && mines[i][j] == 1 ){
		 				 defeat = true; 
		 				 happiness= false ; 
		 				 //-v12-
		 				 endDate = new Date();
		 			 }
		 			
		 		}
		 		
		 	} 
 	}
			if (totalBoxesRevealed() >= 144 - totalMines() /* && victory == false*/) {
				victory = true ; 
				endDate = new Date(); 
			}
	   }  
	   
	  //********************************************
	   public int totalMines(){
		    int total =  0 ; 
			for(int i =0 ; i<16;i++  ){
		 		for(int j=0;j<9;j++ ){
		 			 if(mines[i][j] == 1){
		 				 total++ ; 
		 			 }
		 			
		 		}
		 		
		 	}  
		    return total ;   
	   } 
	   
		//********************************************
   
	   
	   public int totalBoxesRevealed(){
		   int total = 0 ; 
			for(int i =0 ; i<16;i++  ){
		 		for(int j=0;j<9;j++ ){
		 			 if(revealed[i][j] == true){
		 				   total ++ ; 
		 			 }
		 			
		 		}
		 		
		 	}  
		   
		   return total ; 
	   }
	
	
	
		//***********OUR BAR  *********************************
	
	
	
	
	
	  public boolean inSmiley() {
		 int  dif = (int) Math.sqrt(Math.abs(mx-smileyCenterX)*Math.abs(mx-smileyCenterX)+Math.abs(my-smileyCenterY)*Math.abs(my-smileyCenterY)) ;  
		  if(dif < 35 ){
			 return true ; 
		 } 
		 return false  ; 
	}
	  
	  
	  public boolean inFlagger() {
			 int  dif = (int) Math.sqrt(Math.abs(mx-flaggerCenterX)*Math.abs(mx-flaggerCenterX)+Math.abs(my-flaggerCenterY)*Math.abs(my-flaggerCenterY)) ;  
			  if(dif < 35 ){
				 return true ; 
			 } 
			 return false  ; 
		}
		  
	 
	  
   //********************************************
  
	public int inBoxX(){
	 	for(int i =0 ; i<16;i++  ){
	 		for(int j=0;j<9;j++ ){
	 			 
	 			 if (mx >= spacing+i*80 && mx < spacing+i*80+80-2*spacing && my >= spacing+j*80+80+26 && my < spacing+j*80+26+80+80-2*spacing) {
				   return i ; 
				}
	 			 
	 			
	 		}
	 		
	 	}  
		return -1;	
	}
	//********************************************

	public int inBoxY(){
	 	for(int i =0 ; i<16;i++  ){
	 		for(int j=0;j<9;j++ ){
	 			 
	 			 if (mx >= spacing+i*80 && mx < spacing+i*80+80-2*spacing && my >= spacing+j*80+80+26 && my < spacing+j*80+26+80+80-2*spacing) {
				   return j ; 
				}
	 		}	
	 	}  
		return -1;
	}	
	
	//********************************************

	
	
	
	//**************** New Game ****************************

	
	 public void resetAll(){
		 resetter = true ; 
		 
		 flagger = false ; // v 13 
		 
		 startDate = new Date() ;
		 vicMesY = -50 ; 
		 vicMes = "Nothing Yet !"; 
		 happiness = true ; 
		 victory = false ; 
		 defeat = false ; 
		 
		 for(int i = 0 ; i < 16 ; i++  ){
		 		for(int j = 0 ; j < 9 ; j++ ){
		 		 	if (rand.nextInt(100) < 20) {
						mines[i][j] = 1 ;
					}else{
						mines[i][j] = 0 ;
					} 
		 		 	revealed[i][j] = false ; 
		 		 	flagged[i][j] = false ; 
		 		 	
		 		}	
		 	}
	      	
	    	for(int i =0 ; i<16;i++  ){
		 		for(int j=0;j<9;j++ ){
		 			neighs =  0 ; 
		 			for(int m=0 ; m<16;m++  ){
		 		 		for(int n=0;n<9;n++ ){
		 		 	       if(!(m == i && n == j )){
		 		 	          if(isN(i,j,m,n) == true)
		 		 	    	neighs ++ ; 
		 		 		}	
		 		 	}
		 		}
		 	      	neighbours[i][j] = neighs ; 
		 		 	
		 		}	
		 	}
		 
	 }
	 

	   

	//******************Niegh*************************
	public boolean isN(int mX ,int mY ,int cX ,int cY){
		 if (mX - cX < 2 && mX - cX >-2 &&  mY- cY < 2 && mY - cY >-2 && mines[cX][cY] == 1  /* && (mX != cX && mY != cY) */  ){
			return true ; 
		}
		return false;
	
	}
	
	
	
	
	
	
	
}

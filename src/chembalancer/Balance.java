/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chembalancer;

import java.util.ArrayList;

/**
 *
 * @author ranyodhmandur
 */
public class Balance {
    //FIELDS
    protected ArrayList< ArrayList<Element> > leftSide, rightSide; //LEFTSIDE = REACTANTS, RIGHTSDE = PRODUCTS
    
    public String balancedLeftSide, balancedRightSide; //BALANCED EQUATION FOR BOTHSIDES
    
    protected ArrayList<Element> listOfElements; //LIST OF ALL ELEMENTS IN THE EQUATION
    
    protected Matrix matrix; //THE MATRIX THAT COMPOSES THE EQUATION
    
    //CONSTRUCTOR
    public Balance( ArrayList< ArrayList<Element> > left, ArrayList< ArrayList<Element> > right ){
        this.leftSide = left; //REACTANT SIDE
        this.rightSide = right; //PRODUCT SIDE
        
        this.listOfElements = this.splitElements(); // LIST OF UNIQE ELEMENTS
        this.matrix = new Matrix( this.getMatrix() ); //BUILDS MATRIX
        
        this.balancedLeftSide = this.buildFinalEquationString( leftSide ); //BALANCED EQUATION FOR FIRST TEXTFIELD
        this.balancedRightSide = this.buildFinalEquationString( rightSide ); //BALANCED EQUATION FOR SECOND TEXTFIELD
    }   
    
    //CHECKS IF THE EQUATION IS BALANCED
    protected boolean isBalanced(){
        int[] currentCount;
        
        for ( Element e : listOfElements ) {
            currentCount = this.count( e ); //USES COUNT METHOD TO CHECK IF LS ELEMENT == RS ELEMENT
            
            if( currentCount[0] != currentCount[1])
                return false;
        }
        
        return true;
    }
    
    //COUNTS AMOUNT OF ELEMENT ON EITHER SIDE USED MAINLY IN isBalanced
    protected int[] count( Element e ){
        //VARIABLES THAT KEEP TRACK OF THE AMOUNT OF ATOMS OF THE ELEMENT ON EITHER SIDE
        int countLeft = 0;
        int countRight = 0;
        
        //PERFORMS THE ACTUAL COUNTING//
        //COUNTS ATOMS ON REACTANT SIDE
        for(int i = 0; i < leftSide.size(); i++){               
                
            for (int j = 0; j < leftSide.get(i).size(); j++) { 
                if( e.name.equals(leftSide.get(i).get(j).name) )
                    countLeft += e.numOfThis;
                }  
            
            }
        
        //COUNTS ATOMS ON PRODUCT SIDE
        for (int i = 0; i < rightSide.size() ; i++) {

            for (int j = 0; j < rightSide.get(i).size(); j++) {
                if( e.name.equals(rightSide.get(i).get(j).name) )
                    countRight+= e.numOfThis;
            }

        }
        
        //RETURNS COUNT OF EITHER SIDE    
        return new int[]{countLeft, countRight};
    }

    //FILLS listOfElements WITH ONLY UNIQUE ELEMENTS
    protected ArrayList<Element> splitElements(){
        Element temp = new Element(null, 0, 0); //INIT ELEMENT USED TO COMPARE AND DETERMINE UNIQUE ELEMENTS
        
        ArrayList< Element > tempList = new ArrayList<>(); //TEMP ARRAY TO HOLD VALUES FOR RETURN
        
        //ONLY NEED TO CHECK ONE SIDE BECAUSE THE OTHER SIDE HAS THE SAME AMOUNT OF ELEMENTS
        for (int i = 0; i < this.leftSide.size(); i++) {
            for (int j = 0; j < this.leftSide.get(i).size(); j++) {
                
                if( !leftSide.get(i).get(j).name.equals(temp.name) ){ //CONDITION THAT CHECKS FOR UNIQUE ELEMENTS
                    tempList.add( leftSide.get(i).get(j) ); //ADDS UNIQUE ELEMENT TO LIST
                    temp = leftSide.get(i).get(j); //SETS TEMP TO LAST ELEMENT INSERTED
                }
                
            }   
        }
        
        return tempList;
    }
    
    //BUILDS MATRIX
    protected int[][] getMatrix(){
        //MATRIX DIMENSIONS WHERE: # OF MOLECULES = COLUMNS AND # OF UNIQUE ELEMENTS = ROWS
        int columns = this.leftSide.size() + this.rightSide.size() + 1; //extra column is for answer
        int rows = this.listOfElements.size();
        
        //POINTER VARS FOR MATRIX
        int columnTracker = 0;
        int rowTracker = 0;
        
        //TEMP MATRIX FOR RETURN VALUE
        int[][] tempMatrix = new int[ rows ][ columns ];
        
        //BUILDS LEFT HALF OF THE MATRIX ( POSITIVE VALUES )
        for( Element e : this.listOfElements){
            //finds column vales for left side
            for (int i = 0; i < leftSide.size(); i++) {
                
                for (int j = 0; j < leftSide.get(i).size(); j++) {
                    
                    if( e.name.equals( leftSide.get(i).get(j).name ) )
                        tempMatrix[rowTracker][columnTracker] = e.numOfThis;
                    
                    else
                        tempMatrix[rowTracker][columnTracker] = 0; //ZERO BECAUSE NO VALUE EXISTS FOR THAT COLUMN
                    
                    columnTracker++;
                }
                
                
            }
            
            //BUILDS RIGHT SIDE OF MATRIX (NEG VALUES)
            for (int i = 0; i < rightSide.size(); i++) {
                
                for (int j = 0; j < rightSide.get(i).size(); j++) {
                    
                    if( e.name.equals( rightSide.get(i).get(j).name ) ){
                        //negative because its on the product side
                        tempMatrix[rowTracker][columnTracker] = -e.numOfThis; 
                    }
                    
                    else
                        tempMatrix[rowTracker][columnTracker] = 0;
                    
                    columnTracker++;
                }
                
            }
            
            tempMatrix[rowTracker][columnTracker] = 0; //sets answer to 0
            
            columnTracker = 0; //resets column values
            rowTracker++; //goes to next row
        }
        
        return tempMatrix;
    }
    
    protected Matrix createMatrix(){
        return new Matrix( this.getMatrix() );
    }
    
    //BUILDS SOLVED EQUATION FOR BOTH SIDES
    protected String buildFinalEquationString( ArrayList< ArrayList<Element> > list){
    
        return null;
    }
    
    //DISPLAYS MATRIX
    /**
     * Displays the matrix as would be written on paper.
     */
    protected void displayMatrix(){
        
        for (int i = 0; i < 10; i++) {
            System.out.print("|"); //FIRST BRACKET OF MATRIX
            
//            for (int j = 0; j < this.matrix[i].length; j++) {
//                System.out.print( this.matrix[i][j] + "\t" ); //PRINTS OUT VALUES FOR EAHC COLUMN
//                
//            }
            System.out.print("| \n"); //SECOND BRACKET OF MATRIX + SETS POINTER TO NET LINE
        }
    }
}

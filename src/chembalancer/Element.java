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
public final class Element { 
    public String name; //NAME OF ELEMENT
    private String newString; //STRING USED FOR CHARARRAY (ALTERED SLIGHTLY AND CONTAINS LEFTSIDE + RIGHTSIDE)
    
    
    public int numOfThis; //# OF ATOMS 
    public int multiplier; // CO OF THE THE MOLECULE
    
    private char[] characterElementArray; //ARRAY OF CHAR CREATED BY CLEAN METHOD, ELEMENT OBJECTTS ARE PICKED OUT OF IT
    
    private ArrayList< ArrayList<Element> > leftElements = new ArrayList<>(); //REACTANT SIDE OF EQUATION
    private ArrayList< ArrayList<Element> > rightElements = new ArrayList<>(); //PRODUCT SIDE OF THE EQUATION
    
    protected ArrayList< String > fractionCoefficients = new ArrayList<>(); //CONTAINS FRACTION COEFFICENTS IN CASE
    
    protected boolean isLeft; //BOOL VALUE TO DETERMINE IF THE POINTER IS POINTING AT THE REACTANT SIDE OR PRODUCT SIDE OF THE EQUATION
    
    //CONSTRUCTOR FOR STORING PROPERTIES OF ELEMENTS AND CREATING ELEMENT OBJECTS( PROBABLY SHOULD BE AN ENUM )
    public Element( String name, int num, int multiplier ){
        this.name = name;
        this.multiplier = multiplier;
        this.numOfThis = num * multiplier;
    }
    /**
     * 
     * @param leftSide
     * @param rightSide 
     */
    //ACTUALLY GETS VALUES NEEDED FOR BALANCING
    public Element(String leftSide, String rightSide){
        this.newString = leftSide + "=" + rightSide; //equal sign makes it easier to work with
        
        this.characterElementArray = this.clean( this.newString ); //turns string into workable array
        this.getElements();        //finds all values required to do the calculations and display answer
    }
    
    public static char[] clean( String e ){ //method for converting string to a workable form
        //.split SUBSTITUTE
        ArrayList< Character > t = new ArrayList<>();
        char[] charArray = e.toCharArray();        
        for ( char m : charArray ) { if( m != ' ' ) t.add(m); }
        
        String full = "   "; //space here on purpose( makes it easier to detect first co-E ) throws less errors
        for (Character s : t) { full += s; }
        full += "   ";
        
        char[] newString = full.toCharArray(); //FINAL RETURN VALUE
        
        //DISPLAYS WHAT THE STRING HAS BEEN TURNED INTO
        System.out.print("[");
        for ( char c : newString ) System.out.print(c + " ");
        System.out.print("] \n");
        
        return newString;
    }
    
    //UPDATES CO'S, USEFULL FOR BALANCE CLASS
    public static void updateMultiplier( ArrayList< Element > list, int newMultiplier ){
        for( Element e : list ) e.multiplier = newMultiplier;
    }
    /**
     * 
     */
    //FIGURE OUT HOW TO CALUCLATE HOW MANY 
    private void getElements(){
        String newElement = "";
        String num = "";
        
        this.isLeft = true;
        
        int newMultiplier = 1;
        int counter;
        
        boolean oneDigit = false;
        boolean wentThroughLoop;
        
        ArrayList< Element > temp = new ArrayList< Element >();

        for (int i = 1; i < characterElementArray.length - 1; i++) {
            
            //DETERMINES WHETHER THE ARRAY IS POINTING TOWARDS THE LEFTSIDE OR RIGHTSIDE OF THE EQUATION
            if( characterElementArray[i] == '=' )
                isLeft = false;
            //top of conditional flow should be most specific case and should becoming broader the farther down it goes
            //special case for polyatomics so i can get it n stuff
            if( false ){
                
            }
            
            //HANDLES CASES FOR TWO LETTER ELEMENTS               
            else if( Character.isUpperCase(characterElementArray[i] ) && Character.isLowerCase(characterElementArray[i + 1]) ){ //checks if the character is uppercase
                newElement += characterElementArray[i];
                newElement += characterElementArray[i + 1];
                if( !Character.isDigit( characterElementArray[i + 1] ) )
                    oneDigit = true; //here so it executes below in conditonal statement, also needa fix the flow
            }
            
            //HANDLES CASES FOR ONE LETTER ELEMENTS
            else if( Character.isUpperCase( this.characterElementArray[i] )  ){
                newElement += characterElementArray[i];
                if( !Character.isDigit( characterElementArray[i + 1] ) )
                    oneDigit = true;
            }
            
            //HANDLES CASE FOR EXISITING COEFFICIENTS NEXT TO ELEMENTS JUST IN CASE
            else if( Character.isDigit( characterElementArray[i] ) && !Character.isLetter( characterElementArray[i - 1]) )
                newMultiplier = Integer.parseInt( Character.toString( characterElementArray[i] ) ); //characters have int values
            
 // ****** important - don't fuck up my conditional flow because i really fucked up here and messed up my whole algorithm. only use if coupled with else ifs for conditions
 //         involoing actually finding the characters, and then seperate it with the actual putting it in arrays.
            
            //LOOKS FOR SUNSCRIPT NUMBERS AND MAKES A NEW ELEMENT AND ADDS IT TO THE TEMP ARRAYLIST
            if( Character.isLetter( characterElementArray[i - 1] ) && ( Character.isDigit(characterElementArray[i] ) || oneDigit ) ){
                counter = i; //ITER VAR FOR BUILDING NUM STRING
                wentThroughLoop = false; //HANDLES CASE WHERE THERE IS NO SUBSCRIPT
                
                //FOR MULTI DIGIT CO'S
                while( Character.isDigit(characterElementArray[counter]) ){
                    wentThroughLoop = true;
                    num += characterElementArray[counter];
                    counter++;
                }
                
                if( !wentThroughLoop ) //CASE FOR NO SUBSCRIPT
                    num = "1";
                
                Element e = new Element( newElement, Integer.parseInt(num), newMultiplier ); //FOR SHOW
                System.out.println("amount of stuff here is " + e.numOfThis + " it's name is " + e.name);
                
                //actually adds element to temp arraylist
                temp.add( e );  //ADDING ELEMTN TO NE ARRAYLIST

                //RESETTING VARS
                newElement = ""; //resets element string
                num = ""; //resets num to an empty string
                newMultiplier = 1; //resets multiplier to 1 //why is new multiplier here and is always 1
                
                oneDigit = false;
            }
            
            //SPLITS MOLECULES                                                              this needs to be fixed (maybe)
            if( (characterElementArray[i] == '+' || characterElementArray[i] == '=') || Character.isUpperCase( characterElementArray[i + 1] ) ){
            //DETERMINES IF THE MOLECULE IS IN THE PRODUCT OR REACTANT SIDE 
                           
                if( isLeft )
                    this.leftElements.add( temp );
                
                else
                    this.rightElements.add( temp );
               //RESETTING VALUES
                temp.clear(); //clears temp so it doesnt combine molecules
                newMultiplier = 1; //resets multiplier to 1
            }
            
        }
    }
    /**
     * 
     * @return 
     */
    //RETURNS REACTANTS
    public ArrayList< ArrayList<Element> > returnReactants(){       
        return this.leftElements;
    }
    /**
     * 
     * @return 
     */
    //RETURNS PRODUCTS
    public ArrayList< ArrayList<Element> > returnProducts(){
        return this.rightElements;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){    
        return String.valueOf( this.numOfThis );
    }
    /**
     * 
     */
    public void displayArray(){
        System.out.println( "Left: " + this.leftElements.toString() );
        System.out.println("Right: " + this.rightElements.toString() );
    }
}

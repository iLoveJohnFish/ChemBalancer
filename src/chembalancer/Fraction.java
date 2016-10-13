/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chembalancer;

/**
 *
 * @author ranyodhmandur
 */
public final class Fraction {    
    private int denom, numer;
    
    /**
     * 
     * @param numer
     * @param denom 
     */
    //CONSTRUCTOR FOR REGUALR FRACTIONS
    public Fraction(int numer, int denom){
        if( denom == 0 )           
            throw new Error("Can't create a fraction where the denominator is 0"); //prevents user from creating non-sensical fractions
       
        else
            this.denom = denom;
        
        this.numer = numer;
        reduce(this);
    }
    /**
     * 
     * @param num 
     */
    // CONSTRUCTOR FOR INTEGERS
    public Fraction( int num ){
        this.denom = 1;
        this.numer = num;
    }
    /**
     * 
     * @param num 
     */
    //CONSTRUCTOR FOR DOUBLES
    public Fraction( double num ){
        int tempDenom = 1;
        double tempNumer = num;
        
        while( tempNumer != (int)tempNumer ){
            tempNumer *= 10;
            tempDenom *= 10;           
        }
        
        this.numer = (int)tempNumer;
        this.denom = tempDenom;
        reduce(this);
    }
    /**
     * takes a String and turns it into a fraction object.
     * 
     * will only parse Strings of the form a/b
     * @param s
     * @return the fraction interpretation of the String
     */
    //CONVERTS STRINGS OF FORM A/B TO FRACTION OBJECTS
    public static Fraction parseFraction( String s ){
        char[] temp = Element.clean(s);
        
        return new Fraction(
                Integer.parseInt( Integer.toString(temp[0]) ), 
                Integer.parseInt( Integer.toString(temp[2]) )
        );
    }
    /**
     * 
     * @param other 
     */
    //REDUCES THE FRACTION PASSED TO IT
    public static void reduce( Fraction other ){
        int divisor = 2; // initially set to 2 because every integer is divisble by 1

        while( divisor <= other.numer && divisor <= other.denom ){
            
            while( other.denom % divisor == 0 && other.numer % divisor == 0 ){
                other.numer /= divisor;
                other.denom /= divisor;
            }
            
            divisor++;
        }     
    }
    /**
     *
     * @param other - another Fraction object to add to this
     * 
     */
    public void add( Fraction other ){
        
        if( other.denom == this.denom )
            this.numer += other.numer;
        
        else{
            this.denom *= other.denom;
            other.numer *= this.denom;
            this.numer += other.numer;                   
        }
        
        reduce(this);
    }
    /**
     * 
     * @param other 
     */
    public void add( double other ){
        this.add( new Fraction( other ) );
    }
    /**
     * 
     * @param other An integer to add on to this Fraction object
     *  
     */
    //FRACTION AND INTGERE ADDITION
    public void add( int other ){
        this.numer += (other * this.denom);
        reduce(this);
    }
    /**
     * 
     * @param other 
     */
    //DIVIDES THIS OBJECT BY ANOTHER FRACTION
    public void divide( Fraction other ){
        this.numer *= other.denom;
        this.denom *= other.numer;
        reduce(this);
    }
    /**
     * 
     * @param other 
     */
    public void divide( double other ){
        this.divide( new Fraction( other ) );
    }
    /**
     * 
     * @param other 
     */
    //DIVIDES THIS BY AN INTEGER
    public void divide( int other ){
        this.denom *= other;
        reduce(this);
    }
    /**
     * divides the constant given by this fraction
     * 
     */
    public static Fraction integerDivide( int other, Fraction f ){
        Fraction temp = new Fraction(other);
        return new Fraction(temp.numer * f.denom, temp.denom * f.numer );
    }
    /**
     * 
     * @param other 
     */
    //SUBTRACTS ANOTHER FRACTION FROM THIS
    public void subtract( Fraction other ){
        
        if( other.denom == this.denom )
            this.numer -= other.numer;
        
        else{
            this.denom *= other.denom;
            other.numer *= this.denom;
            this.numer -= other.numer;            
        }
        
        reduce(this);    
    }
    /**
     * 
     * @param other 
     */
    public void subtract( double other ){
        this.subtract( new Fraction( other ) );
    }
    /**
     * 
     * @param other 
     */
    //SUBTRACTS AN INTEGER FROM THIS
    public void subtract( int other ){
        this.numer -= (other * this.denom);
        reduce(this);
    }
    /**
     * 
     * @param other 
     */
    //MULTIPLIE THIS BY ANOTHER FRACTION
    public void multiply( Fraction other ){
        this.denom *= other.denom;
        this.numer *= other.numer;
        reduce(this);
    }
    /**
     * 
     * @param other 
     */
    public void multiply( double other ){
        this.multiply( new Fraction( other ) );
    }
    /**
     * 
     * @param other 
     */
    //MULTIPLIES THIS BY AN INTEGER
    public void multiply( int other ){
        this.numer *= other; 
        reduce(this);
    }
    /**
     * 
     * @param one
     * @param two
     * @return 
     */
    public static Fraction max( Fraction one, Fraction two ){
        if( one.doubleValue() >= two.doubleValue() )
            return one; 
        else
            return two;
    }
    /**
     * 
     * @param one
     * @param two
     * @return 
     */
    public static Fraction min( Fraction one, Fraction two ){
        if( one.doubleValue() <= two.doubleValue() )
            return one; 
        else
            return two;     
    }
    /**
     * 
     * @param one
     * @param two
     * @return 
     */
    public static int compare( Fraction one,  Fraction two ){
        if( one.doubleValue() > two.doubleValue() )
            return 1;
        
        if( one.doubleValue() == two.doubleValue() )
            return 0;
        
        else
            return -1;

    }
    /**
     * Will compare this fraction object to another fraction object.
     * 
     * this method will only return values based on the value of each fraction.
     * 
     * @param other another fraction to compare with this fraction
     * @return if the value of this > other it will return 1.
     * if the value of this fraction == other it will return 0,
     * if the value of other > this fraction it will return -1,
     * 
     */
    public int compareTo( Fraction other ){
        if( this.doubleValue() > other.doubleValue() )
            return 1;
        
        if( this.doubleValue() == other.doubleValue() )
            return 0;
        
        else
            return -1;
    }
    /**
     * calculates the closest decimal value of this Fraction.
     * 
     * This number may not always be an EXACT value of the fraction.
     * But only returns the closest value of this fraction.
     * 
     * @return closest double value of the Fraction
     */
    public double doubleValue(){
        return (double)( this.numer / this.denom);
    }
    /**
     * calculates the closest integer value of the Fraction to the nearest whole
     * number.
     * 
     * this method should not be overrided.
     * 
     * @return closest integer value of the fraction
     */
    
    public int toInt(){
        return (int)( this.numer / this.denom );
    }
    /**
     * 
     * @param f
     * @return 
     */
    public static void toDouble( Fraction f ){
         //this.value = (double)( f.numer / f.denom );       
    }
    /**
     * 
     * @return 
     */
    public boolean isImproper(){
        return this.numer > this.denom;
    }
    /**
     * returns the absolute interpretation of this fraction object.
     * 
     * @return absolute value of the fraction
     */
    public Fraction abs(){
        return new Fraction( Math.abs(this.numer), Math.abs(this.denom) );
    }
    /**
     * 
     * @return String interpretation of a Fraction
     */
    //DISPLAYS THE FRACTION
    @Override
    public String toString(){
        System.out.println( this.numer + "/" + this.denom );
        return null;
    }
    
    
    /**
     * 
     * @return the numerator of this fraction object
     */
    public int numerator(){
        return this.numer;
    }
    /**
     * 
     * @return the denominator of this fraction object
     */
    public int denominator(){
        return this.denom;
    }
     
    
}

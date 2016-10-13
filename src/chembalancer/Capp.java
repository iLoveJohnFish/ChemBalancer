package chembalancer;



public final class Capp  {    
    
    
    //MAIN METHOD
    public static void main( String[] args ) {
        Element equation = new Element( "2H2O + NaCl", "NaOH + H" ); //put any equation you'd like here
        equation.displayArray();
        
        Fraction faggot = new Fraction( 1.937975572 );
        faggot.toString();
//        Element e = new Element(this.leftSide, this.rightSide);
        //Balance b = new Balance(equation.returnReactants(), equation.returnProducts());
        //b.displayMatrix();
//        this.finalEquation = b.balancedEquation;  
    }

    
}

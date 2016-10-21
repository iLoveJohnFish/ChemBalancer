package chembalancer;



public final class Capp  {    
    /**
     * This is the main class that will identify which classes and functions to use for different scenarios.
     * for example: balancing regular equations, balancing half reactions, redox reactions,
     * this app should also have info on each element, periodic table, options for visual appearance(color, layout), general info on chem like lewis diagrams n shit
     * give attributes about the substances that were created by the reaction -- like reaction type, bond types, weight, color, etc.
     * tell whether something is a diatomic,
     * give a graph how something bonds
     */
    
    //MAIN METHOD
    public static void main( String[] args ) {
        Element equation = new Element( "H2 + NaCl", "NaOH + H" ); //put any equation you'd like here
        equation.displayArray();
        
        Fraction faggot = new Fraction( 1.937975572 );
        faggot.toString();
//        Element e = new Element(this.leftSide, this.rightSide);
        //Balance b = new Balance(equation.returnReactants(), equation.returnProducts());
        //b.displayMatrix();
//        this.finalEquation = b.balancedEquation;  
    }

    
}

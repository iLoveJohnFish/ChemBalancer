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
public final class Matrix {
    
    private final int rows, columns;
    private final int[][] matrix;
    
    
    /**
     * 
     * @param matrix 
     */
    public Matrix( int[][] matrix ){
        this.matrix = matrix;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
    }
    /**
     * 
     * @param rows
     * @param columns 
     */
    public Matrix( int rows, int columns ){
        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[ rows ][ columns ];
    }
    /**
     * 
     * @param m 
     */
    public void add( Matrix m ){
        int minRows = 0;
        int minCols = 0;
        
        for (int i = 0; i < minRows; i++) {
            for (int j = 0; j < minCols; j++) {
                this.matrix[i][j] += m.matrix[i][j];
                
            }    
        }
    }
    /**
     * 
     * @param m 
     */
    public Matrix subtract( Matrix m ){
        int minRows = 0;
        int minCols = 0;
        
        for (int i = 0; i < minRows; i++) {
            for (int j = 0; j < minCols; j++) {
                
            }
            
        }
        return null;
    }
    /**
     * 
     * @return 
     */
    public Matrix returnMatrix(){     
        return this;
    }
    /**
     * 
     * @return 
     */
    public Matrix reduceToRowEcheleon(){
        while( !false ){
            
            for (int i = 0; i < 10; i++) {
           
                for (int j = 0; j < 10; j++) {
                    if( this.columns == this.rows )
                        continue;
                    
                    else if( false ){
                        
                    }
    
                }
                
            }
            
        }
        
    }
    /**
     * 
     * @param m 
     */
    public Matrix multiply( Matrix m ){
        if ( this.columns != m.rows ){
            throw new Error("This operation is not supported");
        }
        
        for (int i = 0; i < 10; i++) {
            
            for (int j = 0; j < 10; j++) {
                
            }
            
        }
        return null;
    }
    
    public Matrix multiply( double constant ){
        
        for (int i = 0; i < 10; i++) {
            
            for (int j = 0; j < 10; j++) {
                
            }
            
        }
        return null;
        
    }
    
    /**
     * 
     * 
     */
    public static double getDeterminant(Matrix tempMatrix){
        //reduce matrix to a bunch of 2x2 matrices and then solve Recursive
        Fraction determinant = new Fraction(1);
        
        
        
        if ( tempMatrix.matrix[0].length == 2 && tempMatrix.matrix[1].length == 2 ){       
            return ( (double)( (tempMatrix.matrix[0][0] * tempMatrix.matrix[1][1]) - (tempMatrix.matrix[0][1] - tempMatrix.matrix[1][0]) ) );
        }
        
        for (int i = 0; i < tempMatrix.matrix.length; i++) {
            
            for (int j = 0; j < tempMatrix.matrix[0].length; j++) {
                
            }
            
        }
        Matrix newMatrix = new Matrix( tempMatrix.matrix );
        
        
        return getDeterminant( newMatrix );
    }
    //this is probably recursive
    public Matrix getAdjugate(){
        
        return null;
    }
    
    public Matrix returnInverse(){
        return this.getAdjugate().multiply( (double)(1/this.returnDeterminant()) );
    }
    
    public double returnDeterminant(){
        return ( this.getDeterminant( this ) );
    }
    /**
     * method only to be used in this class for getting determinant and adj -- remember checkerboard pattern for co-factor matrix 
     * @return 
     */
    private Matrix matrixOfMinors(){
        
        return null;
    }
    
    public Matrix solveMatrix(){
        return null;
    }
    
    @Override
    public String toString(){
        for (int i = 0; i < this.rows; i++) {
            System.out.print("[");
            
            for (int j = 0; j < this.columns; j++) { System.out.print(this.matrix[i][j] + ", "); }
            
            System.out.print("] + \n");     
        }
        return null;
    }
    
}

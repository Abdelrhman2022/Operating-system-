/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixmultiply;

import java.util.Date;

/**
 *
 * @author Genius
 */
public class MatrixMultiply {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date start = new Date();

        int[][] m1 = MatrixGeneratorUtil.generateMatrix(4, 4);
        int[][] m2 = MatrixGeneratorUtil.generateMatrix(4, 4);

        int[][] result = new int[m1.length][m2[0].length];
        ParallelThreadsCreator.multiply(m1, m2, result);

        Date end = new Date();
        MatrixGeneratorUtil.print(result);
        System.out.println("\nTime taken in milli seconds: " + (end.getTime() - start.getTime()));

    }
   
}

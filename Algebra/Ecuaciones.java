package Algebra;
// Flores Romero Andres Gael
// Ecuaciones para las Clases

import DataSets.DatosVariables;

import java.util.ArrayList;

public class Ecuaciones {

    public static void ImprimirMatriz(double[][] Matriz) {
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz[i].length; j++) {
                System.out.printf("%.15f\t", Matriz[i][j]);
            }
            System.out.println();
        }
    }

    public static double[][] OperacionesBasicas(ArrayList<DatosVariables> Datos, int F, int C){
        int Num= Datos.size();
        double SumX = 0, SumY = 0, MultXY = 0, MultX2 = 0,MultX3 = 0, MultX4 = 0, MultXXY = 0;

        for (DatosVariables p : Datos) {
            double X = p.x;
            double y = p.y;

            SumX += X;
            SumY += y;
            MultXY += X * y;
            MultX2 += X * X;
            MultX3 += X * X * X;
            MultX4 += X * X * X * X;
            MultXXY += X * X * y ;
        }

        double[][] MatrizX = new double[F][C];

        MatrizX[0][0] = MultX4;
        MatrizX[0][1] = MultX3;
        MatrizX[0][2] = MultX2;
        MatrizX[1][0] = MultX3;
        MatrizX[1][1] = MultX2;
        MatrizX[1][2] = SumX;
        MatrizX[2][0] = MultX2;
        MatrizX[2][1] = SumX;
        MatrizX[2][2] = Num;
        MatrizX[0][3] = MultXXY;
        MatrizX[1][3] = MultXY;
        MatrizX[2][3] = SumY;

        return MatrizX;
    }

    public static double[][] OperacionesBasicasY(ArrayList<DatosVariables> Datos, int F, int C){
        int Num= Datos.size();
        double SumX = 0, SumY = 0, MultXY = 0, MultX2 = 0,MultX3 = 0, MultX4 = 0, MultXXY = 0;

        for (DatosVariables p : Datos) {
            double X = p.x;
            double y = p.y;

            //Calcular los datos de la ecuacion
            SumX += X;
            SumY += y;
            MultXY += X * y;
            MultX2 += X * X;
            MultX3 += X * X * X;
            MultX4 += X * X * X * X;
            MultXXY += X * X * y ;

        }

        double[][] MatrizY = new double[F][C];

        //Matriz X

        MatrizY[0][0] = MultXXY;
        MatrizY[1][0] = MultXY;
        MatrizY[2][0] = SumY;


        return MatrizY;
    }


    public static double[][] Transpuesta(double[][]PrMatriz){

        double[][] ScMatriz = new double[PrMatriz[0].length][PrMatriz.length];

        for (int i = 0; i < PrMatriz.length; i++) {
            for (int j = 0; j < PrMatriz[0].length; j++) {
                ScMatriz[j][i] = PrMatriz[i][j];
            }
        }

        return ScMatriz;
    }
    
    public static double[][] CalcAumentada(double[][]Matriz){
        
        double[][] MatrizExtra = new double[Matriz.length][Matriz.length];
        double[][] MatrizAumentada = new double[Matriz.length][Matriz.length*2];

        for (int i = 0; i < Matriz.length; i++) {
            MatrizExtra[i][i] = 1.0;
        }

        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz.length; j++) {
                MatrizAumentada[i][j] = Matriz[i][j];
                MatrizAumentada[i][j + Matriz.length] = MatrizExtra[i][j];
            }
        }

        return MatrizAumentada;
    }

    public static double[][] GussJordan(double[][] Matriz) {
        int Filas = Matriz.length, Columns = Matriz[0].length;

        for (int Carreado = 0; Carreado < Filas; Carreado++) {

            double Factor = Matriz[Carreado][Carreado];

            for (int j = Carreado; j < Columns; j++) {
                Matriz[Carreado][j] /= Factor;
            }

            for (int i = 0; i < Filas; i++) {
                if (i != Carreado) {
                    double factor = Matriz[i][Carreado];
                    for (int j = Carreado; j < Columns; j++) {
                        Matriz[i][j] -= factor * Matriz[Carreado][j];
                    }
                }
            }
        }

        return Matriz;
    }

    public static double[][] CalcMatrizInversa(double[][] PrMatriz, double[][]MultTransMX){
        double[][] ScMatriz = new double[PrMatriz.length][PrMatriz[0].length/2];
        for (int i=0; i< PrMatriz.length; i++){
            for (int j=0; j < PrMatriz.length; j++){
                ScMatriz[i][j] = PrMatriz[i][j + MultTransMX.length];
            }
        }

        return ScMatriz;
    }

    public static double[][] MultMatrices(double[][] PrMatriz, double[][] ScMatriz) {
        int FilasPrM = PrMatriz.length, ColumnsPrM = PrMatriz[0].length, FilasScM = ScMatriz.length, ColumnsScM = ScMatriz[0].length;

        if (ColumnsPrM != FilasScM) {
            throw new IllegalArgumentException("ERROR. DATOS NO SON VALIDOS.");
        }
        double[][] MultMatrizXTX = new double[FilasPrM][ColumnsScM];

        for (int i = 0; i < FilasPrM; i++) {
            for (int j = 0; j < ColumnsScM; j++) {
                double SumaTotal = 0;
                for (int k = 0; k < ColumnsPrM; k++) {
                    SumaTotal += PrMatriz[i][k] * ScMatriz[k][j];
                }

                MultMatrizXTX[i][j] = SumaTotal;
            }
        }
        return MultMatrizXTX;
    }

    public static double[][] generatePolynomialMatrix(ArrayList<DatosVariables> datos, int degree) {
        int n = datos.size();
        double[][] matrix = new double[degree + 1][degree + 2];
        
        for (int i = 0; i <= degree; i++) {
            for (int j = 0; j <= degree; j++) {
                matrix[i][j] = 0;
                for (DatosVariables p : datos) {
                    matrix[i][j] += Math.pow(p.x, i + j);
                }
            }
            matrix[i][degree + 1] = 0;
            for (DatosVariables p : datos) {
                matrix[i][degree + 1] += Math.pow(p.x, i) * p.y;
            }
        }
        return matrix;
    }

    public static double[] solvePolynomialCoefficients(ArrayList<DatosVariables> datos, int degree) {
        double[][] matrix = generatePolynomialMatrix(datos, degree);
        double[][] reducedMatrix = GussJordan(matrix);
    
        double[] coefficients = new double[degree + 1];
        for (int i = 0; i <= degree; i++) {
            coefficients[i] = reducedMatrix[i][degree + 1];
        }
        return coefficients;
    }

}
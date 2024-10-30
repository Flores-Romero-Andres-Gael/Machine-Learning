package Main;

import QuadraticRegression.Quadratic;
import SimpleLinearRegression.Simple;
import Polynomial.Polynomial;
import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int op = 0;

        do{
            System.out.println("\n----------Choose an option----------");
            System.out.println("1.- Simple Linear Regression");
            System.out.println("2.- Quadratic Regression");
            System.out.println("3.-Polynomial Regression");
            System.out.println("4.- Exit");
            System.out.println("Option: ");
            op = sc.nextInt();
            switch (op) {
                case 1: 
                    System.out.println("\n----------Simple Linear Regression----------");
                    Simple.main(args);
                    break;
                case 2:
                    System.out.println("\n----------Quadratic Regression----------");
                    Quadratic.main(args);
                    break;    
                case 3:
                    System.out.println("\n----------Polynomial Regression----------");
                    Polynomial.main(args);
                    break;
            }

        }while(op != 3);
        
        System.out.println("\n----------GOOD BYE----------");

        sc.close();
    }

}

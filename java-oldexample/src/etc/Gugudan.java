package etc;

public class Gugudan {
    public static void main(String args[]) {
        for (int i = 2; i >= 0; i--) {
            for (int k = 1; k < 10; k++) {
                for (int j = 3 * (i + 1); j > 3 * i; j--)
                    System.out.print(j + " * " + k + " = " + (j * k) + "\t");
                    System.out.println();
            }
            System.out.println();
        }
    }
}
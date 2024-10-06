package prac_2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            float a = scanner.nextFloat();
            float b = scanner.nextFloat();
            float c = scanner.nextFloat();

            Registry registry = LocateRegistry.getRegistry(null);

            EquationsSolver solver = (EquationsSolver) registry.lookup("solver");

            float[] result = solver.solve(a, b, c);
            System.out.println(Arrays.toString(result));
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}

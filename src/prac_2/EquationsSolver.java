package prac_2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EquationsSolver extends Remote{
    float[] solve(float a, float b, float c) throws RemoteException;
}

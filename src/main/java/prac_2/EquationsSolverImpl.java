package prac_2;

public class EquationsSolverImpl implements EquationsSolver {
    @Override
    public float[] solve(float a, float b, float c) {
        float disc = b * b - 4 * a * c;

        if (disc < 0) {
            throw new ArithmeticException();
        }

        return new float[]{(float) ((-b + Math.sqrt(disc)) / (2 * a)), (float) ((-b - Math.sqrt(disc)) / (2 * a))};
    }
}

package cs1410;

public class Operator
{
    public Double calculate(Double a, Double b){ return Double.valueOf(0);};
}

class SumOperator extends Operator
{
    public Double calculate(Double a, Double b){ return a + b;};
}

class MinOperator extends Operator
{
    public Double calculate(Double a, Double b){ return a<b?a:b;};
}
class MaxOperator extends Operator
{
    public Double calculate(Double a, Double b){ return a>b?a:b;};
}
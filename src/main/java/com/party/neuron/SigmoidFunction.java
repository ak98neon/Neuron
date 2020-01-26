package com.party.neuron;

public class SigmoidFunction implements ActivationFunction {

  @Override
  public Double forward(Double x) {
    return sigmoid(x);
  }

  Double sigmoid(double value) {
    return 1 / (1 + Math.exp(-value));
  }
}

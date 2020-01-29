package com.party.neuron.activate;

public class ReLUFunction implements ActivationFunction {

  @Override
  public Double forward(Double x) {
    return Math.max(0, x);
  }
}

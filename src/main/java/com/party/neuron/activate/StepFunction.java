package com.party.neuron.activate;

public class StepFunction implements ActivationFunction {

  public Double forward(Double x) {
    return x >= 1. ? 1. : 0.;
  }
}

package com.party.neuron.layer;

import java.util.Random;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public interface Layer {

  static double randomWeight() {
    return new Random().nextDouble() - 0.5;
  }

  Array2DRowRealMatrix getNeuronsMatrix();

  Array2DRowRealMatrix getWeightMatrix();
}

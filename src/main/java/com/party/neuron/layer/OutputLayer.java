package com.party.neuron.layer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class OutputLayer implements Layer {

  private Array2DRowRealMatrix neuronsMatrix;

  public OutputLayer(Array2DRowRealMatrix neuronsMatrix) {
    this.neuronsMatrix = neuronsMatrix;
  }

  public static OutputLayer initOutput(int countOfNeurons) {
    Array2DRowRealMatrix neurons = new Array2DRowRealMatrix(1, countOfNeurons);
    for (int i = 0; i < countOfNeurons; i++) {
      neurons.setEntry(0, i, 0);
    }

    return new OutputLayer(neurons);
  }

  @Override
  public Array2DRowRealMatrix getNeuronsMatrix() {
    return this.neuronsMatrix;
  }

  @Override
  public Array2DRowRealMatrix getWeightMatrix() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Array2DRowRealMatrix calculateSignals() {
    throw new UnsupportedOperationException();
  }
}

package com.party.neuron.layer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class OutputLayer implements Layer {

  private Array2DRowRealMatrix neuronsMatrix;

  public OutputLayer(Array2DRowRealMatrix neuronsMatrix) {
    this.neuronsMatrix = neuronsMatrix;
  }

  public static OutputLayer initOutput(int countOfNeurons) {
    Array2DRowRealMatrix neurons = new Array2DRowRealMatrix(countOfNeurons, 1);
    for (int i = 0; i < countOfNeurons; i++) {
      neurons.setEntry(i, 0, 0);
    }

    return new OutputLayer(neurons);
  }

  public Array2DRowRealMatrix forwardResult(Array2DRowRealMatrix hiddenSignals) {
    for (int i = 0; i < neuronsMatrix.getRowDimension(); i++) {
      neuronsMatrix.setEntry(i, 0, hiddenSignals.getEntry(i, 0));
    }
    return neuronsMatrix;
  }

  @Override
  public Array2DRowRealMatrix getNeuronsMatrix() {
    return this.neuronsMatrix;
  }

  @Override
  public Array2DRowRealMatrix getWeightMatrix() {
    throw new UnsupportedOperationException();
  }
}

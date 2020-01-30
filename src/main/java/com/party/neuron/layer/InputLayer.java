package com.party.neuron.layer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class InputLayer implements Layer {

  private Array2DRowRealMatrix neuronsMatrix;
  private Array2DRowRealMatrix weightMatrix;

  public InputLayer(Array2DRowRealMatrix neuronsMatrix,
      Array2DRowRealMatrix weightMatrix) {
    this.neuronsMatrix = neuronsMatrix;
    this.weightMatrix = weightMatrix;
  }

  public static InputLayer init(int countOfNeurons, Layer nextLayer) {
    return init(countOfNeurons, new double[countOfNeurons], nextLayer);
  }

  public static InputLayer init(int countOfNeurons, double[] inputData,
      Layer nextLayer) {
    InitLayer initLayer = InitLayer.init(countOfNeurons, inputData);
    initLayer.connect(nextLayer);
    return new InputLayer(initLayer.getNeuronsMatrix(), initLayer.getWeightMatrix());
  }

  public Array2DRowRealMatrix calculateSignals(double bias) {
    Array2DRowRealMatrix multiply = weightMatrix.multiply(neuronsMatrix);
    for (int i = 0; i < multiply.getRowDimension(); i++) {
      multiply.setEntry(i, 0, multiply.getEntry(i, 0) + bias);
    }
    return multiply;
  }

  @Override
  public Array2DRowRealMatrix getNeuronsMatrix() {
    return this.neuronsMatrix;
  }

  @Override
  public Array2DRowRealMatrix getWeightMatrix() {
    return weightMatrix;
  }
}

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

//  public static InputLayer initWithHidden(int countOfNeurons, double[] inputData,
//      HiddenLayers hiddenLayers) {
//    return init(countOfNeurons, inputData, new InputLayer(
//        hiddenLayers.getNeuronsMatrix(), null)
//    );
//  }

  public static InputLayer init(int countOfNeurons, double[] inputData,
      Layer nextLayer) {
    if (countOfNeurons != inputData.length) {
      throw new RuntimeException("InputLayer: count of neurons not equal to input data length");
    }

    Array2DRowRealMatrix neuronsMatrix = new Array2DRowRealMatrix(1, countOfNeurons);
    for (int i = 0; i < countOfNeurons; i++) {
      neuronsMatrix.setEntry(0, i, inputData[i]);
    }

    Array2DRowRealMatrix weightMatrix = new Array2DRowRealMatrix(
        nextLayer.getNeuronsMatrix().getColumnDimension(),
        countOfNeurons);
    for (int i = 0; i < weightMatrix.getColumnDimension(); i++) {
      for (int j = 0; j < weightMatrix.getRowDimension(); j++) {
        weightMatrix.setEntry(j, i, Layer.randomWeight());
      }
    }

    return new InputLayer(neuronsMatrix, weightMatrix);
  }

  @Override
  public Array2DRowRealMatrix calculateSignals() {
    return this.weightMatrix.multiply(this.neuronsMatrix);
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

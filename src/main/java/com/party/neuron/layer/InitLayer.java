package com.party.neuron.layer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class InitLayer implements Layer {

  private Array2DRowRealMatrix neuronsMatrix;
  private Array2DRowRealMatrix weightMatrix;

  InitLayer(Array2DRowRealMatrix neuronsMatrix,
      Array2DRowRealMatrix weightMatrix) {
    this.neuronsMatrix = neuronsMatrix;
    this.weightMatrix = weightMatrix;
  }

  public static InitLayer init(int countOfNeurons) {
    return init(countOfNeurons, new double[countOfNeurons]);
  }

  public static InitLayer init(int countOfNeurons, double[] inputData) {
    if (countOfNeurons != inputData.length) {
      throw new RuntimeException("InputLayer: count of neurons not equal to input data length");
    }

    Array2DRowRealMatrix neuronsMatrix = new Array2DRowRealMatrix(countOfNeurons, 1);
    for (int i = 0; i < countOfNeurons; i++) {
      neuronsMatrix.setEntry(i, 0, inputData[i]);
    }
    Array2DRowRealMatrix weightMatrix = new Array2DRowRealMatrix();

    return new InitLayer(neuronsMatrix, weightMatrix);
  }

  public void connect(Layer layer) {
    Array2DRowRealMatrix weight = new Array2DRowRealMatrix(
        layer.getNeuronsMatrix().getRowDimension(),
        this.neuronsMatrix.getRowDimension());
    for (int i = 0; i < weight.getColumnDimension(); i++) {
      for (int j = 0; j < weight.getRowDimension(); j++) {
        weight.setEntry(j, i, Layer.randomWeight());
      }
    }
    this.weightMatrix = weight;
  }

  @Override
  public Array2DRowRealMatrix getNeuronsMatrix() {
    return this.neuronsMatrix;
  }

  @Override
  public Array2DRowRealMatrix getWeightMatrix() {
    return this.weightMatrix;
  }
}

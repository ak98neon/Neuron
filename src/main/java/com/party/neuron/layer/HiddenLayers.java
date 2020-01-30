package com.party.neuron.layer;

import com.party.neuron.activate.SigmoidFunction;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class HiddenLayers implements Layer {

  //Multi-layer neurons:weights
  private final List<Array2DRowRealMatrix> layers;
  private final List<Array2DRowRealMatrix> weights;

  HiddenLayers(List<Array2DRowRealMatrix> layers,
      List<Array2DRowRealMatrix> weights) {
    this.layers = layers;
    this.weights = weights;
  }

  public static HiddenLayers initHidden(int countOfNeurons, int countOfLayers,
      Layer nextLayer) {
    List<Array2DRowRealMatrix> layers = new ArrayList<>();
    List<Array2DRowRealMatrix> weights = new ArrayList<>();

    List<InitLayer> tempInitLayers = new ArrayList<>();
    for (int i = 0; i < countOfLayers; i++) {
      InitLayer initLayer = InitLayer.init(countOfNeurons);
      tempInitLayers.add(initLayer);
    }

    for (int i = 0; i <= countOfLayers - 1; i++) {
      if (i < countOfLayers - 1) {
        InitLayer initLayer = tempInitLayers.get(i);
        initLayer.connect(tempInitLayers.get(i + 1));
        layers.add(initLayer.getNeuronsMatrix());
        weights.add(initLayer.getWeightMatrix());
      }

      if (i == countOfLayers - 1) {
        InitLayer lastLayer = tempInitLayers.get(i);
        lastLayer.connect(nextLayer);
        layers.add(lastLayer.getNeuronsMatrix());
        weights.add(lastLayer.getWeightMatrix());
      }
    }
    return new HiddenLayers(layers, weights);
  }

  public List<Array2DRowRealMatrix> getLayers() {
    return layers;
  }

  public List<Array2DRowRealMatrix> getWeights() {
    return weights;
  }

  @Override
  public Array2DRowRealMatrix getNeuronsMatrix() {
    return layers.get(0);
  }

  @Override
  public Array2DRowRealMatrix getWeightMatrix() {
    return weights.get(0);
  }

  public Array2DRowRealMatrix calculateHiddenSignals(Array2DRowRealMatrix inputSignals,
      double bias) {
    //Set first hidden layer signals from input layer
    layers.set(0, inputSignals);

    List<Array2DRowRealMatrix> activateList = new ArrayList<>();
    for (int i = 0; i < layers.size(); i++) {
      Array2DRowRealMatrix multiply;
      if (i == 0) {
        multiply = weights.get(i).multiply(layers.get(i));
      } else {
        multiply = weights.get(i).multiply(activateList.get(activateList.size() - 1));
      }
      Array2DRowRealMatrix addBiasMatrix = addBias(multiply, bias);
      Array2DRowRealMatrix activateHiddenLayers = activateHiddenLayers(addBiasMatrix);

      activateList.add(activateHiddenLayers);
    }
    return activateList.get(activateList.size() - 1);
  }

  private Array2DRowRealMatrix addBias(Array2DRowRealMatrix neurons, double bias) {
    Array2DRowRealMatrix result = new Array2DRowRealMatrix(neurons.getRowDimension(), 1);
    double[] column = neurons.getColumn(0);
    for (int i = 0; i < neurons.getRowDimension(); i++) {
      double res = column[i] + bias;
      result.setEntry(i, 0, res);
    }
    return result;
  }

  private Array2DRowRealMatrix activateHiddenLayers(Array2DRowRealMatrix neurons) {
    SigmoidFunction sigmoidFunction = new SigmoidFunction();

    Array2DRowRealMatrix result = new Array2DRowRealMatrix(neurons.getRowDimension(), 1);
    double[] column = neurons.getColumn(0);
    for (int i = 0; i < neurons.getRowDimension(); i++) {
      double res = sigmoidFunction.forward(column[i]);
      result.setEntry(i, 0, res);
    }
    return result;
  }
}

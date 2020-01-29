package com.party.neuron.layer;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class HiddenLayers implements Layer {

  //Multi-layer neurons:weights
  private final List<List<Array2DRowRealMatrix>> layers;
  private final List<List<Array2DRowRealMatrix>> weights;

  HiddenLayers(
      List<List<Array2DRowRealMatrix>> layers,
      List<List<Array2DRowRealMatrix>> weights) {
    this.layers = layers;
    this.weights = weights;
  }

  public static HiddenLayers initHidden(int countOfNeurons, int countOfLayers,
      Layer nextLayer) {
    List<List<Array2DRowRealMatrix>> layers = new ArrayList<>();
    List<List<Array2DRowRealMatrix>> weights = new ArrayList<>();

    List<InitLayer> tempInitLayers = new ArrayList<>();
    for (int i = 0; i < countOfLayers; i++) {
      InitLayer initLayer = InitLayer.init(countOfNeurons);
      tempInitLayers.add(initLayer);
    }

    List<Array2DRowRealMatrix> tempL = new ArrayList<>();
    List<Array2DRowRealMatrix> tempW = new ArrayList<>();
    for (int i = 0; i <= countOfLayers - 1; i++) {
      if (i < countOfLayers - 1) {
        InitLayer initLayer = tempInitLayers.get(i);
        initLayer.connect(tempInitLayers.get(i + 1));
        tempL.add(initLayer.getNeuronsMatrix());
        tempW.add(initLayer.getWeightMatrix());
      }

      if (i == countOfLayers - 1) {
        InitLayer lastLayer = tempInitLayers.get(i);
        lastLayer.connect(nextLayer);
        tempL.add(lastLayer.getNeuronsMatrix());
        tempW.add(lastLayer.getWeightMatrix());
      }
    }

    layers.add(tempL);
    weights.add(tempW);
    return new HiddenLayers(layers, weights);
  }

  @Override
  public Array2DRowRealMatrix getNeuronsMatrix() {
    return layers.get(0).get(0);
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

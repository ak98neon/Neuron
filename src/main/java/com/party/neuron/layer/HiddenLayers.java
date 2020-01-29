//package com.party.neuron.layer;
//
//import com.party.neuron.model.DefaultNeuron;
//import com.party.neuron.model.Neuron;
//import java.util.ArrayList;
//import java.util.List;
//
//public class HiddenLayers implements Layer {
//
//  private final List<List<Neuron>> layers;
//
//  public HiddenLayers(List<List<Neuron>> layers) {
//    this.layers = layers;
//  }
//
//  public static HiddenLayers initHidden(int countOfNeurons, int countOfLayers,
//      Layer nextLayer) {
//    List<List<Neuron>> layers = new ArrayList<>();
//
//    for (int i = 0; i < countOfLayers; i++) {
//      List<Neuron> neurons = new ArrayList<>();
//      for (int j = 0; j < countOfNeurons; j++) {
//        neurons.add(new DefaultNeuron());
//      }
//      layers.add(neurons);
//      if (i == countOfLayers - 1) {
//        nextLayer.getNeurons().forEach(x -> layers.get(layers.size() - 1)
//            .forEach(neuron -> neuron.connect(x, Layer.randomWeight())));
//      } else if (i > 0 && i != countOfLayers - 1) {
//        int finalI = i;
//        layers.get(i - 1).forEach(
//            n -> layers.get(finalI).forEach(neuron -> n.connect(neuron, Layer.randomWeight())));
//      }
//    }
//
//    return new HiddenLayers(layers);
//  }
//
//  public List<List<Neuron>> getLayers() {
//    return layers;
//  }
//
//  @Override
//  public List<Neuron> getNeurons() {
//    throw new UnsupportedOperationException();
//  }
//}

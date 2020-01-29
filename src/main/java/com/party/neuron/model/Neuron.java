package com.party.neuron.model;

public interface Neuron {

  void forwardSignalReceived(Neuron from, Double value);

  void backwardSignalReceived(Neuron from, Double value);

  default void connect(Neuron neuron, Double weight) {
    this.addForwardConnection(neuron);
    neuron.addBackwardConnection(this, weight);
  }

  void addForwardConnection(Neuron neuron);

  void addBackwardConnection(Neuron neuron, Double weight);
}

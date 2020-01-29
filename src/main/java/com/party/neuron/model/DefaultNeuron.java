package com.party.neuron.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultNeuron implements Neuron {

  private Double signal;
  private Set<Neuron> forwardConnections = new HashSet<>();
  private Map<Neuron, Double> backwardConnections = new HashMap<>();

  public DefaultNeuron() {
  }

  public DefaultNeuron(Double signal) {
    this.signal = signal;
  }

  @Override
  public void forwardSignalReceived(Neuron from, Double value) {
    forwardConnections.forEach(n -> n.forwardSignalReceived(this, value));
  }

  @Override
  public void backwardSignalReceived(Neuron from, Double value) {
    backwardConnections.forEach((n, w) -> n.backwardSignalReceived(this, value));
  }

  @Override
  public void addForwardConnection(Neuron neuron) {
    forwardConnections.add(neuron);
  }

  @Override
  public void addBackwardConnection(Neuron neuron, Double weight) {
    backwardConnections.put(neuron, weight);
  }

  public Set<Neuron> getForwardConnections() {
    return forwardConnections;
  }

  public Double signal() {
    return signal;
  }
}

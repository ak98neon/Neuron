package com.party.neuron;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ConnectedNeuron implements Neuron {

  private final ActivationFunction activationFunction;

  private final Map<Neuron, Double> inputSignals = new HashMap<>();

  private final Map<Neuron, Double> backwardConnections = new HashMap<>();

  private final Set<Neuron> forwardConnections = new HashSet<>();

  private final String name;

  private final AtomicDouble bias;

  private volatile int signalReceived;

  private volatile double forwardResult;

  private ConnectedNeuron(
      final ActivationFunction activationFunction,
      final String name,
      final double bias) {
    this.activationFunction = activationFunction;
    this.name = name;
    this.bias = new AtomicDouble(bias);
  }

  public double getForwardResult() {
    return forwardResult;
  }

  @Override
  public void forwardSignalReceived(Neuron from, Double value) {
    signalReceived++;
    inputSignals.put(from, value);

    if (backwardConnections.size() == signalReceived) {
      double forwardInputToActivationFunction
          = backwardConnections
          .entrySet()
          .stream()
          .mapToDouble(connection ->
              inputSignals.get(connection.getKey())
                  * connection.getValue())
          .sum() + bias.doubleValue();

      Double signalToSend = activationFunction.forward(forwardInputToActivationFunction);
      forwardResult = signalToSend;

      forwardConnections
          .forEach(con -> con.forwardSignalReceived(ConnectedNeuron.this, signalToSend));
      signalReceived = 0;
    }
  }

  @Override
  public void addForwardConnection(Neuron neuron) {
    forwardConnections.add(neuron);
  }

  @Override
  public void addBackwardConnection(Neuron neuron, Double weight) {
    backwardConnections.put(neuron, weight);
    inputSignals.put(neuron, Double.NaN);
  }

  public static class Builder {

    private double bias = new Random().nextDouble();

    private String name;

    private ActivationFunction activationFunction;

    public Builder bias(final double bias) {
      this.bias = bias;
      return this;
    }

    public Builder name(final String name) {
      this.name = name;
      return this;
    }

    public Builder activationFunction(final ActivationFunction activationFunction) {
      this.activationFunction = activationFunction;
      return this;
    }

    public ConnectedNeuron build() {
      if (activationFunction == null) {
        throw new RuntimeException("ActivationFunction need to be set in order to" +
            " create a ConnectedNeuron");
      }
      return new ConnectedNeuron(
          activationFunction,
          name,
          bias);
    }
  }
}

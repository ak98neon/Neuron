package com.party.neuron;

import com.google.common.util.concurrent.AtomicDouble;
import com.party.neuron.activate.ActivationFunction;
import com.party.neuron.layer.HiddenLayers;
import com.party.neuron.layer.InputLayer;
import com.party.neuron.layer.Layer;
import com.party.neuron.layer.OutputLayer;
import com.party.neuron.model.Neuron;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NeuralNetwork implements Neuron {

  private final String name;

  private final ActivationFunction activationFunction;
  private final List<Layer> layers;
  private final AtomicDouble bias;
  private AtomicInteger signalReceived;
  private volatile double forwardResult;

  private NeuralNetwork(
      final ActivationFunction activationFunction,
      final String name,
      final double bias,
      final List<Layer> layers) {
    this.activationFunction = activationFunction;
    this.name = name;
    this.bias = new AtomicDouble(bias);
    this.signalReceived = new AtomicInteger();
    this.layers = layers;
  }

  public List<Layer> getLayers() {
    return layers;
  }

  public double getForwardResult() {
    return forwardResult;
  }

  @Override
  public void forwardSignalReceived(Neuron from, Double value) {

//    signalReceived.incrementAndGet();
//    inputSignals.put(from, value);
//
//    if (backwardConnections.size() == signalReceived.get()) {
//      double forwardInputToActivationFunction
//          = backwardConnections
//          .entrySet()
//          .stream()
//          .mapToDouble(connection ->
//              inputSignals.get(connection.getKey())
//                  * connection.getValue())
//          .sum() + bias.doubleValue();
//
//      Double signalToSend = activationFunction.forward(forwardInputToActivationFunction);
//      forwardResult = signalToSend;
//
////      forwardConnections
////          .forEach(con -> con.forwardSignalReceived(ConnectedNeuron.this, signalToSend));
//      signalReceived.set(0);
//    }
  }

  @Override
  public void backwardSignalReceived(Neuron from, Double value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addForwardConnection(Neuron neuron) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addBackwardConnection(Neuron neuron, Double weight) {
    throw new UnsupportedOperationException();
  }

  public static class Builder {

    private double bias = new Random().nextDouble() - 0.5;

    private String name;

    private ActivationFunction activationFunction;

    private int countOfInputNeurons;

    private int countOfHiddenNeurons;

    private int countOfOutputNeurons;

    private int hiddenLayersCount;

    private double[] inputData;

    private double[] trainingData;

    public NeuralNetwork.Builder inputData(double... inputData) {
      this.inputData = inputData;
      return this;
    }

    public NeuralNetwork.Builder bias(final double bias) {
      this.bias = bias;
      return this;
    }

    public NeuralNetwork.Builder name(final String name) {
      this.name = name;
      return this;
    }

    public NeuralNetwork.Builder trainingData(double... trainingData) {
      this.trainingData = trainingData;
      return this;
    }

    public NeuralNetwork.Builder countOfInputNeurons(int countOfInputLayer) {
      this.countOfInputNeurons = countOfInputLayer;
      return this;
    }

    public NeuralNetwork.Builder countOfHiddenNeurons(int countOfHiddenNeurons) {
      this.countOfHiddenNeurons = countOfHiddenNeurons;
      return this;
    }

    public NeuralNetwork.Builder countOfOutputNeurons(int countOfOutputNeurons) {
      this.countOfOutputNeurons = countOfOutputNeurons;
      return this;
    }

    public NeuralNetwork.Builder hiddenLayersCount(int hiddenLayersCount) {
      this.hiddenLayersCount = hiddenLayersCount;
      return this;
    }

    public NeuralNetwork.Builder activationFunction(final ActivationFunction activationFunction) {
      this.activationFunction = activationFunction;
      return this;
    }

    public NeuralNetwork build() {
      if (activationFunction == null) {
        throw new RuntimeException("ActivationFunction need to be set in order to" +
            " create a ConnectedNeuron");
      }

      OutputLayer outputLayer = OutputLayer.initOutput(countOfOutputNeurons);
      HiddenLayers hiddenLayers = HiddenLayers.initHidden(countOfHiddenNeurons, hiddenLayersCount,
          outputLayer);
      InputLayer inputLayer = InputLayer.init(countOfInputNeurons, inputData,
          hiddenLayers);

      List<Layer> layers = new ArrayList<>();
      layers.add(inputLayer);
      layers.add(hiddenLayers);
      layers.add(outputLayer);

      return new NeuralNetwork(
          activationFunction,
          name,
          bias,
          layers
      );
    }
  }
}

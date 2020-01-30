package com.party.neuron;

import com.party.neuron.activate.ActivationFunction;
import com.party.neuron.constanse.LayersName;
import com.party.neuron.layer.HiddenLayers;
import com.party.neuron.layer.InputLayer;
import com.party.neuron.layer.Layer;
import com.party.neuron.layer.OutputLayer;
import com.party.neuron.model.Neuron;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class NeuralNetwork implements Neuron {

  private final String name;

  private final ActivationFunction activationFunction;
  private final List<Layer> layers;
  private final double bias;
  private AtomicInteger signalReceived;
  private Array2DRowRealMatrix forwardResult;

  private NeuralNetwork(
      final ActivationFunction activationFunction,
      final String name,
      final double bias,
      final List<Layer> layers) {
    this.activationFunction = activationFunction;
    this.name = name;
    this.bias = bias;
    this.signalReceived = new AtomicInteger();
    this.layers = layers;
  }

  public List<Layer> getLayers() {
    return layers;
  }

  public Array2DRowRealMatrix getForwardResult() {
    forwardSignalByLayers();
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

  public void forwardSignalByLayers() {
    InputLayer inputLayer = (InputLayer) layers.get(LayersName.INPUT_LAYER);
    HiddenLayers hiddenLayer = (HiddenLayers) layers.get(LayersName.HIDDEN_LAYER);
    OutputLayer outputLayer = (OutputLayer) layers.get(LayersName.OUTPUT_LAYER);

    Array2DRowRealMatrix inputLayerSignals = inputLayer.calculateSignals(bias);
    Array2DRowRealMatrix hiddenLayerSignals = hiddenLayer
        .calculateHiddenSignals(inputLayerSignals, bias);
    this.forwardResult = outputLayer.forwardResult(hiddenLayerSignals);
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

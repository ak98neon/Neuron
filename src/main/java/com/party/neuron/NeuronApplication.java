package com.party.neuron;

import com.party.neuron.activate.SigmoidFunction;
import java.util.Random;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeuronApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(NeuronApplication.class, args);
  }

  @Override
  public void run(String... args) {
    double[][] trainingInput = new double[][]{
        {0, 0},
        {0, 1},
        {1, 0},
        {1, 1}
    };

    double[][] trainingOutputs = new double[][]{
        {0}, {1}, {1}, {0}
    };

    NeuralNetwork neuralNetwork = new NeuralNetwork.Builder()
        .bias(new Random().nextDouble())
        .activationFunction(new SigmoidFunction())
        .countOfInputNeurons(2)
        .inputData(trainingInput[0])
        .trainingData(trainingOutputs[0])
        .countOfHiddenNeurons(3)
        .countOfOutputNeurons(4)
        .hiddenLayersCount(1)
        .name("neural-nn")
        .build();

    Array2DRowRealMatrix array2DRowRealMatrix = neuralNetwork.getLayers().get(0).calculateSignals();
    System.out.println("Result: " + neuralNetwork.getForwardResult());
  }
}

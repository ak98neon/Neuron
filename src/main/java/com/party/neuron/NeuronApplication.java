package com.party.neuron;

import com.party.neuron.activate.SigmoidFunction;
import java.util.Random;
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
        .inputData(trainingInput[1])
        .trainingData(trainingOutputs[0])
        .countOfHiddenNeurons(3)
        .hiddenLayersCount(1)
        .countOfOutputNeurons(4)
        .name("neural-nn")
        .build();

    System.out.println("Result: " + neuralNetwork.getForwardResult());
  }
}

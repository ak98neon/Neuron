package com.party.neuron;

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

    InputNeuron inputX = new InputNeuron();
    InputNeuron inputY = new InputNeuron();

    ConnectedNeuron outputNeuron = new ConnectedNeuron.Builder()
        .bias(-1)
        .activationFunction(new SigmoidFunction())
        .build();

    inputX.connect(outputNeuron, 1.);
    inputY.connect(outputNeuron, 1.);

    inputX.forwardSignalReceived(null, 1.);
    inputY.forwardSignalReceived(null, 0.);

    double result = outputNeuron.getForwardResult();
    System.out.printf("Prediction %3f\n", result);

//    InputNeuron inputFriend = new InputNeuron();
//    InputNeuron inputVodka = new InputNeuron();
//    InputNeuron inputSunny = new InputNeuron();
//
//    ConnectedNeuron outputNeuron = new ConnectedNeuron.Builder()
//        .bias(-1)
//        .activationFunction(new SigmoidFunction())
//        .build();
//
//    inputFriend.connect(outputNeuron, 1.);
//    inputVodka.connect(outputNeuron, 1.);
//    inputSunny.connect(outputNeuron, 1.);
//
//    inputFriend.forwardSignalReceived(null, 1.);
//    inputVodka.forwardSignalReceived(null, 0.);
//    inputSunny.forwardSignalReceived(null, 1.);
//
//    double result = outputNeuron.getForwardResult();
//    System.out.printf("Prediction %3f\n", result);
  }

//  public double backwardForward() {
//    for (int i = 0; i < 20_000; i++) {
//
//    }
//  }
}

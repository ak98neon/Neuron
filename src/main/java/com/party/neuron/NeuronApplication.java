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
    InputNeuron inputFriend = new InputNeuron();
    InputNeuron inputVodka = new InputNeuron();
    InputNeuron inputSunny = new InputNeuron();

    ConnectedNeuron outputNeuron = new ConnectedNeuron.Builder()
        .bias(-1)
        .activationFunction(new StepFunction())
        .build();

    inputFriend.connect(outputNeuron, 1.);
    inputVodka.connect(outputNeuron, 1.);
    inputSunny.connect(outputNeuron, 1.);

    inputFriend.forwardSignalReceived(null, 1.);
    inputVodka.forwardSignalReceived(null, 0.);
    inputSunny.forwardSignalReceived(null, 1.);

    double result = outputNeuron.getForwardResult();
    System.out.printf("Prediction %3f\n", result);
  }
}

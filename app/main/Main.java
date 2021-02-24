package main;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import models.ExamplePersistentActor;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("tests");
      ActorRef exampleActor = actorSystem.actorOf(Props.create(ExamplePersistentActor.class));
      Scanner scan = new Scanner(System.in);
      for (int i = 0; i < 5; i++) {
          System.out.println("Your input?");
          String input = scan.nextLine();
          exampleActor.tell(new ExamplePersistentActor.Add(input),exampleActor);
      }






    }
}

package models;


import akka.japi.Procedure;
import akka.persistence.AbstractPersistentActor;
import akka.persistence.SnapshotOffer;


public class ExamplePersistentActor extends AbstractPersistentActor {

    private ExampleState state = new ExampleState();
    private int snapShotInterval = 1000;


    interface Command {}

    public static class Add implements Command {
        public final String data;

        public Add(String data) {
            this.data = data;
        }
    }

    public enum Clear implements Command {
        INSTANCE
    }


    @Override
    public String persistenceId() {
        return "example_persistent_actor_id";
    }

    @Override
    public Receive createReceiveRecover() {
        return receiveBuilder()
                .match(Added.class, (e) -> state.addItem(e.data))
                .match(SnapshotOffer.class, ss -> {
                    state = (ExampleState) ss.snapshot();
                    System.out.println(state.getLast());
                } )
                .build();
    }
//todo test of persistAll
//    private Procedure<Eve> procedure =  param-> {
//        state = param.addItem(param.data);
//        getContext().getSystem().getEventStream().publish(param);
//        saveSnapshot(param.copy());
//    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Add.class,
                        c -> {
                            final Added evt = new Added(c.data);
                            persist(
                                    evt,
                                    (Added e) -> {
                                       state = state.addItem(e.data);
                                        getContext().getSystem().getEventStream().publish(e);
                                            saveSnapshot(state.copy());
                                        }); })
                .matchEquals("print", s -> System.out.println(state))
                .build();
    }


}








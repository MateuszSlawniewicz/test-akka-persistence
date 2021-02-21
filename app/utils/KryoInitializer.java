package utils;

import com.esotericsoftware.kryo.Kryo;
import models.ExamplePersistentActor;


import java.util.HashMap;

public class KryoInitializer  {
    public void customize(Kryo kryo) {
        kryo.register(HashMap.class);
        kryo.register(ExamplePersistentActor.class);

    }
}
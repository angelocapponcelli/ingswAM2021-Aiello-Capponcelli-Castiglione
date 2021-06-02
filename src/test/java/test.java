import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class test{
    enum Type{
        coin,
        stone,
        shield,
        servant
    }

    public static void main(String []args){
        Map<Type, Integer> map = new HashMap<>();
        map.put(Type.coin,2);
        map.put(Type.stone,3);

        map.entrySet()
                .forEach(entry -> IntStream.range(0, entry.getValue())
                        .mapToObj(x -> entry.getKey())
                        .forEach(System.out::println));

    }
}
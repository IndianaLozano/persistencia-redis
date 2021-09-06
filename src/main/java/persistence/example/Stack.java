package persistence.example;

import redis.clients.jedis.Jedis;

public class Stack {

    private final Jedis connection;

    public Stack(Jedis connection) {
        this.connection = connection;
    }

    public void push(int value) {
        //RPUSH: append one or multiple elements to a list.
        //LPUSH: prepend one or multiple elements to a list.
        connection.rpush("firstStack", String.valueOf(value));
    }

    public int pop() {
        if(connection.llen("firstStack") == 0){
            throw new UnderflowException();
        } else {
            return Integer.parseInt(connection.rpop("firstStack"));
        }
    }

    public boolean isEmpty() {
        if(connection.llen("firstStack") == 0){
            return true;
        }
        return false;
    }

    public static class UnderflowException extends RuntimeException {    }

}

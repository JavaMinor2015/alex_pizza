package pizza.domain.beans.socket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by alex on 12/3/15.
 */
@ServerEndpoint("/websocket")
public class EchoEndpoint {
    private static final Queue<Session> queue = new ConcurrentLinkedQueue<>();
    private static final Logger LOGGER = LogManager.getLogger(EchoEndpoint.class);

    @OnMessage
    public void textMessage(Session session, String msg) {
        System.err.println(msg);
    }

    public void send(final String msg) {
        try {
            for (Session sess : queue) {
                sess.getBasicRemote().sendText(msg);
                LOGGER.error(msg);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    @OnOpen
    public void openConnection(Session session) {
        /* Register this connection in the queue */
        queue.add(session);
        LOGGER.info("Connection opened.");
    }

    @OnClose
    public void closedConnection(Session session) {
        /* Remove this connection from the queue */
        queue.remove(session);
        LOGGER.info("Connection closed.");
    }

    @OnError
    public void error(Session session, Throwable t) {
        /* Remove this connection from the queue */
        queue.remove(session);
        LOGGER.info(t.toString());
        LOGGER.info("Connection error.");
    }
}

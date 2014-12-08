package hotciv.observer;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

/**
 * Created by Henrik on 08-12-14.
 */
public class ObserverGame implements GameObserver {

    public Player player;
    public int age;
    public Position position;

    @Override
    public void worldChangedAt(Position pos) {
        System.out.println("Jeg er en observer, der har fået kaldt worldChangedAt()");
        this.position = pos;
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        player = nextPlayer;
        this.age = age;
        System.out.println("Jeg er en observer, der har fået kaldt turnEnds()");
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        System.out.println("Jeg er en observer, der har fået kaldt tileFocusChangedAt()");
    }
}

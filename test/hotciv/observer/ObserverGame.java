package hotciv.observer;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

/**
 * Created by Henrik on 08-12-14.
 */
public class ObserverGame implements GameObserver {

    public String msg = "";

    @Override
    public void worldChangedAt(Position pos) {
        //System.out.println("Jeg er en observer, der har fået kaldt worldChangedAt()");
        msg += "WorldChangedAt (" + pos.getRow() + "," + pos.getColumn() + ") ";
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        msg += "Turn has ended";
       // System.out.println("Jeg er en observer, der har fået kaldt turnEnds()");
}

    @Override
    public void tileFocusChangedAt(Position position) {
       // System.out.println("Jeg er en observer, der har fået kaldt tileFocusChangedAt()");
    }
}

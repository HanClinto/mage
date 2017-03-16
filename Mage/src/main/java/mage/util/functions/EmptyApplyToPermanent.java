package mage.util.functions;

import mage.MageObject;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 * @author noxx
 */
public class EmptyApplyToPermanent extends ApplyToPermanent {

    @Override
    public boolean apply(Game game, Permanent permanent) {
        // do nothing
        return true;
    }

    @Override
    public boolean apply(Game game, MageObject mageObject) {
        return true;
    }

}

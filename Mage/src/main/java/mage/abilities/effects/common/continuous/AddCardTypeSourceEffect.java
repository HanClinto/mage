/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.abilities.effects.common.continuous;

import mage.MageObjectReference;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.constants.*;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 * @author emerald000
 */
public class AddCardTypeSourceEffect extends ContinuousEffectImpl {

    private final CardType addedCardType;

    public AddCardTypeSourceEffect(CardType addedCardType, Duration duration) {
        super(duration, Layer.TypeChangingEffects_4, SubLayer.NA, Outcome.Benefit);
        this.addedCardType = addedCardType;
        if (addedCardType == CardType.ENCHANTMENT) {
            dependencyTypes.add(DependencyType.EnchantmentAddingRemoving);
        }
    }

    public AddCardTypeSourceEffect(final AddCardTypeSourceEffect effect) {
        super(effect);
        this.addedCardType = effect.addedCardType;
    }

    @Override
    public void init(Ability source, Game game) {
        super.init(source, game);
        affectedObjectList.add(new MageObjectReference(source.getSourceId(), game.getState().getZoneChangeCounter(source.getSourceId()), game));
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getSourceId());
        if (permanent != null && affectedObjectList.contains(new MageObjectReference(permanent, game))) {
            if (!permanent.getCardType().contains(addedCardType)) {
                permanent.getCardType().add(addedCardType);
            }
            return true;
        } else if (this.getDuration() == Duration.Custom) {
            this.discard();
        }
        return false;
    }

    @Override
    public AddCardTypeSourceEffect copy() {
        return new AddCardTypeSourceEffect(this);
    }

    @Override
    public String getText(Mode mode) {
        if (staticText != null && !staticText.isEmpty()) {
            return staticText;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{this} becomes ").append(addedCardType.toString()).append(" in addition to its other types " + this.getDuration().toString());
        return sb.toString();
    }
}

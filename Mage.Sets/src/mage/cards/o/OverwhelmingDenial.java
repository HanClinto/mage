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
package mage.cards.o;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.CantBeCounteredSourceEffect;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.keyword.SurgeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.target.TargetSpell;

/**
 *
 * @author fireshoes
 */
public class OverwhelmingDenial extends CardImpl {

    public OverwhelmingDenial(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{2}{U}{U}");

        // Overwhelming Denial can't be countered by spell or abilities.
        Effect effect = new CantBeCounteredSourceEffect();
        effect.setText("{this} can't be countered by spells or abilities");
        Ability ability = new SimpleStaticAbility(Zone.STACK, effect);
        ability.setRuleAtTheTop(true);
        this.addAbility(ability);
        
        // Counter target spell.
        this.getSpellAbility().addTarget(new TargetSpell());
        this.getSpellAbility().addEffect(new CounterTargetEffect());
        
        // Has to be placed last here, because added spellAbility objects (e.g. effects) have to be copied from this
        // Surge {U}{U} (You may cast this spell for its surge cost if you or a teammate has cast another spell this turn)
        addAbility(new SurgeAbility(this, "{U}{U}"));
    }

    public OverwhelmingDenial(final OverwhelmingDenial card) {
        super(card);
    }

    @Override
    public OverwhelmingDenial copy() {
        return new OverwhelmingDenial(this);
    }
}

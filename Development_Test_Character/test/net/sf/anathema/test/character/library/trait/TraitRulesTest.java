package net.sf.anathema.test.character.library.trait;

import net.sf.anathema.character.generic.dummy.DummyLimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TraitRulesTest {

  private DummyLimitationContext limitationContext = new DummyLimitationContext();
  private ITraitTemplate template = EasyMock.createMock(ITraitTemplate.class);
  private TraitRules traitRules = new TraitRules(AbilityType.Archery, template, limitationContext);

  @Test
  public void testAbsoluteMaximumValue() throws Exception {
    int absoluteMaxValue = 9;
    ITraitLimitation limitation = EasyMock.createMock(ITraitLimitation.class);
    EasyMock.expect(limitation.getAbsoluteLimit(limitationContext)).andReturn(absoluteMaxValue);
    EasyMock.expect(template.getLimitation()).andReturn(limitation);
    EasyMock.replay(template, limitation);
    int actualMaximalValue = traitRules.getAbsoluteMaximumValue();
    EasyMock.verify(template, limitation);
    assertEquals(absoluteMaxValue, actualMaximalValue);
  }
}
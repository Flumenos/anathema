package net.sf.anathema.character.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.BasicContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.content.abilities.AbilitiesContent;
import net.sf.anathema.character.reporting.pdf.content.abilities.AbilitiesContentFactory;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContent;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContentFactory;
import net.sf.anathema.character.reporting.pdf.content.essence.SimpleEssenceContent;
import net.sf.anathema.character.reporting.pdf.content.essence.SimpleEssenceContentFactory;
import net.sf.anathema.character.reporting.pdf.content.experience.ExperienceContent;
import net.sf.anathema.character.reporting.pdf.content.experience.ExperienceContentFactory;
import net.sf.anathema.character.reporting.pdf.content.virtues.VirtueContent;
import net.sf.anathema.character.reporting.pdf.content.virtues.VirtueContentFactory;
import net.sf.anathema.character.reporting.pdf.content.willpower.WillpowerContent;
import net.sf.anathema.character.reporting.pdf.content.willpower.WillpowerContentFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes.AttributesEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.essence.SimpleEssenceBoxEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.notes.NotesEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.social.SocialCombatEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.ExtendedWillpowerEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.SimpleWillpowerEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

public class CharacterReportingModule extends CharacterModuleAdapter<CharacterReportingModuleObject> {

  private CharacterReportingModuleObject moduleObject;

  @Override
  public void initModuleObject() {
    this.moduleObject = new CharacterReportingModuleObject();
  }

  @Override
  public CharacterReportingModuleObject getModuleObject() {
    return moduleObject;
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    addReportContents(resources);
    addReportEncoders();
  }

  private void addReportEncoders() {
    EncoderRegistry registry = moduleObject.getEncoderRegistry();
    registry.add(new AttributesEncoderFactory());
    registry.add(new ExperienceBoxEncoderFactory());
    registry.add(new NotesEncoderFactory());
    registry.add(new SimpleEssenceBoxEncoderFactory());
    registry.add(new SocialCombatEncoderFactory());
    registry.add(new SimpleWillpowerEncoderFactory());
    registry.add(new ExtendedWillpowerEncoderFactory());
  }

  private void addReportContents(IResources resources) {
    ReportContentRegistry registry = moduleObject.getContentRegistry();
    registry.addFactory(VirtueContent.class, new VirtueContentFactory(resources));
    registry.addFactory(ExtendedEssenceContent.class, new ExtendedEssenceContentFactory(resources));
    registry.addFactory(SimpleEssenceContent.class, new SimpleEssenceContentFactory(resources));
    registry.addFactory(AbilitiesContent.class, new AbilitiesContentFactory(resources));
    registry.addFactory(ExperienceContent.class, new ExperienceContentFactory(resources));
    registry.addFactory(WillpowerContent.class, new WillpowerContentFactory(resources));
    registry.addFactory(BasicContent.class, new BasicContentFactory());
  }
}

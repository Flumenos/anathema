package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.magic.charms.type.IReflexiveSpecialsModel;

public interface IReflexiveSpecialsEntryModel extends IReflexiveSpecialsModel {

  public void setSplitEnabled(boolean splitEnabled);

  public void setStep(Integer newValue);

  public void setDefenseStep(Integer newValue);
}
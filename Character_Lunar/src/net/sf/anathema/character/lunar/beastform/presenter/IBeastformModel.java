package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.lunar.beastform.model.IBeastformGroupCost;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IBeastformModel extends IAdditionalModel {

  public int getCharmValue();

  public void setCharmLearnCount(int newValue);

  public void addCharmLearnCountChangedListener(IIntValueChangedListener listener);

  public IBeastformAttribute[] getAttributes();

  public IGiftModel getGiftModel();

  public IBeastformGroupCost getAttributeCostModel();
}
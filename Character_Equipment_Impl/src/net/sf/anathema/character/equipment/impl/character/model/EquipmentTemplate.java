package net.sf.anathema.character.equipment.impl.character.model;

import java.util.List;
import java.util.Map;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class EquipmentTemplate implements IEquipmentTemplate {

  private final Map<String, List<IEquipmentStats>> statsByRuleSet;
  private final String description;
  private final String name;
  private final ICollectionFactory collectionFactory;
  private final String material;
  private final String composition;

  public EquipmentTemplate(
      String name,
      String description,
      MaterialComposition composition,
      MagicalMaterial material,
      ICollectionFactory collectionFactory) {
    this.name = name;
    this.description = description;
    this.composition = composition.getId();
    if (material == null) {
      this.material = null;
    }
    else {
      this.material = material.getId();
    }
    this.collectionFactory = collectionFactory;
    this.statsByRuleSet = collectionFactory.createHashMap();
  }

  public String getDescription() {
    return description;
  }

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet) {
    List<IEquipmentStats> relevantStats = statsByRuleSet.get(ruleSet.getId());
    if (relevantStats == null) {
      return new IEquipmentStats[0];
    }
    return relevantStats.toArray(new IEquipmentStats[relevantStats.size()]);
  }

  public synchronized void addStats(IExaltedRuleSet ruleSet, IEquipmentStats stats) {
    List<IEquipmentStats> statList = statsByRuleSet.get(ruleSet.getId());
    if (statList == null) {
      statList = collectionFactory.createList();
      statsByRuleSet.put(ruleSet.getId(), statList);
    }
    statList.add(stats);
  }

  public String getName() {
    return name;
  }

  public MagicalMaterial getMaterial() {
    if (material == null) {
      return null;
    }
    return MagicalMaterial.valueOf(material);
  }

  public MaterialComposition getComposition() {
    return MaterialComposition.valueOf(composition);
  }
}
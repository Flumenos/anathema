package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.equipment.MeleeWeaponType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.quality.model.QualityExclusion;
import net.sf.anathema.character.library.quality.model.QualityPrerequisite;

public class GiftProvider {

  // TODO: Rest

  public static IGift[] getAllGifts() {
    List<IGift> gifts = new ArrayList<IGift>();
    Gift horrifyingFirst = new AttributePointsProvidingGift("HorrifyingMightFirst", 2);//$NON-NLS-1$
    gifts.add(horrifyingFirst);
    Gift horrifyingLater = new AttributePointsProvidingGift("HorrifyingMightLater", 1);//$NON-NLS-1$
    gifts.add(horrifyingLater);
    horrifyingFirst.addCondition(new QualityExclusion(horrifyingLater));
    horrifyingLater.addCondition(new QualityExclusion(horrifyingFirst));
    Gift bestialReflexesI = new Gift("BestialReflexesI");//$NON-NLS-1$
    gifts.add(bestialReflexesI);
    Gift bestialReflexesII = new Gift("BestialReflexesII");//$NON-NLS-1$
    bestialReflexesII.addCondition(new QualityPrerequisite(bestialReflexesI));
    gifts.add(bestialReflexesII);
    Gift lightningSpeed = new Gift("LightningSpeed");//$NON-NLS-1$
    gifts.add(lightningSpeed);
    Gift spiderFoot = new Gift("Spider-FootClimbing");//$NON-NLS-1$
    spiderFoot.addCondition(new QualityPrerequisite(new IGift[] { bestialReflexesI, lightningSpeed }));
    gifts.add(spiderFoot);
    Gift glueFoot = new Gift("Glue-FootClimbing");//$NON-NLS-1$
    glueFoot.addCondition(new QualityPrerequisite(spiderFoot));
    gifts.add(glueFoot);
    Gift giftHands = new Gift("GiftHands");//$NON-NLS-1$)
    gifts.add(giftHands);
    Gift armArray = new Gift("Arm-Array"); //$NON-NLS-1$
    armArray.addCondition(new QualityPrerequisite(giftHands));
    gifts.add(armArray);
    createBrawlWeaponGifts(gifts);
    Gift resilienceNature = new Gift("ResilienceNature"); //$NON-NLS-1$
    gifts.add(resilienceNature);
    Gift woundKnitting = new Gift("Wound-KnittingPower"); //$NON-NLS-1$
    woundKnitting.addCondition(new QualityPrerequisite(resilienceNature));
    gifts.add(woundKnitting);
    Gift appearance = new Gift("FearsomeAppearance"); //$NON-NLS-1$
    gifts.add(appearance);
    Gift visage = new Gift("TerrifyingBestialVisage"); //$NON-NLS-1$
    visage.addCondition(new QualityPrerequisite(appearance));
    gifts.add(visage);
    Gift ruggedHide = new SoakProvidingGift("RuggedHide", 2, false); //$NON-NLS-1$
    gifts.add(ruggedHide);
    Gift beastArmor = new SoakProvidingGift("ImpenetrableBeast-Armor", 6, true); //$NON-NLS-1$
    beastArmor.addCondition(new QualityPrerequisite(ruggedHide));
    gifts.add(beastArmor);
    Gift poisonBite = new Gift("PoisonBite"); //$NON-NLS-1$
    gifts.add(poisonBite);
    Gift deadlyBreath = new Gift("DeadlyBreath"); //$NON-NLS-1$
    deadlyBreath.addCondition(new QualityPrerequisite(poisonBite));
    gifts.add(deadlyBreath);
    Gift senses = new AttributeEnhancingGift("EnhancedSenses", AttributeType.Perception, 2); //$NON-NLS-1$
    gifts.add(senses);
    Gift sight = new Gift("GhostSight"); //$NON-NLS-1$
    sight.addCondition(new QualityPrerequisite(senses));
    gifts.add(sight);
    gifts.add(new Gift("AspectGillman"));//$NON-NLS-1$)
    Gift flutteringWings = new Gift("FlutteringWings");//$NON-NLS-1$
    gifts.add(flutteringWings);
    Gift soaringPinions = new Gift("SoaringPinions");//$NON-NLS-1$
    soaringPinions.addCondition(new QualityPrerequisite(flutteringWings));
    gifts.add(soaringPinions);
    return gifts.toArray(new IGift[gifts.size()]);
  }

  private static void createBrawlWeaponGifts(List<IGift> gifts) {
    final BrawlWeaponProvidingGift beastClaws = new BrawlWeaponProvidingGift("TerribleBeastClaws"); //$NON-NLS-1$
    for (IExaltedRuleSet rules : ExaltedRuleSet.values()) {
      rules.accept(new IRuleSetVisitor() {
        public void visitCoreRules(IExaltedRuleSet set) {
          beastClaws.addHandWeapon(set, new MeleeWeaponType("DeadlyBeastmanTransformation.Weapon.Claws", //$NON-NLS-1$
              AbilityType.Brawl,
              3,
              1,
              3,
              HealthType.Lethal,
              1,
              null));
          beastClaws.addBiteWeapon(set, new MeleeWeaponType("DeadlyBeastmanTransformation.Weapon.Bite", //$NON-NLS-1$
              AbilityType.Brawl,
              0,
              2,
              5,
              HealthType.Lethal,
              0,
              null));
        }

        public void visitPowerCombat(IExaltedRuleSet set) {
          beastClaws.addHandWeapon(set, new MeleeWeaponType("DeadlyBeastmanTransformation.Weapon.Claws", //$NON-NLS-1$
              AbilityType.Brawl,
              2,
              1,
              5,
              HealthType.Lethal,
              1,
              4));
          beastClaws.addBiteWeapon(set, new MeleeWeaponType("DeadlyBeastmanTransformation.Weapon.Bite", //$NON-NLS-1$
              AbilityType.Brawl,
              -6,
              -1,
              8,
              HealthType.Lethal,
              -1,
              2));
        }
      });
    }
    gifts.add(beastClaws);
    final BrawlWeaponProvidingGift savageTalons = new BrawlWeaponProvidingGift("SavageMoonsilverTalons"); //$NON-NLS-1$
    for (IExaltedRuleSet rules : ExaltedRuleSet.values()) {
      rules.accept(new IRuleSetVisitor() {
        public void visitCoreRules(IExaltedRuleSet set) {
          savageTalons.addHandWeapon(set, new MeleeWeaponType("DeadlyBeastmanTransformation.Weapon.Claws", //$NON-NLS-1$
              AbilityType.Brawl,
              6,
              4,
              5,
              HealthType.Lethal,
              4,
              null));
          savageTalons.addBiteWeapon(set, new MeleeWeaponType("DeadlyBeastmanTransformation.Weapon.Bite", //$NON-NLS-1$
              AbilityType.Brawl,
              3,
              2,
              8,
              HealthType.Lethal,
              0,
              null));
        }

        public void visitPowerCombat(IExaltedRuleSet set) {
          savageTalons.addHandWeapon(set, new MeleeWeaponType("DeadlyBeastmanTransformation.Weapon.Claws", //$NON-NLS-1$
              AbilityType.Brawl,
              2,
              3,
              7,
              HealthType.Lethal,
              2,
              7));
          savageTalons.addBiteWeapon(set, new MeleeWeaponType("DeadlyBeastmanTransformation.Weapon.Bite", //$NON-NLS-1$
              AbilityType.Brawl,
              -4,
              0,
              10,
              HealthType.Lethal,
              0,
              2));
        }
      });
    }
    savageTalons.addCondition(new QualityPrerequisite(beastClaws));
    gifts.add(savageTalons);
  }
}

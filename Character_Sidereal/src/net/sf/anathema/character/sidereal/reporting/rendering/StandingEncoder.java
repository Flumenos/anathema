package net.sf.anathema.character.sidereal.reporting.rendering;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.sidereal.SiderealCharacterModule;
import net.sf.anathema.lib.resources.IResources;

public class StandingEncoder implements ContentEncoder {

  private final IResources resources;
  private final float lineHeight;
  private final PdfTraitEncoder smallTraitEncoder;

  public StandingEncoder(int fontSize, IResources resources) {
    this.lineHeight = fontSize * 1.5f;
    this.resources = resources;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder();
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    int yPosition = (int) (bounds.getMaxY() - lineHeight);
    graphics.drawLabelledContent(getLabel("Label.Allegiance"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    int salary = getBackground(reportContent.getCharacter(), SiderealCharacterModule.BACKGROUND_ID_SALARY);
    yPosition -= smallTraitEncoder.encodeWithText(graphics, resources.getString("Sheet.Sidereal.Standing.Salary"), //$NON-NLS-1$
      new Position(bounds.x, yPosition), bounds.width, salary, 5);
    int manse = getBackground(reportContent.getCharacter(), SiderealCharacterModule.BACKGROUND_ID_CELESTIAL_MANSE);
    smallTraitEncoder.encodeWithText(graphics, resources.getString("Sheet.Sidereal.Standing.Manse"), new Position( //$NON-NLS-1$
      bounds.x, yPosition), bounds.width, manse, 5);
  }

  private int getBackground(IGenericCharacter character, String id) {
    int backgroundValue = 0;
    for (IGenericTrait background : character.getBackgrounds()) {
      if (background.getType().getId().equals(id)) {
        backgroundValue = background.getCurrentValue();
      }
    }
    return backgroundValue;
  }

  public String getHeaderKey(ReportContent content) {
    return "Sidereal.Standing"; //$NON-NLS-1$
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.Sidereal.Standing." + key) + ":"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}

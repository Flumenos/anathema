package net.sf.anathema.campaign.module.reporting;

import net.sf.anathema.campaign.reporting.MultiColumnSeriesReport;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.IReport;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class SeriesReportFactory implements IReportFactory {

  public IReport[] createReport(IResources resources, IRegistry<String, IAnathemaExtension> extensionPointRegistry) {
    return new IReport[] { new MultiColumnSeriesReport(resources) };
  }
}
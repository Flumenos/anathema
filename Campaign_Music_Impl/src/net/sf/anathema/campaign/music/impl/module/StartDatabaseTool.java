package net.sf.anathema.campaign.music.impl.module;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.database.StartDatabaseAction;

public class StartDatabaseTool implements IAnathemaTool {

  public void add(IResources resources, IAnathemaModel model, IAnathemaToolbar toolbar) {
    toolbar.addSeparator();
    MusicDatabaseActionProperties properties = new MusicDatabaseActionProperties(resources);
    toolbar.addTools(StartDatabaseAction.createToolAction(resources, model, properties));
  }
}
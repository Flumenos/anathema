package net.sf.anathema.character.lunar.heartsblood.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;

public class RemovableStringView implements IRemovableStringView {

  private final JButton button;
  private JPanel contentPanel;
  private final JLabel label;

  public RemovableStringView(Icon removeIcon, String string) {
    this.label = new JLabel(string);
    this.button = new JButton(removeIcon);
    button.setPreferredSize(new Dimension(removeIcon.getIconWidth() + 4, removeIcon.getIconHeight() + 4));
  }

  public JPanel addContent(JPanel panel) {
    this.contentPanel = panel;
    panel.add(label, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(button);
    return panel;
  }

  public void addRemoveButtonListener(ActionListener listener) {
    button.addActionListener(listener);
  }

  public void setDeleteEnabled(boolean enabled) {
    button.setEnabled(enabled);
  }

  public void delete() {
    contentPanel.remove(label);
    contentPanel.remove(button);
    contentPanel.revalidate();
  }
}
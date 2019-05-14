package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tab extends JPanel {

    public Tab() {
        super(new GridLayout(1,1));
        JTabbedPane tabbedPane=new JTabbedPane();
        ImageIcon icon=createImageIcon("tab.jp1g");
        JComponent panel1=makeTextPanel("权限管理");
        tabbedPane.addTab("权限管理",icon, panel1,"Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        JComponent panel2=makeTextPanel("插件管理");
        tabbedPane.addTab("插件管理",icon,panel2,"Does nothing");
        tabbedPane.setMnemonicAt(1,KeyEvent.VK_2);
        add(tabbedPane);
    }

    protected JComponent makeTextPanel(String text)
    {
        JPanel panel=new JPanel(false);
        JLabel filler=new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1,1));
        panel.add(filler);
        return panel;
    }

    protected static ImageIcon createImageIcon(String path)
    {
        java.net.URL imgURL=Tab.class.getResource(path);
        if(imgURL!=null)
        {
            return new ImageIcon(imgURL);
        }
        else
        {
            System.err.println("Couldn't find file: "+path);
            return null;
        }
    }

}

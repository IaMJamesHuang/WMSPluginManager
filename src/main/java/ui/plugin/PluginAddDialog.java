package ui.plugin;

import dto.UploadPluginDto;
import net.RequestManagement;
import po.Plugin;
import ui.FileChooseUtil;
import ui.main.WindowParam;
import util.TextUtil;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class PluginAddDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nick;
    private JTextField name;
    private JTextField version;
    private JTextField path;
    private JButton select;

    private File selectFile;

    private PluginPanel pluginPanel;

    public PluginAddDialog(PluginPanel pluginPanel) {
        this.pluginPanel = pluginPanel;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        initView();
    }

    private void initView() {
        path.setEnabled(false);
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile = FileChooseUtil.chooseFile(select);
                if (selectFile != null) {
                    path.setText(selectFile.getPath());
                } else {
                    path.setText("");
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        if (TextUtil.isEmpty(nick.getText())) {
            showMessage("插件名称");
            return;
        }
        if (TextUtil.isEmpty(name.getText())) {
            showMessage("插件代号");
            return;
        }
        if (TextUtil.isEmpty(version.getText())) {
            showMessage("插件版本");
            return;
        }
        if (TextUtil.isEmpty(path.getText())) {
            showMessage("插件路径");
            return;
        }
        String nickV = nick.getText();
        String nameV = name.getText();
        String versionV = version.getText();
        UploadPluginDto dto = RequestManagement.uploadPlugin(selectFile, nameV, versionV, nickV);
        if (dto != null && dto.getResult() != null) {
            if (pluginPanel != null) {
                pluginPanel.addItem(dto.getResult());
            }
        }
        dispose();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message + "不能为空", "提示", JOptionPane.ERROR_MESSAGE);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    public static void showDialog(PluginPanel pluginPanel) {
        PluginAddDialog dialog = new PluginAddDialog(pluginPanel);
        dialog.setTitle("新增插件");
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        dialog.setResizable(false);    //禁止改变窗口大小
        dialog.setVisible(true);
    }

}

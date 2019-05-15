package ui;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileChooseUtil {

    public static File chooseFile(JComponent component) {

        JFileChooser jfc = new JFileChooser();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        jfc.setCurrentDirectory(fsv.getHomeDirectory());
        //JFileChooser.FILES_AND_DIRECTORIES 选择路径和文件
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //用户选择的路径或文件
        if (jfc.showOpenDialog(component) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            return file;
        }
        return null;
    }

}

package ds.core;

import java.io.*;
import java.util.logging.Logger;

public class FileManager {
    private String userName;

    private String fileSeparator = System.getProperty("file.separator");
    private String rootFolder;


    private final Logger LOG = Logger.getLogger(FileManager.class.getName());

    public FileManager(String userName) {
        this.userName = userName;
        this.rootFolder =   "." + fileSeparator + this.userName;
    }

    public void createFile(String fileName) {
        try {
            String absoluteFilePath = this.rootFolder + fileSeparator + fileName;
            File file = new File(absoluteFilePath);
            file.getParentFile().mkdir();
            if (file.createNewFile()) {
                LOG.fine(absoluteFilePath + " File Created");
            } else LOG.fine("File " + absoluteFilePath + " already exists");
            RandomAccessFile f = new RandomAccessFile(file, "rw");
            f.setLength(1024 * 1024 * 8);
        } catch (IOException e) {
            LOG.severe("File creating failed");
            e.printStackTrace();
        }
    }

    public void initializeFolder() {
        File index = new File(rootFolder + fileSeparator);
        if(index.isDirectory()) {
            String[] entries = index.list();
            for(String s: entries){
                File currentFile = new File(index.getPath(), s);
                currentFile.delete();
            }
        }
    }
}

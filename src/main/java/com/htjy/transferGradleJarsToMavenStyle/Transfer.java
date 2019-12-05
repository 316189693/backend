

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Transfer {


    public static void generateFolderOrCopyFile(File[] files, String targetPath) throws Exception {
        for (File file: files
                ) {
            generateFolderOrCopyFile(file, targetPath);
        }
    }
    public static boolean isVersionFolder(String[] folders) {
        boolean isVersionFolder = true;
        for (String folderName: folders
                ) {
            try {
                Integer.parseInt(folderName);
            } catch(Exception e) {
                isVersionFolder = false;
            }
        }
        return isVersionFolder;
    }
    public static void generateFolderOrCopyFile(File file, String targetPath) throws Exception{

        if (file.isDirectory()) {

            String[] folders = file.getName().split("\\.");

            if (folders.length > 1 && !isVersionFolder(folders)) {
                for (String folderName: folders
                        ) {
                    targetPath += "/"+folderName;
                }
            } else if (file.getName().length() != 40){

                targetPath += "/"+file.getName();
            }

            if (!new File(targetPath).exists()) {
                System.out.println(targetPath);
                new File(targetPath).mkdirs();
            }
            generateFolderOrCopyFile(file.listFiles(), targetPath);
        } else {
            System.out.println(file.getName());
            System.out.println(targetPath +'/'+ file.getName());


            File outPutFile = new File(targetPath +'/'+ file.getName());
            if (!outPutFile.exists()) {
                outPutFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(outPutFile);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] content = new byte[1000];
                while(fileInputStream.read(content) > 0) {
                    fileOutputStream.write(content);
                }
            }
        }

    }

    public static void main(String[] args){
        File file = new File("E:/test");
        String targetFolder = "E:/target";
        try {
            generateFolderOrCopyFile(file, targetFolder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

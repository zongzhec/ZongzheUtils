package foo.zongzhe.utils.file;

import foo.zongzhe.utils.common.LogUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Utils used for common file process
 */

public class FileUtil {

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * Zip file function
     *
     * @param sourceFilePath must contain the file extension
     * @param destFilePath
     */
    public static void zipFile(String sourceFilePath, String destFilePath) throws IOException {
        if (!fileExists(sourceFilePath)) throw new FileNotFoundException("File not found in: " + sourceFilePath);

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFilePath));
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);

        compressRecur(out, bos, sourceFile, null);

        bos.close();
        out.close();
    }

    /**
     * Compress file recursively
     *
     * @param out
     * @param bos
     * @param input
     * @param name
     */
    public static void compressRecur(ZipOutputStream out, BufferedOutputStream bos, File input, String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }
        if (input.isDirectory()) {
            // If it is a directory, pick files from it and then compress.
            File[] fileList = input.listFiles();

            assert fileList != null;
            if (fileList.length == 0) {
                // If the dir is empty, just create an empty zip file
                out.putNextEntry(new ZipEntry(name + "/"));
            } else {
                // Zip files recursively
                for (File file : fileList) {
                    compressRecur(out, bos, file, name + "/" + file.getName());
                }
            }
        } else {
            // If it is a file, write it into the zip
            out.putNextEntry(new ZipEntry(name));
            FileInputStream fis = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fis);
            int len;

            // Write file contents into zip
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bis.close();
            fis.close();
        }
    }

    /**
     * Unzip file from given file path.
     *
     * @param sourceFilePath inputfilePath
     */
    public static void unzipFile(String sourceFilePath, String destFilePath) throws IOException {
        if (!fileExists(sourceFilePath)) throw new FileNotFoundException("File not found in: " + sourceFilePath);
        File sourceFile = new File(sourceFilePath);
        ZipFile zipFile = new ZipFile(sourceFile);

        // Start to UnZip
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();

            if (zipEntry.isDirectory()) {
                // If it is a directory, create a file folder
                // TODO: how to use the dirPath
                String dirPath = destFilePath + "/" + zipEntry.getName();
                File destFile = new File(destFilePath);
                destFile.mkdirs();
            } else {
                // If it is a file, create an empty file, and copy the contents via IO.
                File destFile = new File(destFilePath + "/" + zipEntry.getName());

                // Ensure the parent file folder does exist
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                destFile.createNewFile();

                // Write contents into the file
                InputStream is = zipFile.getInputStream(zipEntry);
                FileOutputStream fos = new FileOutputStream(destFile);
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }

                fos.close();
                is.close();
            }
        }
    }

    public static ArrayList<String> readFile(String path) {
        ArrayList<String> fileContent = new ArrayList<>();
        File file = new File(path);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found in " + path);
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(is);
        Scanner s = new Scanner(reader); //.useDelimiter("\t");
        while (s.hasNextLine()) {
            fileContent.add(s.nextLine());
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
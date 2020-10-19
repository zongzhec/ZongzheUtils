package foo.zongzhe.utils.file;

import org.junit.Test;

import java.io.IOException;

public class FileUtilTest {

    @Test
    public void zipFile(){
        try {
            FileUtil.unzipFile("D:\\CTSAdapterUnzip2", "D:\\CTSAdapterZip.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unzipFile(){
        try {
            FileUtil.unzipFile("D:\\CTSAdapter.zip", "D:\\CTSAdapterUnzip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

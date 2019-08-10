package foo.zongzhe.file.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.File;

@RunWith(Suite.class)
public class FileUtilTest {

    @Test
    public void fileExistsTest(){
        File file = new File("ExcelTest.xlsx");
        
    }
}

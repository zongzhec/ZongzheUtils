package foo.zongzhe.comon.utils;

import foo.zongzhe.common.utils.LogUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;

public class LogUtilTest {

    @Test
    public void printInfoTest(){
        LogUtil.printInfo("printing info");
    }

}

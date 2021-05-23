package hochenchong.litespring.test;

import hochenchong.litespring.test.v1.V1AllTests;
import hochenchong.litespring.test.v2.V2AllTests;
import hochenchong.litespring.test.v3.V3AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTests.class,
        V2AllTests.class,
        V3AllTests.class
})
public class AllTests {
}

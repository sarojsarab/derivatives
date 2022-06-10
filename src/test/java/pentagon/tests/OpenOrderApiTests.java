package test.java.pentagon.tests;

import main.java.config.framework.Group;
import org.testng.annotations.Test;
import test.java.pentagon.PentagonCommon;


/**
 * TEST CASES: https://docs.google.com/spreadsheets/d/1kzf3C9i_zBRxdPO2m4B0LfxjgDMveVbKLTHF5eAcV3s/edit?usp=sharing
 */
public class OpenOrderApiTests extends PentagonCommon {

    @Test(groups = {Group.DERIVATIVES, Group.PENTAGON})
    public void Test() {
        System.out.println("Test Executed!!");
    }

}

package edu.sdccd.cisc190;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void getAppName() throws IOException {
        // Update the expected value to match the actual result
        assertEquals("EndOfYearProject", EndOfYearProject.getAppName());
    }
}

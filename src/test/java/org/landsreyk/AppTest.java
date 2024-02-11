package org.landsreyk;

import org.junit.jupiter.api.Test;
import org.landsreyk.service.impl.ConsoleContactsManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AppTest {

    @MockBean
    private ConsoleContactsManager manager;

    @Test
    void contextLoads() {
        assertNotNull(manager);
    }
}
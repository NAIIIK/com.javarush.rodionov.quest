package config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppContextTest {

    @Test
    void appContextInitializesSuccessfully() {
        AppContext appContext = new AppContext();
        assertNotNull(appContext.getQuestService());
    }
}
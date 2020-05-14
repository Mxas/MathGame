package lt.mk.mathgame.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class PicServiceTest {

    private PicService service;

    @Before
    public void setUp() throws Exception {
        service = new PicService("/home/mkarpinskas/Documents/Fotos");
    }

    @Test
    public void test_correct() {
        Optional<InputStream> url = service.nextCorrectIS();
        assertNotNull(url);
        assertTrue(url.isPresent());
    }

    @Test
    public void test_incorrect() {
        Optional<InputStream> url = service.nextIncorrectIS();
        assertNotNull(url);
        assertTrue(url.isPresent());
    }

    @Test
    public void test_fotos() {
         service.initByPath();
    }

}
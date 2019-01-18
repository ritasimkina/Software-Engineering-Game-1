import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import MessagesBase.HalfMap;
import MessagesBase.HalfMapNode;
import MessagesBase.PlayerRegistration;
import Main.GameInformation;
import MessagesBase.Terrain;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class GameInformationTest {

    @Test
    public void PlayerRegistration_EverythingOK() {
        PlayerRegistration reg = new PlayerRegistration("A123", "Johnny", "Cage");

        assertNotNull(reg.getStudentID());
        assertNotNull(reg.getStudentFirstName());
        assertNotNull(reg.getStudentLastName());
    }
}

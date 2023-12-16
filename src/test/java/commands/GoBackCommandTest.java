package commands;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.commands.GoBackCommand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class GoBackCommandTest {

    @Mock
    private JDrawingFrame mockJDrawingFrame;

    private GoBackCommand goBackCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        goBackCommand = new GoBackCommand(mockJDrawingFrame);
    }

    @Test
    public void testExecute() {
        // Appelle la méthode execute
        goBackCommand.execute();

        // Vérifie que la méthode goBack de JDrawingFrame a été appelée
        verify(mockJDrawingFrame, times(1)).goBack();
    }
}
package com.example.hwpart6.command.move;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.ListAppender;

import com.example.hwpart6.IoCAbstractTest;
import com.example.hwpart6.command.log.LogErrorCommand;
import com.example.hwpart6.command.operations.move.MoveNonLinearCommand;
import com.example.hwpart6.ioc.IoC;
import com.example.hwpart6.objects.SpaceshipObject;
import com.example.hwpart6.operations.Movable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static com.example.hwpart6.TestUtils.clearSpaceshipObject;
import static org.junit.jupiter.api.Assertions.*;

class MoveNonLinearCommandTest extends IoCAbstractTest {
    private final ListAppender listAppender = new ListAppender<>();
    private final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    private SpaceshipObject spaceshipObject = new SpaceshipObject();

    private Movable movableAdapter;

    @BeforeEach
    public void beforeTest() {
        clearSpaceshipObject();
        spaceshipObject = SpaceshipObject.getInstance();
        movableAdapter = IoC.resolve("MovableAdapter", spaceshipObject);
        listAppender.start();
        context.getLogger(LogErrorCommand.class).addAppender(listAppender);
    }

    @AfterEach
    public void afterTest() {
        clearSpaceshipObject();
        listAppender.stop();
    }

    @Test
    void shouldMoveSpaceship() {
        // given Position (12, 5) and velocityVector (-7, 3)
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", new double[]{12, 5});
        spaceshipObject.setProperty("VelocityVector", new double[]{-7, 3});
        double[] expectedPosition = new double[]{5, 8};

        // when move
        new MoveNonLinearCommand(spaceshipObject).execute();

        // then should change position to (5, 8)
        double[] actualPosition = movableAdapter.getPosition().get();
        assertArrayEquals(actualPosition, expectedPosition, 0);
    }

    @Test
    void shouldThrowExceptionWhenMoveSpaceshipWithNoPosition() {
        // given
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("VelocityVector", new double[]{-7, 3});

        // when
        new MoveNonLinearCommand(spaceshipObject).execute();

        // then
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(1, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains("verdict: error when try to move"))
        );
    }

    @Test
    void shouldThrowExceptionWhenMoveSpaceshipWithNoVelocity() {
        // given
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", new double[]{12, 5});
        spaceshipObject.setProperty("Direction", 3);
        spaceshipObject.setProperty("DirectionsNumber", 8);

        // when
        new MoveNonLinearCommand(spaceshipObject).execute();

        // then
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(2, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains("Error in operation: com.example.hwpart6.command.operations.ChangeVelocityVectorCommand"))
        );
    }

    @Test
    void shouldThrowExceptionWhenMoveSpaceshipWithNoDirection() {
        // given
        var spaceshipObject = SpaceshipObject.getInstance();
        spaceshipObject.setProperty("Position", new double[]{12, 5});
        spaceshipObject.setProperty("Velocity", 3);

        // when
        new MoveNonLinearCommand(spaceshipObject).execute();

        // then
        var logs = listAppender.list;
        assertAll(
                () -> assertEquals(3, logs.size()),
                () -> assertTrue(logs.get(0).toString().contains("Error in operation: com.example.hwpart6.command.operations.RotateCommand"))
        );
    }
}

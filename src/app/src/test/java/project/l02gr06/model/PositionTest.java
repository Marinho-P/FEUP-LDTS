package project.l02gr06.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position;

    @BeforeEach
    void setUp() {
        position = new Position(5,5);
    }

    @Test
    void testPosition() {
        assertEquals(5, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    void testMoveDown() {
        position = position.getDown();
        assertEquals(6, position.getY());
        assertEquals(5, position.getX());
    }

    @Test
    void testMoveUp() {
        position = position.getUp();
        assertEquals(5, position.getX());
        assertEquals(4, position.getY());
    }

    @Test
    void testMoveRight(){
        position = position.getRight();
        assertEquals(6, position.getX());
        assertEquals(5, position.getY());
    }

    @Test
    void testMoveLeft(){
        position = position.getLeft();
        assertEquals(4, position.getX());
        assertEquals(5, position.getY());
    }

}
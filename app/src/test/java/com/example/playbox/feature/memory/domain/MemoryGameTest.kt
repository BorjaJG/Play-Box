package com.example.playbox.feature.memory.domain

import com.example.playbox.feature.memory.domain.model.MemoryGame
import org.junit.Assert.*
import org.junit.Test

class MemoryGameTest {
    private fun board() = MemoryGame(listOf(0, 0, 1, 1, 2, 2, 3, 3))
    @Test fun matchingPairCountsOneAttemptAndStaysMatched() {
        val result = board().reveal(0).reveal(1)
        assertEquals(setOf(0, 1), result.matched)
        assertEquals(1, result.attempts)
        assertTrue(result.revealed.isEmpty())
    }
    @Test fun mismatchBlocksThirdCardUntilConcealed() {
        val mismatch = board().reveal(0).reveal(2)
        assertEquals(mismatch, mismatch.reveal(4))
        assertEquals(setOf(4), mismatch.conceal().reveal(4).revealed)
        assertEquals(1, mismatch.attempts)
    }
    @Test fun repeatedAndInvalidSelectionsDoNotCount() {
        val first = board().reveal(0)
        assertEquals(first, first.reveal(0))
        assertEquals(first, first.reveal(-1))
        assertEquals(first, first.reveal(8))
        assertEquals(0, first.attempts)
    }
    @Test fun allPairsFinishInFourAttemptsAndCannotChange() {
        var game = board()
        for (index in 0..7) game = game.reveal(index)
        assertTrue(game.isFinished)
        assertEquals(4, game.attempts)
        assertEquals(game, game.reveal(0))
    }
    @Test fun newBoardContainsExactlyFourPairs() {
        val cards = MemoryGame.newGame().cards
        assertEquals(mapOf(0 to 2, 1 to 2, 2 to 2, 3 to 2), cards.groupingBy { it }.eachCount())
    }
}

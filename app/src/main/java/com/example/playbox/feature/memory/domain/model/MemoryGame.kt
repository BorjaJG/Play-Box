package com.example.playbox.feature.memory.domain.model

/** Pure Kotlin rules: no Android, Compose or database dependencies. */
data class MemoryGame(
    val cards: List<Int>,
    val revealed: Set<Int> = emptySet(),
    val matched: Set<Int> = emptySet(),
    val attempts: Int = 0,
) {
    val isFinished get() = matched.size == cards.size
    val needsConceal get() = revealed.size == 2

    fun reveal(index: Int): MemoryGame {
        if (index !in cards.indices || index in matched || index in revealed || needsConceal) return this
        val next = revealed + index
        if (next.size == 1) return copy(revealed = next)
        val pair = next.toList()
        return if (cards[pair[0]] == cards[pair[1]]) {
            copy(revealed = emptySet(), matched = matched + next, attempts = attempts + 1)
        } else copy(revealed = next, attempts = attempts + 1)
    }

    fun conceal() = if (needsConceal) copy(revealed = emptySet()) else this

    companion object {
        fun newGame() = MemoryGame((0..3).flatMap { listOf(it, it) }.shuffled())
    }
}

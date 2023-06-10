package ir.sharif.library.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ir.sharif.library.bookcard.BookCard
import ir.sharif.library.entities.Book

data class BookWithCount(val book: Book, val count: Int = 0)

@Composable
fun BooksList(
    modifier: Modifier = Modifier,
    showClose: Boolean = false,
    showCounter: Boolean = false,
    booksWithCount: List<BookWithCount> = listOf(),
    onClose: (Book) -> Unit = {},
    onClick: (Book) -> Unit = {},
    onIncrease: (Book) -> Unit = {},
    onDecrease: (Book) -> Unit = {},
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        itemsIndexed(items = booksWithCount) { index, bookWithCount ->
            val book = bookWithCount.book
            BookCard(
                title = book.title,
                author = book.author,
                rate = "4.5/5",
                showClose = showClose,
                showCounter = showCounter,
                cover = rememberAsyncImagePainter(
                    model = book.cover,
                ),
                price = "$${book.price}",
                onClose = { onClose(book) },
                count = "${bookWithCount.count}",
                onIncrease = { onIncrease(book) },
                onDecrease = { onDecrease(book) },
                onClick = { onClick(book) }
            )
        }
        item { Spacer(modifier = Modifier.size(0.dp)) }
    }
}


@Composable
fun BooksList(
    modifier: Modifier = Modifier,
    showClose: Boolean = false,
    books: List<Book> = listOf(),
    onClose: (Book) -> Unit = {},
    onClick: (Book) -> Unit = {},
    onIncrease: (Book) -> Unit = {},
    onDecrease: (Book) -> Unit = {},
) = BooksList(
    modifier = modifier,
    showClose = showClose,
    showCounter = false,
    booksWithCount = books.map { BookWithCount(it) },
    onClose = onClose,
    onClick = onClick,
    onIncrease = onIncrease,
    onDecrease = onDecrease,
)
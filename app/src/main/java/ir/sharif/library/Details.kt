package ir.sharif.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.sharif.library.ui.theme.LibraryTheme

@Composable
fun Details(paddingValues: PaddingValues) {
    Column(
        androidx.compose.ui.Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "The Book Title",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) {
            Image(
                modifier = Modifier
                    .size(144.dp, 216.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                painter = painterResource(id = R.drawable.book_card_cover),
                contentDescription = "book cover",
                contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier.padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row {
                    Text(text = "Author: ")
                    Text(text = "Seyed", fontWeight = FontWeight.SemiBold)
                }
                Row {
                    Text(text = "Category: ")
                    Text(text = "Noval", fontWeight = FontWeight.SemiBold)
                }
                Row {
                    Text(text = "Rating: ")
                    Text(text = "4.5/5", fontWeight = FontWeight.SemiBold)
                }
                Row {
                    Text(text = "Price: ")
                    Text(text = "$20.0", fontWeight = FontWeight.SemiBold)
                }
                Row {
                    Text(text = "Publisher : ")
                    Text(text = "Litwin Books", fontWeight = FontWeight.SemiBold)
                }
                Row {
                    Text(text = "Revision:")
                    Text(text = "14", fontWeight = FontWeight.SemiBold)
                }
                Row {
                    Text(text = "Publish Year:")
                    Text(text = "1990", fontWeight = FontWeight.SemiBold)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { },
                shape = RoundedCornerShape(size = 4.dp)
            ) {
                Text(text = "add to cart")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { },
                shape = RoundedCornerShape(size = 4.dp)
            ) {
                Text(text = "add to favorites")
            }
        }
        Text(text = "Description:", fontWeight = FontWeight.SemiBold)
        Text(
            modifier = Modifier,
            text = "Blah blah blah blah lbahl Blah blah blah blah lba h blah blah blah lba h blah blah blah lba h blah blah blah lba h blah blah blah lba h blah blah blah lba h blah blah blah lbahl  Blah blah blah blah lbahl  Blah blah blah blah lbahl  Blah blah blah blah lbahl  Blah blah blah blah lbahl  Blah blah blah blah lbahl ",
        )


    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    LibraryTheme {
        Details(PaddingValues(0.dp))
    }
}
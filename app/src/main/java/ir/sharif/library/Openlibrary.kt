package ir.sharif.library

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface OpenLibraryService {
    @GET("/api/books?format=json&jscmd=data")
    suspend fun getBookDetails(@Query("bibkeys") olids: String): Map<String, BookDetails>

    companion object {
        var apiService: OpenLibraryService? = null
        fun getInstance() : OpenLibraryService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://openlibrary.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(OpenLibraryService::class.java)
            }
            return apiService!!
        }
    }
}


data class BookDetails(
    val title: String,
    val authors: List<Author>,
    val cover: Cover,
) {

    data class Author (val name: String)

    data class Cover (
        val small: String,
        val medium: String,
        val large: String,
    )
}
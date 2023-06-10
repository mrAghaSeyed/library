package ir.sharif.library

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface OpenLibraryService {
    @GET("/api/books?format=json&jscmd=details")
    suspend fun getBookDetails(@Query("bibkeys") olids: String): Map<String, OpenLibraryBook>

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

data class OpenLibraryBook(
    val details: BookDetails
)

data class BookDetails(
    val title: String,
    val authors: List<NamedObject>?,
    val covers: List<String>?,
    val description: String?,
    val revision: Int,
    val publishers: List<String>?,
    @SerializedName("publish_date")
    val publishDate: String,
    val subjects: List<String>?
) {

    data class NamedObject (val name: String)

    val cover: String
        get() = "https://covers.openlibrary.org/b/id/${covers?.firstOrNull() ?: ""}-M.jpg"
}
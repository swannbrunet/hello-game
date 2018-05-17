package fr.epita.hello_game

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class service  {
    // A List to store or objects
    val data = arrayListOf<GameList>()
    // The base URL where the WebService is located
    val baseURL = "http://jsonplaceholder.typicode.com/"
    // Use GSON library to create our JSON parser
    val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
    // Create a Retrofit client object targeting the provided URL
// and add a JSON converter (because we are expecting json responses)
    val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(jsonConverter)
            .build()
    // Use the client to create a service:
// an object implementing the interface to the WebService
    val service: WebServiceInterface = retrofit.create(WebServiceInterface::class.java)
}
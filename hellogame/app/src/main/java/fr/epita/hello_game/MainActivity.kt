package fr.epita.hello_game

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    val data = arrayListOf<GameList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           // A List to store or objects
           // The base URL where the WebService is located
        val baseURL = "https://androidlessonsapi.herokuapp.com/api/"
           // Use GSON library to create our JSON parser
        val jsonConverter = GsonConverterFactory.create(GsonBuilder().create())
           // Cr eate a Retrofit client object targeting the provided URL
           // and add a JSON converter (because we are expecting json responses)
        val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(jsonConverter)
                .build()
           // Use the client to create a service:
           // an object implementing the interface to the WebService
        val service: WebServiceInterface = retrofit.create(WebServiceInterface::class.java)

        val callback = object : Callback<List<GameList>> {
            override fun onFailure(call: Call<List<GameList>>?, t: Throwable?) {
                // Code here what happens if calling the WebService fails
                Log.d("TAG", "WebService call failed")
            }
            override fun onResponse(call: Call<List<GameList>>?,
                                    response: Response<List<GameList>>?) {
                // Code here what happens when WebService responds
                if (response != null) {
                    if (response.code() == 200) {
                        // We got our data !
                        val responseData = response.body()
                        if (responseData != null) {
                            data.addAll(responseData)
                            Log.d("TAG", "WebService success : " + data.size)
                            val random = Random()
                            button.text = data[random.nextInt(data.size)].name.toString();
                            button2.text = data[random.nextInt(data.size)].name.toString();
                            button3.text = data[random.nextInt(data.size)].name.toString();
                            button4.text = data[random.nextInt(data.size)].name.toString();
                        }
                        else {
                            Log.d("TAG", "Error empty data")
                        }
                    }
                    else
                    {
                        Log.d("TAG", "error return code " + response.code() + "reponse" + response.body())
                    }
                }
                else
                {
                    Log.d("TAG", "<reponse> is null")
                }
            }
        }
        service.listgame().enqueue(callback)
        setContentView(R.layout.activity_main)

    }
}

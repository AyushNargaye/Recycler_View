package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var newsArrayList: ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        myRecyclerView = findViewById(R.id.recyclerView)

        val newsImageArray = arrayOf(
            R.drawable.tom_cruise,
            R.drawable.leonardo,
            R.drawable.chris_bale,
            R.drawable.rdj,
            R.drawable.hugh_jackman,
            R.drawable.jake_gyllenhaal,
            R.drawable.ana_de_armas

        )
        val newsHeadingArray = arrayOf(
            "Tom Cruise Set to Break New Records with Daredevil Stunts in Latest Mission: Impossible Film!",
            "Leonardo DiCaprio Takes on Climate Change Again with Groundbreaking Environmental Documentary",
            "Christian Bale Transforms for Gritty New Role in Psychological Drama, Wowing Critics",
            "Robert Downey Jr. Returns to the Superhero Genre with Mysterious New Marvel Project",
            "Hugh Jackman Reprises Wolverine Role for Final Time in Epic X-Men Reunion Film",
            "Jack Gyllenhaal Set to Star in Intense Crime Thriller, Promising a Gripping Performance",
            "Ana de Armas Stuns Audiences in Lead Role of Upcoming Sci-Fi Blockbuster"
        )

        val newsContent = arrayOf(

            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content)

        )

        // to set items inside recyclerview, vertically scrolling, horizontally, uniform grid
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        newsArrayList = arrayListOf<News>()

        for( index in newsImageArray.indices){
            val news = News(newsHeadingArray[index], newsImageArray[index], newsContent[index])
            newsArrayList.add(news)
        }
        var myAdapter = MyAdapter(newsArrayList, this)
        myRecyclerView.adapter = MyAdapter(newsArrayList, this)
        myAdapter.setItemClickListener(object  : MyAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                // on clicking each time, what action do you want to perform

                val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
                intent.putExtra("heading", newsArrayList[position].newsHeading)
                intent.putExtra("imageId", newsArrayList[position].newsImage)
                intent.putExtra("newsContent", newsArrayList[position].newsContent)
                startActivity(intent)
            }
        })
    }
}
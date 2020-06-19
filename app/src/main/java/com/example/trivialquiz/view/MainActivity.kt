package com.example.trivialquiz.view

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trivialquiz.R
import com.example.trivialquiz.adapter.JokesListAdapter
import com.example.trivialquiz.adapter.ViewPagerAdapter
import com.example.trivialquiz.helper.ViewPagerHelper
import com.example.trivialquiz.model.ImageModel
import com.example.trivialquiz.model.JokesModelItem
import com.example.trivialquiz.viewmodel.JokesViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: JokesViewModel
    private lateinit var adapter: JokesListAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var jokesList: ArrayList<JokesModelItem> = ArrayList()
    private var imgList: ArrayList<ImageModel> = ArrayList()
    private val viewPagerHelper = ViewPagerHelper()
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        rec_jokes_list.layoutManager = LinearLayoutManager(this)
        initialiseViewModel()
        initialiseViewPager()
        initialiseAdapter()
        rec_jokes_list.adapter = adapter
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.programming_jokes)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_item_webview -> {
                openWebView()
            }
        }
        return true
    }

    private fun openWebView() {
        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)
    }

    private fun initialiseViewPager() {
        loadImgList()
        viewPagerAdapter = ViewPagerAdapter(imgList, this)
        viewpager.adapter = viewPagerAdapter
        TabLayoutMediator(tab_layout, viewpager) { tab, position ->
            tab.view.isClickable = false
        }.attach()
        viewPagerHelper.autoSwipeViewPager(viewpager)
    }

    private fun loadImgList() {
        imgList.clear()
        imgList.add(ImageModel("https://www.apifonica.com/public/img/blog/programming-jokes-1-apifonica-blog.jpg"))
        imgList.add(ImageModel("https://1stwebdesigner.com/wp-content/uploads/2011/10/preview_large_prgramjokecuming.jpg"))
        imgList.add(ImageModel("https://pbs.twimg.com/media/DUljdlGV4AMXC7U.jpg"))
        imgList.add(ImageModel("https://www.photoverse.co/wp-content/uploads/2018/10/10-Funny-and-Interesting-Facts-About-Programming-Tips-Tricks-750x430.png"))
    }

    private fun initialiseAdapter() {
        adapter = JokesListAdapter(jokesList, this)
    }

    private fun initialiseViewModel() {
        viewModel = ViewModelProvider(this).get(JokesViewModel::class.java)
        viewModel.getJokes()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.jokesList.observe(this, Observer { list ->
            if (list != null) {
                jokesList.clear()
                jokesList.addAll(list as ArrayList<JokesModelItem>)
                adapter.notifyDataSetChanged()
                progress.visibility = View.GONE
            } else {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
                progress.visibility = View.GONE
            }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            progress.visibility = View.GONE
        })
    }
}

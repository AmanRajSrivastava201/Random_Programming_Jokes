package com.example.trivialquiz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.trivialquiz.R
import kotlinx.android.synthetic.main.activity_web_view2.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view2)
        supportActionBar?.hide()
        webview.webViewClient = WebViewClient()
        webview.loadUrl("https://devhumor.com/")
    }
}

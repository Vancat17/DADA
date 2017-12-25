package com.scujcc.dahuo.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem

import android.widget.ImageButton
import android.widget.Toast

import com.scujcc.dahuo.R
import com.scujcc.dahuo.R.id.drawerLayout
import com.scujcc.dahuo.content.SimplePagerAdapter
import com.scujcc.dahuo.message.MessageActivity
import com.scujcc.dahuo.user.UserDetailActivity

import com.scujcc.dahuo.function.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private var pagerAdapter: SimplePagerAdapter? = null


    //点两次返回键退出程序
    private var mExitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(main_toolbar)

        search_button.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)
        }

        main_fab.setOnClickListener { view ->
            Snackbar.make(view, "先放着，过会儿再做", Snackbar.LENGTH_LONG)
                    .setAction("添加", null).show()

        }


        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val headView = nav_view.inflateHeaderView(R.layout.nav_header_main)
        val mHeaderView = headView.findViewById<ImageButton>(R.id.user_imageview)
        mHeaderView.setOnClickListener {
            val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
            startActivity(intent)
        }

        pagerAdapter = SimplePagerAdapter(supportFragmentManager, this)
        main_viewpager.adapter = pagerAdapter
        main_tab_layout.setupWithViewPager(main_viewpager)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_message) {

            val intent = Intent(this@MainActivity, MessageActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {
            R.id.nav_stroke -> {
                val intent = Intent(this@MainActivity, StrokeActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_like -> {
                val intent = Intent(this@MainActivity, LikeActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_wallet -> {
                val intent = Intent(this@MainActivity, WalletActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_service -> {
                val intent = Intent(this@MainActivity, ServiceActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_setting -> {
                val intent = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intent)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                mExitTime = System.currentTimeMillis()
            } else {
                System.exit(0)
            }

            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

package com.scujcc.dada.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem

import android.widget.Toast

import com.scujcc.dada.R
import com.scujcc.dada.add.AddActivity
import com.scujcc.dada.content.SimplePagerAdapter
import com.scujcc.dada.message.MessageActivity
import com.scujcc.dada.user.UserDetailActivity

import com.scujcc.dada.function.*
import com.scujcc.dada.function.settings.SettingActivity
import com.scujcc.dada.function.stroke.StrokeActivity
import com.scujcc.dada.user.UserItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.litepal.LitePal

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private var pagerAdapter: SimplePagerAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LitePal.initialize(applicationContext)
        setSupportActionBar(main_toolbar)

        val user = UserItem("1136836811",R.drawable.ic_user_photo, "搭搭",1, "90后",null,null,null,1,false,false)

        search_button.setOnClickListener {
            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)
        }

        main_fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val headView = nav_view.inflateHeaderView(R.layout.nav_header_main)
        headView.user_image.setImageResource(user.photoId!!)
        headView.user_name.text = user.name
        headView.user_vip_level.text = vipLevel(user.vip)
        headView.user_image.setOnClickListener {
            val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
            intent.putExtra("USER_DETAIL", user)
            startActivity(intent)
        }

        pagerAdapter = SimplePagerAdapter(supportFragmentManager, this)
        main_viewpager.adapter = pagerAdapter
        main_tab_layout.setupWithViewPager(main_viewpager)
    }

    private fun vipLevel(level: Int): String {
        return when(level) {
            0 -> "普通会员"
            1 -> "黄金会员"
            2 -> "钻石会员"
            else -> "我的会员"
        }
    }
    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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

    //点两次返回键退出程序
    private var mExitTime: Long = 0

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

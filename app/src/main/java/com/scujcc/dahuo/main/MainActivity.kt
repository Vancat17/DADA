package com.scujcc.dahuo.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

import com.scujcc.dahuo.R
import com.scujcc.dahuo.content.SimplePagerAdapter
import com.scujcc.dahuo.function.LikeActivity
import com.scujcc.dahuo.function.ServiceActivity
import com.scujcc.dahuo.function.SettingActivity
import com.scujcc.dahuo.function.StrokeActivity
import com.scujcc.dahuo.function.WalletActivity
import com.scujcc.dahuo.message.MessageActivity
import com.scujcc.dahuo.user.UserDetailActivity

import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mMainLocation: Button
    private lateinit var mToolbar: Toolbar
    private lateinit var mMainTablayout: TabLayout
    private lateinit var mMainViewpager: ViewPager
    private lateinit var mFab: FloatingActionButton
    private lateinit var mNavView: NavigationView
    private lateinit var mDrawerLayout: DrawerLayout

    private var pagerAdapter: SimplePagerAdapter? = null


    //点两次返回键退出程序
    private var mExitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainLocation = findViewById(R.id.main_location)
        mToolbar = findViewById(R.id.toolbar)
        mMainTablayout = findViewById(R.id.main_tablayout)
        mFab = findViewById(R.id.fab)
        mMainViewpager = findViewById(R.id.main_viewpager)
        mNavView = findViewById(R.id.nav_view)
        mDrawerLayout = findViewById(R.id.drawer_layout)

        setSupportActionBar(mToolbar)

        mFab.setOnClickListener { view ->
            Snackbar.make(view, "先放着，过会儿再做", Snackbar.LENGTH_LONG)
                    .setAction("添加", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavView.setNavigationItemSelectedListener(this)

        val headView = mNavView.inflateHeaderView(R.layout.nav_header_main)
        val mHeaderView = headView.findViewById<ImageButton>(R.id.imageView)
        mHeaderView.setOnClickListener {
            val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
            startActivity(intent)
        }

        pagerAdapter = SimplePagerAdapter(supportFragmentManager, this)
        mMainViewpager.adapter = pagerAdapter
        mMainTablayout.setupWithViewPager(mMainViewpager)
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
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

        if (id == R.id.nav_stroke) {
            val intent = Intent(this@MainActivity, StrokeActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_like) {
            val intent = Intent(this@MainActivity, LikeActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_wallet) {
            val intent = Intent(this@MainActivity, WalletActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_service) {
            val intent = Intent(this@MainActivity, ServiceActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.nav_setting) {
            val intent = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(intent)
        }

        mDrawerLayout.closeDrawer(GravityCompat.START)
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

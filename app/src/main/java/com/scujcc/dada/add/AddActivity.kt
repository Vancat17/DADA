package com.scujcc.dada.add

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kaopiz.kprogresshud.KProgressHUD
import com.scujcc.dada.R
import com.scujcc.dada.add.activity.CategoryActivity
import com.scujcc.dada.add.adapter.SelectImageAdapter
import com.scujcc.dada.add.utils.KeyboardUtil
import com.scujcc.dada.common.cityselector.AddressActivity
import com.scujcc.dada.common.dateselector.model.DateParams
import com.scujcc.dada.helper.*
import com.yuyh.library.imgsel.ISNav
import kotlinx.android.synthetic.main.add_activity.*
import kotlinx.android.synthetic.main.price_keyboard.*
import org.litepal.crud.DataSupport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddActivity : Activity() {

    private var hud: KProgressHUD? = null
    private lateinit var adapter: SelectImageAdapter
    private var images: ArrayList<String> = ArrayList()

    private val user = DataSupport.findLast(User::class.java)

//    private val appid = "1256129579"
//
//    private var secretId = "AKIDyylKekRDZfV5QVpBrIUQ5WkkW3LOrtLZ"
//    private var secretKey = "7zd2C7oHVUFk27yax6ze4E9qJXVDepkS"
//    private var keyDuration: Long = 600 //SecretKey 的有效时间，单位秒

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        window.statusBarColor = Color.WHITE
        ISNav.getInstance().init { context, path, imageView -> Glide.with(context).load(path).into(imageView) }

        adapter = SelectImageAdapter(this)
        rv_image.adapter = adapter
        rv_image.layoutManager = GridLayoutManager(this, 4)

        buttonClick()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_LIST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val pathList = data.getStringArrayListExtra("result")

            for (path in pathList) {
                images.add(path)
                adapter.refresh(images)
            }
        } else if (requestCode == REQUEST_CAMERA_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val path = data.getStringExtra("result")
            images.add(path)
            adapter.refresh(images)
        }

        if (requestCode == LOCATION_REQUEST_CODE && data != null) {
            add_location.text = data.getStringExtra("LOCATION")
        }

        if (requestCode == CATEGORY_REQUEST_CODE && data != null) {
            add_category.text = data.getStringExtra("CATEGORY")
        }

        hud?.dismiss()

    }
    @SuppressLint("SetTextI18n", "ClickableViewAccessibility", "SimpleDateFormat")
    private fun buttonClick() {

        back_button.setOnClickListener { finish() }


        val keyboardUtil = KeyboardUtil(this)
        keyboardUtil.setOnOkClick {
            if (validate()) {
                ll_price_select!!.visibility = View.GONE
                add_button.visibility = View.VISIBLE
                add_price.text = "¥" + et_price.text + "/人，" +  "  原价 ¥" + et_original_price.text + "/人," + " 人数 :" + et_num.text + "/" + et_totalNum.text
            }
        }
        keyboardUtil.setOnCancelClick {
            ll_price_select!!.visibility = View.GONE
            add_button.visibility = View.VISIBLE
        }

        //价格
        add_price.setOnClickListener {
            keyboardUtil.attachTo(et_price)
            et_price.requestFocus()
            ll_price_select!!.visibility = View.VISIBLE
            add_button.visibility = View.INVISIBLE
        }
        et_price.setOnTouchListener { _, _ ->
            keyboardUtil.attachTo(et_price)
            false
        }
        et_original_price.setOnTouchListener { _, _ ->
            keyboardUtil.attachTo(et_original_price)
            false
        }
        et_num.setOnTouchListener { _, _ ->
            keyboardUtil.attachTo(et_num)
            false
        }
        et_totalNum.setOnTouchListener { _, _ ->
            keyboardUtil.attachTo(et_totalNum)
            false
        }

        //位置
        add_location.setOnClickListener {
            hud = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("数据加载中")
                    .show()
            val intent = Intent(this, AddressActivity::class.java)
            startActivityForResult(intent, LOCATION_REQUEST_CODE)
        }

        //分类
        add_category.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivityForResult(intent, CATEGORY_REQUEST_CODE)
        }

        //时间
        add_time.setOnClickListener {

            showDatePickDialog(DateParams.TYPE_YEAR, DateParams.TYPE_MONTH, DateParams.TYPE_DAY,
                    DateParams.TYPE_HOUR, DateParams.TYPE_MINUTE)
        }

        //创建实例进行发布
        add_button.setOnClickListener {

            if (judgment()) {

                val retrofit = Retrofit.Builder()
                        .baseUrl(getString(R.string.baseUrl)) // 设置 网络请求 Url
                        .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                        .build()

                val request = retrofit.create<PostRequest>(PostRequest::class.java)
                try {

                    val date = Date(System.currentTimeMillis())
                    val contentId = SimpleDateFormat("yyyyMMddHHmmss").format(date)
                    val content = Content(contentId, contentId,getString(R.string.simpleImage), add_time.text.toString(), et_num.text.toString().toInt(),et_totalNum.text.toString().toInt(), add_location.text.toString(), add_category.text.toString(),add_topic.text.toString(),et_price.text.toString().toDouble(),user.name,add_content.text.toString(),getString(R.string.avatar),95)
                    val call = request.postContent(content)
                    call.enqueue(object : Callback<Content> {
                        override fun onResponse(call: Call<Content>?, response: Response<Content>?) {
                            Toast.makeText(applicationContext, "发布成功", Toast.LENGTH_SHORT).show()


                            val retrofit2 = Retrofit.Builder()
                                    .baseUrl(getString(R.string.baseUrl)) // 设置 网络请求 Url
                                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                                    .build()
                            val request2 = retrofit2.create<PutRequest>(PutRequest::class.java)
                            try {
                                val stroke= Stroke(contentId, content.date!!, content.topic!!, content.location!!,content.image, "","","","","")
                                val call2 = request2.updateStrokes(user.userId, stroke)
                                call2.enqueue(object : Callback<String> {
                                    override fun onResponse(call: Call<String>?, response: Response<String>?) {
                                    }

                                    override fun onFailure(call: Call<String>?, t: Throwable?) {
                                    }
                                })
                            } catch (e : Exception) {}

                        }
                        override fun onFailure(call: Call<Content>?, t: Throwable?) {
                            Toast.makeText(applicationContext, "发布失败", Toast.LENGTH_SHORT).show()

                        }
                    })
                } catch (ig : Exception) {

                }
                finish()
            }
        }
    }

    //发布条件判断
    private fun judgment() : Boolean {

        if (add_topic.text == null) {
            Toast.makeText(applicationContext, "标题不能为空哦", Toast.LENGTH_SHORT).show()
            return false
        }
        if (add_content.text == null) {
            Toast.makeText(applicationContext, "内容不能为空哦", Toast.LENGTH_SHORT).show()
            return false
        }
        if (add_category.text == "分类") {
            Toast.makeText(applicationContext, "请选择分类", Toast.LENGTH_SHORT).show()
            return false
        }
        if (add_price.text == "价格") {
            Toast.makeText(applicationContext, "请设置价格", Toast.LENGTH_SHORT).show()
            return false
        }
        if (add_time.text == "时间") {
            Toast.makeText(applicationContext, "请设置价格", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    //价格输入判断
    private fun validate(): Boolean {
        return when {
            et_price!!.text.toString() == "" -> {
                Toast.makeText(application, "价格不能为空", Toast.LENGTH_SHORT).show()
                false
            }
            et_original_price!!.text.toString() == "" -> {
                Toast.makeText(application, "原价不能为空", Toast.LENGTH_SHORT).show()
                false
            }
            et_totalNum!!.text.toString() == "" -> {
                Toast.makeText(application, "总人数不能为空", Toast.LENGTH_SHORT).show()
                false
            }
            et_num.text.toString().toInt() >= et_totalNum.text.toString().toInt() -> {
                Toast.makeText(application, "现有人数不能大于总人数", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun showDatePickDialog(@DateParams.Type vararg style: Int) {
        val todayCal = Calendar.getInstance()
        val startCal = Calendar.getInstance()
        val endCal = Calendar.getInstance()
        endCal.add(Calendar.YEAR, 6)

        com.scujcc.dada.common.dateselector.view.DatePickDialog.Builder()
                .setTypes(*style)
                .setCurrentDate(todayCal.time)
                .setStartDate(startCal.time)
                .setEndDate(endCal.time)
                .setOnSureListener { date ->
                    val message = SimpleDateFormat("MM-dd HH:mm").format(date)
                    add_time.text = message
                }
                .show(this)
    }

    companion object {
        val REQUEST_LIST_CODE = 1
        val REQUEST_CAMERA_CODE = 2
        val LOCATION_REQUEST_CODE = 3
        val CATEGORY_REQUEST_CODE = 5

    }
}
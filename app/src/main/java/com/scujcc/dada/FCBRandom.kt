package com.scujcc.dada

import java.util.*

/**
 * Created by  范朝波 on 2017/12/25.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
object FCBRandom {

    //随机标题
    internal fun randomTopic(): String {
        val topics = ArrayList<String>()

        topics.add("本周五体育馆打羽毛球")
        topics.add("下周一活动凑人数")
        topics.add("明晚拼滴滴回泸州")
        topics.add("今晚篮球场打篮球")
        topics.add("明天越野公园越野")
        topics.add("本周四健身房搭档")
        topics.add("今晚七点操场5km长跑")

        val random = Random()
        return topics[random.nextInt(topics.size)]
    }

    //随机价格
    internal fun randomPrice(): Int {
        val price = ArrayList<Int>()

        price.add(90)
        price.add(88)
        price.add(35)
        price.add(78)
        price.add(98)
        price.add(29)
        price.add(0)
        val random = Random()
        return price[random.nextInt(price.size)]
    }

    //随机用户名
    internal fun randomSender(): String {
        val senders = ArrayList<String>()

        senders.add("ABJCD")
        senders.add("ABJCD")
        senders.add("ABJCD")
        senders.add("ABJCD")
        senders.add("ABJCD")
        senders.add("ABJCD")
        senders.add("ABJCD")
        val random = Random()
        return senders[random.nextInt(senders.size)]
    }

    //随机人数
    internal fun randomPeopleNum(): Int {
        val num = ArrayList<Int>()

        num.add(3)
        num.add(6)
        num.add(4)
        num.add(8)
        num.add(9)
        num.add(2)
        num.add(3)

        val random = Random()
        return num[random.nextInt(num.size)]
    }

    //随机标签
    internal fun randomTag(): String {
        val tags = ArrayList<String>()

        tags.add("骑行")
        tags.add("羽毛球")
        tags.add("篮球")
        tags.add("游泳")
        tags.add("送餐")
        tags.add("拼车")
        tags.add("团购")
        val random = Random()
        return tags[random.nextInt(tags.size)]
    }

    //随机图片
    internal fun randomImage(): Int {
        val images = ArrayList<Int>()

        images.add(R.drawable.download)
        images.add(R.drawable.download2)
        images.add(R.drawable.download3)
        images.add(R.drawable.download4)
        images.add(R.drawable.download5)
        images.add(R.drawable.images)
        images.add(R.drawable.images1)
        val random = Random()
        return images[random.nextInt(images.size)]
    }

    //随机内容
    internal fun randomContent(): String {
        val contents = ArrayList<String>()

        contents.add("HHHHHHH")
        contents.add("i 吃的比较")
        contents.add("会比较快乐")
        contents.add("如何靠近路边金额可")
        contents.add("风格哦如何让加班")
        contents.add("发热和频率份健康")
        contents.add("发热片和 i 了博客积分可润肤")
        val random = Random()
        return contents[random.nextInt(contents.size)]
    }
}

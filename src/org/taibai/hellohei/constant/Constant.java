package org.taibai.hellohei.constant;

/**
 * <p>Creation Time: 2021-09-21 18:00:49</p>
 * <p>Description: 各种常量，集中管理</p>
 *
 * @author 太白
 */
public class Constant {

    public static class ImageShow {
        /**
         * 主体的长与宽
         */
        public static final int ImageHeight = 100;
        public static final int ImageWidth = 100;

        private static final String prefix = "/org/taibai/hellohei/img/";

        // 主要的动画（不应该只有一个，后面再改吧）
        public static final String mainImage = prefix + "shake-head-txt.gif";
        // 洗澡
        public static final String bathImage = prefix + "licking the claw.gif";
        // 再见
        public static final String byeImage = prefix + "bye.gif";
        // 图标
        public static final String iconImage = prefix + "icon.png";
        // 玩吉他
        public static final String guitarImage = prefix + "playing guitar.gif";
        // 心情增加
        public static final String emotionIncreasingImage = prefix + "smiling clouds.png";

        public static class ItemImage {
            // 食物清单——鸡蛋
            public static final String eggImage = prefix + "foods/egg.png";
            // 食物清单——牛奶
            public static final String milkImage = prefix + "foods/milk.png";

            // 洗澡用品——肥皂
            public static final String soapImage = prefix + "bath/soap.png";
        }

    }

    public static class UserInterface {
        /**
         * 交互时间，例如点击罗小黑会响应一个动作，该动作持续RunTime
         */
        public static final int ActionRunTime = 3;

        /**
         * 说话的消息框维持的时间
         */
        public static final int SayingRunTime = 4;

        /**
         * 碎碎念
         */
        public static final String[] selfTalking = {
                "嘿咻~",
                "点我~",
                "小白，这个字怎么念呀",
                "想吃甘蔗了……",
                "在干嘛呢~"
        };
    }

}

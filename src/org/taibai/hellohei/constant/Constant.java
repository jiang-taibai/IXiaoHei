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

        public static final String mainImage = "/org/taibai/hellohei/img/licking the claw.gif";
        public static final String byeImage = "/org/taibai/hellohei/img/bye.gif";
        public static final String iconImage = "/org/taibai/hellohei/img/icon.png";
        public static final String guitarImage = "/org/taibai/hellohei/img/playing guitar.gif";
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

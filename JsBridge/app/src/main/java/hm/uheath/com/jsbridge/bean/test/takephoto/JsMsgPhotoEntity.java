package hm.uheath.com.jsbridge.bean.test.takephoto;

/**
 * TODO:
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public class JsMsgPhotoEntity {
    public static final int TAKEREQUESTCODE=10001;
    public static final int SELECTREQUESTCODE=10002;


    /**
     * data : {"width":1,"height":2,"iscompress":true}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * width : 1
         * height : 2
         * iscompress : true
         */

        private int width;
        private int height;
        private boolean iscompress;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public boolean isIscompress() {
            return iscompress;
        }

        public void setIscompress(boolean iscompress) {
            this.iscompress = iscompress;
        }
    }
}

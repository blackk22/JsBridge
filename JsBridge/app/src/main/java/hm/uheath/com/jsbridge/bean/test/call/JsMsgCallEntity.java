package hm.uheath.com.jsbridge.bean.test.call;

import hm.uheath.com.jsbridge.JsMessage;

/**
 * TODO:
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public class JsMsgCallEntity extends JsMessage {
    public static final int CALLCODE=10003;
    /**
     * data : {"number":1}
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
         * number : 1
         */

        private String number;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}


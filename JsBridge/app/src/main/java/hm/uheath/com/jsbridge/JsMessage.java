package hm.uheath.com.jsbridge;

/**
 * TODO:
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public class JsMessage {
    /**
     *  action : js的意图
     */

    private String action;
    /**
     *  callback : native处理后调用js
     */
    private String callback;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}


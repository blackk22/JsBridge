package hm.uheath.com.jsbridge;

/**
 * TODO:
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public class HandleResult {
    private Object mData;

    public Object getData() {
        return mData;
    }

    public void setData(Object data) {
        mData = data;
    }

    public HandleResult(Object data) {
        mData = data;
    }
    public HandleResult() {
    }
}

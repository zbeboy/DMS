package top.zbeboy.dms.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AjaxUtils {
    private Boolean state;
    private String msg;
    private Map<String, Object> mapResult = new HashMap<>();
    private List<Object> listResult = new ArrayList<>();
    private Map<String, Object> result = new HashMap<>();

    public static AjaxUtils of() {
        return new AjaxUtils();
    }

    public AjaxUtils success() {
        this.state = true;
        return this;
    }

    public AjaxUtils fail() {
        this.state = false;
        return this;
    }

    public AjaxUtils msg(String msg) {
        this.msg = msg;
        return this;
    }

    public AjaxUtils map(Map<String, Object> map) {
        this.mapResult = map;
        return this;
    }

    public AjaxUtils list(List<Object> list) {
        this.listResult = list;
        return this;
    }

    public AjaxUtils put(String attribute, Object value) {
        this.result.put(attribute, value);
        return this;
    }

    public Map<String, Object> send() {
        this.result.put("state", this.state);
        this.result.put("msg", this.msg);
        this.result.put("mapResult", this.mapResult);
        this.result.put("listResult", this.listResult);
        return this.result;
    }
}

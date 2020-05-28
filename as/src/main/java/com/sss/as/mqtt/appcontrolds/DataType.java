package com.sss.as.mqtt.appcontrolds;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.SerializedName;

public enum DataType {

    @SerializedName("SceneData")
    SCENE_DATA("SceneData"),
    @SerializedName("SceneMark")
    SCENE_MARK("SceneMark");
    private final String value;
    private final static Map<String, DataType> CONSTANTS = new HashMap<String, DataType>();

    static {
        for (DataType c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private DataType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }

    public static DataType fromValue(String value) {
        DataType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}

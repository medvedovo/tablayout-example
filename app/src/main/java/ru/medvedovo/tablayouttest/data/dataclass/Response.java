package ru.medvedovo.tablayouttest.data.dataclass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public List<Item> data;
}

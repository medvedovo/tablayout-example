package ru.medvedovo.tablayouttest.data.dataclass;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("url")
    public String url;

    @SerializedName("title")
    public String title;
}

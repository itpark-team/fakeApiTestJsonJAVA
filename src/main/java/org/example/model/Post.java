package org.example.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String bodya;
}

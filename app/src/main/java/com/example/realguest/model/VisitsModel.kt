package com.example.realguest.model

import com.google.gson.annotations.SerializedName

data class Visits(
    val content: List<Visit>,
    val pageable: Pageable,
    val last: Boolean,

    @SerializedName("total_pages")
    val totalPages: Long,

    @SerializedName("total_elements")
    val totalElements: Long,

    val first: Boolean,
    val number: Long,

    @SerializedName("number_of_elements")
    val numberOfElements: Long,
    val size: Long,
    val empty: Boolean
)

data class Visit(
    @SerializedName("date_time")
    val dateTime: String,
    val authorized: Boolean,
    val description: Description,
    val suitable: Suitable
)

data class Description(
    val title: String,
    val devices: String,
    val portrait: String,

    @SerializedName("filtering_interval")
    val filteringInterval: String
)

data class Suitable(
    val primary: List<User>,
    val secondary: List<User>
)

data class User(
    val id: Long,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("photo_100")
    val photo100: String
)

data class Pageable(
    val page_number: Long,

    val page_size: Long,

    val offset: Long
)
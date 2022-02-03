package com.example.realguest.data

import com.beust.klaxon.Json

data class Visits(
    val content: List<Visit>,
    val pageable: Pageable,
    val last: Boolean,

    @Json(name = "total_pages")
    val totalPages: Long,

    @Json(name = "total_elements")
    val totalElements: Long,

    val first: Boolean,
    val number: Long,

    @Json(name = "number_of_elements")
    val numberOfElements: Long,

    val size: Long,
    val empty: Boolean
)

data class Visit(
    @Json(name = "date_time")
    val dateTime: String,

    val authorized: Boolean,
    val description: Description,
    val suitable: Suitable
)

data class Description(
    val title: String,
    val devices: String,
    val portrait: String,

    @Json(name = "filtering_interval")
    val filteringInterval: String
)

data class Suitable(
    val primary: List<User>,
    val secondary: List<User>
)

data class User(
    val id: Long,

    @Json(name = "first_name")
    val firstName: String,

    @Json(name = "last_name")
    val lastName: String,

    @Json(name = "photo_100")
    val photo100: String
)

data class Pageable(
    @Json(name = "page_number")
    val pageNumber: Long,

    @Json(name = "page_size")
    val pageSize: Long,

    val offset: Long
)
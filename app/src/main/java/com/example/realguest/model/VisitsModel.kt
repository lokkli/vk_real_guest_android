package com.example.realguest.model

data class Visits(
    val content: List<Visit>,
    val pageable: Pageable,
    val last: Boolean,
    val total_pages: Long,
    val total_elements: Long,
    val first: Boolean,
    val number: Long,
    val number_of_elements: Long,
    val size: Long,
    val empty: Boolean
)

data class Visit(
    val date_time: String,
    val description: List<Description>,
    val suitable: List<User>,
    val title: String
)

data class Description(
    val code: String,
    val value: String
)

data class User(
    val id: Long,
    val first_name: String,
    val last_name: String,
    val photo_50: String
)

data class Pageable(
    val page_number: Long,
    val page_size: Long,
    val offset: Long
)
package com.luna.pagingdemo.model

import com.google.gson.annotations.SerializedName

/*
{
  "page": 1,
  "per_page": 6,
  "total": 12,
  "total_pages": 2,
  "data": [
    {
      "id": 1,
      "email": "george.bluth@reqres.in",
      "first_name": "George",
      "last_name": "Bluth",
      "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"
    },
    {
      "id": 2,
      "email": "janet.weaver@reqres.in",
      "first_name": "Janet",
      "last_name": "Weaver",
      "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"
    }
  ]
}
* */
class
User {
    @field:SerializedName("avatar")
    var avatar: String? = null
    @field:SerializedName("email")
    var email: String? = null
    @field:SerializedName("first_name")
    var firstName: String? = null
    @field:SerializedName("id")
    var id: Long? = null
    @field:SerializedName("last_name")
    var lastName: String? = null
}
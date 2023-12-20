package com.example.selldo

data class LoginResponse(val authentication_token:String,
                         val sales_id:String,
                         val role:String,
                         val department:String,
                         val industry:String,
                        val merge_leads:Boolean,
                        val allow_to_reassign:Boolean)

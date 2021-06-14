package com.wisaly.wisaly_kotlin_spring.utils.exceptions

import java.lang.Exception

class UserNotFound(override val message:String?):Exception(message)
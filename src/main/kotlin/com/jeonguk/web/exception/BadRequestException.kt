package com.jeonguk.web.exception

import java.lang.RuntimeException

class BadRequestException(override val message: String?) : RuntimeException()
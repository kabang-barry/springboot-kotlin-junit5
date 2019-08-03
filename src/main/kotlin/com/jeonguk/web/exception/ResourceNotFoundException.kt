package com.jeonguk.web.exception

import java.lang.RuntimeException

class ResourceNotFoundException(override val message: String?) : RuntimeException()
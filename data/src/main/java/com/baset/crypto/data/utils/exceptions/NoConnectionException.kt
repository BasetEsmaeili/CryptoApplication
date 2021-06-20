package com.baset.crypto.data.utils.exceptions

import java.io.IOException

class NoConnectionException(override val message: String?) : IOException(message)
package com.purpleshade.pms.utils

import java.io.IOException

/**
 * Created by pawan on 03,June,2020
 */

class ApiExceptions(message : String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
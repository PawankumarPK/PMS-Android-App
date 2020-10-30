package com.purpleshade.pms.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.purpleshade.pms.activity.BaseActivity

/**
 * Created by pawan on 28,October,2020
 */
open class BaseFragment : Fragment() {

    lateinit var baseActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseActivity = activity as BaseActivity
    }
}
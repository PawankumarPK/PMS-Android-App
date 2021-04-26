package com.purpleshade.pms.activity

import android.app.Activity
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.purpleshade.pms.R
import com.purpleshade.pms.db.MyDatabase
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.network.RetrofitClient
import com.purpleshade.pms.utils.*
import com.purpleshade.pms.utils.customObject.Flag
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.delete_warning_dialog.view.*
import kotlin.system.exitProcess

class BaseActivity : AppCompatActivity() {
    lateinit var exitAppBottomSheetDialog: BottomSheetDialog

    companion object {
        var INSTANCE: MyDatabase? = null
    }

    val user = RoomUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        RetrofitClient.initRosAPI(Helper.getConfigValue(this, "api_url")!!)
        INSTANCE = getAppDataBase()!!

        checkConnection()

    }

    private fun checkConnection() {
        val manager = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo
        if (activeNetwork == null) {
            Flag.networkProblem = true
            //mNetworkCheckLayout.show()
            mProgressBar.hide()
        } else {
            Flag.networkProblem = false
            // mNetworkCheckLayout.gone()
        }
        Handler().postDelayed({
            checkConnection()
        }, 500)
    }

    private fun getAppDataBase(): MyDatabase? {
        if (INSTANCE == null) {
            synchronized(MyDatabase::class) {
                INSTANCE = Room.databaseBuilder(applicationContext, MyDatabase::class.java, "myDB")
                    .allowMainThreadQueries().build()
            }
        }
        return INSTANCE
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (Flag.backPressCount == 0 && Flag.backPressString == "login") {
                exitAppBottomSheetDialog = BottomSheetDialog(this)
                exitAppBottomSheetVisible(this)
            }
            return super.onKeyDown(keyCode, event)
        }
        return true
    }

    private fun exitAppBottomSheetVisible(actvity: Activity) {
        val deleteBottomSheetView = LayoutInflater.from(this).inflate(R.layout.exit_app_dialog, actvity.findViewById<View>(R.id.deleteBottomSheetContainer) as LinearLayout?)
        exitAppBottomSheetDialog.setContentView(deleteBottomSheetView)

        deleteBottomSheetView.mYes.setOnClickListener {
            finish()
            exitProcess(0)
        }

        exitAppBottomSheetDialog.show()
    }


}
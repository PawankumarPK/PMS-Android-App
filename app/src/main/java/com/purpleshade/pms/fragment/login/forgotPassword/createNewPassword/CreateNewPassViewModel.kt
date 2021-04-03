package com.purpleshade.pms.fragment.login.forgotPassword.createNewPassword

import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.CreateNewPassRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.snackbar


class CreateNewPassViewModel(val context: Context, val editText: EditText, val confirmPassword :EditText,val imageView: ImageView, val progressBar: ProgressBar, val repository: CreateNewPassRepository) : ViewModel() {

    var label: String? = null
    var buttonText: String? = null
    var editTextText: String? = null
    var confirmEditText: String? = null
    var authListener: AuthListener? = null
    var visiblity = true
    var maxLength = 4
    val roomUser = RoomUser()


    fun resetButtonOnClick(view: View) {
        progressBar.show()
        when {
            editTextText.isNullOrEmpty() -> {
                view.snackbar(context, "Please enter a password", R.color.colorBlackGrey)
                progressBar.hide()
                return
            }
            confirmEditText.isNullOrEmpty() || confirmEditText != editTextText -> {
                view.snackbar(context, "Password did not match", R.color.colorBlackGrey)
                progressBar.hide()
                return
            }
        }

        if (Flag.appPin) {
            roomUser.appPassword = confirmEditText.toString()
            BaseActivity.INSTANCE!!.myDao().pinUpdate(confirmEditText.toString(), 1)

            repository.confirmPin = confirmEditText
            val repo = repository.updatePin(context, progressBar)
            authListener!!.onSuccess(repo)
            repository.view = view

            return
        }

        val repo = repository.createNewPassword(context, editText, progressBar)
        authListener!!.onSuccess(repo)

        repository.view = view

    }

    fun passwordVisibilityOnClick(view: View) {
        if (visiblity) {
            visiblity = false
            editText.transformationMethod = null
            imageView.setImageResource(R.drawable.ic_visibilty_off)
        } else {
            visiblity = true
            editText.transformationMethod = PasswordTransformationMethod()
            imageView.setImageResource(R.drawable.ic_visible)

        }
    }

    private fun editTextMaxLength() {
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = LengthFilter(maxLength)
        editText.filters = filterArray
        confirmPassword.filters = filterArray
    }

    fun appPinEvent() {
        if (Flag.appPin) {
            buttonText = "Reset Pin"
            editText.inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD
            confirmPassword.inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD
            editTextMaxLength()
            label = "Create new pin"
        } else {
            buttonText = "Reset Password"
            label = "Create new password"
        }

    }

    fun backOnClick(view:View){
        view.findNavController().navigate(R.id.loginFragment)
    }

}
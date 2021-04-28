package com.purpleshade.pms.fragment.login.forgotPassword.createNewPassword

import android.R.attr.password
import android.app.Dialog
import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
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
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.create_new_pass_fragment.*
import kotlinx.android.synthetic.main.logout_msg_dialog.*


class CreateNewPassViewModel(val context: Context, val editText: EditText, val confirmPassword: EditText, val imageView: ImageView, val progressBar: ProgressBar, val repository: CreateNewPassRepository) : ViewModel() {

    var label: String? = null
    var buttonText: String? = null
    var editTextText: String? = null
    var confirmEditText: String? = null
    var authListener: AuthListener? = null
    var visiblity = true
    var maxLength = 4
    val roomUser = RoomUser()

    lateinit var mDialog: Dialog



    fun resetButtonOnClick(view: View) {
        if (Flag.networkProblem) {
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }
        when {
            editTextText.isNullOrEmpty() -> {
                view.snackbar(context, "Please enter a password", R.color.colorBlackGrey)
                return
            }
            confirmEditText.isNullOrEmpty() || confirmEditText != editTextText -> {
                view.snackbar(context, "Password did not match", R.color.colorBlackGrey)
                return
            }
        }

        if (Flag.appPin) {
            progressBar.show()

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
        editText.requestFocus()
        removeForgotPassField()

        if (Flag.appPin) {
            buttonText = "Reset Pin"
            editTextMaxLength()
            editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
            confirmPassword.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
            label = "Create new pin"
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            confirmPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            buttonText = "Reset Password"
            label = "Create new password"
        }

    }

    fun removeForgotPassField() {
        val repo = repository.removeForgotPassField(context, progressBar)
        authListener!!.onSuccess(repo)
    }

    fun backOnClick(view: View) {
        view.findNavController().navigate(R.id.loginFragment)
    }

    fun cancelProcessDialog(view: View) {
        if (Flag.networkProblem) {
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }

        mDialog = Dialog(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.back_msg_warning_dialog, null, false)
        mDialog.setContentView(layout)

        mDialog.mYes.setOnClickListener {
//            logoutOnClick(view)
            view.findNavController().navigate(R.id.loginFragment)
            mDialog.dismiss()
        }
        mDialog.mNo.setOnClickListener {
            mDialog.dismiss()
        }
        mDialog.show()

    }


}
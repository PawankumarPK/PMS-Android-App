package com.purpleshade.pms.fragment.login.forgotPassword.verification

import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.FpVerificationRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.toast
import kotlinx.android.synthetic.main.logout_msg_dialog.*

class FpVerificationViewModel(
    val context: Context,
    private val box1: EditText,
    private val box2: EditText,
    private val box3: EditText,
    private val box4: EditText,
    private val box5: EditText,
    private val box6: EditText,
    var progressBar: ProgressBar,
    val repository: FpVerificationRepository
) : ViewModel() {

    var authListener: AuthListener? = null
    val sb = StringBuilder()

    lateinit var mDialog: Dialog


    fun backOnClick(view:View){
        view.findNavController().navigate(R.id.forgotPasswordFragment)
    }

    fun verifyOnClick(view: View) {
        /*if (Flag.networkProblem) {
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }
        progressBar.show()

        if (Flag.forgotPassToken != sb.toString()) {
            context.toast(context.getString(R.string.verification_failed))
            progressBar.hide()
            sb.clear()
            return
        }

        val repo = repository.verifyForgetPasswordCode(context, progressBar)
        authListener!!.onSuccess(repo)
        repository.view = view

        sb.clear()*/


        view!!.findNavController().navigate(R.id.action_fpVerificationFragment_to_createNewPassFragment)

    }

    fun getVerificationToken() {
        progressBar.show()
        val repo = repository.getVerificationTokenForMatch(context, progressBar)
        authListener!!.onSuccess(repo)
    }

    fun resendVerificationCodeOnClick(view:View){
        if (Flag.networkProblem) {
            context.toast(context.getString(R.string.no_internet_connection))
            return
        }
        progressBar.show()
        val removeForgotPassFieldRepo = repository.removeForgotPassField(context, progressBar)
        authListener!!.onSuccess(removeForgotPassFieldRepo)


        val repo = repository.resendVerificationCode(context, progressBar)
        authListener!!.onSuccess(repo)

        view.findNavController().navigate(R.id.fpVerificationFragment)
        repository.view = view

    }

    fun createPinBoxes() {
        val edit = arrayOf<EditText>(box1, box2, box3, box4, box5, box6)

        box1.addTextChangedListener(GenericTextWatcher(box1, edit))
        box2.addTextChangedListener(GenericTextWatcher(box2, edit))
        box3.addTextChangedListener(GenericTextWatcher(box3, edit))
        box4.addTextChangedListener(GenericTextWatcher(box4, edit))
        box5.addTextChangedListener(GenericTextWatcher(box5, edit))
        box6.addTextChangedListener(GenericTextWatcher(box6, edit))


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
            view.findNavController().navigate(R.id.forgotPasswordFragment)
            mDialog.dismiss()
        }
        mDialog.mNo.setOnClickListener {
            mDialog.dismiss()
        }
        mDialog.show()

    }



    inner class GenericTextWatcher(private val view: View, private val editText: Array<EditText>) : TextWatcher {

        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            sb.append(text)

            when (view.id) {
                R.id.mEditTextBoxOne -> if (text.length == 1) editText[1].requestFocus()
                R.id.mEditTextBoxTwo -> if (text.length == 1) editText[2].requestFocus() else if (text.isEmpty()) editText[0].requestFocus()
                R.id.mEditTextBoxThree -> if (text.length == 1) editText[3].requestFocus() else if (text.isEmpty()) editText[1].requestFocus()
                R.id.mEditTextBoxFour -> if (text.length == 1) editText[4].requestFocus() else if (text.isEmpty()) editText[2].requestFocus()
                R.id.mEditTextBoxFive ->
                    if (text.length == 1)
                        editText[5].requestFocus()
                    else if (text.isEmpty())
                        editText[3].requestFocus()

                R.id.mEditTextBoxSix ->
                    if (text.length == 1)
                        editText[5].requestFocus()
                    else if (text.isEmpty()) {
                        editText[4].requestFocus()
                    }

                // view.findNavController().navigate(R.id.profileFragment)

            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            /*if (sb.length == 1)
                sb.deleteCharAt(0)*/
        }

        override fun onTextChanged(s: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            /*if (box6.length() == 0) {
                sb.append(s)
                //box6.clearFocus()
                box5.requestFocus()
                box4.isCursorVisible = true
            }*/
        }
    }
}
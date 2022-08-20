package com.example.themoviebooking.viewpods

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.themoviebooking.activities.HomeActivity
import com.example.themoviebooking.delegates.LoginButtonDelegate
import kotlinx.android.synthetic.main.view_pod_login_button.view.*

class LoginButtonViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs)  {

    lateinit var mDelegate: LoginButtonDelegate

    fun setDelegate(delegate: LoginButtonDelegate) {
        this.mDelegate = delegate
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        btnContinue.setOnClickListener {
            mDelegate.onTapConfirm()
        }
    }
}
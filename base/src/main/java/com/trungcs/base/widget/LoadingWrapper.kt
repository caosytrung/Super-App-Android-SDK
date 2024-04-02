//package com.trungcs.base.widget
//
//import android.app.AlertDialog
//import android.content.Context
//import androidx.compose.runtime.Composable
//import com.trungcs.base.R
//
//@Composable
//fun LoadingWrapper(
//    viewModel
//    content: @Composable () -> Unit
//) {
//
//    var showDialog by remember { mutableStateOf(false) }
//
//    fun show(context: Context) {
//        if (progressDialog == null) {
//            progressDialog = AlertDialog
//                .Builder(context, R.style.ProgressBarStyle)
//                .setCancelable(false)
//                .setView(R.layout.cart_ui_common_layout_dialog_progress)
//                .create()
//        }
//        progressDialog?.show()
//    }
//
//    fun hide() {
//        progressDialog?.dismiss()
//        progressDialog = null
//    }
//}
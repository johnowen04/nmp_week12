package com.ubaya160420016.s160420016_week12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    val REQUEST_SELECT_CONTACT = 1
    val REQUEST_SELECT_TEMPLATE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputPhoneNumber.setEndIconOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }

            startActivityForResult(intent, REQUEST_SELECT_CONTACT)
        }

        btnPickTemplate.setOnClickListener {
            val intent = Intent(this, MessageTemplateActivity::class.java)
            startActivityForResult(intent, REQUEST_SELECT_TEMPLATE)
        }

        btnSend.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, editMessage.text.toString())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(intent, "Send My Text")
            startActivity(shareIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_SELECT_CONTACT -> {
                    val contactUri = data?.data
                    val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val cursor =
                        contactUri?.let {
                            contentResolver.query(it, projection, null, null, null)
                        }
                    if (cursor != null && cursor.moveToFirst()) {
                        val hp = cursor.getString(0)
                        editPhoneNumber.setText(hp)
                    }
                }
                REQUEST_SELECT_TEMPLATE -> {
                    val message = data?.getStringExtra(MessageTemplateActivity.EXTRA_TEMPLATE)
                    editMessage.setText(message)

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
package com.ajolla.lactomama.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityEditProfileBinding
import com.ajolla.lactomama.model.User
import com.google.android.material.textfield.TextInputEditText

class EditProfile: AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
}

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityEditProfileBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val btnCancel = findViewById<Button>(R.id.btncancel)
//        val btnSave = findViewById<Button>(R.id.btnsavee)
//
//        btnCancel.setOnClickListener {
//
//            finish()         }
//
//        btnSave.setOnClickListener {
//            val intent = Intent(this, EditProfile::class.java)
//            startActivity(intent)
//        }
//
//        binding.imvback31.setOnClickListener {
//            val intent = Intent(this,ProfileFragment::class.java)
//            startActivity(intent)
//        }
//    }
//}


//       lateinit var fullName: TextInputEditText
//       lateinit var email: TextInputEditText
//       lateinit var username: TextInputEditText
//       lateinit var password: TextInputEditText
//       lateinit var saveButton: Button
//       lateinit var cancelButton: Button
//
//        private var isEditing: Boolean = false
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(R.layout.activity_edit_profile)
//
//
//            fullName = findViewById()
//            email = findViewById()
//            username = findViewById()
//            password = findViewById()
//            saveButton = findViewById()
//            cancelButton = findViewById()
//
//
//            saveButton.setOnClickListener {
//
//            }
//
//            cancelButton.setOnClickListener {
//
//
//        }
//
//
//     fun Fields(user: User) {
//            fullName.setText(user.fullName)
//            email.setText(user.email)
//            username.setText(user.userName)
//
//        }
//
//    fun getUserFromFields(): User {
//            val fullName = fullName.text.toString()
//            val email = email.text.toString()
//            val username = username.text.toString()
//            val password = password.text.toString()
//            return User(fullName, email, username, password)
//        }
//
//       fun validateForm(): Boolean {
//            var isValid = true
//
//            email.error = null
//
//            val email = email.text.toString()
//            if (isValid(email)) {
//                email.error = "Invalid email"
//                isValid = false
//            }
//
//            val username = username.text.toString()
//            if (username.isEmpty()) {
//                username.error = "Username is required"
//                isValid = false
//            }
//
//            val password = password.text.toString()
//            if (password.length < 6) {
//                password.error = "Password must be at least 6 characters"
//                isValid = false
//            }
//
//            return isValid
//        }
//    }
//}
//





package com.ajolla.lactomama.ui.status

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.FragmentLoginBinding
import com.ajolla.lactomama.model.BabyDataClass
import com.ajolla.lactomama.viewModel.Viewmodel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class LoginFragment : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: FragmentLoginBinding
    var selectedBitmap: Bitmap? = null
    private lateinit var gender: String
    var selectedPostImage: Uri? = null
    private val calendar = Calendar.getInstance()
    private lateinit var viewmodel: Viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[Viewmodel::class.java]
        gender = ""
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.viewModelScope.launch {
            viewmodel.getData().forEach {
                val babyFullName = it.babyFullName
                binding.editTextTextPersonName2.setText(babyFullName)
                val babyBirtDate = it.birthDate
                binding.loginBirtyDate.text = babyBirtDate
                val babyDueDate = it.dueDate
                binding.loginDueDate.text = babyDueDate
                val babyTimeBirth = it.timeOfBirth
                binding.loginTimeBirth.text = babyTimeBirth

                var value = it.babyProfileImage
                val bitmap = value?.let { it1 -> BitmapFactory.decodeByteArray(value, 0, it1.size) }

                binding.selectProfilImage.setImageBitmap(bitmap)

                if (it.babyGender == "boy") {
                    binding.loginImageViewBoy.setImageResource(R.drawable.selected_boy)
                }
                if (it.babyGender == "girl") {
                    binding.loginImageViewBoy.setImageResource(R.drawable.selected_girl)
                }
            }
        }
        binding.loginBirtyDateView.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(requireActivity(), this@LoginFragment, 2023, 0, 1)
            datePickerDialog.show()
            control()

        }
        binding.loginTimeBirthView.setOnClickListener {
            showTimePickerDialog(binding.loginTimeBirth)
            control()
        }
        binding.loginDueDate.setOnClickListener {

            showTimePickerDialog(binding.loginDueDate)
            control()
        }
        val selectProfileImage: CircleImageView = binding.selectProfilImage
        selectProfileImage.setOnClickListener {
            selectedImage()
            control()
        }
        binding.loginImageViewBoy.setOnClickListener {
            selectBoy()
            control()
        }
        binding.loginImageViewGirl.setOnClickListener {
            selectGirl()
            control()
        }
        binding.editTextTextPersonName2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var value = s.toString()

                if (value == "") {
                    binding.view55.setBackgroundResource(R.drawable.buttonrectangle)
                    control()
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.view55.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                if (binding.editTextTextPersonName2.text.toString().isNotEmpty() &&
                    binding.loginBirtyDate.text.toString().isNotEmpty() &&
                    binding.loginDueDate.text.toString().isNotEmpty() &&
                    binding.loginTimeBirth.text.toString().isNotEmpty() &&
                    gender.isNotEmpty()
                ) {
                    if (selectedBitmap != null) {
                        saveData()
                    }
                        activity?.runOnUiThread {
                            val intent = Intent(requireContext(), ChildHomePageActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }

                } else {
                    activity?.runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Please fill in information!!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    fun selectedImage() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 2)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            selectedPostImage = data.data
            binding.selectProfilImage.visibility = View.VISIBLE
            if (selectedPostImage != null) {

                if (Build.VERSION.SDK_INT >= 28) {

                    val source = ImageDecoder.createSource(
                        requireContext().contentResolver,
                        selectedPostImage!!

                    )
                    selectedBitmap = ImageDecoder.decodeBitmap(source)
                    binding.selectProfilImage.setImageBitmap(selectedBitmap)
                } else {
                    selectedBitmap = MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        selectedPostImage
                    )
                    binding.selectProfilImage.setImageBitmap(selectedBitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        binding.loginBirtyDate.text = formatter.format(selectedDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = LocalTime.of(hourOfDay, minute)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        binding.loginTimeBirth.text = formatter.format(selectedTime)
    }

    private fun showTimePickerDialog(textview: TextView) {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
            textview.text = time.toString()
        }

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(requireContext(), timeSetListener, hour, minute, true).show()
    }

    fun selectBoy() {
        binding.loginImageViewBoy.setImageResource(R.drawable.selected_boy)
        binding.loginImageViewGirl.setImageResource(R.drawable.unselected_girl)
        gender = "boy"
        control()

    }
    fun selectGirl() {
        binding.loginImageViewBoy.setImageResource(R.drawable.unselected_boy)
        binding.loginImageViewGirl.setImageResource(R.drawable.selected_girl)
        gender = "girl"
        control()
    }

    @SuppressLint("SuspiciousIndentation")
    fun saveData() {
        val babyName = binding.editTextTextPersonName2.text.toString()
        val birthDate = binding.loginBirtyDate.text.toString()
        val timeBirth = binding.loginTimeBirth.text.toString()
        val dueDate = binding.loginDueDate.text.toString()
        val stream = ByteArrayOutputStream()
        selectedBitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val imageByteArray = stream.toByteArray()


        val user = BabyDataClass(
            babyFullName = babyName,
            birthDate = birthDate,
            timeOfBirth = timeBirth,
            dueDate = dueDate,
            babyGender = gender,
            babyProfileImage = imageByteArray
        )
        viewmodel.insertBaby(user)
    }

    fun control() {
        if (binding.editTextTextPersonName2.text.toString().isNotEmpty() &&
            binding.loginBirtyDate.text.toString().isNotEmpty() &&
            binding.loginDueDate.text.toString().isNotEmpty() &&
            binding.loginTimeBirth.text.toString().isNotEmpty()&& gender.isNotEmpty()
        )
        {
            binding.view55.setBackgroundResource(R.drawable.buttonrectangle)
        }
    }

}
package com.ajolla.lactomama.ui.status

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajolla.lactomama.R
import com.ajolla.lactomama.adapter.DiaperAdapter
import com.ajolla.lactomama.adapter.FeedingAdapter
import com.ajolla.lactomama.adapter.SleepAdapter
import com.ajolla.lactomama.adapter.StatusAdapter
import com.ajolla.lactomama.databinding.ActivityCalendarBinding
import com.ajolla.lactomama.model.StatusDataClass
import com.ajolla.lactomama.viewModel.Viewmodel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding
    private lateinit var viewmodel: Viewmodel
    private var list: MutableList<StatusDataClass> = mutableListOf()
    private lateinit var adapter: StatusAdapter
    private lateinit var adapter1: FeedingAdapter
    private lateinit var adapter2: DiaperAdapter
    private lateinit var adapter3: SleepAdapter

    var listFeeding: MutableList<String> = mutableListOf()
    var listDiaper: MutableList<String> = mutableListOf()
    var listSleep: MutableList<String> = mutableListOf()

    var listFeedingToday: MutableList<String> = mutableListOf()
    var listDiaperToday: MutableList<String> = mutableListOf()
    var listSleepToday: MutableList<String> = mutableListOf()

    var listFeedingHour: MutableList<String> = mutableListOf()
    var listDiaperHour: MutableList<String> = mutableListOf()
    var listSleepHour: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[Viewmodel::class.java]
        adapter = StatusAdapter(list)
        adapter1 = FeedingAdapter(listFeeding, listFeedingToday, listFeedingHour)
        adapter2 = DiaperAdapter(listDiaper, listDiaperToday, listSleepHour)
        adapter3 = SleepAdapter(listSleep, listSleepToday, listSleepHour)

        imageviewAll()
        getAllAdapter()
        binding.dateCalander.text = date()
        adapter.notifyDataSetChanged()

        binding.allStatus.setOnClickListener {
            imageviewAll()
            getAllAdapter()
        }
        binding.feedingStatus.setOnClickListener {
            imageviewFeeding()
            getFeedingAdapter()
        }
        binding.diaperStatus.setOnClickListener {
            imageviewDiaper()
            getDiaperAdapter()
        }
        binding.sleepStatus.setOnClickListener {
            imageviewSleep()
            getSleepAdapter()
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getAllAdapter() {
        listCheck()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getFeedingAdapter() {
        listCheck()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter1
        adapter1.notifyDataSetChanged()
    }

    private fun getDiaperAdapter() {
        listCheck()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter2
        adapter2.notifyDataSetChanged()
    }

    private fun getSleepAdapter() {
        listCheck()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter3
        adapter3.notifyDataSetChanged()
    }

    private fun imageviewAll() {
        binding.allStatus.setImageResource(R.drawable.text_all_selected)
        binding.feedingStatus.setImageResource(R.drawable.icon_feeding_unselected)
        binding.diaperStatus.setImageResource(R.drawable.diaper_unselected)
        binding.sleepStatus.setImageResource(R.drawable.sleeping_unselected)
    }

    private fun imageviewFeeding() {
        binding.allStatus.setImageResource(R.drawable.text_all_unselected)
        binding.feedingStatus.setImageResource(R.drawable.icon_feeding_selected)
        binding.diaperStatus.setImageResource(R.drawable.diaper_unselected)
        binding.sleepStatus.setImageResource(R.drawable.sleeping_unselected)
    }

    private fun imageviewDiaper() {
        binding.allStatus.setImageResource(R.drawable.text_all_unselected)
        binding.feedingStatus.setImageResource(R.drawable.icon_feeding_unselected)
        binding.diaperStatus.setImageResource(R.drawable.diaper_select)
        binding.sleepStatus.setImageResource(R.drawable.sleeping_unselected)
    }

    private fun imageviewSleep() {
        binding.allStatus.setImageResource(R.drawable.text_all_unselected)
        binding.feedingStatus.setImageResource(R.drawable.icon_feeding_unselected)
        binding.diaperStatus.setImageResource(R.drawable.diaper_unselected)
        binding.sleepStatus.setImageResource(R.drawable.sleeping_selected)
    }

    private fun listCheck() {
        viewmodel.viewModelScope.launch {
            list.clear()

            listFeeding.clear()
            listDiaper.clear()
            listSleep.clear()

            viewmodel.getStatus().forEach {
                list.addAll(listOf(it))
                if (it.feedingNote != null) {
                    listFeeding.add(it.feedingNote)
                    listFeedingToday.add(it.today)
                    listFeedingHour.add(it.hour)
                }
                if (it.diaperNote != null) {
                    listDiaper.add(it.diaperNote)
                    listDiaperToday.add(it.today)
                    listDiaperHour.add(it.hour)
                }
                if (it.sleepNote != null) {
                    listSleep.add(it.sleepNote)
                    listSleepToday.add(it.today)
                    listSleepHour.add(it.hour)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun date(): String {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy (EEEE)", Locale.getDefault())
        val currentDate = Date()
        val today = dateFormat.format(currentDate)
        println(today)
        return today
    }

}
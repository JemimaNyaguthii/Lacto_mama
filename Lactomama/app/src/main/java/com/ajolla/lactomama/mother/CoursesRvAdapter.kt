import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.CoursesListItemBinding
import com.ajolla.lactomama.model.CourseDetailsActivity
import com.ajolla.lactomama.mother.CoursesData

class CoursesRvAdapter(
    private val courseList: List<CoursesData>,
    private val onItemClick: (CoursesData) -> Unit
) : RecyclerView.Adapter<CoursesRvAdapter.CoursesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val binding = CoursesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        val currentCourse = courseList[position]
        val binding = holder.binding

        binding.ivCareerWoman.tag = currentCourse.image
        binding.tvLactationist.text = currentCourse.name
        binding.tvDelete.text = currentCourse.price
        binding.tvDescription.text = currentCourse.description
        binding.tvPeriod.text = currentCourse.period
        holder.itemView.setOnClickListener {
            onItemClick(currentCourse)
        }

        binding.tvDescription.setOnClickListener {
            val intent = Intent(it.context, CourseDetailsActivity::class.java)
            intent.putExtra("name", currentCourse.name)
            intent.putExtra("image", currentCourse.image)
            intent.putExtra("period", currentCourse.period)
            intent.putExtra("price", currentCourse.price)
            intent.putExtra("description", currentCourse.description)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    class CoursesViewHolder(val binding: CoursesListItemBinding) : RecyclerView.ViewHolder(binding.root)
}


import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.R
import com.example.doglist.databinding.DogViewItemBinding
import com.squareup.picasso.Picasso
import com.example.utils.models.DogArticle
import com.squareup.picasso.MemoryPolicy

class DogArticleViewHolder(
    private val binding: DogViewItemBinding,
    private val listener: ArticlesAdapter.onArticleListener
) : RecyclerView.ViewHolder(binding.root) {
    var dogArticle: DogArticle? = null


    init {
        binding.apply {
            favSwitcher.setOnClickListener {
                val bitmap = dogImage.drawToBitmap()
                listener.onArticleClick(adapterPosition, bitmap, dogImage.id.toString())
                favSwitcher.showNext()
            }
        }
    }


    fun updateView() {
       binding.dogFact.text = dogArticle?.facts?.get(0)
        if (dogArticle?.url != null) {
            //val file = File(dogArticle?.url)
            Picasso.get()
                // .load(file)
                .load(dogArticle?.url)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                //.load("https://i.pinimg.com/564x/15/36/e7/1536e7de67f8f992c595a308ec8ae363.jpg")
                .error(R.drawable.genuine_beauty)
                .into(binding.dogImage)
        }
    }
}




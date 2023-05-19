import androidx.core.view.ViewCompat
import androidx.core.view.drawToBitmap
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.R
import com.example.doglist.databinding.DogViewItemBinding
import com.example.utils.models.DogArticle
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class DogArticleViewHolder(
    private val binding: DogViewItemBinding,
    private val listener: ArticlesAdapter.onArticleListener
) : RecyclerView.ViewHolder(binding.root) {
    var dogArticle: DogArticle? = null


    init {
        binding.apply {
            favSwitcher.setOnClickListener {
                if (!ViewCompat.isLaidOut(it)) {
                    throw IllegalStateException("View needs to be laid out before calling drawToBitmap()")
                } else {
                    val bitmap = dogImage.drawToBitmap()
                    listener.onArticleClick(adapterPosition, bitmap, dogImage.id.toString())
                    favSwitcher.showNext()
                }
            }
        }
    }


    fun updateView() {
       binding.dogFact.text = dogArticle?.facts?.get(0)
        if (dogArticle?.url != null&&dogArticle?.url!="") {
            Picasso.get()
                .load(dogArticle?.url)
                .error(R.drawable.genuine_beauty)
                .into(binding.dogImage, object : Callback.EmptyCallback() {
                    override fun onSuccess() {
                        binding.favSwitcher.isVisible=true
                    }

                    override fun onError(e: Exception?) {
                        super.onError(e)
                        binding.favSwitcher.isVisible=true
                    }
                })
        }


    }
}




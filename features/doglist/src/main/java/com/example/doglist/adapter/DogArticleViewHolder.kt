import android.view.View
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.R
import com.squareup.picasso.Picasso
import com.example.utils.models.DogArticle
import com.squareup.picasso.MemoryPolicy
import java.lang.ref.WeakReference

class DogArticleViewHolder(itemView: View, listener: ArticlesAdapter.onArticleListener) :
    RecyclerView.ViewHolder(itemView) {
    private val view = WeakReference(itemView)
    private var textViewTitle: TextView? = null
    private var imageView: ImageView? = null
    private var imageSwitcher: ImageSwitcher? = null
    var dogArticle: DogArticle? = null


    init {
        view.get()?.let {
            //binding=FragmentDogListBinding.bind(it)
            textViewTitle = it.findViewById(R.id.dogFact)
            imageView = it.findViewById(R.id.dogImage)
            imageSwitcher = it.findViewById(R.id.favSwitcher)
            imageSwitcher?.setOnClickListener {
                val bitmap = imageView?.drawToBitmap()
                if (bitmap != null)
                    listener.onArticleClick(adapterPosition, bitmap,imageView?.id.toString())
                imageSwitcher?.showNext()

            }
        }
    }


    fun updateView() {
        textViewTitle?.text = dogArticle?.facts?.get(0)
        if (dogArticle?.url != null) {
            //val file = File(dogArticle?.url)
            Picasso.get()
               // .load(file)
                .load(dogArticle?.url)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                //.load("https://i.pinimg.com/564x/15/36/e7/1536e7de67f8f992c595a308ec8ae363.jpg")
                .error(R.drawable.genuine_beauty)
                .into(imageView)
        }
    }
}

    class FooterViewHolder(itemView: View, listener: ArticlesAdapter.onArticleListener) :
        RecyclerView.ViewHolder(itemView) {
        private var loadButton: Button? = null

        init {
            loadButton = itemView.findViewById(R.id.loadButton)
            loadButton?.setOnClickListener {
                listener.onFooterClick()
            }
        }
    }


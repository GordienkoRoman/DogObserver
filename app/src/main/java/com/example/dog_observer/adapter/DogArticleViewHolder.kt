
import android.view.View
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dog_observer.R
import com.example.dog_observer.models.DogArticle
import com.squareup.picasso.Picasso
import java.lang.ref.WeakReference

class DogArticleViewHolder(itemView: View, listener: DogsAdapter.OnItemClickListener): RecyclerView.ViewHolder(itemView)  {
    private val view = WeakReference(itemView)
    private var textViewTitle: TextView?=null
    private var imageView: ImageView?=null
    private var imageSwitcher: ImageSwitcher? = null
    var dogArticle: DogArticle? = null
    init {
        view.get()?.let {
            textViewTitle= it.findViewById(R.id.dogFact)
            imageView = it.findViewById(R.id.dogImage)
            imageSwitcher = it.findViewById(R.id.favSwitcher)
            imageSwitcher?.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
    fun updateView()
    {
        textViewTitle?.text = dogArticle?.facts?.get(0)
        if(dogArticle?.url != null)
        Picasso.get()
            .load(dogArticle?.url)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
      //  imageView?.setImageURI()
        //imageView?. = job?.location
    }


}
class FooterViewHolder(itemView: View, listener: DogsAdapter.OnItemClickListener): RecyclerView.ViewHolder(itemView) {
    private var loadButton: Button? = null
    init{
        loadButton=itemView.findViewById(R.id.loadButton)
        loadButton?.setOnClickListener {
            listener.onFooterClick()
        }
    }


    }

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.R
import com.example.utils.models.DogArticle
import java.io.File

class ArticlesAdapter(onArticleListener: onArticleListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dogArticleList = mutableListOf<DogArticle>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    private companion object {
        const val FOOTER_VIEW_TYPE = 2
    }

    private val listener = onArticleListener

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newArticles: MutableList<DogArticle>) {
        dogArticleList.clear()
        dogArticleList.addAll(newArticles)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {

        when (position) {
            dogArticleList.size -> return FOOTER_VIEW_TYPE
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == FOOTER_VIEW_TYPE)
            return FooterViewHolder(
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.footer_view_item, parent, false), listener
            )
        //if (viewType==HEADER_VIEW_TYPE)
        //   return JobViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_header,parent,false))
        return DogArticleViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.dog_view_item, parent, false), listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.bind(model = dogArticleList[position])
        if (holder is DogArticleViewHolder) {
            holder.dogArticle = dogArticleList[position]
            holder.updateView()
        }
    }

    fun updateImg(article: DogArticle){
        dogArticleList[dogArticleList.size-1].url = article.url
        notifyItemChanged(dogArticleList.size-1)
    }
    fun updateFacts(article: DogArticle)
    {
        dogArticleList[dogArticleList.size-1].facts = article.facts
        notifyItemChanged(dogArticleList.size-1)
    }

    fun insertItem(item: DogArticle) {
        dogArticleList.add(item)
        notifyItemInserted(dogArticleList.size)
    }

    override fun getItemCount(): Int {
        return dogArticleList.count() + 1
    }



    interface onArticleListener {
        fun onArticleClick(position: Int, bitmap: Bitmap,path : String)
        fun onFooterClick()
    }
}


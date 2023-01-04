import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.R
import com.example.utils.models.DogArticle

public class DogsAdapter(onArticleListener: onArticleListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dogArticleList = mutableListOf<DogArticle>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    private companion object {
        const val HEADER_VIEW_TYPE = 1
        const val FOOTER_VIEW_TYPE = 2
    }

    private val listener = onArticleListener
    //private val mListener: OnItemClickListener = OnItemClickListener()

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

    fun insertItem(item: DogArticle) {
        dogArticleList.add( item)
        notifyItemInserted(dogArticleList.size)
    }

    override fun getItemCount(): Int {
        return dogArticleList.count() + 1
    }



    interface onArticleListener {
        fun onArticleClick(position: Int)
        fun onFooterClick()
    }
}


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dog_observer.R
import com.example.dog_observer.models.DogArticle

public class DogsAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dogArticleList = mutableListOf<DogArticle>()
    private val HEADER_VIEW_TYPE: Int = 1
    private val FOOTER_VIEW_TYPE: Int = 2
    private lateinit var onClickListener: OnItemClickListener
   //private val mListener: OnItemClickListener = OnItemClickListener()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newArticles: MutableList<DogArticle>) {
        dogArticleList.clear()
        dogArticleList.addAll(newArticles)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
         if(position==dogArticleList.size)
             return FOOTER_VIEW_TYPE
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==FOOTER_VIEW_TYPE)
            return FooterViewHolder(
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.footer_view_item, parent, false),onClickListener)
        //if (viewType==HEADER_VIEW_TYPE)
        //   return JobViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_header,parent,false))
        return DogArticleViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.dog_view_item, parent, false),onClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.bind(model = dogArticleList[position])
        if(holder is DogArticleViewHolder)
        {
            holder.dogArticle = dogArticleList[position]
            holder.updateView()
        }
    }

    fun insertItem(item: DogArticle) {
        dogArticleList.add(0, item)
        notifyItemInserted(0)
    }

    override fun getItemCount(): Int {
        return dogArticleList.count()+1
    }

    fun loadData(list: MutableList<DogArticle>) {
        dogArticleList.addAll(list)
        notifyItemRangeChanged(dogArticleList.size - list.size + 1, list.size)
    }
    fun setOnItemClickListener(listener: OnItemClickListener)
    {
        onClickListener=listener
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onFooterClick()
    }
}


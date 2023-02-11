package di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlin.properties.Delegates
import kotlin.properties.Delegates.notNull

internal class ArticlesComponentViewModel: ViewModel() {
    val component = DaggerArticlesComponent.builder().deps(ArticleDepsProvider.deps).Build()
    //val viewModelProvider = DaggerArticlesComponent.builder().deps()
}
interface ArticleDepsProvider {
    val deps:ArticlesDeps
    companion object : ArticleDepsProvider by ArticleDepsStore
}
object ArticleDepsStore: ArticleDepsProvider {
    override var deps: ArticlesDeps by notNull()
}
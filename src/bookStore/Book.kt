package bookStore

open class Book(
    protected val title: String = "Title",
    protected val author: String,
    protected val pages: Int = 500
) {
    private var pagesRead: Int = 0
    protected open fun readPage() {
        pagesRead++
    }
}
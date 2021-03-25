package bookStore

class Ebook(
    title: String,
    author: String,
    pages: Int,
    private val format: String = "Text"): Book(title, author, pages) {
    private var wordsRead: Int = 0

    override fun readPage() {
        wordsRead +=250
    }
}
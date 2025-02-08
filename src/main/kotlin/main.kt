
data class Comments(
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost : Boolean = false,
    val canClose : Boolean = false,
    val canOpen : Boolean = false,
)

data class Copyright(
    val id: Int = 0,
    val link: String = "",
    val name: String = "",
    val type: String = "",
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false,
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 0
)

data class PostSource(
    val key: String = ""
)

data class Geo(
    val type: String = "",
    val coordinates: String = "",
    val place: Unit = Unit
)

data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 0,
    val placeholder: Unit = Unit,
    val canPublishFreeCopy: Boolean = false,
    val editMode: String = ""
)
data class Sizes(
    val type: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)

data class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val userId: Int = 0,
    val text:String = "",
    val date: Int = 0,
    val sizes: Array<Sizes> = emptyArray<Sizes>(),
    val width: Int = 0,
    val height: Int = 0
)

data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val artist: String = "",
    val title: String = "",
    val duration: Int = 0,
    val url:String = "",
    val lyricsId: Int = 0,
    val albumId: Int = 0,
    val genreId: Int = 0,
    val date: Int = 0,
    val noSearch: Int = 0,
    val isHq: Int = 0
)

data class Video(
    val vid: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val description: String = "",
    val duration: Int = 0,
    val link: String = "",
    val image: String = "",
    val imageMedium: String = "",
    val date: Int = 0,
    val player: String = ""
)

data class PhotoPreview(
    val array: Any? = null
)

data class Graffiti(
    val src: String = "",
    val width: Int = 0,
    val height: Int = 0
)

data class AudioMessage(
    val duration: Int = 0,
    val waveform: Array<Int> = emptyArray(),
    val linkOgg: String = "",
    val linkMp3: String = ""
)

data class Preview(
    val photo: PhotoPreview = PhotoPreview(),
    val graffiti: Graffiti = Graffiti(),
    val audioMessage: AudioMessage = AudioMessage()
)

data class File(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val size: Int = 0,
    val ext: String = "",
    val url: String = "",
    val date: Int = 0,
    val type: Int = 0,
    val preview: PhotoPreview = PhotoPreview()
)

data class Link(
    val url: String = "",
    val title: String = "",
    val caption: String = "",
    val description: String = "",
    val photo: Any? = null,
    val product: Any? = null,
    val button: Any? = null,
    val previewPage: String = "",
    val previewUrl: String = ""
)

abstract class Attachments(val type: String)
class PhotoAttachment(val photo: Photo = Photo()) : Attachments( "Photo")
class AudioAttachment(val audio: Audio = Audio()) : Attachments("Audio")
class VideoAttachment(val video: Video = Video()) : Attachments("Video")
class FileAttachment(val file: File = File()) : Attachments("File")
class LinkAttachment(val link: Link = Link()) : Attachments("Link")


data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    var comment: Array<Comment> = emptyArray<Comment>(),
    val copyright: Copyright? = null,
    val likes: Likes? = null,
    val reports: Reposts? = null,
    val views: Views? = null,
    val postType: String = "",
    val postSource: PostSource? = null,
    var attachments: Array<Attachments> = emptyArray<Attachments>(),
    val geo: Geo? = null,
    val signerId: Int = 0,
    val copyHistory: Array<Post> = emptyArray<Post>(),
    val canPin:Boolean = false,
    val canDelete:Boolean = false,
    val canEdit:Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Donut? = Donut().copy(),
    val postponedId: Int = 0
)

data class DonutComment(
    val isDon: Boolean = false,
    val placeholder: String = ""
)

data class Thread(
    val count: Int = 0,
    val items: Array<Comment> = emptyArray<Comment>(),
    val canPost: Boolean = false,
    val showReplyButton: Boolean = true,
    val groupsCanPost: Boolean = false
)

data class Comment (
    val id: Int = 0,
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String,
    val donut: DonutComment = DonutComment(),
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: Attachments? = null,
    val parentsStack: Array<Int> = emptyArray<Int>(),
    val thread: Thread = Thread()
)

class PostNotFoundException(message: String): Exception(message)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 0

    fun add(post: Post): Post {
        nextId ++
        posts += post.copy(id = nextId)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postItem) in posts.withIndex()) {
            if (postItem.id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        nextId = 0
    }

    private fun findPostById(postId: Int): Post? {
        for ((index, postItem) in posts.withIndex()) {
            if (postItem.id == postId) {
                return posts[index]
            }
        }
        return null
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        val post = findPostById(postId)
        post ?: throw PostNotFoundException("No this post!")
        post.comment += comment
        return post.comment.last()
    }
}

fun main(){
    var post = Post()
    post.attachments += PhotoAttachment()
    post.attachments += AudioAttachment()
    post.attachments += VideoAttachment()
    post.attachments += FileAttachment()
    post.attachments += LinkAttachment()

    WallService.add(post)
    try {
        println(WallService.createComment(1,Comment(text = "First comment!")).toString())
    } catch (e: PostNotFoundException) {
        println("Пост не найден")
    }

    return
}
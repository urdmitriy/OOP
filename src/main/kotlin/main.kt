
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
    val comment: Comments? = null,
    val copyright: Copyright? = null,
    val likes: Likes? = null,
    val reports: Reposts? = null,
    val views: Views? = null,
    val postType: String = "",
    val postSource: PostSource? = null,
    val attachments: Array<Unit> = emptyArray<Unit>(),
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

}
fun main(){

}
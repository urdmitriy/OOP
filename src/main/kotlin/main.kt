data class Comments(
    val count: Int,
    val canPost: Boolean,
    val groupsCanPost : Boolean,
    val canClose : Boolean,
    val canOpen : Boolean,
)

data class Copyright(
    val id: Int,
    val link: String,
    val name: String,
    val type: String,
)

data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean,
)

data class Reposts(
    val count: Int,
    val userReposted: Boolean
)

data class Views(
    val count: Int
)

data class PostSource(
    val key: String
)

data class Geo(
    val type: String,
    val coordinates: String,
    val place: Unit
)

data class Donut(
    val isDonut: Boolean,
    val paidDuration: Int,
    val placeholder: Unit,
    val canPublishFreeCopy: Boolean,
    val editMode: String
)

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comment: Comments,
    val copyright: Copyright,
    val likes: Likes,
    val reports: Reposts,
    val views: Views,
    val postType: String,
    val postSource: PostSource,
    val attachments: Array<Unit>,
    val geo: Geo,
    val signerId: Int,
    val copyHistory: Array<Post>,
    val canPin:Boolean,
    val canDelete:Boolean,
    val canEdit:Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Donut,
    val postponedId: Int
)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = nextId)
        nextId ++
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

}
fun main(){

}
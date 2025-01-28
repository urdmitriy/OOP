import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MainKtTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun postAdd() {

        val post = Post(
            id = 0,
            ownerId = 0,
            fromId = 0,
            createdBy = 0,
            date = 0,
            text = "",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comment = Comments(),
            copyright = Copyright(),
            likes = Likes(),
            reports = Reposts(),
            views = Views(),
            postType = "",
            postSource = PostSource(),
            attachments = emptyArray<Unit>(),
            geo = Geo(),
            signerId = 0,
            copyHistory = emptyArray<Post>(),
            canPin = false,
            canDelete = false,
            canEdit = false,
            isPinned = false,
            markedAsAds = false,
            isFavorite = false,
            donut = Donut(),
            postponedId = 0
        )
        assertEquals(WallService.add(post).id, 1)
    }

    @Test
    fun postUpdate() {

        val post = Post(
            id = 0,
            ownerId = 0,
            fromId = 0,
            createdBy = 0,
            date = 0,
            text = "",
            replyOwnerId = 0,
            replyPostId = 0,
            friendsOnly = false,
            comment = Comments(),
            copyright = Copyright(),
            likes = Likes(),
            reports = Reposts(),
            views = Views(),
            postType = "",
            postSource = PostSource(),
            attachments = emptyArray<Unit>(),
            geo = Geo(),
            signerId = 0,
            copyHistory = emptyArray<Post>(),
            canPin = false,
            canDelete = false,
            canEdit = false,
            isPinned = false,
            markedAsAds = false,
            isFavorite = false,
            donut = Donut(),
            postponedId = 0
        )

        WallService.add(post)
        WallService.add(post.copy())

        assertEquals(WallService.update(post.copy(id = 2, ownerId = 10)), true)
        assertEquals(WallService.update(post.copy(id = 3, ownerId = 10)), false)

    }
}
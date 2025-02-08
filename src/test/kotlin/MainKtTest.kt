import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import Photo as Photo

class MainKtTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun postAdd() {

        val post = Post()
        assertEquals(WallService.add(post).id, 1)
    }

    @Test
    fun postUpdateExist() {

        val post = Post()

        WallService.add(post)
        WallService.add(post)

        assertTrue(WallService.update(post.copy(id = 2, ownerId = 10)))
    }

    @Test
    fun postUpdateNoExist() {
        var post = Post()
        WallService.add(post)
        WallService.add(post)
        assertFalse(WallService.update(post.copy(id = 3, ownerId = 10)))
    }

    @Test
    fun addCommentExistPost() {
        val post = Post()
        WallService.add(post)
        WallService.createComment(1, Comment(text = "First comment!"))
        assertEquals(WallService.createComment(1, Comment(text = "First comment!")).id, 0)
    }

    @Test(expected = PostNotFoundException::class)
    fun addCommentNullPost() {
        val post = Post()
        WallService.add(post)
        WallService.createComment(0, Comment(text = "First comment!"))
    }
}
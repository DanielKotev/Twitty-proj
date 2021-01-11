import axios from "axios"

const API_URL = 'http://localhost:8080/comment'

class CommentServices {
    saveComment(content, userId, postId, commentId = null) {
        return axios.post(API_URL + '/save', {
            id: commentId,
            content: content,
            userId: userId,
            postId: postId
        })
    }
    deleteComment(id) {
        return axios.delete(API_URL + '/delete', {
            params: {
                id: id
            }
        })
    }
}

export default new CommentServices()
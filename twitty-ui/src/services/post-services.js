import axios from 'axios'

const API_URL = 'http://localhost:8080/post'

class PostServices {
    getComments(id) {
        return axios.get(API_URL + '/comments',
            {
                params:
                    {
                        id: id
                    }
            })
    }
    savePost(content, userId, id = null) {
        return axios.post(API_URL + "/save",
            {
                id: id,
                content: content,
                userId: userId
            })
    }
    deletePost(id) {
        return axios.delete(API_URL + "/delete", {
            params: {
                id: id
            }
        })
    }
}

export default new PostServices()
import axios from 'axios'

const API_URL = 'http://localhost:8080/user'

class UserService {
    getPostsOfFollowed(id, currentPage, perPage) {
        return axios.get(API_URL + '/followedPosts',
            {
                params:
                    {
                        id: id,
                        currentPage: currentPage,
                        perPage: perPage
                    }
            })
    }
}

export default new UserService()
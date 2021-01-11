import axios from 'axios'

const API_URL = 'http://localhost:8080/user'
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*'

class UserServices {
    login (username, password) {
        return axios.get(API_URL + '/login',
            {
                params: {
                    userName: username,
                    password: password
                }
            })
    }
    saveUser (form) {
        return axios.post(API_URL + '/save',
            {
               username: form.username,
               password: form.password
            })
    }
    getUserById (id) {
        return axios.get(API_URL +'/getById', { params:{id: id}})
    }
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
    getPostsOfUser(id, currentPage, perPage) {
        return axios.get(API_URL + '/posts',
            {
                params:
                    {
                        id: id,
                        currentPage: currentPage,
                        perPage: perPage
                    }
            })
    }
    follow(following, followed) {
        return axios.post(API_URL + '/follow', null,{
            params: {
                followerId: following,
                followedId: followed
            }
        })
    }
    unfollow(following, followed) {
        return axios.post(API_URL + '/unfollow', null,{
            params: {
                followerId: following,
                followedId: followed
            }
        })
    }
    isFollowing(following, followed) {
        return axios.get(API_URL + '/isFollowing',
            {
                params: {
                    followerId: following,
                    followedId: followed
                }
            })
    }
}
export default new UserServices()
import axios from 'axios'

const API_URL = 'http://localhost:8080/user'

class UserServices {
    saveUser (form) {
        return axios.post(API_URL + '/save',
            {
                username: form.username,
               password: form.password
            })
    }

}
export default new UserServices()
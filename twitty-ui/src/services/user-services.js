import axios from 'axios'

const API_URL = 'http://localhost:8080/user'

class UserServices {

    saveUser(username,password) {
        return axios.post(API_URL + '/save',
            {

                    username: username,
                    password: password

            })
    }
}
export default new UserServices()
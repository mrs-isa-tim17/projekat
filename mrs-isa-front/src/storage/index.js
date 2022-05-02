import {createStore} from 'vuex'
import {auth} from './auth.module'

const index = createStore({
    modules: {
        auth,
    },
})

export default index;

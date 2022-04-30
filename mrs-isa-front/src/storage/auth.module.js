import AuthService from '../servieces/AuthServce';
import {error} from "@babel/eslint-parser/lib/convert";

const user = JSON.parse(localStorage.getItem('user'));

const initState = user
    ? {status: {loggedIn: true}, user}
    : {status: {loggedIn: false}, user: null}

export const auth = {
    namespaced: true,
    state: initState,
    actions: {
        login({commit}, user) {
            return AuthService.login(user)
                .then(user =>{
                    commit('loginSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    commit('LoginFailure');
                    return Promise.reject(error);
                });

        },
        logout({commit}){
            AuthService.logout();
            commit('logout');
        },
    },
    mutations: {
        loginSuccess(state, user) {
            state.status.loggedIn = true;
            state.user = user;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.user = null;
        }
    }
}


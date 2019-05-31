import store_state from "@/store/stores_state";

const state = store_state;
let mutations = {};
let actions = {};
for (let k in state) {
    let key = 'set' + k.substring(0, 1).toUpperCase() + k.substring(1);
    mutations[key] = (state, value) => {
        state[k] = value;
    };
    actions[key] = (context, value) => {
        context.commit(key, value);
    };
}

export default {
    state, mutations, actions
}
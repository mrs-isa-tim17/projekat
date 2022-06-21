import { computed } from "vue";

export default function useSubmitButton(user, errors) {

    const isSignupButtonDisabled = computed(() => {
        let disabled = true;
        for (let prop in user) {
            console.log(user[prop]);
            if (!user[prop] || errors[prop]) {
                disabled = true;
                break;
            }
            disabled = false;
        }
        return disabled;
    });

    return { isSignupButtonDisabled }
}
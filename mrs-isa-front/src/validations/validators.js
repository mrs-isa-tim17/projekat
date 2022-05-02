export default function useValidators() {

    const isEmpty = (fieldName, fieldValue) => {
        return !fieldValue ? "Polje je obavezno." : "";
    }
    const isEqual = (password, againPassword) => {
        return !password===againPassword? "Lozinke se ne poklapaju":"";
    }

    const minLength = (fieldName, fieldValue, min) => {
        return fieldValue.length < min ? `Mora biti minimum ${min} karaktera` : "";
    }

    const isEmail = (fieldName, fieldValue) => {
        let re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return !re.test(fieldValue) ? "Nije ispravan email." : "";
    }

    const isNum = (fieldName, fieldValue) => {
        let isNum = /^\d+$/.test(fieldValue);
        return !isNum ? "Polje sadrži samo brojeve." : "";
    }

    return { isEqual,isEmpty, minLength, isEmail, isNum }
}
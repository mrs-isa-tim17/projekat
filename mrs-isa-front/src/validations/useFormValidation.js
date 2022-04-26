import { reactive } from "@vue/reactivity";
import useValidators from '@/validations/validators'


const errors = reactive({});


export default function useFormValidation() {


    const { isEmpty, minLength, isEmail, isNum } = useValidators();

    const validateNameField = (fieldName, fieldValue) => {
        errors[fieldName] = !fieldValue ? isEmpty(fieldName, fieldValue) : minLength(fieldName, fieldValue, 4)
    }

    const validateSurnameField = (fieldSurname, fieldValue) => {
        errors[fieldSurname] = !fieldValue ? isEmpty(fieldSurname, fieldValue) : minLength(fieldSurname, fieldValue, 4)
    }

    const validateAddressField = (fieldAddress, fieldValue) => {
        errors[fieldAddress] = !fieldValue ? isEmpty(fieldAddress, fieldValue) : minLength(fieldAddress, fieldValue, 4)
    }

    const validateCityField = (fieldCity, fieldValue) => {
        errors[fieldCity] = !fieldValue ? isEmpty(fieldCity, fieldValue) : minLength(fieldCity, fieldValue, 4)
    }

    const validateCountryField = (fieldCountry, fieldValue) => {
        errors[fieldCountry] = !fieldValue ? isEmpty(fieldCountry, fieldValue) : minLength(fieldCountry, fieldValue, 4)
    }

    const validateEmailField = (fieldName, fieldValue) => {
        errors[fieldName] = !fieldValue ? isEmpty(fieldName, fieldValue) : isEmail(fieldName, fieldValue)
    }

    const validatePhoneField = (fieldName, fieldValue) => {
        errors[fieldName] = !fieldValue ? isEmpty(fieldName, fieldValue) : isNum(fieldName, fieldValue)
    }

    const validatePasswordField = (fieldName, fieldValue) => {
        errors[fieldName] = !fieldValue ? isEmpty(fieldName, fieldValue) : minLength(fieldName, fieldValue, 8)
    }

    const validatePasswordAgainField = (fieldName, fieldValue) => {
        errors[fieldName] = !fieldValue ? isEmpty(fieldName, fieldValue) : minLength(fieldName, fieldValue, 8)
    }
   /* const validatePasswordConfirm = (password, passwordAgain) =>{
        errors[passwordAgain] = isEqual(password,passwordAgain):minLength(fieldName, fieldValue, 8);
    }*/

    return { errors, validatePasswordAgainField, validateAddressField, validateCityField, validateCountryField, validateNameField, validateSurnameField, validateEmailField, validatePhoneField, validatePasswordField }
}
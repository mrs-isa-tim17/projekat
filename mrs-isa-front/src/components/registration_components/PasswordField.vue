<template>
  <div class="field">
    <div class="ui left icon input big">
      <i class="fa fa-key icon"></i>
      <input
          type="password"
          placeholder="Lozinka"
          autocomplete="off"
          v-model="input"
          @input="$emit('update:modelValue', $event.target.value)"
          @keyup="validateInput"
          @blur="validateInput"
      />
    </div>
    <div class="ui basic label pointing red" v-if="errors.password" style="color:red;font-size:15px;">
      {{ errors.password }}
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import useFormValidation from "@/validations/useFormValidation";
export default {
  setup() {
    let input = ref("");
    const { validatePasswordField, errors } = useFormValidation();
    const validateInput = () => {
      validatePasswordField("password", input.value);
    };
    return { input, errors, validateInput };
  },
};
</script>

<style scoped>
input{
  width: 300px;
}
i{
  padding: 7px;
  background: #5085A5;
  color: white;
  min-width: 30px;
  text-align: center;
}
</style>
<template>
  <div class="field">
    <div class="ui left icon input big">
      <i class="fa fa-user icon"></i>
      <input
          type="text"
          placeholder="Ime"
          autocomplete="off"
          @input="$emit('update:modelValue', $event.target.value)"
          v-model="input"
          @keyup="validateInput"
          @blur="validateInput"
      />
    </div>
    <p class="ui basic label pointing red" v-if="errors.name" style="color:red;font-size:15px;">
      {{ errors.name }}
    </p>
  </div>
</template>

<script>
import { ref } from "vue";
import useFormValidation from "@/validations/useFormValidation";
export default {

  setup() {
    let input = ref("");
    const { validateNameField, errors } = useFormValidation();
    const validateInput = () => {
      validateNameField("name", input.value);
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
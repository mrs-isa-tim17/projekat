<template>
  <div class="field">
    <div class="ui left icon input big">
      <i class="fa fa-building-o"></i>
      <input
          type="text"
          placeholder="Grad"
          autocomplete="off"
          @input="$emit('update:modelValue', $event.target.value)"
          v-model="input"
          @keyup="validateInput"
          @blur="validateInput"
      />
    </div>
    <div class="ui basic label pointing red" v-if="errors.city" style="color:red;font-size:15px;">
      {{ errors.city }}
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import useFormValidation from "@/validations/useFormValidation";
export default {
  setup() {
    let input = ref("");
    const { validateCityField, errors } = useFormValidation();
    const validateInput = () => {
      validateCityField("city", input.value);
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
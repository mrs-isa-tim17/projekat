<template>
  <div class="field">
    <div class="ui left icon input big">
      <i class="fa fa-map-marker"></i>
      <input
          type="text"
          placeholder="Adresa"
          autocomplete="off"
          @input="$emit('update:modelValue', $event.target.value)"
          v-model="input"
          @keyup="validateInput"
          @blur="validateInput"
      />
    </div>
    <div class="ui basic label pointing red" v-if="errors.address" style="color:red;font-size:15px;">
      {{ errors.address }}
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import useFormValidation from "@/validations/useFormValidation";
export default {
  setup() {
    let input = ref("");
    const { validateAddressField, errors } = useFormValidation();
    const validateInput = () => {
      validateAddressField("address", input.value);
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
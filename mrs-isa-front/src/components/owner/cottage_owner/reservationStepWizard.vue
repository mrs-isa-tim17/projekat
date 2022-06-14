<template>
  <form-wizard>
    <tab-content title="About You" :selected="true">
      This is content of Tab 1
    </tab-content>
    <tab-content title="About your Company">
      <p>Can contains</p>
      <p>Multiple Elements</p>
    </tab-content>
    <tab-content title="Finishing Up">
      <p>Or an image .. or any thing</p>

    </tab-content>
  </form-wizard>
</template>

<script>
import {FormWizard, TabContent} from 'vue-step-wizard';
import 'vue-step-wizard/dist/vue-step-wizard.css';

export default {
  name: "reservationStepWizard",
  components:{FormWizard,TabContent}
}
</script>

<style scoped>

</style>
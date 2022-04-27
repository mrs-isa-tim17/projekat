<template>
  <div v-show="show" class="overlay">


    <div class="dialog">

      <div class="dialog__content">
        <h2 class="dialog__title" v-text="title"></h2>
        <label>Trenutna lozinka</label><br>
        <input type="password"><br>
        <label>Nova lozinka</label><br>
        <input type="password"><br>
        <label>Ponovi novu lozinku</label><br>
        <input type="password"><br>
      </div>

      <hr />

      <div class="dialog__footer">
        <button @click="cancel" class="dialog__cancel">Otkaži</button>
        <button @click="confirm" class="dialog__confirm">Izmeni</button>
      </div>

    </div>

  </div>
</template>

<script>
export default {
  props: ['show', 'title', 'description', 'cancel', 'confirm']
}
</script>
<style>
.overlay {
  --tw-bg-opacity: 1;
  background-color: rgba(0, 0, 0, var(--tw-bg-opacity));
  --tw-bg-opacity: 0.5;
  height: 100%;
  position: fixed;
  top: 0px;
  right: 0px;
  bottom: 0px;
  left: 0px;
  width: 100%;
  z-index: 10;
}
.dialog {
  --tw-bg-opacity: 1;
  background-color: rgba(255, 255, 255, var(--tw-bg-opacity));
  border-radius: 0.75rem;
  margin-left: auto;
  margin-right: auto;
  margin-top: 2.5rem;
  max-width: 100%;
  width: 24rem;
}
.dialog__content {
  padding-left: 0.75rem;
  padding-right: 0.75rem;
  padding-top: 1rem;
  padding-bottom: 1rem;
}
.dialog__title {
  font-weight: 500;
  font-size: 1.125rem;
  line-height: 1.75rem;
  margin-bottom: 0.5rem;
  --tw-text-opacity: 1;
  color: rgba(17, 24, 39, var(--tw-text-opacity));
}

.dialog__footer {
  display: flex;
  justify-content: flex-end;
  padding-top: 1rem;
  padding-bottom: 1rem;
}
.dialog__cancel {
  border-radius: 0.75rem;
  font-weight: 500;
  margin-right: 1rem;
}
.dialog__cancel:focus{
  outline: 2px solid transparent;
  outline-offset: 2px;
}
.dialog__cancel{
  padding-top: 0.75rem;
  padding-bottom: 0.75rem;
  padding-left: 1rem;
  padding-right: 1rem;
}
.dialog__cancel:focus{


  --tw-ring-opacity: 1;
  --tw-ring-color: rgba(75, 85, 99, var(--tw-ring-opacity));
  --tw-ring-opacity: 0.5;
}
.dialog__cancel{
  --tw-text-opacity: 1;
  color: rgba(17, 24, 39, var(--tw-text-opacity));
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.dialog__cancel:hover {
  --tw-text-opacity: 1;
  color: rgba(55, 65, 81, var(--tw-text-opacity));
}
.dialog__confirm {
  --tw-bg-opacity: 1;
  background-color: rgba(254, 226, 226, var(--tw-bg-opacity));
  border-radius: 0.75rem;
  font-weight: 500;
  margin-right: 1rem;
}
.dialog__confirm:focus{
  outline: 2px solid transparent;
  outline-offset: 2px;
}
.dialog__confirm{
  padding-top: 0.75rem;
  padding-bottom: 0.75rem;
  padding-left: 1rem;
  padding-right: 1rem;
}
.dialog__confirm:focus{


  --tw-ring-opacity: 1;
  --tw-ring-color: rgba(220, 38, 38, var(--tw-ring-opacity));
  --tw-ring-opacity: 0.5;
}
.dialog__confirm{
  --tw-text-opacity: 1;
  color: rgba(220, 38, 38, var(--tw-text-opacity));
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.dialog__confirm:hover {
  --tw-bg-opacity: 1;
  background-color: rgba(252, 165, 165, var(--tw-bg-opacity));
  --tw-text-opacity: 1;
  color: rgba(153, 27, 27, var(--tw-text-opacity));
}
</style>
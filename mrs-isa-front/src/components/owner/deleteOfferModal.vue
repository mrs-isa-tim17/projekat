<template>
  <button id="update" style= "background-color: #687864;font-weight: bold;" type="button" @click="openForm" class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target=modalId>
    <i class="fa fa-delete icon"></i> {{button_name}}
  </button>


  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="deleteCottageModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" style="background-color:#31708E;color:whitesmoke;">
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal">{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          Da li ste sigurni da želite da obrišete Vašu ponudu <b>{{this.offer.name}}</b>?
          <div class="modal-footer">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="deleteOffer" class="btn btn-primary">Potvrdi</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import CottageService from "@/servieces/cottage_owner/CottageService";
import swal from "sweetalert2";
import ShipService from "@/servieces/ship_owner/ShipService";

export default {
  name: "deleteCottageModal",
  props: ["offer","index", "header","button_name","type"],
  created:
      function () {
        console.log(this.index);
        console.log(this.offer);
        this.modalId = "#"+this.index;
      },
  methods:{
    openForm(){
      console.log("forma");
      var modalToggle = document.getElementById(this.index);

      ///myModal.show(modalToggle)


      console.log(this.offer);
      $('#'+this.index).show(modalToggle);
      $('#'+this.index).focus(modalToggle);
    },
    deleteOffer(){
      console.log(this.type);
      if(this.type == "cottage"){
      CottageService.deleteCottage(this.offer.id).then((response) =>
      {
        console.log(this.offer.id);
        if(response.data){
          this.accept=true;
          this.$emit("accept",this.accept);
          swal.fire({title:'Obrisana vikendica!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          this.$router.push('/cottageOwner/home');
        }
        else{
          swal.fire({title:'Ne možete obrisati vikendicu, postoje nerealizovane rezervacije!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }
      })
      const modal = document.getElementById(this.index);
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');

      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';
    }
      else if(this.type =="ship"){
        console.log(this.offer.id);
        ShipService.deleteShip(this.offer.id).then((response) =>
            {

              console.log(this.offer.id);
              console.log(response.data);
              if(response.data){
                this.accept=true;
                this.$emit("accept",this.accept);
                console.log(this.offer.id);
                swal.fire({title:'Obrisan brod!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
                this.$router.push('/shipOwner/home');
              }
              else{
                swal.fire({title:'Ne možete obrisati brod, postoje nerealizovane rezervacije!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
              }

            }
        )
        const modal = document.getElementById(this.index);
        modal.classList.remove('show');
        modal.setAttribute('aria-hidden', 'true');
        modal.setAttribute('style', 'display: none');
        const modalBackdrops = document.getElementsByClassName('modal-backdrop');

        // remove opened modal backdrop
        document.body.removeChild(modalBackdrops[0]);
        document.body.style.overflow = 'auto';
      }
    }
  },
  data(){
    return{
      modalId:"",
      accept:false,

    }
  }
}
</script>

<style scoped>

</style>
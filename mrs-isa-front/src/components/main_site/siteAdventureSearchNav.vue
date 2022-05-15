<template>
  <div style="margin-left: 20px; background-color: #CDCDCD; padding-right: 270px; padding-left: 10px;padding-bottom: 10px; padding-top: 10px;">
    <ul class="nav flex-column" style="width: 240px;">
      <li class="nav-item pt-2" style="width: 140px;">
        <div class="row">
          <div class="col-8">

            Od datuma
          </div>
          <div class="col-4">
            <input type="date" id="start" name="trip-start"
                   value="2018-07-22"
                   min="2018-01-01" max="2030-12-31">
          </div>
        </div>
      </li>
      <li class="nav-item  pb-2" style="width: 140px;">
        <div class="row">
          <div class="col-8">
            Do datuma
          </div>
          <div class="col-4">
            <input type="date" id="start" name="trip-start"
                   value="2018-07-22"
                   min="2018-01-01" max="2030-12-31">
          </div>
        </div>
      </li>
      <li class="nav-item  py-2" style="display: inline; width: 250px;" >
        <div class="row">
          <div class="col-3">
            Ocena
          </div>
          <div class="col-3">
            <select id="ratingBy" class="form-select" style="width: 70px"  aria-label="Default select1">
              <option selected=""></option>
              <option value="1">==</option>
              <option value="2">&#60;=</option>
              <option value="3">&#60;</option>
              <option value="4">&#62;=</option>
              <option value="5">&#62;</option>
            </select>
          </div>
          <div class="col-4">
            <select id="rating" class="form-select" style="width: 120px" aria-label="Default select2">
              <option selected=""></option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </div>
        </div>

      </li>
      <li class="nav-item  py-2" style="display: inline; width: 250px;" >
        <div class="row">
          <div class="col-3">
            Kapacitet
          </div>
          <div class="col-3">

            <select id="ratingBy" class="form-select" style="width: 70px;"  aria-label="Default select1">
              <option selected=""></option>
              <option value="1">==</option>
              <option value="2">&#60;=</option>
              <option value="3">&#60;</option>
              <option value="4">&#62;=</option>
              <option value="5">&#62;</option>
            </select>
          </div>
          <div class="col-4">

            <input type="text" style="width: 120px;  height: 38px;">
          </div>
        </div>
      </li>
      <li class="nav-item py-2" style="display: inline; width: 250px;" >

        <div class="row">
          <div class="col-3">
            Cena
          </div>
          <div class="col-3">

            <select id="ratingBy" class="form-select" style="width: 70px;"  aria-label="Default select1">
              <option selected=""></option>
              <option value="1">==</option>
              <option value="2">&#60;=</option>
              <option value="3">&#60;</option>
              <option value="4">&#62;=</option>
              <option value="5">&#62;</option>
            </select>
          </div>
          <div class="col-4">

            <input type="text" style="width: 120px;  height: 38px;">
          </div>
        </div>
      </li>
      <li class="nav-item py-2" style="display: inline; width: 120px;" >
        Adresa
        <open-maps :lon="lon" :lat="lat" style="width: 250px;"></open-maps>
      </li>

      <li class="nav-item pt-3">
        <button style="margin-left: 80px;">Pretraži</button>
      </li>
    </ul>
  </div>

</template>

<script>
import OpenMaps from "@/components/VueMaps";
export default {
  name: "siteAdventureSearchNav",
  components: {
    OpenMaps,

  },
  data(){
    return{
      lon: 2,
      lat: 9
    }
  }
}
</script>

<style scoped>

</style>

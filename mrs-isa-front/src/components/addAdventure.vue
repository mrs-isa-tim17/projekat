<template>
  <div class="container">
    <instructor-header></instructor-header>
    <div class="row">
      <div class="col-4  d-flex justify-content-center"
           style="border-style: solid; border-width: medium; background-color: #B0C4DE;">
        <div>
          <inputField :label="adventureNameLabel" info=""></inputField>
          <inputField :label="cityLabel" info=""></inputField>
          <inputField :label="streetLabel" info=""></inputField>
          <inputField :label="numberLabel" info=""></inputField>
          <inputField :label="countryLabel" info=""></inputField>

          <inputField :label="priceLabel" info=""></inputField>
          <div class="p-2">
            <label>{{ descriptionLabel }}</label>
            <br>
            <textarea type="text" ref="input" :value=info size="25">
            </textarea>
          </div>

        </div>
      </div>
      <div class="col-4 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #B0C4DE;">
        <div>


          <div class="p-2">
            <div class="mb-3">
              <label for="formFile" class="form-label">{{ imgLabel }}</label>
              <input class="form-control" type="file" id="formFile">
            </div>
          </div>

          <inputField :label="capacityLabel" info=""></inputField>

          <div class="p-2">
            <label>{{ biographyLabel }}</label>
            <br>
            <textarea type="text" ref="input" :value=info size="25">
            </textarea>
          </div>
          <br>
          <label>{{ ruleLabel }}</label>
          <br>
          <div class="p-2" style="border-style: solid; border-width: medium;">
            <div  align="left" class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
              <label class="form-check-label" for="flexCheckDefault">
                {{ rule1 }}
              </label>
            </div>
            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
              <label class="form-check-label" for="flexCheckDefault">
                {{ rule2 }}
              </label>
            </div>

            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
              <label class="form-check-label" for="flexCheckDefault">
                {{ rule3 }}
              </label>
            </div>

            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
              <label class="form-check-label" for="flexCheckDefault">
                {{ rule4 }}
              </label>
            </div>

            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
              <label class="form-check-label" for="flexCheckDefault">
                {{ rule5 }}
              </label>
            </div>
          </div>

          <br>
          <label>{{ additionalEquipmentLabel }}</label>
          <br>
          <div class= "p-2" style="border-style: solid; border-width: medium;">
            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
              <label class="form-check-label" for="flexCheckDefault">
                {{ additionalEquipment1 }}
              </label>
            </div>
            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
              <label class="form-check-label" for="flexCheckDefault">
                {{ additionalEquipment2 }}
              </label>
            </div>
          </div>

        </div>


      </div>


      <div class="col-4 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #B0C4DE;">
        <div>
          <div class="p-2">
            <br>
            <label>{{ fishingEquipmentLabel }}</label>
            <br>
            <div  class="p-2" style="border-style: solid; border-width: medium;">
              <div align="left" class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                  {{ fishingEquipment1 }}
                </label>
              </div>
              <div align="left" class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                  {{ fishingEquipment2 }}
                </label>
              </div>
              <div align="left" class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                  {{ fishingEquipment3 }}
                </label>
              </div>
              <div align="left" class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                  {{ fishingEquipment4 }}
                </label>
              </div>
            </div>

          </div>
          <br>
          <label> {{ cancelConditionsLabel }} </label>
          <br>

          <div class="p-2" style="border-style: solid; border-width: medium;">

            <p>Za odustanak od rezervacije u roku <b>{{ cancelRule1 }}</b> dana plaća se <input type="text" size="1"/> %
              ukupnog iznosa</p>
            <p>Za odustanak od rezervacije u roku <b>{{ cancelRule2 }}</b> dana plaća se <input type="text" size="1"/> %
              ukupnog iznosa</p>
            <p>Za odustanak od rezervacije u roku <b>{{ cancelRule3 }}</b> dana plaća se <input type="text" size="1"/> %
              ukupnog iznosa</p>
            <p>Za odustanak od rezervacije u roku <b>{{ cancelRule4 }}</b> dana plaća se <input type="text" size="1"/> %
              ukupnog iznosa</p>

          </div>


        </div>
      </div>
    </div>
    <div align="right">
      <botton  class= "col-1 btn btn-primary  btn-md  me-md-2"  >Dodaj</botton>
      <botton  class="col-1 btn btn-secondary  btn-md  me-md-2" >Odustani</botton>
    </div>
  </div>

</template>

<script>

import InputField from "@/components/inputField";
import InstructorHeader from "@/components/insrtuctorHeader"

export default {
  name: "addAdventure",
  components: {
    InputField,
    InstructorHeader,
  },


  data() {
    return {
      message: "Obavezno polje",
      errorMessage: "Obavezno polje",
      successMessage: "Uspešno sačuvana avantura",

      adventureNameLabel: "Naziv avanture*",
      cityLabel: "Grad*",
      streetLabel: "Adresa*",
      numberLabel: "Broj*",
      countryLabel: "Država*",

      descriptionLabel: "Opis avanture*",
      imgLabel: "Fotografije",
      priceLabel: "Cena*",
      capacityLabel: "Kapacitet*",
      biographyLabel: "Biografija instruktora*",

      ruleLabel: "Pravila ponašanja*",
      fishingEquipmentLabel: "Pecaroška oprema*",
      additionalEquipmentLabel: "Dodatna oprema*",
      cancelConditionsLabel: "Uslovi otkaza*",


      rule1: "dozvoljeno pecanje",
      rule2: "zabranjeno pecanje",
      rule3: "dozvoljeno kupanje",
      rule4: "zabranjeno kupanje",
      rule5: "volimo ljubimce",

      fishingEquipment1: "štapovi",
      fishingEquipment2: "mamac",
      fishingEquipment3: "udice",
      fishingEquipment4: "čamac",

      additionalEquipment1: "kabanica",
      additionalEquipment2: "baterijska lampa",

      cancelRule1: "od manje 5",
      cancelRule2: "od više 5",
      cancelRule3: "od više 10",
      cancelRule4: "od više 15",
      cancelRule5: "od više 20",


    }
  }
}
</script>


<style scoped>

</style>
<template>
  <div style="overflow-scrolling: auto">
    <client-header></client-header>

    <div>
      <client-subscribed-entities-list @rerender="forceRerender" :key="myKey" :offers="entitiesSubscribedFor"> </client-subscribed-entities-list>
    </div>


  </div>
</template>

<script>
import ClientSubscribedEntitiesList from "@/components/client/clientSubscribedEntitiesList";
import clientServce from "@/servieces/ClientServce";
import ClientHeader from "@/components/client/clientHeader";
export default {
  name: "clientSubscribedEntities",
  components: {ClientHeader, ClientSubscribedEntitiesList},
  created: function() {
    this.getEntitiesClientSubscribedFor();
  },
  methods:{
    getEntitiesClientSubscribedFor(){
      clientServce.getEntitiesSubscribedFor(JSON.parse(localStorage.user).id)
          .then(response =>{
            this.entitiesSubscribedFor = response.data;
            this.myKey++;
          })
    }
  },
  data(){
    return{
      entitiesSubscribedFor: []
    }
  }
}
</script>

<style scoped>

</style>

<template>
  <ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="height:400px"
          :moveTolerance="4" @click="registerClick" ref="map">

    <ol-view ref="view" :center="center" :rotation="rotation" :zoom="zoom"
             :projection="projection" @zoomChanged="zoomChanged"
             @centerChanged="centerChanged"/>

    <ol-tile-layer>
      <ol-source-osm />
    </ol-tile-layer>


    <ol-zoom-control />
    <ol-zoomslider-control />
    <ol-attribution-control/>



    <ol-vector-layer>
      <ol-source-vector>
        <ol-feature>
          <ol-geom-point :coordinates="coordinate"></ol-geom-point>
          <ol-style>
              <ol-style-icon :src="pointer" :scale="0.1"></ol-style-icon>
          </ol-style>
        </ol-feature>

      </ol-source-vector>

    </ol-vector-layer>

  </ol-map>
</template>

<script>
import 'vue3-openlayers';
import {inject, ref} from 'vue'
//https://vue3openlayers.netlify.app/
export default {
  name: "open-maps",
  props: ['lon', 'lat'],
  methods:{
    registerClick(event) {
      let lonlat = this.map.getCoordinateFromPixel(event.pixel);
      this.center = lonlat;
      this.coordinate = lonlat;
      this.$emit('coordinate-changed', lonlat[0], lonlat[1]);
    },
    zoomChanged(currentZoom) {
      this.currentZoom = currentZoom;
    },
    centerChanged(center) {
      this.currentCenter = center;
    },
  },
  mounted() {
    this.center = [this.lon, this.lat];
    this.coordinate = [this.lon, this.lat];
  },
  setup() {
    const center = ref([0,0]);
    const projection = ref('EPSG:4326');
    const zoom = ref(8);
    const rotation = ref(0);
    const view = ref(null);
    const map = ref(null);
    const radius = ref(10);
    const strokeWidth = ref(10)
    const strokeColor = ref('red')
    const fillColor = ref('white')
    const coordinate = ref([0,0])

    const pointer = require("@/assets/pin.png");

    const url = ref("https://openlayers.org/en/latest/examples/data/geojson/countries.geojson")

    const format = inject('ol-format');
    console.log(format)
    const geoJson = new format.GeoJSON();

    return {
      center,
      projection,
      zoom,
      rotation,
      map,
      view,
      pointer,
      url,
      geoJson,
      radius,
      strokeWidth,
      strokeColor,
      fillColor,
      coordinate

    }
  },
  data() {
    return {
      currentCenter: this.center,
      currentZoom: this.zoom,
    }
  },
}
</script>

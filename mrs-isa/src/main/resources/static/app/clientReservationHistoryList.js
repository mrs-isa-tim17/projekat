Vue.component('cottage-history-list', {
    props: ['from', 'cottagesHistory', 'numToDisplay'],
    template: `
                <div class="container" >
                    <div class="p-2" v-for="(item, index) in cottagesHistory"  :key="item.name"
                            v-if="checkIfNeedsToBeDisplayed(index, from, numToDisplay)">
                        <history-element :cottage="item" :key="myKey" @rerender="forceRerendering(item)"> </history-element>
                    </div>
                </div>
    `,
    methods : {
        checkIfNeedsToBeDisplayed(index, from, numToDisplay){
            untilElement = parseInt(numToDisplay) + parseInt(from);
            if (index >= from && index < untilElement){
                return true;
            }
            return false;
        },
        forceRerendering(offer){
            this.myKey += 1;
        }
    },
    data() {
        return {
            myKey: 1
        }
    }
});


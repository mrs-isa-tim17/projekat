Vue.component('client-option', {
    props: ['option_link', 'image_path', 'text'],
    template: `
                <div class="col-3 px-5" >
                    <a :href=option_link>
                        <div class="card-lg">
                            <div class="card-body">
                                <img :src=image_path height="150">
                            </div>
                            <div class="card-footer text-center">
                                {{text}}
                            </div>
                        </div>
                    </a>
                </div>
    `,
    methods : {

    },
    data() {
        return {
        }
    }
});


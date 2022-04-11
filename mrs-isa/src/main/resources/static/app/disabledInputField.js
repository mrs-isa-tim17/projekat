Vue.component('disabled-input-field', {
	template: `
    <div class="p-2">
        <label >{{label}}</label>
		<br>
		<input type="text" ref="input" :value=info size="25" disabled>
    </div>
	`,
    props: ["label", "info"],
    data() {
        return{
            code: ""
        }
    }
});

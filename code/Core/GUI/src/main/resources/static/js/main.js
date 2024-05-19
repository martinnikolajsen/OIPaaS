// Vue.js app instance
new Vue({
    el: '#app',
    data() {
        return {
          resourceIdLoaded: null
        };
    },
    methods: {
        getResourceIdLoaded() {
            return this.resourceIdLoaded;
        },
        setResourceIdLoaded(value) {
            this.resourceIdLoaded = value;
        }
    },
    provide() {
        return {
          getResourceIdLoaded: this.getResourceIdLoaded,
          setResourceIdLoaded: this.setResourceIdLoaded,
          resourceIdLoaded: this.resourceIdLoaded
        };
    },
});

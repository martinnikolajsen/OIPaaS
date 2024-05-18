// Vue.js app instance
new Vue({
    el: '#app',
    data() {
        return {
          resourceIdLoaded: null
        };
    },
    methods: {
        setResourceIdLoaded(inp) {
          this.resourceIdLoaded = inp;
        }
      },
    provide() {
        return {
          resourceIdLoaded: this.resourceIdLoaded,
          setResourceIdLoaded: this.setResourceIdLoaded
        };
    },
});

Vue.component('page-footer', {
  template: `
    <div class='page-footer'>{{version}}</div>
  `,
  data() {
    return {
      version: 'OIPaaS - v1.0.0'
    };
  },
  mounted() {
    // Set the page title when the component is mounted
    document.title = this.version;
  },
});
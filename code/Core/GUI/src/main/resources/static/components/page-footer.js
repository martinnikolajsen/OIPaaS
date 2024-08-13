// page-footer.js

const {defineComponent, onMounted, ref } = Vue//: from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js';

export default defineComponent({
  name: 'PageFooter',
  setup() {
    // Define reactive state
    const version = ref('OIPaaS - v1.0.0');

    // Use lifecycle hook onMounted to run code after component is mounted
    onMounted(() => {
      document.title = version.value;
    });

    // Return the reactive state to the template
    return {
      version
    };
  },
  template: `
    <div class='page-footer'>{{ version }}</div>
  `
});

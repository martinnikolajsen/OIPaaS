// App.js

import { defineComponent } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'; // Import Vue from CDN

// Define and export your root component
export default defineComponent({
  name: 'App',
  template: `
    <div>
      <PageMenu />
      <PageContent />
      <PageFooter />
    </div>
  `
});

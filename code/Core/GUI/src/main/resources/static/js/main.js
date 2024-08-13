// Access global Vue and VueRouter from the CDN
const { createApp, provide, inject, ref } = window.Vue; // Vue is globally available
const { createRouter, createWebHistory } = window.VueRouter; // VueRouter is globally available

// Import your components
import PageMenu from '../components/page-menu.js'; // Adjust the path as needed
import PageFooter from '../components/page-footer.js'; // Adjust the path as needed
import router from './router.js'; // Adjust the path as needed

// Import the main App component
import App from './App.js'; // Adjust the path if needed



// Create the Vue app
const app = createApp({
  setup() {
    // Create a reactive state for resourceIdLoaded
    const resourceIdLoaded = ref(null);

    // Define methods for getting and setting the resourceIdLoaded
    const getResourceIdLoaded = () => resourceIdLoaded.value;
    const setResourceIdLoaded = (value) => {
      resourceIdLoaded.value = value;
    };

    // Provide these methods and state to the entire app
    provide('getResourceIdLoaded', getResourceIdLoaded);
    provide('setResourceIdLoaded', setResourceIdLoaded);
    provide('resourceIdLoaded', resourceIdLoaded);

    return {
      resourceIdLoaded,
      getResourceIdLoaded,
      setResourceIdLoaded
    };
  }
});

// Register global components
app.component('PageMenu', PageMenu);
app.component('PageFooter', PageFooter);

// Use the router in the Vue app
app.use(router);

// Use the App component as the root component
app.component('App', App);

// Mount the app
app.mount('#app');

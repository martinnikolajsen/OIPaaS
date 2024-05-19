// top-menu.js

// Access data-argument value
const activePageLoaded = window.activePage ?? '/';

// Define Vue component
Vue.component('page-menu', {
  template: `
    <nav class="page-menu">
        <div v-for="(item, index) in menuItems" :key="index">
          <a :href="item.url" :class="{ 'active': item.url == activePageLoaded || (item.url.startsWith(activePageLoaded) && false) }"><span v-html="item.label"></span></a>
        </div>
    </nav>
  `,
  inject: ['getResourceIdLoaded', 'setResourceIdLoaded', 'resourceIdLoaded'],
  data() {
    return {
      menuItems: [
        { label: '<i class="fa fa-home"></i>', url: '/' },
        { label: 'Workspaces', url: '/workspaces.html?resourceId=' + this.getResourceIdLoaded() },
        { label: 'Users', url: '/users.html?resourceId=' + this.getResourceIdLoaded() },
        { label: 'Groups', url: '/groups.html?resourceId=' + this.getResourceIdLoaded() },
        { label: 'Connections', url: '/connections.html?resourceId=' + this.getResourceIdLoaded() },
        { label: 'Logging', url: '/logging.html?resourceId=' + this.getResourceIdLoaded() },
        { label: 'Help', url: '/help.html?resourceId=' + this.getResourceIdLoaded() }
        // Add more menu items as needed
      ],
      activePageLoaded: window.location.pathname // Set activePage data property based on the passed argument
    };
  },
    computed: {
      resourceIdLoaded() {
        return this.getResourceIdLoaded();
      }
    },
  watch: {
    resourceIdLoaded(newVal) {
      console.log('resourceIdLoaded updated:', newVal);
    }
  }
});

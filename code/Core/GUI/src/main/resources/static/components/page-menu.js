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
  data() {
    return {
      menuItems: [
        { label: '<i class="fa fa-home"></i>', url: '/' },
        { label: 'Workspaces', url: '/workspaces.html' },
        { label: 'Users', url: '/users.html' },
        { label: 'Groups', url: '/groups.html' },
        { label: 'Connections', url: '/connections.html' },
        { label: 'Logging', url: '/logging.html' },
        { label: 'Help', url: '/help.html' }
        // Add more menu items as needed
      ],
      activePageLoaded: window.location.pathname // Set activePage data property based on the passed argument
    };
  }
});

// top-menu.js

const { defineComponent, inject, ref, watch, computed } = Vue;
const { useRoute } = VueRouter;

// Define Vue component
const PageMenu = defineComponent({
  name: 'PageMenu',
  template: `
    <nav class="page-menu">
        <div v-for="(item, index) in menuItems" :key="index">
            <router-link
              :to="item.url"
              :class="{ 'active': isActive(item.url) }">
              <span v-html="item.label"></span>
            </router-link>
        </div>
    </nav>
  `,
  setup() {
    // Get the current route
    const route = useRoute();

    // Use inject to get dependencies
    const getResourceIdLoaded = inject('getResourceIdLoaded');
    const setResourceIdLoaded = inject('setResourceIdLoaded');

    // Define menu items with a reactive reference
    const menuItems = ref([
      { label: '<i class="fa fa-home"></i>', url: '/' },
      { label: 'Workspaces', url: `/workspaces.html?resourceId=${getResourceIdLoaded()}` },
      { label: 'Users', url: `/users.html` },
      { label: 'Groups', url: `/groups.html` },
      { label: 'Connections', url: `/connections.html` },
      { label: 'Logging', url: `/logging.html` },
      { label: 'Help', url: `/help.html` }
      // Add more menu items as needed
    ]);

    // Computed property to determine if the current route matches the item URL
    const isActive = (url) => {
        console.log("url ", url, route.path, (url.startsWith(route.path) && url !== "/"));
      return route.path === url || (url.startsWith(route.path) && route.path !== "/"); // Adjust as needed
    };

    return {
      menuItems,
      isActive // Expose isActive to the template
    };
  }
});

export default PageMenu;

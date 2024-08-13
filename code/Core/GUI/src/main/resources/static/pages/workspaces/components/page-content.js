// page-content.js

const { defineComponent, ref, onMounted, inject, watch } = Vue;//from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'; // Import Vue from CDN

export default defineComponent({
  name: 'PageContent',
  template: `
    <div class='page-content'>
        <div v-if="error" class="error">
            {{errorMessage}}
        </div>
        <div v-if="loading" class="loader">
          Loading...
        </div>
        <div v-else>
            <div class="content-header">
                <h1>{{name}}</h1>
                <div class="content-top-options">
                    <button @click="addResource()">Add resource</button>
                </div>
            </div>
            <div class="breadcrumb">
                <div v-for="resourceCollection in breadcrumb" :key="resourceCollection.id" @click="viewResource(resourceCollection)">{{resourceCollection.name}}</div>
            </div>
            <div class="resources">
                <div v-for="resource in collection" :key="resource.id" class="resource">
                    <div :class="resource.resourceType" @click="viewResource(resource)" @contextmenu.prevent="removeResource(resource)">
                        {{ resource.name }}
                    </div>
                </div>
            </div>
        </div>
    </div>
  `,
  setup() {
    const loading = ref(true);
    const error = ref(false);
    const name = ref('');
    const collection = ref([]);
    const breadcrumb = ref([]);
    const errorMessage = ref('');

    const getResourceIdLoaded = inject('getResourceIdLoaded');
    const setResourceIdLoaded = inject('setResourceIdLoaded');

    const loadData = (id) => {
      // Check if breadcrumb has the ID; if so, remove it and all after
      const index = breadcrumb.value.findIndex(item => item.id === id);
      if (index !== -1) {
        breadcrumb.value.splice(index);
      }

      // Fetch resource
      fetch('/resource/' + id)
        .then(response => response.json())
        .then(data => {
          // Update component's data with the received data
          name.value = data.name;
          collection.value = data.collection;
          loading.value = false;
          error.value = false;
          errorMessage.value = '';
          breadcrumb.value.push(data);
        })
        .catch(error => {
          console.error('Error fetching data:', error);
          errorMessage.value = 'Error fetching data.';
          loading.value = false;
          error.value = true;
        });
    };
    const addResource = () => {
        const url = '/resources/add?resourceId=' + getResourceIdLoaded() + '&name=test123456&type=ResourceCollection';
        fetch(url, {
          method: 'POST', // Specify the request method
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded' // Specify the content type
          },
        }).then(data => {
            loadData(getResourceIdLoaded());
        });
    };
    const removeResource = (resource) => {
        const url = '/resources/remove?resourceId=' + resource.id;
        fetch(url, {
          method: 'DELETE', // Specify the request method
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded' // Specify the content type
          },
        }).then(data => {
            loadData(getResourceIdLoaded());
        });
    }
    const viewResource = (resource) => {
      setResourceIdLoaded(resource.id);
      switch (resource.resourceType) {
        case 'CollectionResource':
          loadData(resource.id);
          break;
        case 'FlowResource':
          console.log("Open flow view!", getResourceIdLoaded(), "resource id", resource.id);
          window.location.href = '/workspaces/FlowResource.html?resourceId=' + getResourceIdLoaded();
          // Open the flow page
          break;
        default:
          alert("Unknown resource type " + resource.resourceType);
      }
    };

    onMounted(() => {
      // Load data when the component is mounted
      const resourceId = new URLSearchParams(window.location.search).get('resourceId') == null || new URLSearchParams(window.location.search).get('resourceId') == "null" ? 0 : new URLSearchParams(window.location.search).get('resourceId');
      console.log("loading resource..", resourceId);
      loadData(resourceId);
    });

    // Return reactive properties and methods
    return {
      loading,
      error,
      name,
      collection,
      breadcrumb,
      errorMessage,
      viewResource,
      addResource,
      removeResource
    };
  }
});

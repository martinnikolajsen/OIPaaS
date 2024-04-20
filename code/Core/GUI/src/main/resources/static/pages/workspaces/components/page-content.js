Vue.component('page-content', {
  template: `
    <div class='page-content'>
        <div v-if="error" class="error">
            {{errorMessage}}
        </div>
        <div v-if="loading" class="loader">
          Loading...
        </div>
        <div v-else>
            <h1>{{name}}</h1>
            <div class="breadcrumb">
                <div v-for="item in breadcrumb" :key="item.id" @click="handleItemClick(item)">{{item.name}}</div>
            </div>
            <div class="resouces">
                <div v-for="item in collection" :key="item.id">
                    <div :class="item.resourceType" @click="handleItemClick(item)">
                        {{ item.name }}
                    </div>
                </div>
            </div>
        </div>
    </div>
  `,
  data() {
    return {
      loading: true,
      error: false,
      name: '',
      collection: [],
      breadcrumb: []
    };
  },
     mounted() {
       // Make an HTTP GET request to fetch data from the REST service
        this.loadData(0);
     },
     methods: {
        loadData(id) {
            //We need to check if our breadcrumb has the ID, if it does we remove it and all after
            let index = this.breadcrumb.findIndex(item => item.id === id);
            if (index !== -1) {
                this.breadcrumb.splice(index);
            }
            //Fetch resource
           fetch('/resource/' + id)
             .then(response => response.json())
             .then(data => {
               // Update the component's data with the received data
               this.name = data.name;
               this.collection = data.collection;
               this.loading = false;
               this.error = false;
               this.errorMessage = '';
               this.breadcrumb.push(data);
             })
             .catch(error => {
               console.error('Error fetching data:', error);
               this.errorMessage(error);
             });
        },
        handleItemClick(item) {
          // Check if the clicked item has the desired resourceType
          switch(item.resourceType){
            case 'CollectionResource':
                this.loadData(item.id);
                break;
            case 'FlowResource':
                console.log("Open flow view!");
                break;
            default:
                alert("Unknown resource type " + item.resourceType);
          }
        },
        errorMessage(message){
           this.loading = false;
           this.error = true;
           this.errorMessage = message;
        }
     }
});
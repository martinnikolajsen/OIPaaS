Vue.component('page-content', {
  template: `
    <div class='page-content'>{{content}}</div>
  `,
  data() {
    return {
      content: 'This is the welcome page!'
    };
  }
});
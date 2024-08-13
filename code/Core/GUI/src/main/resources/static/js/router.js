// router.js
const { createRouter, createWebHistory } = VueRouter;
import HomePage from '../pages/home/components/page-content.js'; // Adjust path as needed
import AboutPage from '../pages/workspaces/components/page-content.js'; // Adjust path as needed

const routes = [
  { path: '/', component: HomePage },
  { path: '/workspaces.html', component: AboutPage },
  // Add more routes as needed
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './main/App';
import reportWebVitals from './reportWebVitals';

import {
    createBrowserRouter,
    RouterProvider,
} from 'react-router-dom';
import Dashboard from "./main/components/Dashboard";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />
    },
    {
        path: "/dashboard",
        element: <Dashboard />
    }
]);

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
      <RouterProvider router={router} />
  </React.StrictMode>
);

reportWebVitals();

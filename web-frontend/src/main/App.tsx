import React, {lazy} from 'react';
import Authentication from "./pages/Authentication";

import { Routes, Route } from "react-router-dom";
import ErrorPage from "./pages/ErrorPage";

const Dashboard = lazy(() => import('./pages/Dashboard'));

function App() {
  return (
      <Routes>
          <Route path='/' element={
              <React.Suspense fallback="loading...">
                  <Dashboard />
              </React.Suspense>}
          />
          <Route path='auth' element={<Authentication />} />
          <Route path='*' element={<ErrorPage />} />
      </Routes>
  );
}

export default App;

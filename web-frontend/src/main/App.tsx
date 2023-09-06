import React from 'react';
import Authentication from "./pages/Authentication";

import { Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import ErrorPage from "./pages/ErrorPage";

function App() {
  return (
      <Routes>
          <Route path='/' element={<Dashboard />} />
          <Route path='auth' element={<Authentication />} />
          <Route path='*' element={<ErrorPage />} />
      </Routes>
  );
}

export default App;

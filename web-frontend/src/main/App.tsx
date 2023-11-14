import React, {lazy} from 'react';
import Authentication from "./pages/Authentication";

import { Routes, Route } from "react-router-dom";
import ErrorPage from "./pages/ErrorPage";
import StatsPage from "./pages/page/StatsPage";
import {EventPage} from "./pages/page/EventPage";
import {PrivateServerPage} from "./pages/page/PrivateServerPage";
import {LeagueTeamsPage} from "./pages/page/LeagueTeamsPage";

const Dashboard = lazy(() => import('./pages/Dashboard'));

function App() {
  return (
      <Routes>
          <Route path='/' element={
              <React.Suspense fallback="loading...">
                  <Dashboard />
              </React.Suspense>}>
              <Route path='stats' element={<StatsPage />} />
              <Route path='league-teams' element={<LeagueTeamsPage />} />
              <Route path='events' element={<EventPage />} />
              <Route path='private-servers' element={<PrivateServerPage />} />
          </Route>
          <Route path='auth' element={<Authentication />} />
          <Route path='*' element={<ErrorPage />} />
      </Routes>
  );
}

export default App;

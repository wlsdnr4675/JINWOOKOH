import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import ScrollIntoView from "./webapp/common/helpers/ScrollIntoView";
import ScrollToTop from "./webapp/common/helpers/ScrollToTop";


import { HomeVideoBg } from "webapp/common/index";

function App() {
  return (
    <Router basename="/">
      <ScrollIntoView>
        <ScrollToTop>
          <Switch>
            <Route
              exact
              path={`/`}
              component={HomeVideoBg}
            />
          </Switch>
        </ScrollToTop>
      </ScrollIntoView>
    </Router>
  );
}

export default App;

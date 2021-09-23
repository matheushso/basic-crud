import { Switch, Route, BrowserRouter as Router } from 'react-router-dom';
import Home from './components/Home';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div>
      <Router>
        <Switch>
          <Route exact path="/">
            <Home></Home>
          </Route>
          {/* <Route exact path="/developers/edit/:idParaEditar">
            <Editar></Editar>
          </Route> */}
        </Switch>
      </Router>
    </div>
  );
}

export default App;
import './home.css';

import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './home.routes';
import HomeController from './home.controller';
import HomeService from './home.service';

export default angular.module('app.home', [uirouter])
  .config(routing)
  .controller('HomeController', HomeController)
  .service('HomeService', HomeService)
  .name;
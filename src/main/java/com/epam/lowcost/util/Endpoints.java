package com.epam.lowcost.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Endpoints {
//  CRUD endpoints

    public static final String ALL = "/all";
    public static final String ADD = "/add";
    public static final String SEARCH = "/search";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";


//  Flight Controller endpoints

    public static final String FLIGHTS = "/flights";
    public static final String NEW_TICKET = "/new-ticket";
    public static final String RETURN = "/return";
    public static final String FLIGHT = "/flight";
    public static final String PAGE = "/page";

//  Login Controller endpoints

    public static final String REGISTRATION = "/registration";
    public static final String LOGOUT = "/logout";
    public static final String ADMIN = "/admin";

//  Plane Controller endpoints

    public static final String PLANE = "/plane";

//  Tickets Controller endpoints

    public static final String TICKETS = "/tickets";
    public static final String SELF = "/self";
    public static final String CANCEL = "/cancel";
    public static final String PAY = "/pay";


//  User Controller endpoints

    public static final String USER = "/user";
    public static final String USERS = "/users";
    public static final String CHANGE_PASSWORD = "/change-password";

    // View endpoints
    public static final String BUY = "buy";
    public static final String FLIGHT_SPAGE = "flightsPage";
    public static final String FLIGHT_SETTINGS = "flightSettings";
    public static final String LOGIN = "/login";
    public static final String PLANES_PAGE = "planesPage";
    public static final String REGISTRATION_PAGE = "registrationPage";
    public static final String SEARCH_PAGE = "searchPage";
    public static final String SETTINGS_PAGE = "settingsPage";
    public static final String TICKETS_PAGE = "ticketsPage";
    public static final String USER_PAGE = "userPage";
    public static final String ADD_PLANE_PAGE = "addPlane";
    public static final String USERS_PAGE = "usersPage";
    public static final String ACCOUNT = "account";
    public static final String AIRPORTS_PAGE = "airportsPage";
    public static final String PLANE_SETTINGS = "planesSettings";
    public static final String FIRST_PAGE = "/1";
    public static final String PLANES_SETTINGS = "planeSettings";

    //  Airport Controller endpoints
    public static final String AIRPORT = "/airport";
    public static final String USER_SETTINGS = "userSettings";
    public static final String BLOCK_USER = "/block-user";
    public static final String UNBLOCK_USER = "/unblock-user";
    public static final String UPDATE_USER = "/update-user";
    public static final String LOGIN_PAGE = "loginPage";
    public static final String AIRPORTSPAGE="airportsPage";
    public static final String FLIGHTSETTINGS="flightSettings";
    public static final String FLIGHTSPAGE="flightsPage";
    public static final String ADDFLIGHT="addFlight";
    public static final String SEARCHPAGE="searchPage";

    public static final String PDF = "/pdf" ;
}


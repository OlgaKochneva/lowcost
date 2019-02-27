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

//  Login Controller endpoints

    public static final String ENTRY = "/entry";
    public static final String ADMIN_PANEL = "/admin-panel";
    public static final String REGISTRATION = "/registration";
    public static final String LOG_OUT = "/log-out";

//  Plane Controller endpoints

    public static final String PLANE = "/plane";

//  Tickets Controller endpoints

    public static final String TICKETS = "/tickets";
    public static final String SELF = "/self";

//  User Controller endpoints

    public static final String USER = "/user";
    public static final String ENROLL = "/enroll";
    public static final String SETTINGS = "/settings";
    public static final String CHANGE_PASSWORD = "/change-password";

// Airport Controller endpoints
    public static final String AIRPORT="/airport";
    public static final String LOGIN = "/login";

//Views
    public static final String FLIGHTSPAGE="flightsPage";
    public static final String FLIGHTSETTINGS="flightSettings";
    public static final String ADDFLIGHT = "addFlight";
    public static final String ADMIN = "admin";
    public static final String BUY = "buy";
    public static final String PLANESPAGE = "planesPage";
    public static final String REGISTRATIONPAGE = "registrationPage";
    public static final String SEARCHPAGE = "searchPage";
    public static final String SETTINGSPAGE = "settingsPage";
    public static final String TICKETSPAGE = "ticketsPage";
    public static final String USERPAGE = "userPage";
    public static final String USERSPAGE = "usersPage";
    public static final String AIRPORTSPAGE = "airportsPage";
    public static final String PLANESSETTINGS="planesSettings";
    public static final String ADDPLANE="addPlane";

}


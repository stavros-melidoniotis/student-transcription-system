var express = require('express');
var request = require('xhr-request');
var path = require('path');
var bodyParser = require('body-parser');

var app = express();
var router = express.Router();

app.use(bodyParser.json({limit: '50mb'})); // support json encoded bodies
app.use(bodyParser.urlencoded({limit: '50mb', extended: true})); // support encoded bodies

app.use('/assets', express.static('assets'));

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

//variable is set true when login is successful
var loggedIn = false;
//get student's amka when login is successful
var loggedInAmka;

//paths to display html pages
router.get('/', function (req, res) {
    res.sendFile(path.join(__dirname + '/login-page.html'));
});

router.get('/logout', function (req, res) {
    loggedIn = false;
    res.sendFile(path.join(__dirname + '/login-page.html'));
});

router.get('/home_page', function (req, res) {
    if (loggedIn) {
        res.sendFile(path.join(__dirname + '/home-page.html'));
    } else {
        res.sendFile(path.join(__dirname + '/login-page.html'));
    }
});

router.get('/register', function (req, res) {
    res.sendFile(path.join(__dirname + '/student-register.html'));
});

router.get('/application/fill', function (req, res) {
    if (loggedIn) {
        res.sendFile(path.join(__dirname + '/application-fill.html'));
    } else {
        res.sendFile(path.join(__dirname + '/login-page.html'));
    }
});

router.get('/application/check_progress', function (req, res) {
    if (loggedIn) {
        res.sendFile(path.join(__dirname + '/application-progress.html'));
    } else {
        res.sendFile(path.join(__dirname + '/login-page.html'));
    }
});

router.get('/about', function (req, res) {
    res.sendFile(path.join(__dirname + '/about.html'));
});

router.get('/contact', function (req, res) {
    res.sendFile(path.join(__dirname + '/contact.html'));
});

//handle student registratiom
app.post('/register', function (req, res) {
    console.log("Register Student Request");

    var form_fullname = req.body.fullname;
    var form_amka = req.body.amka;
    var form_currDep = req.body.currDep;
    var form_semester = req.body.semester;
    var form_id = req.body.id;
    var form_email = req.body.email;
    var form_password = req.body.password;

    request('http://snf-857635.vm.okeanos.grnet.gr:8080/IntranetSystem/api/student/register', {
        method: 'POST',
        json: true,
        body: {
            fullname: form_fullname,
            amka: form_amka,
            currDep: form_currDep,
            semester: form_semester,
            id: form_id,
            email: form_email,
            password: form_password
        },
        headers: {
            'content-type': 'application/json;charset=UTF-8;'
        },
    }, function (err, data) {
        if (err) throw err
        res.send(data);
    });
});

//handle application creation
app.post('/application/fill', function (req, res) {
    console.log("Fill Application Request");

    var form_amka = req.body.amka;
    var form_hasBrotherSameCity = req.body.hasBrotherSameCity;
    var form_noOfUnderageBrothers = req.body.noOfUnderageBrothers;
    var form_familyIncome = req.body.familyIncome;
    var form_cityOfOrigin = req.body.cityOfOrigin;
    var form_choice1 = req.body.choice1;
    var form_choice2 = req.body.choice2;
    var form_input1 = req.body.input1;
    var form_input2 = req.body.input2;
    var form_input3 = req.body.input3;
    var form_input4 = req.body.input4;

    request('http://snf-857635.vm.okeanos.grnet.gr:8080/IntranetSystem/api/student/application/fill', {
        method: 'POST',
        json: true,
        body: {
            amka: form_amka,
            hasBrotherSameCity: form_hasBrotherSameCity,
            noOfUnderageBrothers: form_noOfUnderageBrothers,
            familyIncome: form_familyIncome,
            cityOfOrigin: form_cityOfOrigin,
            choice1: form_choice1,
            choice2: form_choice2,
            input1 : form_input1,
            input2 : form_input2,
            input3 : form_input3,
            input4 : form_input4
        },
        headers: {
            'content-type': 'application/json;charset=UTF-8;'
        },
    }, function (err, data) {
        if (err) throw err

        if (data.message == 'APPLICATION_EXISTS') {
            console.log('Student has already applied');
        } else {
            console.log('Application saved');
        }
        res.send(data);
    });
});

//handle login request
app.post('/login', function (req, res) {
    console.log('Login Request\n');

    var form_username = req.body.username;
    var form_password = req.body.password;

    console.log(form_username);
    console.log(form_password);

    request('http://snf-857635.vm.okeanos.grnet.gr:8080/IntranetSystem/api/student/login', {
        method: 'POST',
        json: true,
        body: {
            username: form_username,
            password: form_password
        },
        headers: {
            'content-type': 'application/json;charset=UTF-8;'
        },
    }, function (err, data) {
        if (err) throw err

        if (data.message == "correct") {
            console.log(form_username + ": Login Approved\n");
            loggedIn = true;
            loggedInAmka = data.loggedInAmka;
        } else {
            console.log(form_username + ": Login Denied\n");
        }
        res.send(data);
    });
});

//handle progress check
app.post('/application/check_progress', function (req, res) {
    request('http://snf-857635.vm.okeanos.grnet.gr:8080/IntranetSystem/api/student/application/check_progress', {
        method: 'POST',
        json: true,
        body: {
            amka: loggedInAmka
        },
        headers: {
            'content-type': 'application/json;charset=UTF-8;'
        },
    }, function (err, data) {
        if (err) throw err

        res.send(data);
    });
});


app.use('/', router);
console.log('server listening on snf-858012.vm.okeanos.grnet.gr:3393');
app.listen(3393);

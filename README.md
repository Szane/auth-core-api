[TOC]

# README
A simple authentication and authorization HTTP server.

APIs provided: 

- Create User
- Delete User
- Create Role
- Delete Role
- Add Role to User
- Authenticate
- Invalidate
- Check Role
- All Roles

## Instruction
The service is designed according to DDD model. 
The structure is divided into three layers: API, domain and Dao. 
The base package provides simulated bean management function, interface mapping function and simple HTTP server implementation.
The security performance of the service needs to be strengthened.Some of my ideas:
   1. Verification code function can be added to prevent brute force cracking; 
   2. Limit the number of password errors and reject the login request of the account within 10 minutes after reaching the limit; 
   3. HTTPS service can be provided for secure transmission; 
   4. Prohibit users from using weak passwords,etc.

## Run
```
mvn clean package -U
```
```aidl
 java -jar .\target\auth-core-api-1.0-SNAPSHOT-jar-with-dependencies.jar
```


## Test
By sending Http request.

```aidl
curl -XPOST http://localhost:8099/api/auth/createUser -d '{
    "name": "user1"
}'
```


## API
### Create User
request 
```aidl
POST /api/auth/createUser
{
    "name": "user1",
    "password": "123456"
}
```

response 
```aidl
{
    "code": 0,
    "message": "success",
    "data": true
}
```
```aidl
{
    "code": -1,
    "message": "request failed",
    "data": null
}
```

### Delete User
request 
```aidl
POST /api/auth/deleteUser 
{
    "userName": "user1"
}
```

response 
```aidl
{
    "code": 0,
    "message": "success",
    "data": true
}
```
```aidl
{
    "code": -1,
    "message": "request failed",
    "data": null
}
```

### Create Role
```aidl
POST /api/auth/createRole 
{
    "name": "manager"
}
```
```aidl
{
    "code": 0,
    "message": "success",
    "data": true
}
```
```aidl
{
    "code": -1,
    "message": "request failed",
    "data": null
}
```

### Delete Role
request 
```aidl
POST /api/auth/deleteRole 
{
    "roleName": "role1"
}
```

response 
```aidl
{
    "code": 0,
    "message": "success",
    "data": true
}
```
```aidl
{
    "code": -1,
    "message": "request failed",
    "data": null
}
```

### Add Role to User
request
```aidl
POST /api/auth/addRoleToUser 
{
    "userName": "user1",
    "roleName": "manager"
}
```

response
```aidl
{
    "code": 0,
    "message": "success",
    "data": true
}
```
```aidl
{
    "code": -1,
    "message": "request failed",
    "data": null
}
```


### Authenticate
request 
```aidl
POST /api/auth/authenticate
{
    "name": "user1",
    "password": "123456"
}
```
response 
```aidl
{
    "code": 0,
    "message": "success",
    "data": "PNCaGtmVrcq1vhpSH4kdTKOPpiXBupw76cuSLCwQK7c="
}
```
```aidl
{
    "code": -1,
    "message": "request failed",
    "data": null
}
```

### invalidate
request
```aidl
POST /api/auth/invalidate
{
    "token": "ATokenFromAuthenticate"
}
```
response 
```aidl
{
    "code": 0,
    "message": "success",
    "data": true
}
```
```aidl
{
    "code": -1,
    "message": "request failed",
    "data": null
}
```

### Check Role
```aidl
POST /api/auth/checkRole
{
    "token": "ATokenFromAuthenticate",
    "roleName": "manager"
}
```
response sample
```aidl
{
    "code": 0,
    "message": "success",
    "date": true
}
```
```aidl
{
    "code": -1,
    "message": "request failed",
    "data": null
}
```

### All Roles
```aidl
POST /api/auth/allRoles
{
    "token": "ATokenFromAuthenticate"
}
```
response
```aidl
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            "name": "manager"
        }
        ...
    ]
}
```







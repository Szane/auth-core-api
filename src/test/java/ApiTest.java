import com.hsbc.api.AuthApi;
import com.hsbc.base.Result;
import com.hsbc.domain.Role;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class ApiTest {
    AuthApi authApi = new AuthApi();

    @Test
    public void testCreateUser() {
        Result<Boolean> result1 = authApi.createUser("{\"name\":\"user1\",\"password\":\"123456\"}");
        Assert.assertEquals(0, result1.getCode());
        Result<Boolean> result2 = authApi.createUser("{\"name\":\"user2\",\"password\":\"foobar\"}");
        Assert.assertEquals(0, result2.getCode());
        Result<Boolean> result3 = authApi.createUser("{\"name\":\"user1\"}");
        Assert.assertEquals(-1, result3.getCode());
        Result<Boolean> result4 = authApi.createUser("{\"password\":\"1\"}");
        Assert.assertEquals(-1, result4.getCode());
    }

    @Test
    public void testDeleteUser() {
        authApi.createUser("{\"name\":\"user3\",\"password\":\"123456\"}");
        Result<Boolean> result1 = authApi.deleteUser("{\"name\":\"user3\"}");
        Assert.assertEquals(0, result1.getCode());
        Result<Boolean> result2 = authApi.deleteUser("{\"name\":\"usernotexsits\"}");
        Assert.assertEquals(-1, result2.getCode());
    }

    @Test
    public void testCreateRole() {
        Result<Boolean> result1 = authApi.createRole("{\"name\":\"admin\"}");
        Assert.assertEquals(0, result1.getCode());
        Result<Boolean> result2 = authApi.createRole("{\"name\":\"guest\"}");
        Assert.assertEquals(0, result2.getCode());
        Result<Boolean> result3 = authApi.createRole("{\"name\":\"\"}");
        Assert.assertEquals(-1, result3.getCode());
    }

    @Test
    public void testDeleteRole() {
        authApi.createRole("{\"name\":\"guest1\"}");
        Result<Boolean> result1 = authApi.deleteRole("{\"name\":\"guest1\"}");
        Assert.assertEquals(0, result1.getCode());
        Result<Boolean> result2 = authApi.deleteRole("{\"name\":\"president\"}");
        Assert.assertEquals(-1, result2.getCode());
    }

    @Test
    public void testAddRoleToUser() {
        authApi.createRole("{\"name\":\"manager\"}");
        authApi.createUser("{\"name\":\"user4\",\"password\":\"123456\"}");
        Result<Boolean> result1 = authApi.addRoleToUser("{\"userName\":\"user4\",\"roleName\":\"manager\"}");
        Assert.assertEquals(0, result1.getCode());
        Result<Boolean> result2 = authApi.addRoleToUser("{\"userName\":\"user5\",\"roleName\":\"manager\"}");
        Assert.assertEquals(-1, result2.getCode());
        Result<Boolean> result3 = authApi.addRoleToUser("{\"userName\":\"user4\",\"roleName\":\"president\"}");
        Assert.assertEquals(-1, result3.getCode());
    }

    @Test
    public void testAuthenticate() {
        authApi.createRole("{\"name\":\"manager\"}");
        authApi.createUser("{\"name\":\"user6\",\"password\":\"123456\"}");
        Result<String> result1 = authApi.authenticate("{\"name\":\"user6\",\"password\":\"123456\"}");
        Assert.assertEquals(0, result1.getCode());
        Result<String> result2 = authApi.authenticate("{\"name\":\"user6\",\"password\":\"000000\"}");
        Assert.assertEquals(-1, result2.getCode());
    }

    @Test
    public void testInvalidate() {
        authApi.createRole("{\"name\":\"manager\"}");
        authApi.createUser("{\"name\":\"user7\",\"password\":\"123456\"}");
        authApi.authenticate("{\"name\":\"user7\",\"password\":\"123456\"}");
        Result<String> t = authApi.authenticate("{\"name\":\"user7\",\"password\":\"123456\"}");
        Result<Boolean> result = authApi.invalidate("{\"token\":\"" + t.getData() + "\"}");
        Assert.assertEquals(0, result.getCode());
    }

    @Test
    public void checkRole() {
        authApi.createRole("{\"name\":\"manager\"}");
        authApi.createUser("{\"name\":\"user8\",\"password\":\"123456\"}");
        authApi.authenticate("{\"name\":\"user8\",\"password\":\"123456\"}");
        authApi.addRoleToUser("{\"userName\":\"user8\",\"roleName\":\"manager\"}");
        Result<String> t = authApi.authenticate("{\"name\":\"user8\",\"password\":\"123456\"}");
        Result<Boolean> result1 = authApi.checkRole("{\"token\":\"" + t.getData() + "\",\"roleName\":\"manager\"}");
        Assert.assertEquals(0, result1.getCode());
        Result<Boolean> result2 = authApi.checkRole("{\"token\":\"" + t.getData() + "\",\"roleName\":\"guest\"}");
        Assert.assertEquals(-1, result2.getCode());
    }

    @Test
    public void allRoles() {
        authApi.createRole("{\"name\":\"manager\"}");
        authApi.createUser("{\"name\":\"user9\",\"password\":\"123456\"}");
        authApi.authenticate("{\"name\":\"user9\",\"password\":\"123456\"}");
        authApi.addRoleToUser("{\"userName\":\"user9\",\"roleName\":\"manager\"}");
        Result<String> t = authApi.authenticate("{\"name\":\"user9\",\"password\":\"123456\"}");
        Result<Set<Role>> result1 = authApi.allRoles("{\"token\":\"" + t.getData() + "\"}");
        Assert.assertEquals(0, result1.getCode());
        Result<Set<Role>> result2 = authApi.allRoles("{\"token\":\"none=\"}");
        Assert.assertEquals(-1, result2.getCode());
    }
}

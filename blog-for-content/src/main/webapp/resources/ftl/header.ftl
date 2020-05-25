<#-- @ftlvariable name="userBlogInfo" type="ru.naumow.dto.BlogInfo" -->
<#-- @ftlvariable name="user" type="ru.naumow.dto.UserDto" -->
<div class="shadow-sm d-flex d-lg-flex d-xl-flex justify-content-lg-center align-items-lg-center justify-content-xl-center head-panel">
    <div class="row d-md-flex justify-content-md-center align-items-md-center header-section">
        <div class="col d-flex d-sm-flex d-md-flex d-xl-flex align-items-center align-items-sm-center align-items-md-center justify-content-xl-start align-items-xl-center">
            <i class="fas fa-paperclip" style="font-size: 16px;color: #ffaa6d;cursor: pointer;"></i>
            <p class="d-xl-flex" style="font-weight: bold;margin: 0;width: 101px;cursor: pointer;">PaperClip</p>
        </div>
        <div class="col d-sm-flex d-md-flex d-lg-flex justify-content-sm-end justify-content-md-end justify-content-lg-end"
             style="display: flex;justify-content: right;align-content: flex-end;max-width: 549px;">
            <div class="d-sm-flex d-md-flex align-items-sm-center align-items-md-center align-items-lg-center align-items-xl-center"
                 style="height: 100%;width: 154px;margin-right: 25px;"><input type="text" placeholder="Поиск..."
                                                                              style="height: 23px;font-size: 12px;"><i
                        class="fas fa-search" style="font-size: 17px;margin-left: 5px;cursor: pointer;"></i></div>
        </div>
        <div class="col d-flex d-sm-flex d-md-flex d-xl-flex justify-content-end justify-content-sm-end justify-content-md-end justify-content-lg-end justify-content-xl-end align-items-xl-center"
             style="max-width: 276px;">
            <!-- Start: dropdown-profile -->
            <div class="d-md-flex align-items-md-center dropdown">
                <div class="d-sm-flex d-xl-flex align-items-sm-center align-items-xl-center dropdown-toggle"
                     type="button" data-toggle="dropdown" aria-expanded="true">
                    <div class="rounded-circle"
                         style="background-image: url('${user.avatarUrl}');background-color: rgba(150,106,39,0.6);background-size: cover;background-position: bottom;background-repeat: no-repeat;height: 30px;width: 30px;"></div>
                    <p style="margin-bottom: 0;margin-left: 3px;margin-right: 3px;">
                        <strong>${user.name} ${user.surname}</strong><br></p>
                </div>
                <!-- Start: dropdown-profile-menu -->
                <div class="dropdown-menu" role="menu"
                     style="cursor: pointer">
                    <div class="dropdown-item" role="presentation"
                         onclick="window.location.href = '/${userBlogInfo.alias}'">
                        <p>Мой блог</p>
                    </div>
                    <div class="dropdown-item" role="presentation"
                         onclick="window.location.href = '/profile'">
                        <p>Мой профиль</p>
                    </div>

                    <div class="dropdown-item" role="presentation"
                         onclick="window.location.href = '/recommendations'">
                        <p>Мои рекоммендации</p>
                    </div>
                    <div class="dropdown-item" role="presentation"
                         onclick="$('#logout-form').submit();">
                        <form id="logout-form" action="/logout" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <p>Выйти</p>
                    </div>
                </div>
                <!-- End: dropdown-profile-menu -->
            </div>
            <!-- End: dropdown-profile -->
        </div>
    </div>
</div>
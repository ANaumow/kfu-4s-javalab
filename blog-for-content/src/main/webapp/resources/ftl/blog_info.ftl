<#-- @ftlvariable name="userDto" type="ru.naumow.dto.UserDto" -->
<#-- @ftlvariable name="currentBlog" type="ru.naumow.dto.BlogDto" -->
<div style="background-color: rgba(255,255,255,0);">
    <div>
        <div>
            <div class="custom-post-section"
                 style="width: 100%;margin: 0;background-color: #ffffff;padding: 12px;">
                <div class="row d-xl-flex m-auto justify-content-xl-start">
                    <div class="col d-xl-flex justify-content-xl-end align-items-xl-start"
                         style="max-width: 113px;">
                        <div class="rounded-circle"
                             style="background-image: url('${currentBlog.info.avatarUrl}');background-color: rgba(150,106,39,0.6);background-size: cover;background-position: center;background-repeat: no-repeat;height: 75px;width: 75px;"></div>
                    </div>
                    <div class="col d-xl-flex justify-content-xl-start align-items-xl-center"
                         style="padding: 0;">
                        <div class="row d-flex d-lg-flex flex-column"
                             style="margin: 0;min-height: 50%;">
                            <div class="col" style="padding: 0;height: 22px;">
                                <p class="text-left"
                                   style="font-size: 20px;filter: blur(0px);font-style: normal;font-weight: bold;margin: 0;cursor: pointer;">${currentBlog.info.title}</p>
                            </div>
                            <div class="col" style="padding: 0;height: 21px;">
                                <p style="margin: 0;padding: 0;font-size: 15px;">${currentBlog.info.subTitle}</p>
                            </div>
                            <div class="col" style="padding: 0;">
                                <p style="margin: 0;height: 17px;">${currentBlog.info.subCount} подписчиков<br></p>
                                <p style="margin: 0;">${currentBlog.postCount} записей<br></p>
                            </div>
                        </div>
                    </div>
                    <div class="col d-flex d-sm-flex d-md-flex d-lg-flex d-xl-flex justify-content-end align-items-start justify-content-sm-end align-items-sm-start justify-content-md-end align-items-md-start justify-content-lg-end align-items-lg-start justify-content-xl-end align-items-xl-start">
                        <!-- Start: dropdown-profile -->
                        <div class="d-md-flex align-items-md-center dropdown" style="margin-top: 7px;">
                            <div class="d-sm-flex d-xl-flex align-items-sm-center align-items-xl-center dropdown-toggle"
                                 type="button" data-toggle="dropdown" aria-expanded="true">
                                <p class="lead"
                                   style="margin-bottom: 0;margin-left: 3px;margin-right: 3px;font-size: 12px;line-height: 22px;">
                                    <strong>Редактировать</strong></p>
                            </div>
                            <!-- Start: dropdown-profile-menu -->
                            <div class="dropdown-menu" role="menu" style="padding: 0;min-width: auto;">
                                <div class="dropdown-item custom-nav-item" role="presentation"
                                     style="padding: 2px 14px;"
                                     onclick="window.location.href='/blog-edit'">
                                    <p class="lead" style="margin: 0;font-size: 15px; " >Дизайн блога</p>

                                </div>
                                <div class="dropdown-item custom-nav-item" role="presentation"
                                     style="padding: 2px 14px;"
                                     onclick="window.location.href='/editor?type=l'">
                                    <p class="lead" style="margin: 0;font-size: 15px;"
                                    >Панель слева</p>
                                </div>
                                <div class="dropdown-item custom-nav-item" role="presentation"
                                     style="padding: 2px 14px;"
                                     onclick="window.location.href='/editor?type=r'">
                                    <p class="lead" style="padding: 0;margin: 0;font-size: 15px;">Панель
                                        справа</p>
                                </div>
                            </div>
                            <!-- End: dropdown-profile-menu -->
                        </div>
                        <!-- End: dropdown-profile -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
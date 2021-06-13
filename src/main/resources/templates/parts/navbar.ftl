<#include "security.ftl">
<#import "login.ftl" as login>

<nav class="navbar navbar-expand-lg navbar-dark lor_color">
    <a class="navbar-brand" href="/">Automatic LOR Cleaner</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if isUser>
            <li class="nav-item">
                <a class="nav-link" href="/user-choice">Мои обеды</a>
            </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/dinners">Обеды</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользователей</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/dinners-set">Настройка обедов по датам</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/view-choices">Таблица обедов</a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3">${name}</div>
        <#if isUser>
            <@login.logout />
        <#else><a class="btn btn-primary" href="/login" role="button">Вход</a>
        </#if>
    </div>
</nav>
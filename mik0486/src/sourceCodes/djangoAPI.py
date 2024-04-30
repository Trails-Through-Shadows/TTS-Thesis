from django.urls import path

from src.api.views import *

urlpatterns = [
    path('table/<str:table>/', ApiView.as_view(), name='apiQueryTable'),
    path('table/<str:table>/<int:id>/', ApiView.as_view(), name='...'),
    path('validate/<str:table>/', validateTable, name='apiValidTable'),
    path('campaign/<int:id>/map', createMap, name='apiCreateMap'),
    path('filters', createFilter, name='apiCreateFilter'),
    path('modal', createModal, name='apiCreateModal'),
    path('modal/<str:table>', createSearchModal, name='apiCreateSe...'),
    path('card/<int:actionId>', createCardData, name='apiCardData')
]